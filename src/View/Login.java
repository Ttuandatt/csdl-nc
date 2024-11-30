/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author ACER
 */
public class Login extends JFrame{
    JLabel lbUsername, lbPassword;
    JTextField tfUsername, tfPassword;
    //Constructor
    public Login(){
        initComponents();
    }
    
    public void initComponents(){
        JFrame f = new JFrame("Login");
        f.setSize(800,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.blue);
        
        // Split mainPanel into 2 parts by adding 2 panels: leftPanel & rightPanel
        //leftPanel for Login image
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.green);

        //rightPanel for Login fields
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.darkGray);

        //Customize ratio & position of the 2 panels
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(leftPanel, gbc);
        gbc.weightx = 0.3;
        gbc.gridx = 1;
        mainPanel.add(rightPanel, gbc);

        //Add input fields into rightPanel
        lbUsername = new JLabel("Username");
        lbUsername.setBounds(10, 50, 100, 20);
        lbUsername.setForeground(Color.white);
        rightPanel.add(lbUsername);
        tfUsername = new JTextField();
        tfUsername.setBounds(10, 75, 150, 20);
        rightPanel.add(tfUsername);

        lbPassword = new JLabel("Pasword");
        lbPassword.setBounds(10, 120, 100, 20);
        lbPassword.setForeground(Color.white);
        rightPanel.add(lbPassword);
        tfPassword = new JTextField();
        tfPassword.setBounds(10, 145, 150, 20);
        rightPanel.add(tfPassword);

        f.add(mainPanel);
        f.setVisible(true);
    }
    
    
    public static void main(String[] args){
        Login login = new Login();
    }
}
