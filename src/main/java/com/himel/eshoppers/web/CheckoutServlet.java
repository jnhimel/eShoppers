package com.himel.eshoppers.web;

import com.himel.eshoppers.domain.Cart;
import com.himel.eshoppers.domain.CartItem;
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

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(CheckoutServlet.class);

    private CartService cartService
            = new CartServiceImpl(new CartRepositoryImpl(),
            new ProductRepositoryImpl(),
            new CartItemRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOGGER.info("Serving checkout page");

        User currentUser = SecurityContext.getCurrentUser(req);
        LOGGER.info("User: {}",currentUser.getFirstName());
        var cart = cartService.getCartByUser(currentUser);
        LOGGER.info("Total Items: {}", cart.getTotalItem());
        for (CartItem cartItem:
             cart.getCartItems()) {
            LOGGER.info("Item name: {}", cartItem.getProduct().getName());
            LOGGER.info("Item Description: {}", cartItem.getProduct().getDescription());
        }
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/WEB-INF/checkout.jsp")
                .forward(req,resp);
    }

}
