package com.foodorderingsystem.service;

import com.foodorderingsystem.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private List<MenuItem> menuItems;

    public MenuService() {
        // Initialize menu items (this could also come from a database)
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1, "Pizza", 10.99));
        menuItems.add(new MenuItem(2, "Burger", 8.99));
        menuItems.add(new MenuItem(3, "Pasta", 12.99));
        menuItems.add(new MenuItem(4, "Salad", 6.49));
        menuItems.add(new MenuItem(5, "Tacos", 7.99));
        menuItems.add(new MenuItem(6, "Sandwich", 5.49));
        menuItems.add(new MenuItem(7, "Fries", 3.49));
        menuItems.add(new MenuItem(8, "Sushi", 15.99));
        menuItems.add(new MenuItem(9, "Ice Cream", 4.99));
        menuItems.add(new MenuItem(10, "Coffee", 2.99));
        menuItems.add(new MenuItem(11, "Smoothie", 5.99));
        menuItems.add(new MenuItem(12, "Steak", 19.99));
        menuItems.add(new MenuItem(13, "Chicken Wings", 9.99));
        menuItems.add(new MenuItem(14, "Fish and Chips", 11.99));
        menuItems.add(new MenuItem(15, "Hot Dog", 4.49));

        // Add more items as needed
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    // Method to get a menu item by its ID
    public MenuItem getMenuItemById(int id) {
        for (MenuItem item : menuItems) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // Return null if not found
    }
}
