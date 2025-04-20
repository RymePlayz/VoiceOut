package ryme.Ui;

import ryme.Oop.*;
import ryme.Helper.*;

public class RegisterForm extends javax.swing.JFrame {

    public RegisterForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        registerBtn = new javax.swing.JButton();
        goToLoginLbl = new javax.swing.JLabel();
        nameFld = new javax.swing.JTextField();
        cNumberFld = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        GenderComBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        userNameFld = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        passwordFld = new javax.swing.JPasswordField();
        bioFld = new javax.swing.JTextField();
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
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jLabel4.setBackground(new java.awt.Color(30, 144, 255));
        jLabel4.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(30, 144, 255));
        jLabel4.setText("Bio:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));

        jLabel5.setBackground(new java.awt.Color(30, 144, 255));
        jLabel5.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(30, 144, 255));
        jLabel5.setText("Gender:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        registerBtn.setBackground(new java.awt.Color(176, 176, 176));
        registerBtn.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 24)); // NOI18N
        registerBtn.setForeground(new java.awt.Color(0, 51, 102));
        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        jPanel1.add(registerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 280, 30));

        goToLoginLbl.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 8)); // NOI18N
        goToLoginLbl.setForeground(new java.awt.Color(176, 176, 176));
        goToLoginLbl.setText("Go to Login?");
        jPanel1.add(goToLoginLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, -1, -1));

        nameFld.setBackground(new java.awt.Color(176, 176, 176));
        nameFld.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        nameFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(nameFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 180, 20));

        cNumberFld.setBackground(new java.awt.Color(176, 176, 176));
        cNumberFld.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        cNumberFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(cNumberFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 180, 20));

        jLabel7.setBackground(new java.awt.Color(30, 144, 255));
        jLabel7.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(30, 144, 255));
        jLabel7.setText("EPhillID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jLabel8.setBackground(new java.awt.Color(30, 144, 255));
        jLabel8.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(30, 144, 255));
        jLabel8.setText("Contact#:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        GenderComBox.setBackground(new java.awt.Color(176, 176, 176));
        GenderComBox.setForeground(new java.awt.Color(255, 255, 255));
        GenderComBox.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Prefer not to say", "Male", "Female" }));
        GenderComBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenderComBoxActionPerformed(evt);
            }
        });
        jPanel1.add(GenderComBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 180, -1));

        jLabel9.setBackground(new java.awt.Color(30, 144, 255));
        jLabel9.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(30, 144, 255));
        jLabel9.setText("Password:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        userNameFld.setBackground(new java.awt.Color(176, 176, 176));
        userNameFld.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        userNameFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(userNameFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 180, 20));

        jLabel10.setBackground(new java.awt.Color(30, 144, 255));
        jLabel10.setFont(new java.awt.Font("DejaVu Sans Condensed", 2, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(30, 144, 255));
        jLabel10.setText("Name:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));

        passwordFld.setBackground(new java.awt.Color(176, 176, 176));
        passwordFld.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        passwordFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(passwordFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 180, -1));

        bioFld.setBackground(new java.awt.Color(176, 176, 176));
        bioFld.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 12)); // NOI18N
        bioFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(bioFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 180, 20));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 360, 400));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graycircle.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void GenderComBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_GenderComBoxActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_GenderComBoxActionPerformed

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_registerBtnActionPerformed}
        userNameFld.setName("EPhillID");
        nameFld.setName("Name");
        bioFld.setName("Bio");
        passwordFld.setName("Password");
        cNumberFld.setName("Contact #");

        Validation validator = new Validation();

        if (userNameFld.getText().trim().isEmpty()) {
            validator.addCustomError("• EPhillID must not be blank!");
        } else if (!userNameFld.getText().trim().matches("\\d+")) {
            validator.addCustomError("• EPhillID must be a valid number (only digits allowed).");
        }

        if (nameFld.getText().trim().isEmpty()) {
            validator.addCustomError("• Name must not be blank!");
        } else if (nameFld.getText().trim().length() <= 4) {
            validator.addCustomError("• Name must have more than 4 characters.");
        }

        if (bioFld.getText().trim().isEmpty()) {
            validator.addCustomError("• Bio must not be blank!");
        }

        if (passwordFld.getText().trim().isEmpty()) {
            validator.addCustomError("• Password must not be blank!");
        } else if (passwordFld.getText().trim().length() <= 6) {
            validator.addCustomError("• Password must have more than 6 characters.");
        }

        if (cNumberFld.getText().trim().isEmpty()) {
            validator.addCustomError("• Contact # must not be blank!");
        } else if (!cNumberFld.getText().trim().matches("\\d{11}")) {
            validator.addCustomError("• Contact # must be exactly 11 digits.");
        }

        if (!validator.displayErrors()) {
            return;
        }

// Place your database logic here.
    }

    public static void main(String args[]) {
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> GenderComBox;
    private javax.swing.JTextField bioFld;
    private javax.swing.JTextField cNumberFld;
    private javax.swing.JLabel goToLoginLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nameFld;
    private javax.swing.JPasswordField passwordFld;
    private javax.swing.JButton registerBtn;
    private javax.swing.JTextField userNameFld;
    // End of variables declaration//GEN-END:variables
}
