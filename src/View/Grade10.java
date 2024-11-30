/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Question;
import DAO.QuestionDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Controller.QuestionController;
import javax.swing.table.TableColumnModel;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */
public class
Grade10 extends JPanel{
    JTable tableQuest = new JTable();
    JTable tableSelectedQuest = new JTable();
    DefaultTableModel questionModel = new DefaultTableModel();
    DefaultTableModel selectedQuestionModel = new DefaultTableModel();
    private ArrayList<Question> questArr = new ArrayList<>();
    private QuestionController questionController;
    private QuestionDAO questionDAO = new QuestionDAO();
    
    JLabel lbID, lbDate, lbGrade, lbClass, lbExamName, lbDuration, lbExamID;
    JTextField tfID, tfDate, tfExamName, tfDuration, tfExamID;
    JButton buttonAddQuestion, buttonCreateExam, buttonCancel;
    public Grade10(){
        initComponents();
        questionController = new QuestionController(questionDAO); // Initialize QuestionController
        loadQuestionList(); // Call method to load data into table
        loadSelectedQuestionList();
    }
    
    public void initComponents() {
        this.setLayout(new GridBagLayout());

        // Create panels
        JPanel upPanel = new JPanel(new GridBagLayout());
        upPanel.setBackground(Color.CYAN);
        JPanel downPanel = new JPanel(new BorderLayout());
        downPanel.setBackground(Color.GREEN);

        // GridBagConstraints for upPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 0.4; // Ratio 40%
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(upPanel, gbc);

        // GridBagConstraints for downPanel
        gbc.weighty = 0.6; // Ratio 60%
        gbc.gridy = 1;
        add(downPanel, gbc);

        // Add table into JScrollPane and customize
        JScrollPane sp1 = new JScrollPane(tableQuest);
        downPanel.add(sp1, BorderLayout.CENTER);

        // Split upPanel into 2 parts by adding 2 panels: leftPanel & rightPanel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.DARK_GRAY);
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.LIGHT_GRAY);

        gbc.weightx = 0.6;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        upPanel.add(leftPanel, gbc);

        gbc.weightx = 0.4;
        gbc.gridx = 1;
        upPanel.add(rightPanel, gbc);

        //Add table for selected question into leftPanel
        JScrollPane sp2 = new JScrollPane(tableSelectedQuest);
        leftPanel.add(sp2, BorderLayout.CENTER);
        
        //Set labels, textfield in rightPanel
        lbID = new JLabel("Creator ID:");
        lbID.setBounds(10, 10, 100, 20);
        rightPanel.add(lbID);
        tfID = new JTextField();
        tfID.setBounds(90, 10, 100, 20);
        rightPanel.add(tfID);
        
        lbDate = new JLabel("Create date:");
        lbDate.setBounds(10, 40, 100, 20);
        rightPanel.add(lbDate);
        tfDate = new JTextField();
        tfDate.setBounds(90, 40, 100, 20);
        rightPanel.add(tfDate);
        
        lbGrade = new JLabel("Grade:");
        lbGrade.setBounds(10, 70, 100, 20);
        rightPanel.add(lbGrade);
        //create combo box for grade
        String[] combo = {"10","11","12"};
        JComboBox cb = new JComboBox(combo);
        cb.setBounds(90, 70, 100, 20);
        rightPanel.add(cb);
        
        lbExamName = new JLabel("Exam name:");
        lbExamName.setBounds(10, 100, 100, 20);
        rightPanel.add(lbExamName);
        tfExamName = new JTextField();
        tfExamName.setBounds(90, 100, 200, 20);
        rightPanel.add(tfExamName);
        
        lbDuration = new JLabel("Duration");
        lbDuration.setBounds(10, 130, 100, 20);
        rightPanel.add(lbDuration);
        tfDuration = new JTextField();
        tfDuration.setBounds(90, 130, 100, 20);
        rightPanel.add(tfDuration);
        
        lbExamID = new JLabel("Exam ID:");
        lbExamID.setBounds(10, 160, 100, 20);
        rightPanel.add(lbExamID);
        tfExamID = new JTextField();
        tfExamID.setBounds(90, 160, 100, 20);
        rightPanel.add(tfExamID);
        
        buttonAddQuestion = new JButton("Add question");
        buttonAddQuestion.setBounds(160, 280, 110, 20);
        rightPanel.add(buttonAddQuestion);
        
        buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(330, 280, 80, 20);
        rightPanel.add(buttonCancel);
        
        buttonCreateExam = new JButton("Create");
        buttonCreateExam.setBounds(420, 280, 80, 20);
        rightPanel.add(buttonCreateExam);
        
    }


    // Data load method
    public void loadQuestionList(){
        tableQuest.setModel(questionModel);
        questionModel.addColumn("ID");
        questionModel.addColumn("Content");
        questionModel.addColumn("Answer 1");
        questionModel.addColumn("Answer 2");
        questionModel.addColumn("Answer 3");
        questionModel.addColumn("Answer 4");
        questionModel.addColumn("Correct Answer");
        questionModel.addColumn("Created by");
        questionModel.addColumn("Created date");
        
        //Customize size of columns
        TableColumnModel columnModel = tableQuest.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(7);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(130);
        columnModel.getColumn(3).setPreferredWidth(130);
        columnModel.getColumn(4).setPreferredWidth(130);
        columnModel.getColumn(5).setPreferredWidth(130);
        columnModel.getColumn(6).setPreferredWidth(10);
        columnModel.getColumn(7).setPreferredWidth(10);
        columnModel.getColumn(7).setPreferredWidth(10);
        questArr = questionController.getAllQuestions();
        for(int i=0;i<questArr.size();i++){
            Question question = questArr.get(i);
            String id = question.getQuestionID();
            String content = question.getQuestionContent();
            String a1 = question.getAnswer1();
            String a2 = question.getAnswer2();
            String a3 = question.getAnswer3();
            String a4 = question.getAnswer4();
            String correctA = question.getCorrectAnswer();
            String createdBy = question.getCreatedBy();
            Date createdDate = question.getCreatedDate();
            
            Object[] row = {id, content, a1, a2, a3, a4, correctA, createdBy, createdDate};
            questionModel.addRow(row);
        }
    }
    
    public void loadSelectedQuestionList(){
        tableSelectedQuest.setModel(selectedQuestionModel);
        selectedQuestionModel.addColumn("ID");
        selectedQuestionModel.addColumn("Content");
    }

}
