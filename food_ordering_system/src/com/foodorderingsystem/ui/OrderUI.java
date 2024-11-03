package com.foodorderingsystem.ui;

import com.foodorderingsystem.model.MenuItem;
import com.foodorderingsystem.model.Order;
import com.foodorderingsystem.service.MenuService;
import com.foodorderingsystem.service.OrderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderUI extends JFrame {
    private JComboBox<String> menuComboBox;
    private JTextField quantityField;
    private JButton orderButton;
    private JButton viewStatusButton;
    private int userId;

    // Constructor to accept userId and menuItemId
    public OrderUI(int userId, int menuItemId) {
        this.userId = userId; // Store the user ID

        setTitle("Place Order");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuComboBox = new JComboBox<>();
        loadMenuItems();

        // Set the selected item based on menuItemId
        if (menuItemId != -1) {
            menuComboBox.setSelectedItem(menuItemId + " - " + getMenuItemNameById(menuItemId));
        }

        quantityField = new JTextField(5);
        orderButton = new JButton("Place Order");
        viewStatusButton = new JButton("View Order Status");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });

        // Open OrderStatusUI to view the user's order statuses
        viewStatusButton.addActionListener(e -> new OrderStatusUI(userId).setVisible(true));

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Select Menu Item:"));
        panel.add(menuComboBox);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel());
        panel.add(orderButton);
        panel.add(new JLabel()); // Empty space for alignment
        panel.add(viewStatusButton); // Add the view status button

        add(panel);
    }

    private void loadMenuItems() {
        MenuService menuService = new MenuService();
        List<MenuItem> menuItems = menuService.getMenuItems();
        for (MenuItem item : menuItems) {
            menuComboBox.addItem(item.getId() + " - " + item.getName() + " ($" + item.getPrice() + ")");
        }
    }

    private String getMenuItemNameById(int menuItemId) {
        MenuService menuService = new MenuService();
        MenuItem item = menuService.getMenuItemById(menuItemId);
        return item != null ? item.getName() : "Unknown Item";
    }

    private void placeOrder() {
        String selectedItem = (String) menuComboBox.getSelectedItem();
        int menuItemId = Integer.parseInt(selectedItem.split(" - ")[0]);
        int quantity;

        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
            return;
        }

        // Create a new Order object with the status set to "Pending"
        Order order = new Order();
        order.setUserId(userId);
        order.setMenuItemId(menuItemId);
        order.setQuantity(quantity);
        order.setStatus("Pending"); // Set the status to "Pending"

        OrderService orderService = new OrderService();
        if (orderService.placeOrder(order)) {
            JOptionPane.showMessageDialog(this, "Order placed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to place order.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Replace 1 with the actual userId in a real application
            new OrderUI(1, -1).setVisible(true); // Pass -1 if there's no specific menuItemId
        });
    }
}
