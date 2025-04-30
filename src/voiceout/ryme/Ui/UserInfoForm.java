package voiceout.ryme.Ui;

import javax.swing.table.DefaultTableModel;
import voiceout.ryme.Helper.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class UserInfoForm extends javax.swing.JFrame {

    public UserInfoForm() {
        initComponents(); // ✅ Initialize UI components
        showCurrentUserData(); // ✅ Load user data from current session
        goToDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
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

    private void showCurrentUserData() {
        CurrentSession session = CurrentSession.getInstance();
        DefaultTableModel model = (DefaultTableModel) user_edit_table.getModel();

        // ✅ Clear existing rows to prevent duplication
        model.setRowCount(0);

        // ✅ Add current session data as a row
        Object[] row = {
            session.getUserId(),
            session.getUsername(),
            session.getPassword(), // ⚠️ Consider hashing passwords for security
            session.getName(),
            session.getAge(),
            session.getEmail(),
            session.getContactNumber(),
            session.getAddress(),
            session.getGender()
        };

        model.addRow(row); // ✅ Add row to the table
    }

    private void updateUserData() {
        DefaultTableModel model = (DefaultTableModel) user_edit_table.getModel();
        int rowCount = model.getRowCount();

        for (int row = 0; row < rowCount; row++) {
            int userId = (int) model.getValueAt(row, 0);
            String username = (String) model.getValueAt(row, 1);
            String password = (String) model.getValueAt(row, 2);
            String name = (String) model.getValueAt(row, 3);
            int age = Integer.parseInt(model.getValueAt(row, 4).toString());
            String email = (String) model.getValueAt(row, 5);
            String contactNum = (String) model.getValueAt(row, 6);
            String address = (String) model.getValueAt(row, 7);
            String gender = (String) model.getValueAt(row, 8);

            // ✅ Check for blank fields
            if (password.isEmpty() || name.isEmpty() || email.isEmpty() || contactNum.isEmpty() || address.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: All fields must be filled.", "Update Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method
            }

            // ✅ Check password length (must be greater than 6)
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(null, "Error: Password must be at least 6 characters long.", "Update Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method
            }
            JOptionPane.showMessageDialog(null, "Updated Succesfully!", "User Data Updated", JOptionPane.INFORMATION_MESSAGE);

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/voiceout", "root", "")) {
                String query = "UPDATE users SET password=?, name=?, age=?, email=?, contact_num=?, address=?, gender=? WHERE user_id=?";
                PreparedStatement pst = conn.prepareStatement(query);

                pst.setString(1, password);
                pst.setString(2, name);
                pst.setInt(3, age);
                pst.setString(4, email);
                pst.setString(5, contactNum);
                pst.setString(6, address);
                pst.setString(7, gender);
                pst.setInt(8, userId);

                int updated = pst.executeUpdate();
                if (updated > 0) {
                    updateCurrentSession(userId, password, name, age, email, contactNum, address, gender);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateCurrentSession(int userId, String password, String name, int age, String email, String contactNum, String address, String gender) {
        CurrentSession session = CurrentSession.getInstance();
        session.setUserId(userId);
        session.setPassword(password);
        session.setName(name);
        session.setAge(age);
        session.setEmail(email);
        session.setContactNumber(contactNum);
        session.setAddress(address);
        session.setGender(gender);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        user_edit_table = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(11, 11, 69));
        jPanel2.setPreferredSize(new java.awt.Dimension(1400, 950));

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

        jLabel8.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("User Info");

        jLabel10.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("User Info");

        user_edit_table.setBackground(new java.awt.Color(102, 102, 102));
        user_edit_table.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        user_edit_table.setForeground(new java.awt.Color(255, 255, 255));
        user_edit_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "EPhillID", "Password", "Name", "Age", "Email", "Contact #", "Address", "Gender"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(user_edit_table);
        if (user_edit_table.getColumnModel().getColumnCount() > 0) {
            user_edit_table.getColumnModel().getColumn(0).setResizable(false);
            user_edit_table.getColumnModel().getColumn(1).setResizable(false);
            user_edit_table.getColumnModel().getColumn(2).setResizable(false);
            user_edit_table.getColumnModel().getColumn(3).setResizable(false);
            user_edit_table.getColumnModel().getColumn(4).setResizable(false);
            user_edit_table.getColumnModel().getColumn(5).setResizable(false);
            user_edit_table.getColumnModel().getColumn(6).setResizable(false);
            user_edit_table.getColumnModel().getColumn(7).setResizable(false);
            user_edit_table.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel11.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Under Contruction");
        jLabel11.setToolTipText("admin panel below");

        jLabel9.setFont(new java.awt.Font("Inter", 1, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Double row to edit");

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(updateBtn)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateBtn)
                .addGap(126, 126, 126)
                .addComponent(jLabel11))
        );

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
        updateUserData();
    }//GEN-LAST:event_updateBtnActionPerformed

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
    private javax.swing.JLabel goToAboutUs;
    private javax.swing.JLabel goToActivityLog;
    private javax.swing.JLabel goToDashboard;
    private javax.swing.JLabel goToDonateToUs;
    private javax.swing.JLabel goToSiteTraffic;
    private javax.swing.JLabel goToUserInfo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logout;
    private javax.swing.JButton updateBtn;
    private javax.swing.JTable user_edit_table;
    // End of variables declaration//GEN-END:variables
}
