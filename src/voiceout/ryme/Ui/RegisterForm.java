package voiceout.ryme.Ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import voiceout.ryme.Oop.*;
import voiceout.ryme.Helper.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import javax.swing.ImageIcon;

public class RegisterForm extends javax.swing.JFrame {

    public RegisterForm() {
        initComponents();
        String absolutePath = "/home/ryme/All/Github/VoiceOutSystem/src/images/icon.png";
        ImageIcon icon = new ImageIcon(absolutePath);
        setIconImage(icon.getImage());

        
        goToLoginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
                dispose();
            }
        });

        addressFld.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enterPressed");
        addressFld.getActionMap().put("enterPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                registerBtnActionPerformed(new ActionEvent(registerBtn, ActionEvent.ACTION_PERFORMED, "Enter Key Pressed"));
            }
        });

        KeyAdapter preventLetters = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        };

        ageFld.addKeyListener(preventLetters);
        contactNumFld.addKeyListener(preventLetters);
        usernameFld.addKeyListener(preventLetters);

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
        passwordFld.addKeyListener(preventLeadingSpaces);
        nameFld.addKeyListener(preventLeadingSpaces);
        ageFld.addKeyListener(preventLeadingSpaces);
        emailFld.addKeyListener(preventLeadingSpaces);
        contactNumFld.addKeyListener(preventLeadingSpaces);
        addressFld.addKeyListener(preventLeadingSpaces);

// Restrict ageFld to 3 digits
        KeyAdapter preventLettersAndLimit = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                JTextField field = (JTextField) e.getSource();
                char c = e.getKeyChar();

                if (!Character.isDigit(c) || field.getText().length() >= 3) {
                    e.consume();
                }
            }
        };
        ageFld.addKeyListener(preventLettersAndLimit);

// Restrict usernameFld to 6 digits
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

// Pre-fill contactNumFld with "09"
        contactNumFld.setText("09");

        KeyAdapter preventDigitsAndLimitContactNum = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                JTextField field = (JTextField) e.getSource();
                char c = e.getKeyChar();

                if (field.getCaretPosition() < 2) {
                    e.consume();
                    return;
                }

                if (!Character.isDigit(c) || field.getText().length() >= 11) {
                    e.consume();
                }
            }
        };
        contactNumFld.addKeyListener(preventDigitsAndLimitContactNum);

        contactNumFld.getDocument().addDocumentListener(new DocumentListener() {
            private void checkAndRestore() {
                SwingUtilities.invokeLater(() -> {
                    if (contactNumFld.getText().isEmpty() || !contactNumFld.getText().startsWith("09")) {
                        contactNumFld.setText("09");
                    }
                });
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkAndRestore();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkAndRestore();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkAndRestore();
            }
        });

    }

    public static String hashPasswordSHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convert bytes to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        contactNumFld = new javax.swing.JTextField();
        passwordFld = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        registerBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        goToLoginBtn = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        usernameFld = new javax.swing.JTextField();
        nameFld = new javax.swing.JTextField();
        addressFld = new javax.swing.JTextField();
        emailFld = new javax.swing.JTextField();
        ageFld = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        genderCBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Voice Out");
        setResizable(false);

        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 650));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contactNumFld.setBackground(new java.awt.Color(102, 102, 102));
        contactNumFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(contactNumFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 280, 30));

        passwordFld.setBackground(new java.awt.Color(102, 102, 102));
        passwordFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(passwordFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 280, 30));

        jLabel2.setFont(new java.awt.Font("Times New Roman Cyr", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Voice Out System");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, -1, -1));

        registerBtn.setBackground(new java.awt.Color(102, 102, 102));
        registerBtn.setForeground(new java.awt.Color(255, 255, 255));
        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        jPanel2.add(registerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 550, 280, 50));

        jLabel3.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Gender:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, -1, -1));

        goToLoginBtn.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        goToLoginBtn.setForeground(new java.awt.Color(255, 255, 255));
        goToLoginBtn.setText("Sign in");
        jPanel2.add(goToLoginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 520, -1, -1));

        jLabel6.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("EPhillID:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, -1, -1));

        usernameFld.setBackground(new java.awt.Color(102, 102, 102));
        usernameFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(usernameFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 280, 30));

        nameFld.setBackground(new java.awt.Color(102, 102, 102));
        nameFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(nameFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 280, 30));

        addressFld.setBackground(new java.awt.Color(102, 102, 102));
        addressFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(addressFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 280, 30));

        emailFld.setBackground(new java.awt.Color(102, 102, 102));
        emailFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(emailFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 280, 30));

        ageFld.setBackground(new java.awt.Color(102, 102, 102));
        ageFld.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(ageFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 280, 30));

        jLabel4.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, -1, -1));

        jLabel7.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Name:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, -1, -1));

        jLabel8.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Age:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, -1, -1));

        jLabel9.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Email:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, -1, -1));

        jLabel10.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Contact #:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, -1, -1));

        jLabel11.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Address:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, -1, -1));

        genderCBox.setBackground(new java.awt.Color(102, 102, 102));
        genderCBox.setForeground(new java.awt.Color(255, 255, 255));
        genderCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prefer not to say", "Male", "Female", " " }));
        jPanel2.add(genderCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 280, -1));

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

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        String username = usernameFld.getText().trim();
        String password = passwordFld.getText().trim();
        String name = nameFld.getText().trim();
        String age = ageFld.getText().trim();
        String email = emailFld.getText().trim();
        String contactNum = contactNumFld.getText().trim();
        String address = addressFld.getText().trim(); // Fixed issue
        String gender = genderCBox.getSelectedItem().toString();

        ValidationManager validator = new ValidationManager() {
            @Override
            public boolean validateFields(JTextField... fields) {
                return super.validateFields(fields);
            }
        };

        if (password.isEmpty()) {
            validator.addCustomError("• Password must not be blank");
        } else if (password.length() < 6) {
            validator.addCustomError("• Password must be at least 6 characters long");
        } else if (!password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
            validator.addCustomError("• Password must include at least one uppercase letter and one number");
        }

        if (!validator.displayErrors()) {
            return;
        }

        try {
            String hashedPassword = hashPasswordSHA256(password);

            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO users (username, password, creation_date, gender, age, contact_num, email, address, name) VALUES (?, ?, CURRENT_TIMESTAMP(6), ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, hashedPassword);
            pst.setString(3, gender);
            pst.setInt(4, Integer.parseInt(age));
            pst.setString(5, contactNum);
            pst.setString(6, email);
            pst.setString(7, address);
            pst.setString(8, name);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Registration successful!");
                usernameFld.setText("");
                passwordFld.setText("");
                nameFld.setText("");
                ageFld.setText("");
                emailFld.setText("");
                contactNumFld.setText("");
                addressFld.setText("");
                LoginForm loginForm = new LoginForm();
                loginForm.show();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed. Please try again.");
            }

            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Account Already Registered", "Account Registration Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_registerBtnActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressFld;
    private javax.swing.JTextField ageFld;
    private javax.swing.JTextField contactNumFld;
    private javax.swing.JTextField emailFld;
    private javax.swing.JComboBox<String> genderCBox;
    private javax.swing.JLabel goToLoginBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nameFld;
    private javax.swing.JPasswordField passwordFld;
    private javax.swing.JButton registerBtn;
    private javax.swing.JTextField usernameFld;
    // End of variables declaration//GEN-END:variables
}
