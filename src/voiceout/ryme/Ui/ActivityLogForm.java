package voiceout.ryme.Ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import voiceout.ryme.Helper.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import javax.swing.ImageIcon;

public class ActivityLogForm extends javax.swing.JFrame {

    private int postId1, postId2, postId3, postId4;

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ActivityLogForm() {
        conn = DBConnection.getConnection();
addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Dashboard dashboard = new Dashboard();
                dashboard.show();
                dispose();
            }
        });
        initComponents();
        refreshActivityLog();
        loadUserPosts();
String absolutePath = "/home/ryme/All/Github/VoiceOutSystem/src/images/icon.png";
        ImageIcon icon = new ImageIcon(absolutePath);
                setIconImage(icon.getImage());

        goToDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose();
            }
        });
lgout.addMouseListener(new java.awt.event.MouseAdapter() {
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
        });
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
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

    private void deletePost(int postId) {
        int userId = CurrentSession.getInstance().getUserId();

        try {
            String archive = "INSERT INTO archived_posts (post_id, user_id, content, donation_goal, donation_received, likes, created_at) "
                    + "SELECT post_id, user_id, content, donation_goal, donation_received, likes, created_at "
                    + "FROM user_post WHERE post_id = ? AND user_id = ?";
            
            String deleteInActive = "DELETE FROM user_post WHERE post_id = ? AND user_id = ?";

            pst = conn.prepareStatement(archive);

            pst.setInt(1, postId);
            pst.setInt(2, userId);
            pst.executeUpdate();
            pst = conn.prepareStatement(deleteInActive);
            pst.setInt(1, postId);
            pst.setInt(2, userId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Post deleted and archived!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            refreshActivityLog();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void loadUserPosts() {
        int userId = CurrentSession.getInstance().getUserId();

        String query = "SELECT post_id, content, donation_goal, donation_received, likes, created_at FROM user_post "
                + "WHERE user_id = ? ORDER BY RAND()";

        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, userId);
            rs = pst.executeQuery();

            int index = 1;
            while (rs.next()) {
                int postId = rs.getInt("post_id");
                String content = rs.getString("content");
                double donationGoal = rs.getDouble("donation_goal");
                double donationReceived = rs.getDouble("donation_received");
                int likes = rs.getInt("likes");
                Timestamp createdAt = rs.getTimestamp("created_at");

                switch (index) {
                    case 1:
                        content1.setText(content);
                        donationGoal1.setText("Goal: " + donationGoal);
                        donationRecieved1.setText("Received: " + donationReceived);
                        likes1.setText("Likes: " + likes);
                        createdAt1.setText("Posted on: " + createdAt);
                        postId1 = postId;
                        break;
                    case 2:
                        content2.setText(content);
                        donationGoal2.setText("Goal: " + donationGoal);
                        donationRecieved2.setText("Received: " + donationReceived);
                        likes2.setText("Likes: " + likes);
                        createdAt2.setText("Posted on: " + createdAt);
                        postId2 = postId;
                        break;
                    case 3:
                        content3.setText(content);
                        donationGoal3.setText("Goal: " + donationGoal);
                        donationRecieved3.setText("Received: " + donationReceived);
                        likes3.setText("Likes: " + likes);
                        createdAt3.setText("Posted on: " + createdAt);
                        postId3 = postId;
                        break;
                    case 4:
                        content4.setText(content);
                        donationGoal4.setText("Goal: " + donationGoal);
                        donationRecieved4.setText("Received: " + donationReceived);
                        likes4.setText("Likes: " + likes);
                        createdAt4.setText("Posted on: " + createdAt);
                        postId4 = postId;
                        break;
                }
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshActivityLog() {
        int userId = CurrentSession.getInstance().getUserId();
        int postCount = 0;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) AS postCount FROM user_post WHERE user_id = ?")) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    postCount = rs.getInt("postCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadUserPosts(); 
        disableExtraPostFields(postCount);
    }

    private void disableExtraPostFields(int postCount) {
        boolean hasPost4 = postCount >= 4;
        content4.setVisible(hasPost4);
        donationGoal4.setVisible(hasPost4);
        donationRecieved4.setVisible(hasPost4);
        likes4.setVisible(hasPost4);
        createdAt4.setVisible(hasPost4);
        commentSec4.setVisible(hasPost4);
        deleteBtn4.setVisible(hasPost4);

        boolean hasPost3 = postCount >= 3;
        content3.setVisible(hasPost3);
        donationGoal3.setVisible(hasPost3);
        donationRecieved3.setVisible(hasPost3);
        likes3.setVisible(hasPost3);
        createdAt3.setVisible(hasPost3);
        commentSec3.setVisible(hasPost3);
        deleteBtn3.setVisible(hasPost3);

        boolean hasPost2 = postCount >= 2;
        content2.setVisible(hasPost2);
        donationGoal2.setVisible(hasPost2);
        donationRecieved2.setVisible(hasPost2);
        likes2.setVisible(hasPost2);
        createdAt2.setVisible(hasPost2);
        commentSec2.setVisible(hasPost2);
        deleteBtn2.setVisible(hasPost2);

        boolean hasPost1 = postCount >= 1;
        content1.setVisible(hasPost1);
        donationGoal1.setVisible(hasPost1);
        donationRecieved1.setVisible(hasPost1);
        likes1.setVisible(hasPost1);
        createdAt1.setVisible(hasPost1);
        commentSec1.setVisible(hasPost1);
        deleteBtn1.setVisible(hasPost1);
    }

    private void viewComments(int postId) {
        Comment commentUI = new Comment(postId);
        commentUI.setVisible(true);
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
                    } else if (postId == postId4) {
                        likes4.setText("Likes: " + updatedLikes);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
        lgout = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        like1 = new javax.swing.JPanel();
        createdAt1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        content1 = new javax.swing.JTextArea();
        commentSec1 = new javax.swing.JButton();
        deleteBtn1 = new javax.swing.JButton();
        likes1 = new javax.swing.JLabel();
        donationGoal1 = new javax.swing.JLabel();
        donationRecieved1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        createdAt2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        content2 = new javax.swing.JTextArea();
        commentSec2 = new javax.swing.JButton();
        deleteBtn2 = new javax.swing.JButton();
        likes2 = new javax.swing.JLabel();
        donationGoal2 = new javax.swing.JLabel();
        donationRecieved2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        createdAt3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        content3 = new javax.swing.JTextArea();
        commentSec3 = new javax.swing.JButton();
        deleteBtn3 = new javax.swing.JButton();
        likes3 = new javax.swing.JLabel();
        donationGoal3 = new javax.swing.JLabel();
        donationRecieved3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        createdAt4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        content4 = new javax.swing.JTextArea();
        commentSec4 = new javax.swing.JButton();
        deleteBtn4 = new javax.swing.JButton();
        likes4 = new javax.swing.JLabel();
        donationGoal4 = new javax.swing.JLabel();
        donationRecieved4 = new javax.swing.JLabel();
        refreshBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Voice Out");
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

        lgout.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lgout.setForeground(new java.awt.Color(255, 255, 255));
        lgout.setText("Logout");

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
                                .addComponent(goToAboutUs)
                                .addComponent(lgout)))))
                .addContainerGap(36, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(lgout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 459, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(79, 79, 79))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, -1));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        like1.setBackground(new java.awt.Color(51, 51, 51));
        like1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createdAt1.setForeground(new java.awt.Color(255, 255, 255));
        createdAt1.setText("created_at");
        like1.add(createdAt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 9, -1, -1));

        content1.setEditable(false);
        content1.setBackground(new java.awt.Color(102, 102, 102));
        content1.setColumns(20);
        content1.setForeground(new java.awt.Color(255, 255, 255));
        content1.setRows(5);
        content1.setText("content\n");
        content1.setCaretColor(new java.awt.Color(102, 102, 102));
        content1.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(content1);

        like1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 41, 514, -1));

        commentSec1.setBackground(new java.awt.Color(102, 102, 102));
        commentSec1.setForeground(new java.awt.Color(255, 255, 255));
        commentSec1.setText("Comments");
        commentSec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentSec1ActionPerformed(evt);
            }
        });
        like1.add(commentSec1, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 144, -1, -1));

        deleteBtn1.setBackground(new java.awt.Color(102, 102, 102));
        deleteBtn1.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn1.setText("Delete");
        deleteBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtn1ActionPerformed(evt);
            }
        });
        like1.add(deleteBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(712, 6, -1, -1));

        likes1.setForeground(new java.awt.Color(255, 255, 255));
        likes1.setText("likes");
        like1.add(likes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 130, -1));

        donationGoal1.setForeground(new java.awt.Color(255, 255, 255));
        donationGoal1.setText("doantion goal");
        like1.add(donationGoal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 130, 20));

        donationRecieved1.setForeground(new java.awt.Color(255, 255, 255));
        donationRecieved1.setText("donation recieved");
        like1.add(donationRecieved1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 130, 20));

        jPanel3.add(like1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 790, 180));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createdAt2.setForeground(new java.awt.Color(255, 255, 255));
        createdAt2.setText("created_at");
        jPanel5.add(createdAt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, -1, -1));

        content2.setEditable(false);
        content2.setBackground(new java.awt.Color(102, 102, 102));
        content2.setColumns(20);
        content2.setForeground(new java.awt.Color(255, 255, 255));
        content2.setRows(5);
        content2.setText("content\n");
        content2.setCaretColor(new java.awt.Color(102, 102, 102));
        content2.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jScrollPane2.setViewportView(content2);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 35, 514, -1));

        commentSec2.setBackground(new java.awt.Color(102, 102, 102));
        commentSec2.setForeground(new java.awt.Color(255, 255, 255));
        commentSec2.setText("Comments");
        commentSec2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentSec2ActionPerformed(evt);
            }
        });
        jPanel5.add(commentSec2, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 138, -1, -1));

        deleteBtn2.setBackground(new java.awt.Color(102, 102, 102));
        deleteBtn2.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn2.setText("Delete");
        deleteBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtn2ActionPerformed(evt);
            }
        });
        jPanel5.add(deleteBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, 20));

        likes2.setForeground(new java.awt.Color(255, 255, 255));
        likes2.setText("likes");
        jPanel5.add(likes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 130, 20));

        donationGoal2.setForeground(new java.awt.Color(255, 255, 255));
        donationGoal2.setText("doantion goal");
        jPanel5.add(donationGoal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 130, 20));

        donationRecieved2.setForeground(new java.awt.Color(255, 255, 255));
        donationRecieved2.setText("donation recieved");
        jPanel5.add(donationRecieved2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 130, 20));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 790, 170));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createdAt3.setForeground(new java.awt.Color(255, 255, 255));
        createdAt3.setText("created_at");
        jPanel6.add(createdAt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, -1, -1));

        content3.setEditable(false);
        content3.setBackground(new java.awt.Color(102, 102, 102));
        content3.setColumns(20);
        content3.setForeground(new java.awt.Color(255, 255, 255));
        content3.setRows(5);
        content3.setText("content\n");
        content3.setCaretColor(new java.awt.Color(102, 102, 102));
        content3.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jScrollPane3.setViewportView(content3);

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 35, 514, -1));

        commentSec3.setBackground(new java.awt.Color(102, 102, 102));
        commentSec3.setForeground(new java.awt.Color(255, 255, 255));
        commentSec3.setText("Comments");
        commentSec3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentSec3ActionPerformed(evt);
            }
        });
        jPanel6.add(commentSec3, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 138, -1, -1));

        deleteBtn3.setBackground(new java.awt.Color(102, 102, 102));
        deleteBtn3.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn3.setText("Delete");
        deleteBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtn3ActionPerformed(evt);
            }
        });
        jPanel6.add(deleteBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, -1));

        likes3.setForeground(new java.awt.Color(255, 255, 255));
        likes3.setText("likes");
        jPanel6.add(likes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 130, 20));

        donationGoal3.setForeground(new java.awt.Color(255, 255, 255));
        donationGoal3.setText("doantion goal");
        jPanel6.add(donationGoal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 130, 20));

        donationRecieved3.setForeground(new java.awt.Color(255, 255, 255));
        donationRecieved3.setText("donation recieved");
        jPanel6.add(donationRecieved3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 130, 20));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 790, 170));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createdAt4.setForeground(new java.awt.Color(255, 255, 255));
        createdAt4.setText("created_at");
        jPanel7.add(createdAt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, -1, -1));

        content4.setEditable(false);
        content4.setBackground(new java.awt.Color(102, 102, 102));
        content4.setColumns(20);
        content4.setForeground(new java.awt.Color(255, 255, 255));
        content4.setRows(5);
        content4.setText("content\n");
        content4.setCaretColor(new java.awt.Color(102, 102, 102));
        content4.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jScrollPane4.setViewportView(content4);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 35, 514, -1));

        commentSec4.setBackground(new java.awt.Color(102, 102, 102));
        commentSec4.setForeground(new java.awt.Color(255, 255, 255));
        commentSec4.setText("Comments");
        commentSec4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentSec4ActionPerformed(evt);
            }
        });
        jPanel7.add(commentSec4, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 138, -1, -1));

        deleteBtn4.setBackground(new java.awt.Color(102, 102, 102));
        deleteBtn4.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn4.setText("Delete");
        deleteBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtn4ActionPerformed(evt);
            }
        });
        jPanel7.add(deleteBtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, -1));

        likes4.setForeground(new java.awt.Color(255, 255, 255));
        likes4.setText("likes");
        jPanel7.add(likes4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 130, 20));

        donationGoal4.setForeground(new java.awt.Color(255, 255, 255));
        donationGoal4.setText("doantion goal");
        jPanel7.add(donationGoal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 130, 20));

        donationRecieved4.setForeground(new java.awt.Color(255, 255, 255));
        donationRecieved4.setText("donation recieved");
        jPanel7.add(donationRecieved4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 130, 20));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 630, 790, 170));

        refreshBtn.setBackground(new java.awt.Color(102, 102, 102));
        refreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshBtn.setText("Load New Batch");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });
        jPanel3.add(refreshBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 960, 820));

        jLabel4.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("User Post:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, 73));

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

    private void deleteBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtn1ActionPerformed
        deletePost(postId1);
    }//GEN-LAST:event_deleteBtn1ActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        refreshActivityLog();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void commentSec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentSec1ActionPerformed
        viewComments(postId1);
    }//GEN-LAST:event_commentSec1ActionPerformed

    private void commentSec2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentSec2ActionPerformed
        viewComments(postId2);
    }//GEN-LAST:event_commentSec2ActionPerformed

    private void commentSec3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentSec3ActionPerformed
        viewComments(postId3);
    }//GEN-LAST:event_commentSec3ActionPerformed

    private void commentSec4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentSec4ActionPerformed
        viewComments(postId4);
    }//GEN-LAST:event_commentSec4ActionPerformed

    private void deleteBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtn2ActionPerformed
        deletePost(postId2);
    }//GEN-LAST:event_deleteBtn2ActionPerformed

    private void deleteBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtn3ActionPerformed
        deletePost(postId3);
    }//GEN-LAST:event_deleteBtn3ActionPerformed

    private void deleteBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtn4ActionPerformed
        deletePost(postId4);
    }//GEN-LAST:event_deleteBtn4ActionPerformed

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
    private javax.swing.JButton commentSec1;
    private javax.swing.JButton commentSec2;
    private javax.swing.JButton commentSec3;
    private javax.swing.JButton commentSec4;
    private javax.swing.JTextArea content1;
    private javax.swing.JTextArea content2;
    private javax.swing.JTextArea content3;
    private javax.swing.JTextArea content4;
    private javax.swing.JLabel createdAt1;
    private javax.swing.JLabel createdAt2;
    private javax.swing.JLabel createdAt3;
    private javax.swing.JLabel createdAt4;
    private javax.swing.JButton deleteBtn1;
    private javax.swing.JButton deleteBtn2;
    private javax.swing.JButton deleteBtn3;
    private javax.swing.JButton deleteBtn4;
    private javax.swing.JLabel donationGoal1;
    private javax.swing.JLabel donationGoal2;
    private javax.swing.JLabel donationGoal3;
    private javax.swing.JLabel donationGoal4;
    private javax.swing.JLabel donationRecieved1;
    private javax.swing.JLabel donationRecieved2;
    private javax.swing.JLabel donationRecieved3;
    private javax.swing.JLabel donationRecieved4;
    private javax.swing.JLabel goToAboutUs;
    private javax.swing.JLabel goToActivtyLog;
    private javax.swing.JLabel goToDashboard;
    private javax.swing.JLabel goToDonateToUs;
    private javax.swing.JLabel goToSiteTraffic;
    private javax.swing.JLabel goToUserInfo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lgout;
    private javax.swing.JPanel like1;
    private javax.swing.JLabel likes1;
    private javax.swing.JLabel likes2;
    private javax.swing.JLabel likes3;
    private javax.swing.JLabel likes4;
    private javax.swing.JLabel logout;
    private javax.swing.JButton refreshBtn;
    // End of variables declaration//GEN-END:variables
}
