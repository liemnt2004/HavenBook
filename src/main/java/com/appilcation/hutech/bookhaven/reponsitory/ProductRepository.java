package com.appilcation.hutech.bookhaven.reponsitory;

import com.appilcation.hutech.bookhaven.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}