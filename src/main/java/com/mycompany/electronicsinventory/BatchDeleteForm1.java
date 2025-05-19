/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicsinventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BatchDeleteForm1 extends JFrame {
    
    private JTable tblProducts;
    private JButton btnDelete;
    private JScrollPane scrollPane;
    private Connection con;

    public BatchDeleteForm1() {
        setTitle("Batch Delete Products");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tblProducts = new JTable();
        tblProducts.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane = new JScrollPane(tblProducts);

        btnDelete = new JButton("Delete Selected");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedProducts();
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(btnDelete, BorderLayout.SOUTH);

        connectToDB();
        loadProducts();
    }

    private void connectToDB() {
        try {
            con = DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "DB Connection Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadProducts() {
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[] { "Product ID", "Name", "Quantity", "Price" });

            PreparedStatement ps = con.prepareStatement("SELECT * FROM product");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                });
            }

            tblProducts.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedProducts() {
        int[] rows = tblProducts.getSelectedRows();

        if (rows.length == 0) {
            JOptionPane.showMessageDialog(this, "No products selected.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Delete selected products?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE product_id = ?");

            for (int row : rows) {
                int id = (int) tblProducts.getValueAt(row, 0);
                ps.setInt(1, id);
                ps.addBatch();
            }

            ps.executeBatch();
            con.commit();

            JOptionPane.showMessageDialog(this, "Selected products deleted.");
            loadProducts(); // Refresh after deletion

        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BatchDeleteForm1().setVisible(true);
        });
    }
}
