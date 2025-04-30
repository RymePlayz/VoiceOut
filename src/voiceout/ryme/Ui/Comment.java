package voiceout.ryme.Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import voiceout.ryme.Helper.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment extends javax.swing.JFrame {

    private int postId;
    private String currentUsername;
    private String currentName;

    Connection conn = null;
    PreparedStatement pst = null;
    
    public Comment(int postId) {
        initComponents();
        
        conn = DBConnection.getConnection();
        
        
        this.postId = postId;
        this.currentUsername = (CurrentSession.getInstance() != null) ? CurrentSession.getInstance().getUsername() : "Unknown";
        this.currentName = (CurrentSession.getInstance() != null) ? CurrentSession.getInstance().getName() : "Anonymous";

        loadPostData();
        loadComments();

        goToUserInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserInfoForm userInfoForm = new UserInfoForm();
                userInfoForm.setVisible(true);
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
        comment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                commentBtnActionPerformed(new ActionEvent(commentBtn, ActionEvent.ACTION_PERFORMED, "Enter Key Pressed"));
            }
        });

    }

    private void loadPostData() {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(
                "SELECT up.name, up.content, up.donation_goal, up.donation_received, u.username "
                + "FROM user_post up "
                + "JOIN users u ON up.user_id = u.user_id "
                + // ✅ Get username associated with post
                "WHERE up.post_id = ?")) {
            pstmt.setInt(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    name.setText(rs.getString("name"));
                    content.setText(rs.getString("content"));
                    username.setText(rs.getString("username")); // ✅ Show username
                    donationGoal.setText("Goal: " + rs.getDouble("donation_goal")); // ✅ Update donation goal
                    donationRecieved.setText("Received: " + rs.getDouble("donation_received")); // ✅ Update received amount
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadComments() {
        StringBuilder commentText = new StringBuilder();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(
                "SELECT uc.comment_content, uc.created_at, u.name AS commenter_name, u.username "
                + "FROM user_comment uc "
                + "JOIN users u ON uc.user_id = u.user_id "
                + // ✅ Always fetch latest name
                "WHERE uc.post_id = ? ORDER BY uc.created_at ASC")) {

            pstmt.setInt(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    commentText.append("==============================\n")
                            .append(rs.getString("username")).append(" (").append(rs.getString("commenter_name")).append("):\n")
                            .append(rs.getString("comment_content")).append("\n")
                            .append("Posted on: ").append(rs.getTimestamp("created_at")).append("\n")
                            .append("==============================\n\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        commentSec.setText(commentText.toString()); // ✅ Display formatted comments dynamically
    }

    private void submitComment() {
        String commentText = comment.getText().trim();
        int userId = CurrentSession.getInstance().getUserId();

        if (commentText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Comment cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO user_comment (post_id, user_id, comment_content, created_at) "
                + "VALUES (?, ?, ?, NOW())")) {
            pstmt.setInt(1, postId);
            pstmt.setInt(2, userId);
            pstmt.setString(3, commentText);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Comment posted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadComments(); // ✅ Refresh comment section after posting
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Prevent duplicate likes
    private void handleLikeAction() {
        int userId = CurrentSession.getInstance().getUserId();

        if (hasUserLikedPost(userId, postId)) {
            JOptionPane.showMessageDialog(null, "You have already liked this post!", "Like Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement("UPDATE user_post SET likes = likes + 1 WHERE post_id = ?")) {
                pstmt.setInt(1, postId);
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET liked_posts = CONCAT(IFNULL(liked_posts, ''), ',', ?) WHERE user_id = ?")) {
                pstmt.setInt(1, postId);
                pstmt.setInt(2, userId);
                pstmt.executeUpdate();
            }

            conn.commit();
            JOptionPane.showMessageDialog(null, "Post liked successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Check if user already liked the post
    private boolean hasUserLikedPost(int userId, int postId) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT liked_posts FROM users WHERE user_id = ?")) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String likedPosts = rs.getString("liked_posts");
                    return likedPosts != null && likedPosts.matches("(.*,)?" + postId + "(,.*)?");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Donation functionality (ensures latest values reflect database)
    private void handleDonationAction() {
        double donationAmount;
        try {
            donationAmount = Double.parseDouble(donation.getText().trim());
            if (donationAmount <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid donation amount. Please enter a positive value.", "Donation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid donation format. Please enter a number.", "Donation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double updatedReceived = updateDonationReceived(postId, donationAmount);
        donationRecieved.setText("Received: " + updatedReceived); // ✅ Reflect updated donation amount
        JOptionPane.showMessageDialog(null, "Thank you for your donation!", "Success", JOptionPane.INFORMATION_MESSAGE);
        loadPostData(); // ✅ Refresh post data after donation
    }

    private double updateDonationReceived(int postId, double amount) {
        double updatedReceived = 0.0;

        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement getStmt = conn.prepareStatement("SELECT donation_received FROM user_post WHERE post_id = ?")) {
                getStmt.setInt(1, postId);
                try (ResultSet rs = getStmt.executeQuery()) {
                    if (rs.next()) {
                        updatedReceived = rs.getDouble("donation_received") + amount;
                    }
                }
            }

            try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE user_post SET donation_received = ? WHERE post_id = ?")) {
                updateStmt.setDouble(1, updatedReceived);
                updateStmt.setInt(2, postId);
                updateStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedReceived;
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
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        content = new javax.swing.JTextArea();
        donationGoal = new javax.swing.JLabel();
        donationRecieved = new javax.swing.JLabel();
        donation = new javax.swing.JTextField();
        like = new javax.swing.JButton();
        donateBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        commentSec = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        comment = new javax.swing.JTextField();
        commentBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
                        .addGap(19, 19, 19)
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

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, -1));

        jLabel4.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Comments section");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 230, 40));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pfp (1).png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, -1));

        name.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setText("name:");
        jPanel3.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        username.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("name:");
        jPanel3.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        content.setEditable(false);
        content.setColumns(20);
        content.setRows(5);
        jScrollPane2.setViewportView(content);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 500, -1));

        donationGoal.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        donationGoal.setForeground(new java.awt.Color(255, 255, 255));
        donationGoal.setText("goal");
        jPanel3.add(donationGoal, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 250, -1));

        donationRecieved.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        donationRecieved.setForeground(new java.awt.Color(255, 255, 255));
        donationRecieved.setText("donation");
        jPanel3.add(donationRecieved, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 250, -1));
        jPanel3.add(donation, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 250, -1));

        like.setText("Like");
        like.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                likeActionPerformed(evt);
            }
        });
        jPanel3.add(like, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 60, -1));

        donateBtn.setText("Donate");
        donateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donateBtnActionPerformed(evt);
            }
        });
        jPanel3.add(donateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, 80, -1));

        commentSec.setEditable(false);
        commentSec.setColumns(20);
        commentSec.setRows(5);
        jScrollPane1.setViewportView(commentSec);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 790, 440));

        jLabel5.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Comment Section:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, 50));
        jPanel3.add(comment, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 683, 790, 40));

        commentBtn.setText("Comment");
        commentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentBtnActionPerformed(evt);
            }
        });
        jPanel3.add(commentBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 740, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 990, 810));

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

    private void likeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_likeActionPerformed
        handleLikeAction();
    }//GEN-LAST:event_likeActionPerformed

    private void donateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donateBtnActionPerformed
        handleDonationAction();
    }//GEN-LAST:event_donateBtnActionPerformed

    private void commentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentBtnActionPerformed
        submitComment();
    }//GEN-LAST:event_commentBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Comment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comment(Dashboard.getSelectedPostIndex()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField comment;
    private javax.swing.JButton commentBtn;
    private javax.swing.JTextArea commentSec;
    private javax.swing.JTextArea content;
    private static javax.swing.JButton donateBtn;
    private static javax.swing.JTextField donation;
    private static javax.swing.JLabel donationGoal;
    private static javax.swing.JLabel donationRecieved;
    private javax.swing.JLabel goToAboutUs;
    private javax.swing.JLabel goToActivityLog;
    private javax.swing.JLabel goToDashboard;
    private javax.swing.JLabel goToDonateToUs;
    private javax.swing.JLabel goToSiteTraffic;
    private javax.swing.JLabel goToUserInfo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JButton like;
    private javax.swing.JLabel logout;
    private static javax.swing.JLabel name;
    private static javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
