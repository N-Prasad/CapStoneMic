package com.prodata.product_app.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.prodata.product_app.entity.Product;
import com.prodata.product_app.repository.ProductCRUDRepository;
import com.prodata.product_app.service.ProductCRUDService;

class ProductCRUDControllerTest {
	
	@Mock
	private ProductCRUDRepository prodRepo;
	
	@InjectMocks
	private ProductCRUDService prodService;
	
	private Product product;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		product = new Product(1L, "Test Product", " Product with id 1 for testing", 534.00);
	}

	@Test
	void testGetAllProducts() {
		when(prodRepo.findById(1L)).thenReturn(Optional.empty());
        Optional<Product> retrievedProduct = prodService.getProductById(1L);

        assertFalse(retrievedProduct.isPresent());
        verify(prodRepo, times(1)).findById(1L);
	}
	
	@Test
	void testGetEmptyAllProducts() {
		when(prodRepo.findById(1L)).thenReturn(Optional.empty());
        Optional<Product> retrievedProduct = prodService.getProductById(1L);
        assertFalse(retrievedProduct.isPresent());
        verify(prodRepo, times(1)).findById(1L);
	}

	@Test
	void testGetProductById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetProductByName() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateProduct() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateProduct() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteProduct() {
		fail("Not yet implemented");
	}

}
