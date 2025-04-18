package com.appilcation.hutech.bookhaven.reponsitory;

import com.appilcation.hutech.bookhaven.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}