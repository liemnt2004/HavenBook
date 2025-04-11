package com.appilcation.hutech.bookhaven.controller;

import com.appilcation.hutech.bookhaven.entity.Product;
import com.appilcation.hutech.bookhaven.entity.User;
import com.appilcation.hutech.bookhaven.service.OrderService;
import com.appilcation.hutech.bookhaven.service.ProductService;
import com.appilcation.hutech.bookhaven.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping("/login")
    public String showLoginPage(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            if (userService.isAdmin(user)) {
                return "redirect:/admin";
            }
            return "redirect:/";
        } else {
            model.addAttribute("error", "Đăng nhập thất bại. Vui lòng kiểm tra lại!");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           @RequestParam String Name,
                           Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp!");
            return "login";
        }
        try {
            userService.registerUser(username, email, password , Name);
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String address,
                           @RequestParam String phone,
                           @RequestParam String cartData,
                           HttpSession session,
                           Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        orderService.createOrder(user.getUsername(), address, phone, cartData);
        model.addAttribute("message", "Đặt hàng thành công! Chúng tôi sẽ giao hàng sớm nhất.");
        return "redirect:/?orderSuccess";
    }

    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        ArrayList<Product> productArrayList = (ArrayList<Product>) productService.getAllProducts();
        if (user == null || !userService.isAdmin(user)) {
            return "redirect:/login";
        }
        model.addAttribute("products", productArrayList);
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("users", userService.getAllUsers()); // Thêm danh sách người dùng
        return "admin";
    }



}