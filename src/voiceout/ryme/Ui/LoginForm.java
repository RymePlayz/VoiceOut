package voiceout.ryme.Ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import voiceout.ryme.Helper.*;
import voiceout.ryme.Oop.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import static voiceout.ryme.Ui.RegisterForm.hashPasswordSHA256;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();
String absolutePath = "/home/ryme/All/Github/VoiceOutSystem/src/images/icon.png";
        ImageIcon icon = new ImageIcon(absolutePath);
                setIconImage(icon.getImage());

        goToRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterForm register = new RegisterForm();
                register.setVisible(true);
                dispose();
            }
        });
        usernameFld.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "moveToPassFld");
        usernameFld.getActionMap().put("moveToPassFld", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                passFld.requestFocusInWindow(); // ✅ Moves focus to password field
            }
        });

        passFld.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enterPressed");
        passFld.getActionMap().put("enterPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loginBtnActionPerformed(new ActionEvent(loginBtn, ActionEvent.ACTION_PERFORMED, "Enter Key Pressed"));
            }
        });
        
        KeyAdapter preventLeadingSpaces = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                JTextField field = (JTextField) e.getSource();
                if (field.getText().isEmpty() && e.getKeyChar() == ' ') {
                    e.consume();
                }
            }
        };

        usernameFld.addKeyListener(preventLeadingSpaces);
        passFld.addKeyListener(preventLeadingSpaces);
        KeyAdapter preventLettersAndLimit2 = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                JTextField field = (JTextField) e.getSource();
                char c = e.getKeyChar();

                if (!Character.isDigit(c) || field.getText().length() >= 6) {
                    e.consume();
                }
            }
        };
        usernameFld.addKeyListener(preventLettersAndLimit2);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        usernameFld = new javax.swing.JTextField();
        passFld = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        goToRegister = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Voice Out");
        setResizable(false);

        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 650));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usernameFld.setBackground(new java.awt.Color(102, 102, 102));
        usernameFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(usernameFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 280, 40));

        passFld.setBackground(new java.awt.Color(102, 102, 102));
        passFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(passFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 350, 280, 40));

        jLabel2.setFont(new java.awt.Font("Times New Roman Cyr", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Voice Out System");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, -1));

        loginBtn.setBackground(new java.awt.Color(102, 102, 102));
        loginBtn.setForeground(new java.awt.Color(255, 255, 255));
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel2.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 280, 50));

        jLabel3.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, -1, -1));

        goToRegister.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        goToRegister.setForeground(new java.awt.Color(255, 255, 255));
        goToRegister.setText("Create an account");
        jPanel2.add(goToRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 410, -1, -1));

        jLabel6.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("EPhillID:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        jLabel1.setText(".");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        String username = usernameFld.getText().trim();
        String pass = passFld.getText().trim();

        ValidationManager validator = new ValidationManager() {
            @Override
            public boolean validateFields(JTextField... fields) {
                return super.validateFields(fields);
            }
        };

        if (username.isEmpty()) {
            validator.addCustomError("• EphillID must not be blank!");
        }

        if (pass.isEmpty()) {
            validator.addCustomError("• Password must not be blank!");
        }

        if (!validator.displayErrors()) {
            return;
        }

        try {
            Connection conn = DBConnection.getConnection();

            // Fetch only the hashed password
            String query = "SELECT * FROM users WHERE username=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");

                String inputHashedPassword = hashPasswordSHA256(pass);

                // Compare the hashed input password with the stored hashed password
                if (inputHashedPassword.equals(storedHashedPassword)) {
                    CurrentSession session = CurrentSession.getInstance();

                    session.setUserId(rs.getInt("user_id"));
                    session.setUsername(rs.getString("username"));
                    session.setContactNumber(rs.getString("contact_num"));
                    session.setGender(rs.getString("gender"));
                    session.setAge(rs.getInt("age"));
                    session.setEmail(rs.getString("email"));
                    session.setAddress(rs.getString("address"));
                    session.setName(rs.getString("name"));
                    session.setCreationDate(rs.getString("creation_date"));

                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    Dashboard dashboard = new Dashboard();
                    dashboard.setVisible(true);
                    dispose();
                } else {
                    validator.addCustomError("• Invalid Username or Password.");
                }
            } else {
                validator.addCustomError("• Invalid Username or Password.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        validator.displayErrors();


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
    private javax.swing.JLabel goToRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passFld;
    private javax.swing.JTextField usernameFld;
    // End of variables declaration//GEN-END:variables
}
