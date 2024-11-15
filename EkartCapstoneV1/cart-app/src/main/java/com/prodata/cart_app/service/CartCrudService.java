package com.prodata.cart_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prodata.cart_app.entity.Cart;
import com.prodata.cart_app.entity.LineItem;
import com.prodata.cart_app.exception.CartNotFoundException;
import com.prodata.cart_app.repository.CartCrudRepository;

@Service
public class CartCrudService {

	@Autowired
	private CartCrudRepository cartRepository;

	public Cart getCart(Long cartId) {
		return cartRepository.findById(cartId)
				.orElseThrow(() -> new CartNotFoundException("Cart not found with ID " + cartId));
	}

	public Cart createCart(Cart cart) {
		return cartRepository.save(cart);
	}

	public Cart addItemToCart(Long cartId, LineItem item) {
		Cart cart = getCart(cartId);
		cart.getLineItems().add(item);
		return cartRepository.save(cart);
	}

	public Cart updateCart(Long cartId, Cart updatedCart) {
		Cart existingCart = getCart(cartId);
		existingCart.setLineItems(updatedCart.getLineItems());
		return cartRepository.save(existingCart);
	}

	public void deleteCart(Long cartId) {
		Cart cart = getCart(cartId);
		cartRepository.delete(cart);
	}

	@Transactional
	public Cart placeOrderAndClearCart(Long cartId) {
		Cart cart = getCart(cartId);
		// Clear items after order is placed
		cart.getLineItems().clear();
		return cartRepository.save(cart);
	}

}
