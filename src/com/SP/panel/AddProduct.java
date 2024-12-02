/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.SP.panel;

import com.SP.db.dbConn;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Statement;

/**
 *
 * @author Arvy
 */
public class AddProduct extends javax.swing.JPanel {

    /**
     * Creates new form AddProduct
     */
    public AddProduct() {
        initComponents();
        db.Connect();
        vp.retrieveData();
    }
    
    dbConn db = new dbConn();
    ViewPanel vp = new ViewPanel();
    
public void AddProd() {    
    try {
        // Retrieve values from the UI
        String brand = brand1.getText();
        String cat = category1.getText();
        String mdl = model.getText();
        String qty = quantity.getText();
        String price = price1.getText();

        // Check if the brand exists in the Brands table
        String checkBrandQuery = "SELECT b_id FROM Brands WHERE brand = ?";
        db.pst = db.con.prepareStatement(checkBrandQuery);
        db.pst.setString(1, brand);
        db.rs = db.pst.executeQuery();

        int brandId = -1;

        // If the brand exists, retrieve its b_id
        if (db.rs.next()) {
            brandId = db.rs.getInt("b_id");  // Brand exists, get the brand_id
        } else {
            // If brand doesn't exist, insert it into the Brands table
            String insertBrandQuery = "INSERT INTO Brands (brand) VALUES (?)";
            db.pst = db.con.prepareStatement(insertBrandQuery, Statement.RETURN_GENERATED_KEYS);
            db.pst.setString(1, brand);
            int rowsAffected = db.pst.executeUpdate();

            // Check if the brand was inserted successfully
            if (rowsAffected > 0) {
                // Get the generated brand_id
                db.rs = db.pst.getGeneratedKeys();
                if (db.rs.next()) {
                    brandId = db.rs.getInt(1);
                }
            }
        }

        // If brand_id is retrieved or inserted successfully, proceed to insert the product
        if (brandId != -1) {
            // Prepare the query to insert the product
            String insertProductQuery = "INSERT INTO Product (category, brand, model, quantity, price) VALUES (?, ?, ?, ?, ?)";
            db.pst = db.con.prepareStatement(insertProductQuery);

            // Set parameters for the insert query
            db.pst.setString(1, cat);  // Category
            db.pst.setString(2, brand); // Brand (directly use the brand name)
            db.pst.setString(3, mdl);   // Model
            db.pst.setInt(4, Integer.parseInt(qty)); // Quantity (ensure it's parsed to integer)
            db.pst.setString(5,price); // Price (ensure it's parsed to BigDecimal)

            // Execute the insert query
            int k = db.pst.executeUpdate();

            // Check if the product was added successfully
            if (k == 1) {
                vp.retrieveData();  // Refresh the data on the view panel
                JOptionPane.showMessageDialog(this, "Record Added!!");
            } else {
                JOptionPane.showMessageDialog(this, "Record Failed To Save!!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to get brand ID.");
        }

    } catch (SQLException ex) {
        Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        // Handle invalid number format for quantity and price
        JOptionPane.showMessageDialog(this, "Invalid number format. Please check the quantity and price values.");
    }
}


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        brand1 = new javax.swing.JTextField();
        category1 = new javax.swing.JTextField();
        model = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        add = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        price1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1920, 970));
        setMinimumSize(new java.awt.Dimension(1920, 970));
        setPreferredSize(new java.awt.Dimension(1920, 970));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        brand1.setBackground(new java.awt.Color(255, 255, 255));
        brand1.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        brand1.setForeground(new java.awt.Color(0, 0, 0));
        brand1.setToolTipText("");
        add(brand1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 680, 90));

        category1.setBackground(new java.awt.Color(255, 255, 255));
        category1.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        category1.setForeground(new java.awt.Color(0, 0, 0));
        category1.setToolTipText("");
        category1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                category1ActionPerformed(evt);
            }
        });
        add(category1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, 680, 90));

        model.setBackground(new java.awt.Color(255, 255, 255));
        model.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        model.setForeground(new java.awt.Color(0, 0, 0));
        model.setToolTipText("");
        add(model, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 430, 680, 90));

        quantity.setBackground(new java.awt.Color(255, 255, 255));
        quantity.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        quantity.setForeground(new java.awt.Color(0, 0, 0));
        quantity.setToolTipText("");
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });
        add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 710, 680, 80));

        add.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("ADD");
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1690, 90, 170, 90));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/addbutton.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 90, -1, -1));

        price1.setBackground(new java.awt.Color(255, 255, 255));
        price1.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        price1.setForeground(new java.awt.Color(0, 0, 0));
        price1.setToolTipText("");
        price1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                price1ActionPerformed(evt);
            }
        });
        add(price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 570, 680, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/Addpanelll.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void price1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_price1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_price1ActionPerformed

    private void category1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_category1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_category1ActionPerformed

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        // TODO add your handling code here:
        AddProd();
    }//GEN-LAST:event_addMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add;
    private javax.swing.JTextField brand1;
    private javax.swing.JTextField category1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField model;
    private javax.swing.JTextField price1;
    private javax.swing.JTextField quantity;
    // End of variables declaration//GEN-END:variables
}
