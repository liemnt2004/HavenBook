package com.appilcation.hutech.bookhaven.controller;


import com.appilcation.hutech.bookhaven.entity.Order;
import com.appilcation.hutech.bookhaven.entity.Product;
import com.appilcation.hutech.bookhaven.entity.User;
import com.appilcation.hutech.bookhaven.service.OrderService;
import com.appilcation.hutech.bookhaven.service.ProductService;
import com.appilcation.hutech.bookhaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;







    @PostMapping("/update-order")
    public String updateOrderStatus(@RequestParam("orderId") Long orderId,
                                    @RequestParam("status") String status) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
            orderService.saveOrder(order);
        }
        return "redirect:/admin?updated";
    }

    @PostMapping("/update-role")
    public String updateUserRole(@RequestParam("username") String username,
                                 @RequestParam("role") String role) {
        User user = userService.findUserById(username);
        if (user != null) {
            user.setRole(role);
            userService.save(user);
        }
        return "redirect:/admin?roleUpdated";
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("price") int price,
                             @RequestParam("image") String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setAuthor(author);
        product.setPrice(price);
        product.setImage(image);
        productService.saveProduct(product);
        return "redirect:/admin?productAdded";
    }

    @GetMapping("/edit-product")
    public String editProductForm(@RequestParam("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("editProduct", product);
            model.addAttribute("orders", orderService.getAllOrders());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("products", productService.getAllProducts());
        }
        return "admin";
    }

    @PostMapping("/update-product")
    public String updateProduct(@RequestParam("id") Long id,
                                @RequestParam("title") String title,
                                @RequestParam("author") String author,
                                @RequestParam("price") int price,
                                @RequestParam("image") String image) {
        Product product = productService.getProductById(id);
        if (product != null) {
            product.setTitle(title);
            product.setAuthor(author);
            product.setPrice(price);
            product.setImage(image);
            productService.saveProduct(product);
        }
        return "redirect:/admin?productUpdated";
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam("productId") Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/admin?productDeleted";
    }
}