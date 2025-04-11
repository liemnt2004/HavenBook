package com.appilcation.hutech.bookhaven.service;

import com.appilcation.hutech.bookhaven.entity.Order;

import com.appilcation.hutech.bookhaven.entity.User;
import com.appilcation.hutech.bookhaven.reponsitory.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    public Order createOrder(String username, String address, String phone, String cartData) {
        Order order = new Order();
      User user =  userService.findUserByUsername(username);
        order.setUser(user);
        order.setAddress(address);
        order.setPhone(phone);
        order.setCartData(cartData);
        order.setStatus("Pending"); // Trạng thái ban đầu
        return orderRepository.save(order);
    }



    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Đơn hàng không tồn tại!"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}