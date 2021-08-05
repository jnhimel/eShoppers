package com.himel.eshoppers.web;

import com.himel.eshoppers.domain.Cart;
import com.himel.eshoppers.domain.User;
import com.himel.eshoppers.exceptions.ProductNotFoundException;
import com.himel.eshoppers.repository.CartItemRepositoryImpl;
import com.himel.eshoppers.repository.CartRepositoryImpl;
import com.himel.eshoppers.repository.ProductRepositoryImpl;
import com.himel.eshoppers.service.CartService;
import com.himel.eshoppers.service.CartServiceImpl;
import com.himel.eshoppers.util.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServlet.class);

    private CartService cartService  = new CartServiceImpl(new CartRepositoryImpl(), new ProductRepositoryImpl(), new CartItemRepositoryImpl());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productId = req.getParameter("productId");
        LOGGER.info("Received request to add product with id: {}", productId);
        var cart = getCart(req);
        try {
            cartService.addProductToCart(productId,cart);
        } catch (ProductNotFoundException e) {
            LOGGER.error("Error",e);
        }
        resp.sendRedirect("/home");
    }


    private Cart getCart(HttpServletRequest req) {
        final User currentUser = SecurityContext.getCurrentUser(req);
        return cartService.getCartByUser(currentUser);
    }

}
