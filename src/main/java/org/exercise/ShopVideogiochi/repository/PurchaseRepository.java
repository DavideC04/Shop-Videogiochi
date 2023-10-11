package org.exercise.ShopVideogiochi.repository;

import org.exercise.ShopVideogiochi.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query("SELECT p FROM Purchase p WHERE MONTH(p.dateTime) = MONTH(CURRENT_DATE()) AND  YEAR (p.dateTime) = YEAR (CURRENT_DATE())")
    List<Purchase> findPurchasesCurrMonthAndYear();

}
