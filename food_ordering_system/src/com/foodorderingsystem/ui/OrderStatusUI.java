package com.foodorderingsystem.ui;

import com.foodorderingsystem.database.OrderDAO;
import com.foodorderingsystem.model.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderStatusUI extends JFrame {
    private int userId;
    private JList<String> orderList;

    // Constructor to accept the user ID and initialize the UI
    public OrderStatusUI(int userId) {
        this.userId = userId;
        setTitle("Your Orders");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        orderList = new JList<>();
        loadOrders();

        JScrollPane scrollPane = new JScrollPane(orderList);
        scrollPane.setPreferredSize(new Dimension(380, 250));
        add(scrollPane);
    }

    // Load orders by user ID and display them in the JList
    private void loadOrders() {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getOrdersByUserId(userId);
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Order order : orders) {
            listModel.addElement(
                "Order ID: " + order.getId() +
                " - Item ID: " + order.getMenuItemId() +
                " - Quantity: " + order.getQuantity() +
                " - Status: " + order.getStatus()
            );
        }
        orderList.setModel(listModel);
    }

    // Main method to test OrderStatusUI independently
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Replace 1 with the actual user ID in a real application
            new OrderStatusUI(1).setVisible(true);
        });
    }
}
