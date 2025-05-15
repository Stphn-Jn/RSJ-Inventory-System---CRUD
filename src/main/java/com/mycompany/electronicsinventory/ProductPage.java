package com.mycompany.electronicsinventory;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.io.FileFilter;

public class ProductPage extends javax.swing.JFrame {

    Connection con;
    PreparedStatement dbs;

    public ProductPage() {
        initComponents();        
        Connect(); // Call Connect() here to initialize the DB connection
        LoadProductNo();
        disableTableEditingAndClicking(); // jTable Editing and Clicking Disabled
        btnSearchName.setVisible(true);
        btnClearSearch.setVisible(false);
        Fetch();
        
    }
    ResultSet kz;
    
    
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // use cj for modern MySQL versions
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsjess", "root", "");
            JOptionPane.showMessageDialog(this, "Connected to 3306");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void LoadProductNo() {
        try {
            dbs = con.prepareStatement("SELECT id FROM product");
            kz = dbs.executeQuery();
            txtpID.removeAllItems();
            while(kz.next()) {
                txtpID.addItem(kz.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

private void Fetch() {
         try {
            int sh;
            dbs = con.prepareStatement("SELECT * FROM product");
            kz = dbs.executeQuery();
            ResultSetMetaData srh = kz.getMetaData();
            sh = srh .getColumnCount();
            
            DefaultTableModel jn = (DefaultTableModel)jTable1.getModel();
            jn.setRowCount(0);
            while(kz.next()) {
                Vector v2 = new Vector();
                for(int a=1; a<=sh; a++) {
                    
                    v2.add(kz.getString("id"));
                    v2.add(kz.getString("pname"));
                    v2.add(kz.getString("price"));
                    v2.add(kz.getString("qty")); 
                }
              jn.addRow(v2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
}


private void disableTableEditingAndClicking() {
    javax.swing.table.TableModel currentModel = jTable1.getModel();

    javax.swing.table.DefaultTableModel nonEditableModel = new javax.swing.table.DefaultTableModel(
        getTableData(currentModel),
        getColumnNames(currentModel)
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    jTable1.setModel(nonEditableModel);

    jTable1.setRowSelectionAllowed(false);
    jTable1.setColumnSelectionAllowed(false);
    jTable1.setCellSelectionEnabled(false);
}

private Object[][] getTableData(javax.swing.table.TableModel model) {
    int rowCount = model.getRowCount();
    int colCount = model.getColumnCount();
    Object[][] data = new Object[rowCount][colCount];
    for (int i = 0; i < rowCount; i++) {
        for (int j = 0; j < colCount; j++) {
            data[i][j] = model.getValueAt(i, j);
        }
    }
    return data;
}

private Object[] getColumnNames(javax.swing.table.TableModel model) {
    int colCount = model.getColumnCount();
    Object[] columnNames = new Object[colCount];
    for (int i = 0; i < colCount; i++) {
        columnNames[i] = model.getColumnName(i);
    }
    return columnNames;
}


private void searchProductByName(String keyword) {
    DefaultTableModel model = new DefaultTableModel(
        new String[]{"id", "pname", "price", "qty"}, 0);

    String query = "SELECT * FROM product WHERE pname LIKE ?";

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsjess", "root", "");
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, "%" + keyword + "%");

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("pname"),
                    rs.getDouble("price"),
                    rs.getInt("qty")
                };
                model.addRow(row);
            }
        }

        jTable1.setModel(model);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
}

private void loadAllProducts() {
    DefaultTableModel model = new DefaultTableModel(
        new String[]{"id", "pname", "price", "qty"}, 0);

    String query = "SELECT * FROM product";

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsjess", "root", "");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            Object[] row = {
                rs.getInt("id"),
                rs.getString("pname"),
                rs.getDouble("price"),
                rs.getInt("qty")
            };
            model.addRow(row);
        }

        jTable1.setModel(model);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtpID = new javax.swing.JComboBox<>();
        txtQty = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtPName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnSearchName = new javax.swing.JToggleButton();
        txtPP = new javax.swing.JTextField();
        btnClearSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Product Price:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Product Quantity:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Product ID:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Product Name:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        txtpID.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtpID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpIDActionPerformed(evt);
            }
        });
        getContentPane().add(txtpID, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 130, 30));
        getContentPane().add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 160, 30));
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 180, 30));
        getContentPane().add(txtPName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 180, 30));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnExport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExport.setText("Export to CSV");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnAdd)
                .addGap(27, 27, 27)
                .addComponent(btnUpdate)
                .addGap(26, 26, 26)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnExport)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 610, 40));

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch.setText("Search ID");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 100, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product ID", "Product Name", "Price", "Quantity"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 650, 450));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 690, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Search Name:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, 30));

        btnSearchName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearchName.setText("Search Name");
        btnSearchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchNameActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearchName, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, -1, 30));

        txtPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPPActionPerformed(evt);
            }
        });
        getContentPane().add(txtPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 130, 30));

        btnClearSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClearSearch.setText("Clear Search");
        btnClearSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnClearSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 120, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       
        if (txtPName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Product Name is Required!", "Input Error", JOptionPane.WARNING_MESSAGE);
        }else if (txtPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Product Price is Required!", "Input Error", JOptionPane.WARNING_MESSAGE);
        }else if (txtQty.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Product Quantity is Required!", "Input Error", JOptionPane.WARNING_MESSAGE);
        }else 
        
        
        
        try {
            // TODO add your handling code here:
            
            String pname = txtPName.getText();
            String price = txtPrice.getText();
            String qty = txtQty.getText();
            String pid = txtpID.getSelectedItem().toString();
            
            dbs = con.prepareStatement("UPDATE product SET pname=?,price=?,qty=? WHERE id=?");
            
            dbs.setString(1,pname);
            dbs.setString(2,price);
            dbs.setString(3,qty);
            dbs.setString(4,pid);
            
            int k = dbs.executeUpdate();
            if (k==1) {
                JOptionPane.showMessageDialog(this, "Record has been successfully updated");
                txtPName.setText("");
                txtPrice.setText("");
                txtQty.setText("");
                txtPName.requestFocus();
                Fetch();
                LoadProductNo();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            // TODO add your handling code here:
            
            String pid = txtpID.getSelectedItem().toString();
            
            dbs = con.prepareStatement("SELECT * FROM  product WHERE id=?");
            dbs.setString(1,pid);
            kz=dbs.executeQuery();
            
            if(kz.next()== true){
                txtPName.setText(kz.getString(2));
                txtPrice.setText(kz.getString(3));
                txtQty.setText(kz.getString(4));
            } else {
                JOptionPane.showMessageDialog(this, "NO Record Found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        String filename = "C:\\Users\\Administrator\\Documents\\ISE\\ExportedFile.csv";
        
        try {
            FileWriter fw = new FileWriter(filename);
            dbs = con.prepareStatement("SELECT * FROM product");
            kz = dbs.executeQuery();
            
            while (kz.next()) {
                fw.append(kz.getString(1));
                fw.append(',');
                fw.append(kz.getString(2));
                fw.append(',');
                fw.append(kz.getString(3));
                fw.append(',');
                fw.append(kz.getString(4));
                fw.append('\n');
            }
            JOptionPane.showMessageDialog(this, "File Has Been Exported as CSV");
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        if (txtPName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Product Name is Required!", "Input Error", JOptionPane.WARNING_MESSAGE);
        }else if (txtPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Product Price is Required!", "Input Error", JOptionPane.WARNING_MESSAGE);
        }else if (txtQty.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Product Quantity is Required!", "Input Error", JOptionPane.WARNING_MESSAGE);
        }else 
        
        
        try {
            String pname = txtPName.getText().trim();
            String price = txtPrice.getText().trim();
            String qty = txtQty.getText().trim();

            if (pname.isEmpty() || price.isEmpty() || qty.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            dbs = con.prepareStatement("INSERT INTO product (pname, price, qty) VALUES (?, ?, ?)");
            dbs.setString(1, pname);
            dbs.setString(2, price);
            dbs.setString(3, qty);

            int k = dbs.executeUpdate();

            if (k == 1) {
                JOptionPane.showMessageDialog(this, "Record Added Successfully");
                txtPName.setText("");
                txtPrice.setText("");
                txtQty.setText("");
                txtPName.requestFocus();
                Fetch();
                LoadProductNo();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Add Record");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "SQL Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtpIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpIDActionPerformed

    private void btnSearchNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchNameActionPerformed
        // TODO add your handling code here:
        String keyword = txtPP.getText().trim();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a product name to search.");
            return;
        }

        searchProductByName(keyword);

        // Transition buttons
        btnSearchName.setVisible(false);
        btnClearSearch.setVisible(true);
        
    }//GEN-LAST:event_btnSearchNameActionPerformed

    private void txtPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPPActionPerformed
        // TODO add your handling code here:
        // Trigger the search
        btnSearchName.doClick(); // Simulates clicking the search button
    }//GEN-LAST:event_txtPPActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        
        
        try {
            String pid = txtpID.getSelectedItem().toString();
            dbs = con.prepareStatement("DELETE FROM product WHERE id=?");
            dbs.setString(1,pid);
            
            int k = dbs.executeUpdate();
            
            if (k==1){
                JOptionPane.showMessageDialog(this, "Record has been successfully Deleted!!");
                txtPName.setText("");
                txtPrice.setText("");
                txtQty.setText("");
                txtPName.requestFocus();
                Fetch();
                LoadProductNo();
            } else {
                JOptionPane.showMessageDialog(this, "Record failed to Delete!!");
            }
        } catch(SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSearchActionPerformed
        // TODO add your handling code here:
        txtPP.setText("");
        loadAllProducts(); // Reload all data

        // Transition buttons
        btnClearSearch.setVisible(false);
        btnSearchName.setVisible(true);
    }//GEN-LAST:event_btnClearSearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnSearch;
    private javax.swing.JToggleButton btnSearchName;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtPName;
    private javax.swing.JTextField txtPP;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JComboBox<String> txtpID;
    // End of variables declaration//GEN-END:variables
}
