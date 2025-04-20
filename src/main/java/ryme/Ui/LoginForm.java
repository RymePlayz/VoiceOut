package ryme.Ui;

import ryme.Oop.*;
import ryme.Helper.*;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        userNameFld = new javax.swing.JTextField();
        passwordFld = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        goToRegisterLabelBtn = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(44, 44, 44));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 400));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(40, 40, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(30, 144, 255));
        jLabel3.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 144, 255));
        jLabel3.setText("Voice Out");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jLabel4.setBackground(new java.awt.Color(30, 144, 255));
        jLabel4.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(30, 144, 255));
        jLabel4.setText("EPhillID:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel5.setBackground(new java.awt.Color(30, 144, 255));
        jLabel5.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(30, 144, 255));
        jLabel5.setText("Password:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        userNameFld.setBackground(new java.awt.Color(176, 176, 176));
        userNameFld.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        userNameFld.setForeground(new java.awt.Color(30, 144, 255));
        jPanel1.add(userNameFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 180, 40));

        passwordFld.setBackground(new java.awt.Color(176, 176, 176));
        passwordFld.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        passwordFld.setForeground(new java.awt.Color(30, 144, 255));
        passwordFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFldActionPerformed(evt);
            }
        });
        jPanel1.add(passwordFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 180, 40));

        loginBtn.setBackground(new java.awt.Color(176, 176, 176));
        loginBtn.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 24)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(0, 51, 102));
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 280, 30));

        goToRegisterLabelBtn.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 8)); // NOI18N
        goToRegisterLabelBtn.setForeground(new java.awt.Color(176, 176, 176));
        goToRegisterLabelBtn.setText("Do you want to register?");
        jPanel1.add(goToRegisterLabelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 360, 400));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graycircle.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFldActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        userNameFld.setName("EPhillID");
        passwordFld.setName("Password");
        //chanege to null for both ways, true for user flase for terminal
        IValidation validator = new ValidationWithOption(true);

        if (!validator.validateFields(userNameFld, passwordFld)) {
            return;
        }
    }//GEN-LAST:event_loginBtnActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel goToRegisterLabelBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginBtn;
    private static javax.swing.JPasswordField passwordFld;
    private static javax.swing.JTextField userNameFld;
    // End of variables declaration//GEN-END:variables
}
