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

    @OneToMany(mappedBy = "videogame")
    private List<Purchase> purchases;
    @NotBlank(message = "Inserisci la tipologia di videogioco")
    private String genre;
    @NotBlank(message = "Inserisci la tipologia di console")
    private String console;


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

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public Videogame(Integer id, String photo, String title, String editor, String description, BigDecimal price) {
        this.id = id;
        this.photo = photo;
        this.title = title;
        this.editor = editor;
        this.description = description;
        this.price = price;
    }

    public Videogame() {

    }
}
