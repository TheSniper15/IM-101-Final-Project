/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.SP.frame;

import com.SP.db.dbConn;
import com.SP.panel.UpdateProduct;
import com.SP.panel.ViewPanel;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Arvy
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin
     */
    public Admin() {
        initComponents();
        db.Connect();
        retrieveData();
    }
    
    dbConn db = new dbConn();
    public String g_id = "1";
    
   public void addStaff() {
    try {
        String fname = name2.getText();  
        String roleText = role.getText(); 
        String unameText = uname.getText(); 
        String pswdText = pswd.getText();  

        String checkRoleQuery = "SELECT role_id FROM roleTbl WHERE role_set = ?";
        db.pst = db.con.prepareStatement(checkRoleQuery);
        db.pst.setString(1, roleText);  
        db.rs = db.pst.executeQuery();
        
        int roleId = -1;
        
        if (db.rs.next()) {
            roleId = db.rs.getInt("role_id");  
        } else {
            String insertRoleQuery = "INSERT INTO roleTbl (role_set) VALUES (?)";
            db.pst = db.con.prepareStatement(insertRoleQuery, Statement.RETURN_GENERATED_KEYS);
            db.pst.setString(1, roleText); 
            int rowsAffected = db.pst.executeUpdate();
            
            if (rowsAffected > 0) {
  
                db.rs = db.pst.getGeneratedKeys();
                if (db.rs.next()) {
                    roleId = db.rs.getInt(1);
                }
            }
        }

        if (roleId != -1) {
            String insertStaffQuery = "INSERT INTO Staff (fullname, role_ID, password, username) VALUES (?, ?, ?,? )";
            db.pst = db.con.prepareStatement(insertStaffQuery);
            db.pst.setString(1, fname); 
            db.pst.setInt(2, roleId);   
            db.pst.setString(3, pswdText);
            db.pst.setString(4, unameText);

            int rowsAffected = db.pst.executeUpdate();


            if (rowsAffected == 1) {
                JOptionPane.showMessageDialog(this, "Staff record added successfully.");
                name2.setText("");
                role.setText("");
                uname.setText("");  
                pswd.setText("");   
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add staff.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to get role ID.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error adding staff: " + ex.getMessage());
    }
}

    
            public void retrieveData() {
        try {
            int q;
            db.pst = db.con.prepareStatement("SELECT s.id, s.fullname, r.role_set AS role, s.password, s.username\n" +
                                             "FROM Staff s\n" + "JOIN roleTbl r ON s.role_id = r.role_id;");
            db.rs = db.pst.executeQuery();
            java.sql.ResultSetMetaData rss = db.rs.getMetaData();
            q = rss.getColumnCount();  

            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
            df.setRowCount(0);

            while (db.rs.next()) {
                Vector v2 = new Vector();
                for (int a = 1; a  <= q ; a++) {
                    v2.add(db.rs.getString("fullname"));
                    v2.add(db.rs.getString("role"));
                    v2.add(db.rs.getString("password"));
                    v2.add(db.rs.getString("username"));
                    v2.add(db.rs.getInt("id"));
                }
                df.addRow(v2);
            }
            jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(4).setMinWidth(0);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(0);

        } catch (SQLException ex) {
            Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void search() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }

        DefaultTableModel tbModel = (DefaultTableModel) jTable1.getModel();

        String fullname = tbModel.getValueAt(selectedRow, 0).toString();  
        String roletype = tbModel.getValueAt(selectedRow, 1).toString();    
        String pwd = tbModel.getValueAt(selectedRow, 2).toString();  
        String usrnm = tbModel.getValueAt(selectedRow, 3).toString(); 
        g_id = tbModel.getValueAt(selectedRow, 4).toString();

        name2.setText(fullname);
        role.setText(roletype);
        uname.setText(usrnm);
        pswd.setText(pwd);

    }
    
    public void ud()
    {
	    try 
		{
                    String fullname = name2.getText();
                    String roletype =    role.getText();
                    String pwd =   uname.getText();
                    String usrnm =    pswd.getText();
			
                    db.pst = db.con.prepareStatement("UPDATE Staff SET fullname=?,role_ID=?,password=?,username=? WHERE id=?");
			
                    db.pst.setString(1,fullname);
                    db.pst.setString(2,roletype);
                    db.pst.setString(3,pwd);
                    db.pst.setString(4,usrnm);
                    db.pst.setString(5,g_id);

                    int k = db.pst.executeUpdate();
                    if(k == 1)
                    {
                        JOptionPane.showMessageDialog(this,"Record Has Been Updated!!");
                        name2.setText("");
                        role.setText("");
                        uname.setText("");
                        pswd.setText("");
                        
			retrieveData();
                    }
		} 
		catch (SQLException ex) 
		{
                    Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
        private void deleteSelectedProduct() {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
 
            JOptionPane.showMessageDialog(this, "Please select a product to delete.");
            return;
        }


        int productId = (int) jTable1.getValueAt(selectedRow, 4); 


        try {
            String sql = "DELETE FROM Staff WHERE id = ?";
            db.pst = db.con.prepareStatement(sql);
            db.pst.setInt(1, productId);

            int rowsAffected = db.pst.executeUpdate();

            if (rowsAffected > 0) {
                ((DefaultTableModel) jTable1.getModel()).removeRow(selectedRow);

                JOptionPane.showMessageDialog(this, "Product deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete the product.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting the product: " + ex.getMessage());
        } finally {
            try {
                if (db.pst != null) db.pst.close();
            } catch (SQLException ex) {
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        logout = new javax.swing.JLabel();
        name2 = new javax.swing.JTextField();
        role = new javax.swing.JTextField();
        pswd = new javax.swing.JTextField();
        uname = new javax.swing.JTextField();
        admin1 = new javax.swing.JLabel();
        delete1 = new javax.swing.JLabel();
        update1 = new javax.swing.JLabel();
        add = new javax.swing.JLabel();
        panel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Full Name", "Role", "Password", "Username", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 950, 830));

        logout.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setText("LOGOUT");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1697, 30, 170, 60));

        name2.setBackground(new java.awt.Color(255, 255, 255));
        name2.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        name2.setForeground(new java.awt.Color(0, 0, 0));
        name2.setToolTipText("");
        name2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name2ActionPerformed(evt);
            }
        });
        getContentPane().add(name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 280, 430, 40));

        role.setBackground(new java.awt.Color(255, 255, 255));
        role.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        role.setForeground(new java.awt.Color(0, 0, 0));
        role.setToolTipText("");
        role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleActionPerformed(evt);
            }
        });
        getContentPane().add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 360, 430, 50));

        pswd.setBackground(new java.awt.Color(255, 255, 255));
        pswd.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        pswd.setForeground(new java.awt.Color(0, 0, 0));
        pswd.setToolTipText("");
        pswd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswdActionPerformed(evt);
            }
        });
        getContentPane().add(pswd, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 540, 430, 50));

        uname.setBackground(new java.awt.Color(255, 255, 255));
        uname.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        uname.setForeground(new java.awt.Color(0, 0, 0));
        uname.setToolTipText("");
        uname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unameActionPerformed(evt);
            }
        });
        getContentPane().add(uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 460, 430, 40));

        admin1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        admin1.setForeground(new java.awt.Color(255, 255, 255));
        admin1.setText("ADMIN");
        getContentPane().add(admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        delete1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        delete1.setForeground(new java.awt.Color(255, 255, 255));
        delete1.setText("DELETE");
        delete1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delete1MouseClicked(evt);
            }
        });
        getContentPane().add(delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 690, -1, -1));

        update1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        update1.setForeground(new java.awt.Color(255, 255, 255));
        update1.setText("UPDATE");
        update1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                update1MouseClicked(evt);
            }
        });
        getContentPane().add(update1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 700, -1, -1));

        add.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("ADD");
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1730, 690, -1, -1));

        panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/adminpanellll.png"))); // NOI18N
        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 200, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/Delbutton.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 670, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/addbutton.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1660, 670, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/updatebutton.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 670, -1, -1));

        name.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/Admin_1.png"))); // NOI18N
        getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
      login loginFrame = new login();
      loginFrame.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

    private void pswdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswdActionPerformed

    private void unameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unameActionPerformed

    private void delete1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delete1MouseClicked
        // TODO add your handling code here:
        deleteSelectedProduct();
    }//GEN-LAST:event_delete1MouseClicked

    private void update1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update1MouseClicked
        // TODO add your handling code here:
        ud();
    }//GEN-LAST:event_update1MouseClicked

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        // TODO add your handling code here:
        addStaff();
    }//GEN-LAST:event_addMouseClicked

    private void name2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name2ActionPerformed

    private void roleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add;
    private javax.swing.JLabel admin1;
    private javax.swing.JLabel delete1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel name;
    private javax.swing.JTextField name2;
    private javax.swing.JLabel panel;
    private javax.swing.JTextField pswd;
    private javax.swing.JTextField role;
    private javax.swing.JTextField uname;
    private javax.swing.JLabel update1;
    // End of variables declaration//GEN-END:variables
}
