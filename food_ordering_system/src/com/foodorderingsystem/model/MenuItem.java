package com.foodorderingsystem.model;

public class MenuItem {
    private int id;          // Unique identifier for the menu item
    private String name;     // Name of the menu item
    private double price;    // Price of the menu item

    // No-argument constructor
    public MenuItem() {
    }

    // Constructor to initialize a MenuItem
    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Setter for the ID
    public void setId(int id) {
        this.id = id;
    }

    // Setter for the name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for the price
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter for the ID
    public int getId() {
        return id;
    }

    // Getter for the name
    public String getName() {
        return name;
    }

    // Getter for the price
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + " - " + name + " ($" + price + ")";
    }
}
