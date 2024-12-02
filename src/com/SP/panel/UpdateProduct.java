/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.SP.panel;

/**
 *
 * @author Arvy
 */
public class UpdateProduct extends javax.swing.JPanel {

    /**
     * Creates new form UpdateProduct
     */
    public UpdateProduct() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        brand1 = new javax.swing.JTextField();
        category = new javax.swing.JTextField();
        model1 = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        update = new javax.swing.JLabel();
        butup = new javax.swing.JLabel();
        panellin = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Brand", "Category", "Model", "Price"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1000, 920));

        brand1.setEditable(false);
        brand1.setBackground(new java.awt.Color(255, 255, 255));
        brand1.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        brand1.setForeground(new java.awt.Color(0, 0, 0));
        brand1.setText("Acer");
        brand1.setToolTipText("");
        add(brand1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 80, 520, 50));

        category.setEditable(false);
        category.setBackground(new java.awt.Color(255, 255, 255));
        category.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        category.setForeground(new java.awt.Color(0, 0, 0));
        category.setText("Gaming");
        category.setToolTipText("");
        category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryActionPerformed(evt);
            }
        });
        add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 200, 520, 50));

        model1.setEditable(false);
        model1.setBackground(new java.awt.Color(255, 255, 255));
        model1.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        model1.setForeground(new java.awt.Color(0, 0, 0));
        model1.setText("Nitro 58ye");
        model1.setToolTipText("");
        model1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                model1ActionPerformed(evt);
            }
        });
        add(model1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 310, 520, 50));

        price.setEditable(false);
        price.setBackground(new java.awt.Color(255, 255, 255));
        price.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        price.setForeground(new java.awt.Color(0, 0, 0));
        price.setText("48,000");
        price.setToolTipText("");
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 420, 520, 60));

        update.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("UPDATE");
        add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 600, -1, -1));

        butup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/updatebutton.png"))); // NOI18N
        add(butup, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 570, -1, -1));

        panellin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/updatepanell.png"))); // NOI18N
        add(panellin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryActionPerformed

    private void model1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_model1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_model1ActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField brand1;
    private javax.swing.JLabel butup;
    private javax.swing.JTextField category;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField model1;
    private javax.swing.JLabel panellin;
    private javax.swing.JTextField price;
    private javax.swing.JLabel update;
    // End of variables declaration//GEN-END:variables
}
