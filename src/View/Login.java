package View;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Login extends JFrame {
    JPanel pnLeft, pnRight;
    JLabel lblImage, lblLoginBanner;
    InputForm txtUsername, txtPassword;

    public Login() {
        initComponent();
        txtUsername.setText("");
        txtPassword.setText("");
    }

    private void initComponent() {
        FlatIntelliJLaf.setup();
        this.setBackground(Color.WHITE);
        this.setSize(new Dimension(800, 400));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Login");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Left panel with image
        pnLeft = new JPanel(new BorderLayout());
        pnLeft.setPreferredSize(new Dimension(400, 400));
        lblImage = new JLabel(new FlatSVGIcon("./Images/login2.svg"));
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        pnLeft.add(lblImage, BorderLayout.CENTER);
        this.add(pnLeft, BorderLayout.WEST);

        // Right panel with form
        pnRight = new JPanel(null);
        pnRight.setBackground(Color.WHITE);
        pnRight.setPreferredSize(new Dimension(400, 400));

        // Banner
        lblLoginBanner = new JLabel("WELCOME BACK", SwingConstants.CENTER);
        lblLoginBanner.setFont(new Font("Arial", Font.BOLD, 24));
        lblLoginBanner.setBounds(0, 20, 400, 40);
        pnRight.add(lblLoginBanner);

        // Username
        txtUsername = new InputForm("Username");
        txtUsername.setBounds(50, 80, 300, 80);
        pnRight.add(txtUsername);
        
        // Password
        txtPassword = new InputForm("Password", "password");
        txtPassword.setBounds(50, 150, 300, 80);
        pnRight.add(txtPassword);

        // Login button
        JPanel pnlLogIn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnlLogIn.setBackground(new Color(33, 150, 243));
        pnlLogIn.setBounds(120, 300, 150, 40);
        JLabel lblLogin = new JLabel("LOGIN", SwingConstants.CENTER);
        lblLogin.setForeground(Color.WHITE);
        pnlLogIn.add(lblLogin);
        pnlLogIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                login();
            }
        });
        pnRight.add(pnlLogIn);

        this.add(pnRight, BorderLayout.EAST);
    }

    private void login() {
        String username = txtUsername.getText();
        if (username.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Welcome, admin!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username.");
        }
    }

    public static void main(String[] args) {
        Login newLogin = new Login();
        newLogin.setVisible(true);
    }
}
