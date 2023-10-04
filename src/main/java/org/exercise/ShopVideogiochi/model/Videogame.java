package org.exercise.ShopVideogiochi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Videogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String photo;
    private String title;
    private String editor;
    private String description;
    private Integer price;



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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public Videogame(Integer id, String photo, String title, String editor, String description, Integer price) {
        this.id = id;
        this.photo = photo;
        this.title = title;
        this.editor = editor;
        this.description = description;
        this.price = price;
    }

    public Videogame(){

    }
}
