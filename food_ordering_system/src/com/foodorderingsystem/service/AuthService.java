package com.foodorderingsystem.service;

import com.foodorderingsystem.database.UserDAO;

public class AuthService {
    private UserDAO userDAO = new UserDAO();

    public boolean login(String username, String password) {
        return userDAO.authenticateUser(username, password);
    }
}
