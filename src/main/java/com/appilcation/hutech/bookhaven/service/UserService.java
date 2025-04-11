package com.appilcation.hutech.bookhaven.service;

import com.appilcation.hutech.bookhaven.entity.User;
import com.appilcation.hutech.bookhaven.reponsitory.UserRepository; // Chú ý sửa chính tả "reponsitory" -> "repository"
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User registerUser(String username, String email, String password , String name) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("USER"); // Mặc định role là USER khi đăng ký
        user.setName(name);
        return userRepository.save(user);
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }





    // Thêm phương thức để kiểm tra role
    public boolean isAdmin(User user) {
        return user != null && "ADMIN".equals(user.getRole());
    }

    public User findUserByUsername(String username) {
    return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(String username) {
        return  userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }




}