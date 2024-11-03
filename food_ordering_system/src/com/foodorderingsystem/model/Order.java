package com.foodorderingsystem.model;

public class Order {
    private int id;
    private int userId;
    private int menuItemId;
    private int quantity;
    private String status;

    // Constructor to initialize an Order with all fields
    public Order(int id, int userId, int menuItemId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.status = "Pending";
    }

    // Default constructor (in case it's needed elsewhere in your code)
    public Order() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + ", User ID: " + userId + ", Menu Item ID: " + menuItemId + 
               ", Quantity: " + quantity + ", Status: " + status; // Include status in toString
    }
}
