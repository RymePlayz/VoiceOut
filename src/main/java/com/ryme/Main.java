//package com.ryme;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Timestamp;
import java.sql.Statement;

public class Main {

    // database stuff
    private static final String URL = "jdbc:mysql://localhost:3306/voiceout_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // to not let other ui be shown in mainui
    private JFrame childFrame;
    // have a current user varible to set user for other components
    private String currentUser;
    // for closing purposes only
    private JFrame mainFrame;
    public static int currentUserId;

    public static void main(String[] args) {
        // error handler for swings/ui's
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // call register ui to be shown first change to showLoginUI(); to show login
        // first
        SwingUtilities.invokeLater(() -> new Main().showRegisterUI());
    }

    public void showRegisterUI() {
        JFrame registerFrame = new JFrame("Register");
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setSize(400, 300);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setResizable(false);

        JPanel backgroundPanel = new BackgroundPanel(loadBackgroundImage());
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Register", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 105, 180));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        backgroundPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // ePhilID label and text field
        JLabel ephilIdLabel = new JLabel("ePhilID:");
        ephilIdLabel.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(ephilIdLabel, gbc);

        JTextField ephilIdField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(ephilIdField, gbc);

        // Password label and field
        JLabel passLabel = new JLabel("New Password:");
        passLabel.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(passField, gbc);

        // Login button with simplified logic
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(255, 255, 255, 180));
        registerButton.setOpaque(true);
        registerButton.addActionListener(e -> {
            StringBuilder errors = new StringBuilder();

            // Retrieve and trim field values
            String ephilId = ephilIdField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            // Validate ePhilID field: must not be empty.
            if (ephilId.isEmpty()) {
                errors.append("Please fill the ePhilID field.\n");
            }
            // Validate password field: must have at least 6 characters.
            if (password.length() < 6) {
                errors.append("Password must be at least 6 characters long.\n");
            }

            // Check if there were any errors.
            if (errors.length() > 0) {
                // Show all errors in a popup.
                JOptionPane.showMessageDialog(null,
                        errors.toString(), "Registration Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // If there are no errors, insert the new user into the database.
                try (Connection conn = getConnection()) {
                    // Using the columns (ePhilID, password) only,
                    // letting the database assign the current timestamp in created_at.
                    String sql = "INSERT INTO users(ePhilID, password) VALUES (?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, ephilId);
                        pstmt.setString(2, password);

                        int rowsAffected = pstmt.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null,
                                    "User registered successfully!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Registration failed.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    // Display any SQL errors in a popup.
                    JOptionPane.showMessageDialog(null,
                            "Database error: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundPanel.add(registerButton, gbc);

        JButton loginButton = new JButton("Go to Login");
        loginButton.setBackground(new Color(255, 255, 255, 180));
        loginButton.setOpaque(true);
        loginButton.addActionListener(e -> {
            registerFrame.dispose();
            showLoginUI();
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundPanel.add(loginButton, gbc);

        registerFrame.setContentPane(backgroundPanel);
        registerFrame.setVisible(true);

    }

    public void showLoginUI() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setResizable(false);

        JPanel backgroundPanel = new BackgroundPanel(loadBackgroundImage());
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 105, 180));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        backgroundPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // ePhilID label and text field
        JLabel ephilIdLabel = new JLabel("ePhilID:");
        ephilIdLabel.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(ephilIdLabel, gbc);

        JTextField ephilIdField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(ephilIdField, gbc);

        // Password label and field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(passField, gbc);

        // Login button with simplified logic
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(255, 255, 255, 180));
        loginButton.setOpaque(true);
        loginButton.addActionListener(e -> {
            StringBuilder errors = new StringBuilder();

            // Retrieve and trim input values
            String ephilId = ephilIdField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            // Validate the ePhilID field: must not be empty.
            if (ephilId.isEmpty()) {
                errors.append("Please fill the ePhilID field.\n");
            }
            // Validate the password: must have at least 6 characters.
            if (password.length() < 6) {
                errors.append("Password must be at least 6 characters long.\n");
            }

            // Display validation errors if any.
            if (errors.length() > 0) {
                JOptionPane.showMessageDialog(null,
                        errors.toString(),
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // If no errors, try to connect to the database and validate user login.
                try (Connection conn = getConnection()) {
                    String sql = "SELECT * FROM users WHERE ePhilID = ? AND password = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, ephilId);
                        pstmt.setString(2, password);

                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next()) {
                                // User found: login successful.
                                JOptionPane.showMessageDialog(null,
                                        "Login successful!",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);

                                // Set the global currentUser variable
                                currentUser = ephilId;

                                // Close the login frame and transition to main UI
                                loginFrame.dispose();
                                showMainUI();
                            } else {
                                // No matching user found.
                                JOptionPane.showMessageDialog(null,
                                        "User not found.",
                                        "Login Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    // Database connection error or SQL error.
                    JOptionPane.showMessageDialog(null,
                            "Database connection error: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundPanel.add(loginButton, gbc);

        // Register button with simplified logic
        JButton registerButton = new JButton("Go to Register");
        registerButton.setBackground(new Color(255, 255, 255, 180));
        registerButton.setOpaque(true);
        registerButton.addActionListener(e -> {
            loginFrame.dispose();
            showRegisterUI();
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundPanel.add(registerButton, gbc);

        loginFrame.setContentPane(backgroundPanel);
        loginFrame.setVisible(true);
    }

    public void showMainUI() {
        mainFrame = new JFrame("Main Application");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // ---------------- Left Panel ----------------
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(250, mainFrame.getHeight()));

        // Profile icon (profile.png is in the same directory as MainUI.class)
        ImageIcon profileIcon = new ImageIcon(getClass().getResource("profile.png"));
        Image profileImage = profileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel profileLabel = new JLabel(new ImageIcon(profileImage));
        profileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (childFrame == null || !childFrame.isVisible()) {
                    showProfileUI();
                }
            }
        });
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(profileLabel);
        leftPanel.add(Box.createVerticalStrut(30));

        // Activity Log Button
        JButton activityLogButton = new JButton("Activity Log");
        activityLogButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        activityLogButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        activityLogButton.addActionListener(e -> {
            if (childFrame == null || !childFrame.isVisible()) {
                showActivityLogUI();
            }
        });
        leftPanel.add(activityLogButton);
        leftPanel.add(Box.createVerticalStrut(20));

        // Post Petition Button
        JButton postPetitionButton = new JButton("Post Petition");
        postPetitionButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        postPetitionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        postPetitionButton.addActionListener(e -> {
            if (childFrame == null || !childFrame.isVisible()) {
                showPostPetitionUI();
            }
        });
        leftPanel.add(postPetitionButton);
        leftPanel.add(Box.createVerticalGlue());

        // --------------- Posts Container (Center) ---------------
        JPanel postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        postsContainer.setBackground(new Color(245, 245, 245));
        postsContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Remove any existing components in postsContainer.
        postsContainer.removeAll();

        // Query the database for posts.
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT id, user_id, content, donation_goal, donation_received, created_at, author FROM posts ORDER BY created_at DESC")) {

            ResultSet rs = stmt.executeQuery();
            boolean hasPosts = false;
            while (rs.next()) {
                hasPosts = true;
                int postId = rs.getInt("id");
                String content = rs.getString("content");
                double donationGoal = rs.getDouble("donation_goal");
                double donationReceived = rs.getDouble("donation_received");
                java.sql.Timestamp createdAtObj = rs.getTimestamp("created_at");
                String createdAt = createdAtObj.toString();
                String postedBy = rs.getString("author"); // Author's ePhilID

                // Since your schema doesn’t include votes, we default them to 0.
                int upvotes = 0;
                int downvotes = 0;
                int commentCount = getCommentCount(postId);

                postsContainer.add(createPostPanel(postId, postedBy, content, upvotes, downvotes, commentCount,
                        donationGoal, donationReceived, createdAt));
                postsContainer.add(Box.createVerticalStrut(10));
            }
            if (!hasPosts) {
                JLabel noPostsLabel = new JLabel("No posts yet. Please post a petition.");
                noPostsLabel.setForeground(Color.GRAY);
                noPostsLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
                noPostsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                postsContainer.add(noPostsLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame,
                    "Error fetching posts: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(postsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // ---------------- Footer Panel ----------------
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel randomNewsLabel = new JLabel("Breaking: Local petition sparks major civic engagement!");
        randomNewsLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        randomNewsLabel.setForeground(Color.DARK_GRAY);
        randomNewsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel footerCopyright = new JLabel("copyright© 2025 RYME. All rights reserved");
        footerCopyright.setFont(new Font("SansSerif", Font.ITALIC, 10));
        footerCopyright.setForeground(Color.GRAY);
        footerCopyright.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(randomNewsLabel);
        footerPanel.add(Box.createVerticalStrut(5));
        footerPanel.add(footerCopyright);

        // ---------------- Assemble the Main Panel ----------------
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    // Helper method to get comment count for a given post
    public int getCommentCount(int postId) {
        int count = 0;
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM comments WHERE post_id = ?")) {
            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    /**
     * Opens the User Profile UI for the currently logged-in user.
     * Displays basic user details fetched from the database and provides a Logout
     * button.
     * Ensures only one profile window can be opened at a time.
     */
    public void showProfileUI() {
        // If no user is currently logged in, don't open the profile UI.
        if (currentUser == null) {
            JOptionPane.showMessageDialog(null, "No user is logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If a profile window is already open, prevent opening another one.
        if (childFrame != null && childFrame.isVisible()) {
            return;
        }

        // Create a new profile window.
        childFrame = new JFrame("User Profile - " + currentUser);
        childFrame.setSize(350, 250);
        childFrame.setLocationRelativeTo(null); // Center on screen
        childFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel for displaying user details.
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Query the database to fetch the logged-in user's profile information.
        String query = "SELECT id, ePhilID, password, created_at FROM users WHERE ePhilID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, currentUser);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String ephilID = rs.getString("ePhilID");
                String password = rs.getString("password");
                java.sql.Timestamp createdAt = rs.getTimestamp("created_at");

                profilePanel.add(new JLabel("ID: " + id));
                profilePanel.add(Box.createVerticalStrut(5));
                profilePanel.add(new JLabel("ePhilID: " + ephilID));
                profilePanel.add(Box.createVerticalStrut(5));
                profilePanel.add(new JLabel("Password: " + password)); // Consider hiding/encrypting this in production.
                profilePanel.add(Box.createVerticalStrut(5));
                profilePanel.add(new JLabel("Created At: " + createdAt));
            } else {
                profilePanel.add(new JLabel("User not found."));
            }
        } catch (SQLException ex) {
            profilePanel.add(new JLabel("Error fetching user data: " + ex.getMessage()));
            ex.printStackTrace();
        }

        profilePanel.add(Box.createVerticalStrut(20));

        // Add a big red Logout button.
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        logoutButton.addActionListener(e -> {
            // Clear the current user, close the profile window, and return to the login
            // screen.
            currentUser = null;
            System.out.println("User logged out.");
            childFrame.dispose();
            logout();
        });

        profilePanel.add(logoutButton);

        childFrame.add(profilePanel);
        childFrame.setVisible(true);
    }

    // component of userprofile for logout
    public void logout() {
        if (mainFrame != null) {
            mainFrame.dispose();
            mainFrame = null; // Optionally clear the reference
        }
        // Continue with logout actions, such as showing the login UI.
        showLoginUI();
    }

    public void showActivityLogUI() {
        // If a child window (this UI) is already open, don't open another.
        if (childFrame != null && childFrame.isVisible()) {
            return;
        }
        // Create a new frame for the Activity Log UI.
        JFrame activityFrame = new JFrame("Activity Log for " + currentUser);
        activityFrame.setSize(600, 400);
        activityFrame.setLocationRelativeTo(null);
        activityFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the user's posts.
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        postsPanel.setBackground(Color.WHITE);

        // Query the database for posts by the current user.
        String query = "SELECT id, content, created_at FROM posts WHERE author = ? ORDER BY created_at DESC";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, currentUser);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int postId = rs.getInt("id");
                String content = rs.getString("content");
                java.sql.Timestamp createdAt = rs.getTimestamp("created_at");

                // Create a panel for each post.
                JPanel postPanel = new JPanel(new BorderLayout());
                postPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                postPanel.setBackground(Color.LIGHT_GRAY);
                postPanel.setMaximumSize(new Dimension(550, 100));

                // Label to show content.
                JLabel contentLabel = new JLabel(
                        "<html><p style='width:400px; margin:5px;'>" + content + "</p></html>");
                contentLabel.setOpaque(false);

                // Label to show created_at timestamp.
                JLabel dateLabel = new JLabel("Posted on: " + createdAt.toString());
                dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
                dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

                // Create a Delete button.
                JButton deleteButton = new JButton("Delete");
                deleteButton.setBackground(Color.RED);
                deleteButton.setForeground(Color.WHITE);
                deleteButton.setFont(new Font("SansSerif", Font.BOLD, 12));
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Confirm before deleting
                        int confirm = JOptionPane.showConfirmDialog(activityFrame,
                                "Are you sure you want to delete this post?",
                                "Confirm Delete", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            // Perform deletion from the posts table where id and author match.
                            try (Connection conn2 = getConnection();
                                    PreparedStatement pstmt2 = conn2
                                            .prepareStatement("DELETE FROM posts WHERE id = ? AND author = ?")) {

                                pstmt2.setInt(1, postId);
                                pstmt2.setString(2, currentUser);
                                int affected = pstmt2.executeUpdate();
                                if (affected > 0) {
                                    JOptionPane.showMessageDialog(activityFrame, "Post deleted successfully.");
                                    // Refresh the activity log UI.
                                    activityFrame.dispose();
                                    showActivityLogUI();
                                } else {
                                    JOptionPane.showMessageDialog(activityFrame, "Failed to delete post.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(activityFrame,
                                        "Database error: " + ex.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                ex.printStackTrace();
                            }
                        }
                    }
                });

                // Assemble the post panel:
                // Create a top panel with content and the delete button.
                JPanel topPanel = new JPanel(new BorderLayout());
                topPanel.setBackground(Color.LIGHT_GRAY);
                topPanel.add(contentLabel, BorderLayout.CENTER);
                topPanel.add(deleteButton, BorderLayout.EAST);

                postPanel.add(topPanel, BorderLayout.CENTER);
                postPanel.add(dateLabel, BorderLayout.SOUTH);

                // Add the post panel and some vertical spacing to postsPanel.
                postsPanel.add(postPanel);
                postsPanel.add(Box.createVerticalStrut(10));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(activityFrame,
                    "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        // Wrap postsPanel in a scroll pane so it is scrollable.
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        activityFrame.add(scrollPane);
        activityFrame.setVisible(true);
    }

    public void showPostPetitionUI() {
        // If a child window (this UI) is already open, don't open another.
        if (childFrame != null && childFrame.isVisible()) {
            return;
        }

        // Create the "Post Petition" window and store it in the global childFrame.
        childFrame = new JFrame("Post Petition");
        childFrame.setSize(400, 300);
        childFrame.setLocationRelativeTo(null); // Center on screen
        childFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create panel with a vertical layout and padding.
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Label and text area for entering the concern (petition text).
        JLabel concernLabel = new JLabel("Post your concern:");
        JTextArea concernArea = new JTextArea(5, 30);
        JScrollPane concernScroll = new JScrollPane(concernArea);

        // Label and text field for entering the donation goal.
        JLabel donationGoalLabel = new JLabel("Donation Goal:");
        JTextField donationGoalField = new JTextField(20);

        // Add a "Submit Post" button.
        JButton postButton = new JButton("Submit Post");

        // Add the components to the panel.
        panel.add(concernLabel);
        panel.add(concernScroll);
        panel.add(Box.createVerticalStrut(10));
        panel.add(donationGoalLabel);
        panel.add(donationGoalField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(postButton);

        childFrame.add(panel);
        childFrame.setVisible(true);

        // Action for the Submit Post button.
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String concern = concernArea.getText().trim();
                String donationGoalStr = donationGoalField.getText().trim();

                // Validate that both fields are filled.
                if (concern.isEmpty() || donationGoalStr.isEmpty()) {
                    JOptionPane.showMessageDialog(childFrame,
                            "Please fill in both the concern and donation fields.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double donationGoal;
                try {
                    donationGoal = Double.parseDouble(donationGoalStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(childFrame,
                            "Donation goal must be a valid number.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Insert the new post into the database and update the user's record.
                try (Connection conn = getConnection()) {

                    // 1. Retrieve the current user's id and any preexisting post ids.
                    String getUserQuery = "SELECT id, posts FROM users WHERE ePhilID = ?";
                    int userId = -1;
                    String currentPosts = "";
                    try (PreparedStatement pstmt = conn.prepareStatement(getUserQuery)) {
                        pstmt.setString(1, currentUser);
                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next()) {
                                userId = rs.getInt("id");
                                currentPosts = rs.getString("posts"); // May be null if no posts exist.
                            } else {
                                JOptionPane.showMessageDialog(childFrame,
                                        "Current user not found.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }

                    // 2. Insert the new post into the posts table.
                    String insertPostQuery = "INSERT INTO posts (user_id, content, donation_goal, donation_received, created_at, author) "
                            + "VALUES (?, ?, ?, ?, NOW(), ?)";
                    int generatedPostId = -1;
                    try (PreparedStatement pstmt = conn.prepareStatement(insertPostQuery,
                            Statement.RETURN_GENERATED_KEYS)) {
                        pstmt.setInt(1, userId);
                        pstmt.setString(2, concern);
                        pstmt.setDouble(3, donationGoal);
                        pstmt.setDouble(4, 0.0); // donation_received initially 0.
                        pstmt.setString(5, currentUser);

                        int affectedRows = pstmt.executeUpdate();
                        if (affectedRows == 0) {
                            JOptionPane.showMessageDialog(childFrame,
                                    "Creating post failed.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                generatedPostId = generatedKeys.getInt(1);
                            } else {
                                JOptionPane.showMessageDialog(childFrame,
                                        "Failed to retrieve post ID.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }

                    // 3. Update the current user’s record to include the new post id.
                    // The user's posts are stored as comma-separated ids.
                    String newPosts;
                    if (currentPosts == null || currentPosts.trim().isEmpty()) {
                        newPosts = String.valueOf(generatedPostId);
                    } else {
                        newPosts = currentPosts + "," + generatedPostId;
                    }
                    String updateUserQuery = "UPDATE users SET posts = ? WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(updateUserQuery)) {
                        pstmt.setString(1, newPosts);
                        pstmt.setInt(2, userId);
                        pstmt.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(childFrame,
                            "Post successfully submitted!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    childFrame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(childFrame,
                            "Database error: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    private JPanel createPostPanel(final int postId,
            final String postedBy,
            final String content,
            final int upvotes,
            final int downvotes,
            final int commentCount,
            final double donationGoal,
            final double donationReceived,
            final String createdAt) {
        // Create the main post panel
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        postPanel.setBackground(Color.WHITE);

        // --- Top Panel: Displays author and timestamp ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        JLabel userLabel = new JLabel("Posted by: " + postedBy);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        JLabel dateLabel = new JLabel(createdAt);
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        dateLabel.setForeground(Color.DARK_GRAY);
        topPanel.add(userLabel, BorderLayout.WEST);
        topPanel.add(dateLabel, BorderLayout.EAST);

        // --- Content Panel: Displays the post content ---
        JLabel contentLabel = new JLabel("<html><p style='width:500px'>" + content + "</p></html>");
        contentLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // --- Action Panel: Contains upvote, downvote, comment, and donation controls
        // ---
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        actionPanel.setBackground(Color.WHITE);

        // Upvote button
        final JButton upvoteButton = new JButton("👍 " + upvotes);
        upvoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try (Connection conn = getConnection()) {
                    // Check if user already voted
                    String checkQuery = "SELECT * FROM post_votes WHERE user_id = ? AND post_id = ?";
                    try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                        checkStmt.setInt(1, currentUserId);
                        checkStmt.setInt(2, postId);
                        try (ResultSet rs = checkStmt.executeQuery()) {
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(mainFrame, "You have already voted on this post!");
                                return;
                            }
                        }
                    }
                    // Update the posts table
                    String updateQuery = "UPDATE posts SET upvotes = upvotes + 1 WHERE id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, postId);
                        int affected = updateStmt.executeUpdate();
                        if (affected > 0) {
                            // Insert vote record so the user can’t vote again.
                            String insertVote = "INSERT INTO post_votes (user_id, post_id, vote_type) VALUES (?, ?, ?)";
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertVote)) {
                                insertStmt.setInt(1, currentUserId);
                                insertStmt.setInt(2, postId);
                                insertStmt.setString(3, "up");
                                insertStmt.executeUpdate();
                            }
                        }
                    }
                    // Re-read updated upvotes from the database
                    String countQuery = "SELECT upvotes FROM posts WHERE id = ?";
                    try (PreparedStatement countStmt = conn.prepareStatement(countQuery)) {
                        countStmt.setInt(1, postId);
                        try (ResultSet rs2 = countStmt.executeQuery()) {
                            int newUpvotes = upvotes;
                            if (rs2.next()) {
                                newUpvotes = rs2.getInt("upvotes");
                            }
                            upvoteButton.setText("👍 " + newUpvotes);
                        }
                    }
                    JOptionPane.showMessageDialog(mainFrame, "Thank you for your vote!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Error updating upvotes: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Downvote button
        final JButton downvoteButton = new JButton("👎 " + downvotes);
        downvoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try (Connection conn = getConnection()) {
                    // Check if the user already voted
                    String checkQuery = "SELECT * FROM post_votes WHERE user_id = ? AND post_id = ?";
                    try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                        checkStmt.setInt(1, currentUserId);
                        checkStmt.setInt(2, postId);
                        try (ResultSet rs = checkStmt.executeQuery()) {
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(mainFrame, "You have already voted on this post!");
                                return;
                            }
                        }
                    }
                    // Update downvotes in the posts table
                    String updateQuery = "UPDATE posts SET downvotes = downvotes + 1 WHERE id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, postId);
                        int affected = updateStmt.executeUpdate();
                        if (affected > 0) {
                            // Insert a record to prevent duplicate votes.
                            String insertVote = "INSERT INTO post_votes (user_id, post_id, vote_type) VALUES (?, ?, ?)";
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertVote)) {
                                insertStmt.setInt(1, currentUserId);
                                insertStmt.setInt(2, postId);
                                insertStmt.setString(3, "down");
                                insertStmt.executeUpdate();
                            }
                        }
                    }
                    // Re-read updated downvotes
                    String countQuery = "SELECT downvotes FROM posts WHERE id = ?";
                    try (PreparedStatement countStmt = conn.prepareStatement(countQuery)) {
                        countStmt.setInt(1, postId);
                        try (ResultSet rs2 = countStmt.executeQuery()) {
                            int newDownvotes = downvotes;
                            if (rs2.next()) {
                                newDownvotes = rs2.getInt("downvotes");
                            }
                            downvoteButton.setText("👎 " + newDownvotes);
                        }
                    }
                    JOptionPane.showMessageDialog(mainFrame, "Thank you for your vote!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Error updating downvotes: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Comment button – opens the comments UI for this post.
        JButton commentButton = new JButton("💬 " + commentCount);
        commentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                showCommentsUI(postId);
            }
        });

        // Donation panel – displays donation figures and progress.
        JPanel donationPanel = new JPanel(new BorderLayout());
        donationPanel.setBackground(Color.WHITE);
        final JLabel donationLabel = new JLabel(String.format("$%.2f / $%.2f", donationReceived, donationGoal));
        donationLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        final JProgressBar donationProgress = new JProgressBar(0, (int) donationGoal);
        donationProgress.setValue((int) donationReceived);
        donationProgress.setStringPainted(true);
        donationProgress.setPreferredSize(new Dimension(150, 20));
        donationPanel.add(donationLabel, BorderLayout.NORTH);
        donationPanel.add(donationProgress, BorderLayout.CENTER);

        // Donation button – prompts the user, then updates donation_received (from the
        // database).
        JButton donationButton = new JButton("Donate");
        donationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String amountStr = JOptionPane.showInputDialog(mainFrame, "Enter donation amount:");
                if (amountStr != null && !amountStr.isEmpty()) {
                    try {
                        double donateAmount = Double.parseDouble(amountStr);
                        if (donateAmount <= 0) {
                            JOptionPane.showMessageDialog(mainFrame, "Enter a positive donation amount.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        try (Connection conn = getConnection()) {
                            String updateDonationQuery = "UPDATE posts SET donation_received = donation_received + ? WHERE id = ?";
                            try (PreparedStatement updateStmt = conn.prepareStatement(updateDonationQuery)) {
                                updateStmt.setDouble(1, donateAmount);
                                updateStmt.setInt(2, postId);
                                int affected = updateStmt.executeUpdate();
                                if (affected > 0) {
                                    // Re-read the updated donation_received value
                                    String selectQuery = "SELECT donation_received FROM posts WHERE id = ?";
                                    try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                                        selectStmt.setInt(1, postId);
                                        try (ResultSet rs = selectStmt.executeQuery()) {
                                            double newDonation = donationReceived;
                                            if (rs.next()) {
                                                newDonation = rs.getDouble("donation_received");
                                            }
                                            donationLabel
                                                    .setText(String.format("$%.2f / $%.2f", newDonation, donationGoal));
                                            donationProgress.setValue((int) newDonation);
                                            JOptionPane.showMessageDialog(mainFrame, "Thank you for your donation!");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(mainFrame, "Donation update failed.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid donation amount.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Database error: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Add all interactive controls to the action panel.
        actionPanel.add(upvoteButton);
        actionPanel.add(downvoteButton);
        actionPanel.add(commentButton);
        actionPanel.add(donationPanel);
        actionPanel.add(donationButton);

        // --- Assemble the complete post panel ---
        postPanel.add(topPanel);
        postPanel.add(contentLabel);
        postPanel.add(actionPanel);

        return postPanel;
    }

    public void showCommentsUI(int postId) {
        // Create a frame for the comments UI.
        JFrame commentFrame = new JFrame("Comments for Post ID " + postId);
        commentFrame.setSize(500, 400);
        commentFrame.setLocationRelativeTo(null);
        commentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainCommentPanel = new JPanel(new BorderLayout());

        // Panel to display the list of comments.
        JPanel commentsListPanel = new JPanel();
        commentsListPanel.setLayout(new BoxLayout(commentsListPanel, BoxLayout.Y_AXIS));
        commentsListPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(commentsListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Query to load comments for the given post from your comments table.
        // Note: we select "comment" instead of "content", and use "user_id" as the
        // identifier for the comment author.
        String queryComments = "SELECT id, comment, created_at, user_id FROM comments WHERE post_id = ? ORDER BY created_at ASC";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(queryComments)) {
            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String commentText = rs.getString("comment");
                java.sql.Timestamp commentCreatedAt = rs.getTimestamp("created_at");
                int commentUserId = rs.getInt("user_id");

                JPanel commentPanel = new JPanel(new BorderLayout());
                commentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                commentPanel.setBackground(Color.LIGHT_GRAY);

                JLabel commentLabel = new JLabel(
                        "<html><p style='width:400px; margin:5px;'>" + commentText + "</p></html>");
                // Display the user id; you can extend this query/join to display the actual
                // username if needed.
                JLabel commentInfo = new JLabel("By: User " + commentUserId + " at " + commentCreatedAt.toString());
                commentInfo.setFont(new Font("SansSerif", Font.ITALIC, 10));

                commentPanel.add(commentLabel, BorderLayout.CENTER);
                commentPanel.add(commentInfo, BorderLayout.SOUTH);

                commentsListPanel.add(commentPanel);
                commentsListPanel.add(Box.createVerticalStrut(5));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(commentFrame, "Error loading comments: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        // Panel to add a new comment.
        JPanel addCommentPanel = new JPanel();
        addCommentPanel.setLayout(new BoxLayout(addCommentPanel, BoxLayout.Y_AXIS));
        addCommentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextArea commentTextArea = new JTextArea(3, 40);
        JScrollPane textScroll = new JScrollPane(commentTextArea);
        JButton submitCommentButton = new JButton("Submit Comment");

        addCommentPanel.add(new JLabel("Add your comment:"));
        addCommentPanel.add(textScroll);
        addCommentPanel.add(Box.createVerticalStrut(10));
        addCommentPanel.add(submitCommentButton);

        mainCommentPanel.add(scrollPane, BorderLayout.CENTER);
        mainCommentPanel.add(addCommentPanel, BorderLayout.SOUTH);

        commentFrame.add(mainCommentPanel);
        commentFrame.setVisible(true);

        // Action for submit button: insert the new comment with correct column names.
        submitCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commentText = commentTextArea.getText().trim();
                if (commentText.isEmpty()) {
                    JOptionPane.showMessageDialog(commentFrame,
                            "Please enter a comment.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Get the current user's ID from the users table.
                int userId = -1;
                String getUserQuery = "SELECT id FROM users WHERE ePhilID = ?";
                try (Connection conn = getConnection();
                        PreparedStatement stmt = conn.prepareStatement(getUserQuery)) {
                    stmt.setString(1, currentUser);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        userId = rs.getInt("id");
                    } else {
                        JOptionPane.showMessageDialog(commentFrame, "User not found.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(commentFrame, "Error retrieving user: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    return;
                }
                // Insert the comment into the comments table using the correct column name
                // "comment".
                String insertCommentQuery = "INSERT INTO comments (post_id, user_id, comment, created_at) VALUES (?, ?, ?, NOW())";
                try (Connection conn = getConnection();
                        PreparedStatement stmt = conn.prepareStatement(insertCommentQuery)) {
                    stmt.setInt(1, postId);
                    stmt.setInt(2, userId);
                    stmt.setString(3, commentText);
                    int affected = stmt.executeUpdate();
                    if (affected > 0) {
                        JOptionPane.showMessageDialog(commentFrame, "Comment added successfully!");
                        commentFrame.dispose();
                        showCommentsUI(postId); // Refresh the comments UI.
                    } else {
                        JOptionPane.showMessageDialog(commentFrame, "Failed to add comment.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(commentFrame, "Error adding comment: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    // components for ui's
    private Image loadBackgroundImage() {
        URL imageUrl = getClass().getResource("background.png");
        if (imageUrl == null) {
            System.err.println("Background image not found.");
            return null;
        }
        return new ImageIcon(imageUrl).getImage();
    }

    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

}
