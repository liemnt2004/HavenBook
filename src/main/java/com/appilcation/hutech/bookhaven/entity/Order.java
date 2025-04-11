package com.appilcation.hutech.bookhaven.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Liên kết với User

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // Liên kết với Product

    private int quantity;
    private String status;  // Ví dụ: "pending", "shipped", "delivered"
    private String address;
    private String phone;
    private String cartData;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCartData() {
        return cartData;
    }
    public void setCartData(String cartData) {
        this.cartData = cartData;
    }
}