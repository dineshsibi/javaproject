package com.foodorderingsystem.ui;

import com.foodorderingsystem.database.OrderDAO;
import com.foodorderingsystem.model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminUI extends JFrame {
    private OrderDAO orderDAO;
    private JComboBox<String> statusComboBox;
    private JTable orderTable;

    public AdminUI() {
        orderDAO = new OrderDAO();
        setTitle("Admin - Order Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Order status options
        String[] statuses = {"Pending", "Preparing", "Dispatched", "Delivered"};

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table to show orders
        String[] columns = {"Order ID", "User ID", "Menu Item ID", "Quantity", "Status"};
        List<Order> orders = orderDAO.getAllOrders();
        String[][] data = new String[orders.size()][5];
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            data[i][0] = String.valueOf(order.getId());
            data[i][1] = String.valueOf(order.getUserId());
            data[i][2] = String.valueOf(order.getMenuItemId());
            data[i][3] = String.valueOf(order.getQuantity());
            data[i][4] = order.getStatus();
        }
        orderTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(orderTable);

        // Panel for changing status
        JPanel updatePanel = new JPanel();
        statusComboBox = new JComboBox<>(statuses);
        JButton updateButton = new JButton("Update Status");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = orderTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an order to update.");
                    return;
                }

                int orderId = Integer.parseInt(orderTable.getValueAt(selectedRow, 0).toString());
                String newStatus = statusComboBox.getSelectedItem().toString();

                if (orderDAO.updateOrderStatus(orderId, newStatus)) {
                    orderTable.setValueAt(newStatus, selectedRow, 4); // Update table display
                    JOptionPane.showMessageDialog(null, "Order status updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update order status.");
                }
            }
        });

        updatePanel.add(new JLabel("Change Status:"));
        updatePanel.add(statusComboBox);
        updatePanel.add(updateButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(updatePanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminUI().setVisible(true));
    }
}
