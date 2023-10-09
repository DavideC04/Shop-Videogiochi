package org.exercise.ShopVideogiochi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "supplies")
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;
    private Integer supplies;


    private LocalDate purchaseDate;
    private String title;
    private String nameSupplier;
    private BigDecimal price;

    @OneToMany(mappedBy = "supply")
    private List<Videogame> supply;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSupplies() {
        return supplies;
    }

    public void setSupplies(Integer supplies) {
        this.supplies = supplies;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }




    public Supply(Integer id, Integer quantity, Integer supplies, LocalDate purchaseDate, String title, String nameSupplier, BigDecimal price) {
        this.id = id;
        this.quantity = quantity;
        this.supplies = supplies;
        this.purchaseDate = purchaseDate;
        this.title = title;
        this.nameSupplier = nameSupplier;
        this.price = price;
    }

    public Supply(){

    }
}
