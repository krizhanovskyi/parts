package com.parts.model;

import javax.persistence.*;

@Entity
@Table(name = "part", schema = "test")
public class Part {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "necessary")
    private boolean necessary;

    public Part() {
    }

    public Part(String message, int quantity, boolean necessary) {
        this.name = message;
        this.quantity = quantity;
        this.necessary = necessary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNecessary() {
        return necessary;
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
