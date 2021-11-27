package com.himel.eshoppers.web;

import com.himel.eshoppers.domain.Cart;
import com.himel.eshoppers.domain.User;
import com.himel.eshoppers.enums.Action;
import com.himel.eshoppers.repository.CartItemRepositoryImpl;
import com.himel.eshoppers.repository.CartRepositoryImpl;
import com.himel.eshoppers.repository.ProductRepositoryImpl;
import com.himel.eshoppers.service.CartService;
import com.himel.eshoppers.service.CartServiceImpl;
import com.himel.eshoppers.util.SecurityContext;
import com.himel.eshoppers.util.StringUtil;
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
        var action = req.getParameter("action");
        var cart = getCart(req);
        if(StringUtil.isNotEmpty(action)){
            processCart(productId, action, cart);
            resp.sendRedirect("/checkout");
            return;
        }
        LOGGER.info("Received request to add product with id: {} to cart", productId);
        cartService.addProductToCart(productId,cart);
        resp.sendRedirect("/home");
    }


    private Cart getCart(HttpServletRequest req) {
        final User currentUser = SecurityContext.getCurrentUser(req);
        return cartService.getCartByUser(currentUser);
    }

    private void processCart(String productId, String action, Cart cart){
        switch (Action.valueOf(action.toUpperCase())){
            case ADD:
                LOGGER.info("Received request to add product with id: {} to cart", productId);
                cartService.addProductToCart(productId,cart);
                break;
            case REMOVE:
                LOGGER.info("Received request to remove product with id: {} to cart", productId);
                cartService.removeProductFromCart(productId,cart);
                break;
            case REMOVEALL:
                LOGGER.info("Received request to remove all product with id: {} to cart", productId);
                cartService.removeAllProductByProductIdFromCart(productId,cart);
        }
    }

}
