package com.appilcation.hutech.bookhaven.reponsitory;

import com.appilcation.hutech.bookhaven.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}