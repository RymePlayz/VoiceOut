package voiceout.ryme.Ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import voiceout.ryme.Helper.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static voiceout.ryme.Ui.RegisterForm.hashPasswordSHA256;
import java.sql.ResultSet;

public class UserInfoForm extends javax.swing.JFrame {

    public UserInfoForm() {
        initComponents();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Dashboard dashboard = new Dashboard();
                dashboard.show();
                dispose();
            }
        });

        goToSiteTraffic.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SiteTrafficForm siteTrafficForm = new SiteTrafficForm();
                siteTrafficForm.setVisible(true);
                dispose();
            }
        });

        goToDonateToUs.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DonationForm donationForm = new DonationForm();
                donationForm.setVisible(true);
                dispose();
            }
        });

        goToAboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AboutUsForm aboutUsForm = new AboutUsForm();
                aboutUsForm.setVisible(true);
                dispose();
            }
        });
        goToActivityLog.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ActivityLogForm activityLogForm = new ActivityLogForm();
                activityLogForm.setVisible(true);
                dispose();
            }
        });

        goToDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ActivityLogForm activityLogForm = new ActivityLogForm();
                activityLogForm.setVisible(true);
                dispose();
            }
        });
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    CurrentSession currentSession = CurrentSession.getInstance();
                    currentSession.clearSession();
                    dispose();
                    LoginForm loginForm = new LoginForm();
                    loginForm.setVisible(true);
                }
            }
        });
    }

    public void refreshProfile() {
        CurrentSession currentSession = new CurrentSession();
        String name = currentSession.getName();
        int age = currentSession.getAge();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        goToActivityLog = new javax.swing.JLabel();
        goToUserInfo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        goToSiteTraffic = new javax.swing.JLabel();
        goToDonateToUs = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        goToAboutUs = new javax.swing.JLabel();
        goToDashboard = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ephillIdFld = new javax.swing.JTextField();
        contactNumFld = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        emailFld = new javax.swing.JTextField();
        addressFld = new javax.swing.JTextField();
        passFld = new javax.swing.JTextField();
        nameFld = new javax.swing.JTextField();
        ageFld = new javax.swing.JTextField();
        genderFld = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(11, 11, 69));
        jPanel2.setPreferredSize(new java.awt.Dimension(1400, 950));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(63, 94, 171));

        goToActivityLog.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        goToActivityLog.setForeground(new java.awt.Color(255, 255, 255));
        goToActivityLog.setText("Activity Log");

        goToUserInfo.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        goToUserInfo.setForeground(new java.awt.Color(255, 255, 255));
        goToUserInfo.setText("User Info");

        jLabel3.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Under Contruction");

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pfp (1).png"))); // NOI18N

        goToSiteTraffic.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        goToSiteTraffic.setForeground(new java.awt.Color(255, 255, 255));
        goToSiteTraffic.setText("Site Traffic");

        goToDonateToUs.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        goToDonateToUs.setForeground(new java.awt.Color(255, 255, 255));
        goToDonateToUs.setText("Donate To Us");

        jLabel7.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Copyright 2025 RymeNet");

        goToAboutUs.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        goToAboutUs.setForeground(new java.awt.Color(255, 255, 255));
        goToAboutUs.setText("About Us");

        goToDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(logout)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(goToDashboard))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(goToActivityLog)
                                .addComponent(goToUserInfo)
                                .addComponent(jLabel3)
                                .addComponent(goToSiteTraffic)
                                .addComponent(goToDonateToUs)
                                .addComponent(goToAboutUs)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logout)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(goToDashboard)))
                .addGap(52, 52, 52)
                .addComponent(goToUserInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(goToActivityLog)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(goToSiteTraffic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(goToDonateToUs)
                .addGap(18, 18, 18)
                .addComponent(goToAboutUs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 513, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(79, 79, 79))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel8.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Update INFO");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, -1, -1));

        jLabel10.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Password:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 550, -1, -1));

        updateBtn.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel2.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 600, 192, 35));

        jLabel12.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("EPhillID:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 132, -1, -1));

        jLabel13.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Name:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, -1, -1));

        jLabel11.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Age:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 228, -1, -1));

        jLabel14.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Email:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, -1, -1));

        jLabel15.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Contact Number:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 450, -1, -1));

        jLabel16.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Gender:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 282, -1, -1));

        jLabel17.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Address:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, -1, -1));

        jLabel18.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Account Creation Date:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 910, -1, -1));

        ephillIdFld.setEditable(false);
        ephillIdFld.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel2.add(ephillIdFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 129, 455, -1));

        contactNumFld.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel2.add(contactNumFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 440, 455, -1));

        jLabel19.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1069, 433, -1, -1));

        logoutBtn.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        jPanel2.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 600, 179, 35));

        emailFld.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel2.add(emailFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 390, 455, -1));

        addressFld.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel2.add(addressFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 490, 455, -1));

        passFld.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel2.add(passFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 540, 455, -1));

        nameFld.setEditable(false);
        nameFld.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel2.add(nameFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 177, 455, -1));

        ageFld.setEditable(false);
        ageFld.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel2.add(ageFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 225, 455, -1));

        genderFld.setEditable(false);
        genderFld.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jPanel2.add(genderFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 279, 455, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 343, -1, -1));

        jLabel9.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("User Information");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        String username = ephillIdFld.getText().trim();
        String name = nameFld.getText().trim();
        String email = emailFld.getText().trim();
        String contactNum = contactNumFld.getText().trim();
        String address = addressFld.getText().trim();
        String oldPassword = JOptionPane.showInputDialog(null, "Enter current password:");

        try {
            Connection conn = DBConnection.getConnection();

            String checkPasswordQuery = "SELECT password FROM users WHERE username=?";
            PreparedStatement pstCheck = conn.prepareStatement(checkPasswordQuery);
            pstCheck.setString(1, username);
            ResultSet rs = pstCheck.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                String enteredHashedPassword = hashPasswordSHA256(oldPassword);

                if (!enteredHashedPassword.equals(storedHashedPassword)) {
                    JOptionPane.showMessageDialog(null, "Error: Incorrect current password!", "Password Update Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit if password is wrong
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: User not found!", "Update Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String updateQuery = "UPDATE users SET name=?, email=?, contact_num=?, address=? WHERE username=?";
            PreparedStatement pstUpdate = conn.prepareStatement(updateQuery);
            pstUpdate.setString(1, name);
            pstUpdate.setString(2, email);
            pstUpdate.setString(3, contactNum);
            pstUpdate.setString(4, address);
            pstUpdate.setString(5, username);

            int updated = pstUpdate.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "User information updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Update failed. Please try again.", "Update Error", JOptionPane.ERROR_MESSAGE);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error! Contact support.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            CurrentSession currentSession = CurrentSession.getInstance();
            currentSession.clearSession();
            dispose();
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(UserInfoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInfoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInfoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInfoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInfoForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressFld;
    private javax.swing.JTextField ageFld;
    private javax.swing.JTextField contactNumFld;
    private javax.swing.JTextField emailFld;
    private javax.swing.JTextField ephillIdFld;
    private javax.swing.JTextField genderFld;
    private javax.swing.JLabel goToAboutUs;
    private javax.swing.JLabel goToActivityLog;
    private javax.swing.JLabel goToDashboard;
    private javax.swing.JLabel goToDonateToUs;
    private javax.swing.JLabel goToSiteTraffic;
    private javax.swing.JLabel goToUserInfo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel logout;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JTextField nameFld;
    private javax.swing.JTextField passFld;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
