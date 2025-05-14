package voiceout.ryme.Ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import voiceout.ryme.Helper.*;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Dashboard extends javax.swing.JFrame {

    private int postId1, postId2, postId3;

    public Dashboard() {

        initComponents();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    CurrentSession currentSession = CurrentSession.getInstance();
                    currentSession.clearSession();
                    LoginForm loginForm = new LoginForm();
                    loginForm.show();
                    dispose();

                }
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
        donationGoalPost.addKeyListener(preventLeadingSpaces);

        KeyAdapter preventLettersAndLimit2 = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                JTextField field = (JTextField) e.getSource();
                char c = e.getKeyChar();

                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        };
        donationGoalPost.addKeyListener(preventLettersAndLimit2);

        refreshActionPerformed(new java.awt.event.ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Refresh"));
        int userId = (CurrentSession.getInstance() != null) ? CurrentSession.getInstance().getUserId() : -1;

        if (userId <= 0) {
            JOptionPane.showMessageDialog(null, "Error: Invalid user session. Please log in again.", "Session Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
    }

    public int getPostCount() {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM user_post"); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updatePostComponents(int userId, int postLimit) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(
                "SELECT up.post_id, u.name, up.content, up.donation_goal, up.donation_received, up.likes "
                + "FROM user_post up "
                + "JOIN users u ON up.user_id = u.user_id "
                + "ORDER BY RAND() LIMIT ?")) {

            pstmt.setInt(1, postLimit);
            try (ResultSet rs = pstmt.executeQuery()) {
                int index = 1;
                while (rs.next()) {
                    int postId = rs.getInt("post_id");
                    String name = rs.getString("name");
                    String content = rs.getString("content");
                    double donationGoal = rs.getDouble("donation_goal");
                    double donationReceived = rs.getDouble("donation_received");
                    int likess = rs.getInt("likes");
                    String likes = String.valueOf(likess);
                    
                    switch (index) {
                        case 1:
                            content1.setText(content);
                            name1.setText(name);
                            donationGoal1.setText("Goal: $" + donationGoal);
                            donationRecieved1.setText("Received: $" + donationReceived);
                            likes1.setText(likes);
                            postId1 = postId;
                            break;
                        case 2:
                            content2.setText(content);
                            name2.setText(name);
                            donationGoal2.setText("Goal: $" + donationGoal);
                            donationRecieved2.setText("Received: $" + donationReceived);
                            likes2.setText(likes);
                            postId2 = postId;
                            break;
                        case 3:
                            content3.setText(content);
                            name3.setText(name);
                            donationGoal3.setText("Goal: $" + donationGoal);
                            donationRecieved3.setText("Received: $" + donationReceived);
                            likes3.setText(likes);
                            postId3 = postId;
                            break;
                    }
                    index++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleLikeAction(int postId, int userId) {
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

            JOptionPane.showMessageDialog(null, "Post liked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            updateLikesDisplay(postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateLikesDisplay(int postId) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT likes FROM user_post WHERE post_id = ?")) {
            pstmt.setInt(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int updatedLikes = rs.getInt("likes");

                    if (postId == postId1) {
                        likes1.setText("Likes: " + updatedLikes);
                    } else if (postId == postId2) {
                        likes2.setText("Likes: " + updatedLikes);
                    } else if (postId == postId3) {
                        likes3.setText("Likes: " + updatedLikes);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getPostIdForComponent(int postIndex) {
        switch (postIndex) {
            case 1:
                return postId1;
            case 2:
                return postId2;
            case 3:
                return postId3;
            default:
                return -1;
        }
    }

    private JTextField getDonationFieldForComponent(int postIndex) {
        switch (postIndex) {
            case 1:
                return donation1;
            case 2:
                return donation2;
            case 3:
                return donation3;
            default:
                return null;
        }
    }

    private JLabel getDonationReceivedLabelForComponent(int postIndex) {
        switch (postIndex) {
            case 1:
                return donationRecieved1;
            case 2:
                return donationRecieved2;
            case 3:
                return donationRecieved3;
            default:
                return null;
        }
    }

    private void handleDonationAction(int postIndex) {
        int postId = getPostIdForComponent(postIndex);
        JTextField donationField = getDonationFieldForComponent(postIndex);
        JLabel donationReceivedLabel = getDonationReceivedLabelForComponent(postIndex);

        if (postId <= 0) {
            JOptionPane.showMessageDialog(null, "Error: No valid post selected for donation.", "Donation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double donationAmount;
        try {
            donationAmount = Double.parseDouble(donationField.getText().trim());
            if (donationAmount <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid donation amount. Please enter a positive value.", "Donation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid donation format. Please enter a valid number.", "Donation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double updatedReceived = updateDonationReceived(postId, donationAmount);
        donationReceivedLabel.setText("Received: " + updatedReceived);
        JOptionPane.showMessageDialog(null, "Thank you for your donation!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public double updateDonationReceived(int postId, double amount) {
        double updatedReceived = 0.0;
        try (Connection conn = DBConnection.getConnection()) {
            // ✅ Retrieve current donation_received amount
            try (PreparedStatement getStmt = conn.prepareStatement("SELECT donation_received FROM user_post WHERE post_id = ?")) {
                getStmt.setInt(1, postId);
                try (ResultSet rs = getStmt.executeQuery()) {
                    if (rs.next()) {
                        updatedReceived = rs.getDouble("donation_received") + amount;
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Post ID not found.", "Database Error", JOptionPane.ERROR_MESSAGE);
                        return 0.0;
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
            JOptionPane.showMessageDialog(null, "Database error while updating donation.", "Donation Error", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }

        return updatedReceived;
    }

    public boolean hasUserLikedPost(int userId, int postId) {
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

    public void likePost(int userId, int postId) {
        try (Connection conn = DBConnection.getConnection()) {
            if (hasUserLikedPost(userId, postId)) {
                JOptionPane.showMessageDialog(null, "You have already liked this post!", "Like Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

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

    private void openCommentUI(int postIndex) {
        int postId = getPostIdForComponent(postIndex);
        if (postId <= 0) {
            JOptionPane.showMessageDialog(null, "Error: Invalid post.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Comment commentUI = new Comment(postId);
        this.setVisible(false);
        commentUI.setVisible(true);

    }
    private static int selectedPostIndex = -1;

    public static void setSelectedPostIndex(int postIndex) {
        selectedPostIndex = postIndex;
    }

    public static int getSelectedPostIndex() {
        return selectedPostIndex;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        post_area = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        name1 = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        donation1 = new javax.swing.JTextField();
        donateBtn1 = new javax.swing.JButton();
        commentSec1 = new javax.swing.JButton();
        like1 = new javax.swing.JButton();
        donationRecieved1 = new javax.swing.JLabel();
        donationGoal1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        content1 = new javax.swing.JTextArea();
        likes1 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        username2 = new javax.swing.JLabel();
        donation2 = new javax.swing.JTextField();
        donateBtn2 = new javax.swing.JButton();
        commentSec2 = new javax.swing.JButton();
        like2 = new javax.swing.JButton();
        name2 = new javax.swing.JLabel();
        donationRecieved2 = new javax.swing.JLabel();
        donationGoal2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        content2 = new javax.swing.JTextArea();
        likes2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        name3 = new javax.swing.JLabel();
        username3 = new javax.swing.JLabel();
        donation3 = new javax.swing.JTextField();
        donateBtn3 = new javax.swing.JButton();
        commentSec3 = new javax.swing.JButton();
        like3 = new javax.swing.JButton();
        donationRecieved3 = new javax.swing.JLabel();
        donationGoal3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        content3 = new javax.swing.JTextArea();
        likes3 = new javax.swing.JLabel();
        name4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentPostFld = new javax.swing.JTextArea();
        donationGoalPost = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
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
        name5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(11, 11, 69));
        jPanel2.setPreferredSize(new java.awt.Dimension(1400, 950));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        post_area.setBackground(new java.awt.Color(102, 102, 102));
        post_area.setForeground(new java.awt.Color(255, 255, 255));
        post_area.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pfp (1).png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        name1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        name1.setForeground(new java.awt.Color(255, 255, 255));
        name1.setText("name:");
        jPanel4.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        username1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        username1.setForeground(new java.awt.Color(255, 255, 255));
        username1.setText("name:");
        jPanel4.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));
        jPanel4.add(donation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 250, -1));

        donateBtn1.setText("Donate");
        donateBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donateBtn1ActionPerformed(evt);
            }
        });
        jPanel4.add(donateBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 80, -1));

        commentSec1.setText("Comment");
        commentSec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentSec1ActionPerformed(evt);
            }
        });
        jPanel4.add(commentSec1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, -1, -1));

        like1.setText("Like");
        like1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                like1ActionPerformed(evt);
            }
        });
        jPanel4.add(like1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 60, -1));

        donationRecieved1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        donationRecieved1.setForeground(new java.awt.Color(255, 255, 255));
        donationRecieved1.setText("donation");
        jPanel4.add(donationRecieved1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 250, -1));

        donationGoal1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        donationGoal1.setForeground(new java.awt.Color(255, 255, 255));
        donationGoal1.setText("goal");
        jPanel4.add(donationGoal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 250, -1));

        content1.setEditable(false);
        content1.setColumns(20);
        content1.setRows(5);
        jScrollPane2.setViewportView(content1);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 500, -1));

        likes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/likeIcon.png"))); // NOI18N
        jPanel4.add(likes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, -1, -1));

        post_area.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 900, 190));

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        post_area.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, -1, -1));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pfp (1).png"))); // NOI18N
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        username2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        username2.setForeground(new java.awt.Color(255, 255, 255));
        username2.setText("name:");
        jPanel7.add(username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));
        jPanel7.add(donation2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 250, -1));

        donateBtn2.setText("Donate");
        donateBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donateBtn2ActionPerformed(evt);
            }
        });
        jPanel7.add(donateBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 80, -1));

        commentSec2.setText("Comment");
        commentSec2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentSec2ActionPerformed(evt);
            }
        });
        jPanel7.add(commentSec2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, -1, -1));

        like2.setText("Like");
        like2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                like2ActionPerformed(evt);
            }
        });
        jPanel7.add(like2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 60, -1));

        name2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        name2.setForeground(new java.awt.Color(255, 255, 255));
        name2.setText("name:");
        jPanel7.add(name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        donationRecieved2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        donationRecieved2.setForeground(new java.awt.Color(255, 255, 255));
        donationRecieved2.setText("donation");
        jPanel7.add(donationRecieved2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 250, -1));

        donationGoal2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        donationGoal2.setForeground(new java.awt.Color(255, 255, 255));
        donationGoal2.setText("goal");
        jPanel7.add(donationGoal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 250, -1));

        content2.setEditable(false);
        content2.setColumns(20);
        content2.setRows(5);
        jScrollPane4.setViewportView(content2);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 500, -1));

        likes2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/likeIcon.png"))); // NOI18N
        likes2.setText("like");
        jPanel7.add(likes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 50, -1));

        post_area.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 900, 190));

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pfp (1).png"))); // NOI18N
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        name3.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        name3.setForeground(new java.awt.Color(255, 255, 255));
        name3.setText("name:");
        jPanel8.add(name3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        username3.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        username3.setForeground(new java.awt.Color(255, 255, 255));
        username3.setText("name:");
        jPanel8.add(username3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));
        jPanel8.add(donation3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 250, -1));

        donateBtn3.setText("Donate");
        donateBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donateBtn3ActionPerformed(evt);
            }
        });
        jPanel8.add(donateBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 80, -1));

        commentSec3.setText("Comment");
        commentSec3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentSec3ActionPerformed(evt);
            }
        });
        jPanel8.add(commentSec3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, -1, -1));

        like3.setText("Like");
        like3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                like3ActionPerformed(evt);
            }
        });
        jPanel8.add(like3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 60, -1));

        donationRecieved3.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        donationRecieved3.setForeground(new java.awt.Color(255, 255, 255));
        donationRecieved3.setText("donation");
        jPanel8.add(donationRecieved3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 250, -1));

        donationGoal3.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        donationGoal3.setForeground(new java.awt.Color(255, 255, 255));
        donationGoal3.setText("goal");
        jPanel8.add(donationGoal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 250, -1));

        content3.setEditable(false);
        content3.setColumns(20);
        content3.setRows(5);
        jScrollPane3.setViewportView(content3);

        jPanel8.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 500, -1));

        likes3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/likeIcon.png"))); // NOI18N
        likes3.setText("like");
        jPanel8.add(likes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 50, -1));

        post_area.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 900, 190));

        jPanel2.add(post_area, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 990, 710));

        name4.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        name4.setForeground(new java.awt.Color(255, 255, 255));
        name4.setText("Donation Goal");
        jPanel2.add(name4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 250, 60));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        contentPostFld.setBackground(new java.awt.Color(102, 102, 102));
        contentPostFld.setColumns(20);
        contentPostFld.setForeground(new java.awt.Color(255, 255, 255));
        contentPostFld.setRows(5);
        jScrollPane1.setViewportView(contentPostFld);

        donationGoalPost.setBackground(new java.awt.Color(102, 102, 102));
        donationGoalPost.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Post");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(donationGoalPost, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(donationGoalPost, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 990, 160));

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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(logout)
                            .addGap(87, 87, 87)
                            .addComponent(goToDashboard)
                            .addGap(10, 10, 10))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(goToActivityLog)
                            .addComponent(goToUserInfo)
                            .addComponent(jLabel3)
                            .addComponent(goToSiteTraffic)
                            .addComponent(goToDonateToUs)
                            .addComponent(goToAboutUs))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logout)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
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

        name5.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        name5.setForeground(new java.awt.Color(255, 255, 255));
        name5.setText("Post Petition Content");
        jPanel2.add(name5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 390, 60));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CurrentSession session = CurrentSession.getInstance();
        int userId = session.getUserId();
        String name = session.getName();
        String content = contentPostFld.getText();
        String donationGoalStr = donationGoalPost.getText();

        if (content.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Post content cannot be empty!", "Err   or", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double donationGoal = 0.0;
        try {
            if (!donationGoalStr.isEmpty()) {
                donationGoal = Double.parseDouble(donationGoalStr);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid donation goal! Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        contentPostFld.setText("");
        donationGoalPost.setText("");
        refreshActionPerformed(evt);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/voiceout", "root", "")) {
            String query = "INSERT INTO user_post (user_id, name, content, donation_goal, donation_received, likes) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, userId);
                pst.setString(2, name);
                pst.setString(3, content);
                pst.setDouble(4, donationGoal);
                pst.setDouble(5, 0.0);
                pst.setInt(6, 0);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Post added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding post! Please check the database connection.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        int userId = (CurrentSession.getInstance() != null) ? CurrentSession.getInstance().getUserId() : -1;

        content1.setText("");
        username1.setText("");
        name1.setText("");
        donationRecieved1.setText("");

        content2.setText("");
        username2.setText("");
        name2.setText("");
        donationRecieved2.setText("");

        content3.setText("");
        username3.setText("");
        name3.setText("");
        donationRecieved3.setText("");

        int postCount = getPostCount();

        if (postCount == 0) {
            like1.setEnabled(false);
            like2.setEnabled(false);
            like3.setEnabled(false);

            donateBtn1.setEnabled(false);
            donateBtn2.setEnabled(false);
            donateBtn3.setEnabled(false);

            commentSec1.setEnabled(false);
            commentSec2.setEnabled(false);
            commentSec3.setEnabled(false);

            content1.setText("No posts available.");
            content2.setText("No posts available.");
            content3.setText("No posts available.");

        } else {
            like1.setEnabled(postCount >= 1);
            like2.setEnabled(postCount >= 2);
            like3.setEnabled(postCount >= 3);

            donateBtn1.setEnabled(postCount >= 1);
            donateBtn2.setEnabled(postCount >= 2);
            donateBtn3.setEnabled(postCount >= 3);

            commentSec1.setEnabled(postCount >= 1);
            commentSec2.setEnabled(postCount >= 2);
            commentSec3.setEnabled(postCount >= 3);

            updatePostComponents(userId, Math.min(postCount, 3));
        }

    }//GEN-LAST:event_refreshActionPerformed

    private void like3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_like3ActionPerformed
        handleLikeAction(postId3, CurrentSession.getInstance().getUserId());
    }//GEN-LAST:event_like3ActionPerformed

    private void donateBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donateBtn3ActionPerformed
        handleDonationAction(3);
        donation3.setText("");
    }//GEN-LAST:event_donateBtn3ActionPerformed

    private void like1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_like1ActionPerformed
        handleLikeAction(postId1, CurrentSession.getInstance().getUserId());

    }//GEN-LAST:event_like1ActionPerformed

    private void like2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_like2ActionPerformed
        handleLikeAction(postId2, CurrentSession.getInstance().getUserId());
    }//GEN-LAST:event_like2ActionPerformed

    private void donateBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donateBtn2ActionPerformed
        handleDonationAction(2);
        donation2.setText("");

    }//GEN-LAST:event_donateBtn2ActionPerformed

    private void donateBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donateBtn1ActionPerformed
        handleDonationAction(1);
        donation1.setText("");

    }//GEN-LAST:event_donateBtn1ActionPerformed

    private void commentSec3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentSec3ActionPerformed
        openCommentUI(3);
        setSelectedPostIndex(3);
    }//GEN-LAST:event_commentSec3ActionPerformed

    private void commentSec2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentSec2ActionPerformed
        openCommentUI(2);
        setSelectedPostIndex(2);
    }//GEN-LAST:event_commentSec2ActionPerformed

    private void commentSec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentSec1ActionPerformed
        openCommentUI(1);
        setSelectedPostIndex(1);
    }//GEN-LAST:event_commentSec1ActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton commentSec1;
    private static javax.swing.JButton commentSec2;
    private static javax.swing.JButton commentSec3;
    private javax.swing.JTextArea content1;
    private javax.swing.JTextArea content2;
    private javax.swing.JTextArea content3;
    private javax.swing.JTextArea contentPostFld;
    private static javax.swing.JButton donateBtn1;
    private static javax.swing.JButton donateBtn2;
    private static javax.swing.JButton donateBtn3;
    private static javax.swing.JTextField donation1;
    private static javax.swing.JTextField donation2;
    private static javax.swing.JTextField donation3;
    private static javax.swing.JLabel donationGoal1;
    private static javax.swing.JLabel donationGoal2;
    private static javax.swing.JLabel donationGoal3;
    private javax.swing.JTextField donationGoalPost;
    private static javax.swing.JLabel donationRecieved1;
    private static javax.swing.JLabel donationRecieved2;
    private static javax.swing.JLabel donationRecieved3;
    private javax.swing.JLabel goToAboutUs;
    private javax.swing.JLabel goToActivityLog;
    private javax.swing.JLabel goToDashboard;
    private javax.swing.JLabel goToDonateToUs;
    private javax.swing.JLabel goToSiteTraffic;
    private javax.swing.JLabel goToUserInfo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private static javax.swing.JButton like1;
    private static javax.swing.JButton like2;
    private static javax.swing.JButton like3;
    private javax.swing.JLabel likes1;
    private javax.swing.JLabel likes2;
    private javax.swing.JLabel likes3;
    private javax.swing.JLabel logout;
    private static javax.swing.JLabel name1;
    private static javax.swing.JLabel name2;
    private static javax.swing.JLabel name3;
    private static javax.swing.JLabel name4;
    private static javax.swing.JLabel name5;
    private static javax.swing.JPanel post_area;
    private javax.swing.JButton refresh;
    private static javax.swing.JLabel username1;
    private static javax.swing.JLabel username2;
    private static javax.swing.JLabel username3;
    // End of variables declaration//GEN-END:variables
}
