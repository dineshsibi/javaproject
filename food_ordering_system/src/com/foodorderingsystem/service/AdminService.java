package com.foodorderingsystem.service;

import com.foodorderingsystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService {

    public boolean validateAdmin(String username, String password) {
        String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password); // Ideally, the password should be hashed for security

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If a record exists, credentials are valid

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
