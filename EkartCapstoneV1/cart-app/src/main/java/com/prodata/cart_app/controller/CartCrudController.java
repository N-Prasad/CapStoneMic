package com.prodata.cart_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodata.cart_app.entity.Cart;
import com.prodata.cart_app.entity.LineItem;
import com.prodata.cart_app.service.CartCrudService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/cart")
public class CartCrudController {

	@Autowired
	private CartCrudService cartService;

	@GetMapping("/{cartId}")
	public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
		Cart cart = cartService.getCart(cartId);
		return ResponseEntity.ok(cart);
	}

	@PostMapping
	public ResponseEntity<Cart> createCart(@Valid @RequestBody Cart cart) {
		Cart createdCart = cartService.createCart(cart);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
	}

	@PutMapping("path/{cartId}")
	public ResponseEntity<Cart> updateCart(@PathVariable Long cartId, @Valid @RequestBody Cart updatedCart) {
		Cart cart = cartService.updateCart(cartId, updatedCart);
		return ResponseEntity.ok(cart);
	}

	@DeleteMapping("/{cartId}")// delete only if customer account deleted
	public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
		cartService.deleteCart(cartId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{cartId}/add-item")
	public ResponseEntity<Cart> addItemToCart(@PathVariable Long cartId, @Valid @RequestBody LineItem item) {
		Cart updatedCart = cartService.addItemToCart(cartId, item);
		return ResponseEntity.ok(updatedCart);
	}

	@PostMapping("/{cartId}/emptycart")
	public ResponseEntity<Cart> emptyCart(@PathVariable Long cartId) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.placeOrderAndClearCart(cartId));
	}

}
