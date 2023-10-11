package org.exercise.ShopVideogiochi.repository;

import org.exercise.ShopVideogiochi.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query(value = "SELECT p.videogame.id, COUNT(p.id) FROM Purchase p WHERE MONTH(p.dateTime) = MONTH(CURRENT_DATE()) AND  YEAR (p.dateTime) = YEAR (CURRENT_DATE()) GROUP BY p.videogame.id", nativeQuery = false)
    List<Object[]> findPurchasesCurrMonthAndYear();

}
