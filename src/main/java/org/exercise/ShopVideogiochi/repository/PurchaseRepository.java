package org.exercise.ShopVideogiochi.repository;

import org.exercise.ShopVideogiochi.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
