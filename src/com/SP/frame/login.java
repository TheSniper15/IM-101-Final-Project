/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.SP.frame;

import com.SP.db.dbConn;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Arvy
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        //HidePassword.setVisible(false);
	db.Connect();
    }
    
    dbConn db = new dbConn();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        Enter = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setEditable(false);
        username.setBackground(new java.awt.Color(255, 255, 255));
        username.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        username.setForeground(new java.awt.Color(0, 0, 0));
        username.setText("Jeanne Pelayo");
        username.setToolTipText("");
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 500, 510, 70));

        jPasswordField1.setText("password");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 620, 510, 70));

        Enter.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        Enter.setForeground(new java.awt.Color(255, 255, 255));
        Enter.setText("ENTER");
        Enter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EnterMouseEntered(evt);
            }
        });
        getContentPane().add(Enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 840, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/SP/img/LOGIN.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void EnterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnterMouseEntered
        // TODO add your handling code here:
        new Staff_dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_EnterMouseEntered

    /**
     * @param args the command line arguments
     */
    private void logincom()
	{
		String usr = username.getText();
		String pswd = new String(jPasswordField1.getPassword());
		String stat = "Admin";
		
		try
		{
			db.pst = db.con.prepareStatement("SELECT * FROM librarians where username = ? and password = ?");
			db.pst.setString(1, usr);
			db.pst.setString(2,pswd);
			db.rs = db.pst.executeQuery();
			
			if(db.rs.next())
			{
				db.pst = db.con.prepareStatement("SELECT * FROM librarians where username = ? and password = ? and Status = ?");
				db.pst.setString(1, usr);
				db.pst.setString(2,pswd);
				db.pst.setString(3,stat);
				db.rs = db.pst.executeQuery();
			
				if(db.rs.next())
				{
					new Admin().setVisible(true);
					this.dispose();
				}
				else
				{
					new Staff_dashboard().setVisible(true);
					this.dispose();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Username or Password is Incorrect!!");
			}
		}
		catch(SQLException ex)
		{
			Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Enter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
