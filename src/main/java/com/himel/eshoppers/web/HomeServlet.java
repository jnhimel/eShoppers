package com.himel.eshoppers.web;

import com.himel.eshoppers.domain.Cart;
import com.himel.eshoppers.domain.User;
import com.himel.eshoppers.dto.ProductDTO;
import com.himel.eshoppers.repository.CartItemRepositoryImpl;
import com.himel.eshoppers.repository.CartRepositoryImpl;
import com.himel.eshoppers.repository.ProductRepositoryImpl;
import com.himel.eshoppers.service.CartService;
import com.himel.eshoppers.service.CartServiceImpl;
import com.himel.eshoppers.service.ProductService;
import com.himel.eshoppers.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.himel.eshoppers.util.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);
    private ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private CartService cartService = new CartServiceImpl(new CartRepositoryImpl(), new ProductRepositoryImpl(), new CartItemRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving home page");
        List<ProductDTO> allProducts = productService.findAllProductSortedByName();
        LOGGER.info("Total product found {}",allProducts.size());

        if(SecurityContext.isAuthenticated(req)){
            User currentUser = SecurityContext.getCurrentUser(req);
            var cart =  cartService.getCartByUser(currentUser);
            req.setAttribute("cart", cart);
        }

        req.setAttribute("products",allProducts);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req,resp);
    }
}
