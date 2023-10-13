package org.exercise.ShopVideogiochi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "restock")
public class Restock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    @Min(1)
    @Positive
    @NotNull(message = "Non può essere vuoto")
    private Integer quantity;
    @NotBlank(message = "Non può essere vuoto")
    private String supplier;
    @Min(1)
    @NotNull(message = "Non può essere vuoto")
    private BigDecimal price;
    @NotNull
    @ManyToOne
    private Videogame videogame;

    public Restock() {

    }

    @PrePersist
    public void prePersist() {
        date = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Videogame getVideogame() {
        return videogame;
    }

    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restock restock = (Restock) o;
        return Objects.equals(id, restock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
