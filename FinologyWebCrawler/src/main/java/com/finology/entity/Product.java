package com.finology.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = -9166250427234563538L;
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private String price;
    @Column(name = "Description")
    private String description;
    @Column(name = "Extra_Information")
    private String extraInformation;

    public Product() {
    }

    public Product(Long id, String name, String price, String description, String extraInformation) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.extraInformation = extraInformation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                name.equals(product.name) &&
                price.equals(product.price) &&
                description.equals(product.description) &&
                extraInformation.equals(product.extraInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, extraInformation);
    }
}
