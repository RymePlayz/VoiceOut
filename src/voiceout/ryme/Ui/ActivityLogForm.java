package voiceout.ryme.Ui;

import javax.swing.JOptionPane;
import voiceout.ryme.Helper.CurrentSession;

public class ActivityLogForm extends javax.swing.JFrame {

    public ActivityLogForm() {
        initComponents();
        
        goToDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose();
            }
        });
        
        goToUserInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserInfoForm userInfoForm = new UserInfoForm();
                userInfoForm.setVisible(true);
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
        });logout.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    CurrentSession currentSession = CurrentSession.getInstance();
                    currentSession.clearSession();
                    dispose();
                }
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        goToActivtyLog = new javax.swing.JLabel();
        goToUserInfo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        goToSiteTraffic = new javax.swing.JLabel();
        goToDonateToUs = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        goToAboutUs = new javax.swing.JLabel();
        goToDashboard = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(11, 11, 69));
        jPanel2.setPreferredSize(new java.awt.Dimension(1400, 950));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(63, 94, 171));

        goToActivtyLog.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        goToActivtyLog.setForeground(new java.awt.Color(255, 255, 255));
        goToActivtyLog.setText("Activity Log");

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
                                .addComponent(goToActivtyLog)
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
                        .addGap(19, 19, 19)
                        .addComponent(goToDashboard)))
                .addGap(52, 52, 52)
                .addComponent(goToUserInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(goToActivtyLog)
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

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, -1));

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
            java.util.logging.Logger.getLogger(ActivityLogForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActivityLogForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActivityLogForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActivityLogForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActivityLogForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel goToAboutUs;
    private javax.swing.JLabel goToActivtyLog;
    private javax.swing.JLabel goToDashboard;
    private javax.swing.JLabel goToDonateToUs;
    private javax.swing.JLabel goToSiteTraffic;
    private javax.swing.JLabel goToUserInfo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel logout;
    // End of variables declaration//GEN-END:variables
}
