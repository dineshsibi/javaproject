package com.foodorderingsystem.database;

import com.foodorderingsystem.model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // Method to retrieve all orders
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM `Order`"; // Adjust according to your table structure

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Order order = new Order(); // Create an instance using the default constructor
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userId"));
                order.setMenuItemId(resultSet.getInt("menuItemId"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setStatus(resultSet.getString("status")); // Assuming there's a status field
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Method to update the status of an order
    public boolean updateOrderStatus(int orderId, String newStatus) {
        String query = "UPDATE `Order` SET status = ? WHERE id = ?"; // Ensure the table name and column names are correct

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, orderId);
            int rowsAffected = preparedStatement.executeUpdate();
            
            // Check if the update was successful
            return rowsAffected > 0; // True if update was successful, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve orders by user ID
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM `Order` WHERE userId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userId"));
                order.setMenuItemId(resultSet.getInt("menuItemId"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setStatus(resultSet.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
