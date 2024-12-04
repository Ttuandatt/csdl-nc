/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Teacher;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ACER
 */
public class AdminView {
    JLabel lbId, dataId, lbName, dataName, lbRole, dataRole;
    private Teacher teacher;
    
    // Constructor nhận thông tin Teacher từ LoginView
    public AdminView(Teacher teacher) {
        this.teacher = teacher;  // Lưu thông tin teacher
        initComponents();
        updateView();  // Cập nhật giao diện với thông tin của giáo viên
    }
    
    public void initComponents(){
        JFrame f = new JFrame("Admin view");
        f.setSize(1500, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        
        ///////////////////// NAVBAR PANEL ////////////////////////
        JPanel navBarPanel = new JPanel(new BorderLayout());
        navBarPanel.setBackground(Color.red);
        
        //Panel userInfo
        JPanel userInfoPanel = new JPanel(new GridBagLayout());
        userInfoPanel.setBackground(Color.blue);
        userInfoPanel.setPreferredSize(new Dimension(200,100));
        
        //user information labels
        lbId = new JLabel("ID: ");
        lbName = new JLabel("Name: ");
        lbRole = new JLabel("Role: ");
        
        dataId = new JLabel();  //data will be taken from database and set for these 3 labels
        dataName = new JLabel();
        dataRole = new JLabel();
        dataId.setText(teacher.getTeacherId());
        dataName.setText(teacher.getTeacherName());
        dataRole.setText(teacher.getRole());
        //set font color
        lbId.setForeground(Color.white);
        lbName.setForeground(Color.white);
        lbRole.setForeground(Color.white);
        dataId.setForeground(Color.white);
        dataName.setForeground(Color.white);
        dataRole.setForeground(Color.white);
        
        //set font size
        Font labelFont = new Font("Arial", Font.BOLD, 15);
        lbId.setFont(labelFont);
        lbName.setFont(labelFont);
        lbRole.setFont(labelFont);
        dataId.setFont(labelFont);
        dataName.setFont(labelFont);
        dataRole.setFont(labelFont);
        
        //initial a GridBagConstraints object to customize components size & position in userInfoPanel
        GridBagConstraints gbc = new GridBagConstraints();
        //set lbId position in userInfoPanel (row 0, column 0)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; //Căn chữ bên trái
        userInfoPanel.add(lbId, gbc);
        //set dataId position in userInfoPanel (row 0, column 1)
        gbc.gridx = 1;
        userInfoPanel.add(dataId, gbc);
        //set lbName position in userInfoPanel (row 1, column 0)
        gbc.gridx = 0; gbc.gridy = 1;
        userInfoPanel.add(lbName, gbc);
        //set dataName position in userInfoPanel (row 1, column 1)
        gbc.gridx = 1;
        userInfoPanel.add(dataName, gbc);
        //set lbRole position in userInfoPanel (row 2, column 0)
        gbc.gridx = 0; gbc.gridy = 2;
        userInfoPanel.add(lbRole, gbc);
        //set dataRole position in userInfoPanel (row 2, column 1)
        gbc.gridx = 1;
        userInfoPanel.add(dataRole, gbc);
        navBarPanel.add(userInfoPanel, BorderLayout.NORTH);
        
        //initial buttonsPanel using GridBagLayout to control the layout of the navigation buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(10,1));
        buttonsPanel.setBackground(Color.darkGray);
        
        
        //buttons
        JButton createExamButton = new JButton("Create Exam");
        buttonsPanel.add(createExamButton);
        JButton examManaementButton = new JButton("Exam Management");
        buttonsPanel.add(examManaementButton);
        JButton questionManaementButton = new JButton("Question Management");
        buttonsPanel.add(questionManaementButton);

        
        navBarPanel.add(buttonsPanel, BorderLayout.CENTER);
        
        //initial bottomPanel for logout, perrsonal information
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        JButton infoButton = new JButton("Information");
        JButton logoutButton = new JButton("Logout");
        bottomPanel.add(infoButton);
        bottomPanel.add(logoutButton);
        navBarPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(navBarPanel, BorderLayout.WEST);
        

        ///////////////////// CONTENT PANEL ////////////////////////
        //initial contentPanel for creating displaying questions, creting exam tasks
        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(Color.lightGray);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Customize size for components
//        int navigationBarWidth = f.getWidth()/6;
//        int contentWidth = f.getWidth() - navigationBarWidth;
//        navBarPanel.setPreferredSize(new Dimension(navigationBarWidth, f.getHeight()));
//        contentPanel.setPreferredSize(new Dimension(contentWidth, f.getHeight()));

        // Set identify of function buttons to define the GUI class
        final String question_management = "QUESTION";
        final String exam_management = "EXAM";
    
        // Add ActionListener & MouseListener for ExamView management button
        createExamButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ExamView exam = new ExamView(teacher.getTeacherId());
                contentPanel.add(exam, exam_management);
                CardLayout card = (CardLayout)contentPanel.getLayout();
                card.show(contentPanel, exam_management);
            }
        });
        
        
        f.add(mainPanel);
        f.setVisible(true);
    }
    
    
    
    // Cập nhật giao diện với thông tin người dùng
    public void updateView() {
        if (teacher != null) {
            dataId.setText(teacher.getTeacherId());
            dataName.setText(teacher.getTeacherName());
            dataRole.setText(teacher.getRole());
        }
    }
    
    public static void main(String[] args){
        Teacher teacher = new Teacher();
        teacher.setTeacherId("Danny");
        teacher.setTeacherName("TunDat");
        teacher.setRole("Husband");
        AdminView adminView = new AdminView(teacher);
    }
}