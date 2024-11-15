package com.prodata.inventory_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodata.inventory_app.entity.Inventory;

@Repository
public interface InventoryCrudRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findByProductId(Long productId);

}
