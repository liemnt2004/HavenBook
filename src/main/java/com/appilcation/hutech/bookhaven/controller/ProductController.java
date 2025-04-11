package com.appilcation.hutech.bookhaven.controller;
import com.appilcation.hutech.bookhaven.entity.Product;
import com.appilcation.hutech.bookhaven.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        System.out.println("Products: " + products.getFirst().getId()); // In ra console để kiểm tra
        model.addAttribute("products", products);
        return "index"; // Hoặc "products" tùy cấu hình của bạn
    }
}
