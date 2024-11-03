package com.foodorderingsystem.service;

import com.foodorderingsystem.database.DatabaseConnection;
import com.foodorderingsystem.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderService {

    public boolean placeOrder(Order order) {
        // Update the SQL query to include the status field
        String query = "INSERT INTO `Order` (userId, menuItemId, quantity, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters for the prepared statement
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getMenuItemId());
            statement.setInt(3, order.getQuantity());
            statement.setString(4, order.getStatus()); // Add this line to set the status

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if the order was placed successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }
}
