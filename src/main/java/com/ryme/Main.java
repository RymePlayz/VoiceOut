import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Main {
    // database stuff
    private static final String URL = "jdbc:mysql://localhost:3306/voiceout_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
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

            
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(passField, gbc);

        // Login button with simplified logic
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(255, 255, 255, 180));
        registerButton.setOpaque(true);
        registerButton.addActionListener(e -> {
            // set it to connect chck for databse
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
            // set it to connect chck for databse
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
