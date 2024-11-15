package com.prodata.cart_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodata.cart_app.entity.Cart;

@Repository
public interface CartCrudRepository extends JpaRepository<Cart, Long> {

	Optional<Cart> findByCartId(Long cartId);

}
