package com.foodorderingsystem.ui;

import com.foodorderingsystem.model.MenuItem;
import com.foodorderingsystem.service.MenuService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuUI extends JFrame {
    private JList<String> menuList;

    public MenuUI() {
        setTitle("Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuList = new JList<>();
        loadMenuItems();

        JScrollPane scrollPane = new JScrollPane(menuList);
        scrollPane.setPreferredSize(new Dimension(380, 250));
        add(scrollPane);

        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(e -> {
            int selectedIndex = menuList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedItem = menuList.getModel().getElementAt(selectedIndex);
                String[] parts = selectedItem.split(" - ");
                int menuItemId = Integer.parseInt(parts[0]);
                new OrderUI(1, menuItemId).setVisible(true); // Pass the actual userId and selected menuItemId
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a menu item.");
            }
        });

        JPanel panel = new JPanel();
        panel.add(orderButton);
        add(panel, BorderLayout.SOUTH);
    }

    private void loadMenuItems() {
        MenuService menuService = new MenuService();
        List<MenuItem> menuItems = menuService.getMenuItems();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (MenuItem item : menuItems) {
            listModel.addElement(item.getId() + " - " + item.getName() + " ($" + item.getPrice() + ")");
        }
        menuList.setModel(listModel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuUI().setVisible(true);
        });
    }
}
