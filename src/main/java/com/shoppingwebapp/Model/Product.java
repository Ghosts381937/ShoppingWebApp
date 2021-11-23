package com.shoppingwebapp.Model;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private String quantity;

    private String price;

    @Lob
    private byte[] image;

    public Product() {}
    public Product(String name, String description, String quatity, String price, byte[] image) {
        this.name = name;
        this.description = description;
        this.quantity = quatity;
        this.price = price;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(String quatity) {
        this.quantity = quatity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}