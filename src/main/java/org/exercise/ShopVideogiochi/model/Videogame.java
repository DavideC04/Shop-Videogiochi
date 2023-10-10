package org.exercise.ShopVideogiochi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "games")
public class Videogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @URL(message = "Deve essere inserito un URL!")
    private String photo;
    @NotBlank(message = "Il titolo non può essere vuoto!")
    private String title;
    @NotBlank(message = "L'editore non può essere vuoto!")
    private String editor;
    private String description;
    @NotNull(message = "Il prezzo non può essere inferiore o uguale a 0!")
    @Min(1)
    private BigDecimal price;

    @NotBlank(message = "Inserisci il genere")
    private String genre;

    @OneToMany(mappedBy = "videogame", cascade = {CascadeType.REMOVE})
    private List<Purchase> purchases;

    @ManyToMany
    private List<Console> consoleList;
    @NotBlank(message = "Inserisci la tipologia di console")
    private String console;
    @ManyToOne
    private Restock supply;
    @OneToMany(mappedBy = "videogame")
    private List<Restock> restocks;

    public Videogame() {

    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Restock r : restocks) {
            totalQuantity += r.getQuantity();
        }
        return totalQuantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Console> getConsoleList() {
        return consoleList;
    }

    public void setConsoleList(List<Console> consoleList) {
        this.consoleList = consoleList;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public List<Restock> getRestocks() {
        return restocks;
    }

    public void setRestocks(List<Restock> restocks) {
        this.restocks = restocks;
    }
}
