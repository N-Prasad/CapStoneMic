package com.prodata.product_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodata.product_app.entity.Product;

@Repository
public interface ProductCRUDRepository extends JpaRepository<Product, Long>{

	public Optional<Product> findByProductName(String productName);

}
