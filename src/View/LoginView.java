package View;

import Controller.AdminController;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import Controller.LoginController;
import Controller.TeacherController;
import Model.Teacher;

public class LoginView extends JFrame {
    JPanel pnLeft, pnRight;
    JLabel lblImage, lblLoginBanner;
    InputForm txtUsername, txtPassword;
    private LoginController loginController;
    private TeacherController teacherController;
    private AdminController adminController;
    public LoginView() {
        loginController = new LoginController(); // Khởi tạo LoginController
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
                login(); // Gọi phương thức login khi nhấn nút Login
            }
        });
        pnRight.add(pnlLogIn);

        this.add(pnRight, BorderLayout.EAST);
    }

    private void login() {
    String email = txtUsername.getText();
    String password = txtPassword.getText();

    int result = loginController.login(email, password);
    if (result == 1) {
        Teacher teacher = loginController.getUserInfo(email, password);
        JOptionPane.showMessageDialog(this, "Welcome, " + teacher.getTeacherName() + "!");
        this.dispose(); // Đóng cửa sổ LoginView

        // Hiển thị TeacherView
        if ("teacher".equalsIgnoreCase(teacher.getRole())) {
            TeacherView teacherView = new TeacherView(teacher);
            System.out.println(teacherView.getTeacherID() + ", I'm at line 97 of LoginView. I'm TeacherView");
            teacherController = new TeacherController(teacher, teacherView);
            teacherController.transferData(teacherView.getTeacherID());
        }else if ("admin".equalsIgnoreCase(teacher.getRole())) {
            AdminView adminView = new AdminView(teacher);
            System.out.println(adminView.getTeacherID() + ", I'm at line 103 of LoginView. I'm AdminView");
            adminController = new AdminController(teacher, adminView);
            adminController.transferData(adminView.getTeacherID());
        }
    } else if (result == 2) {
        JOptionPane.showMessageDialog(this, "Username or password incorrect");
    } else {
        JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.");
    }
}



    public static void main(String[] args) {
        LoginView newLogin = new LoginView();
        newLogin.setVisible(true);
    }
}
