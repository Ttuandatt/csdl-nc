/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ExamController;
import Model.Question;
import DAO.QuestionDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Controller.QuestionController;
import Controller.LoginController;
import Model.Exam;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.TableColumnModel;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */
public class CreateExamView extends JPanel{
    JTable tableQuest = new JTable();
    JTable tableSelectedQuest = new JTable();
    DefaultTableModel questionModel = new DefaultTableModel();
    DefaultTableModel selectedQuestionModel = new DefaultTableModel();
    private ArrayList<Question> questArr = new ArrayList<>();
    private QuestionController questionController;
    private QuestionDAO questionDAO = new QuestionDAO();
    JComboBox cb1, cb2;
    JLabel lbID, lbDate, lbGrade, lbClass, lbExamName, lbDuration, lbExamID, lbShowBy, lbTopic;
    JTextField tfDate, tfExamName, tfDuration, tfExamID, tfSearch;
    JTextField tfID = new JTextField();
    JButton buttonCreateExam, buttonCancel, buttonSearch, buttonRefresh, buttonOK;
    JRadioButton buttonPublic, buttonPrivate;
    ArrayList<String> topic = new ArrayList<>();
    LoginController loginController = new LoginController();
    ExamController examController = new ExamController();
    
    public CreateExamView(String teacherID){
        initComponents();
        questionController = new QuestionController(questionDAO); // Initialize QuestionController
        loadQuestionList(); // Call method to load data into table
        loadSelectedQuestionList();
//        loadTopic();
        setTeacherID(teacherID); // Gán ID vào textfield
    }
    
    public void initComponents() {
        this.setLayout(new GridBagLayout());

        // Create panels
        JPanel upPanel = new JPanel(new GridBagLayout());
        upPanel.setBackground(Color.CYAN);
        JPanel downPanel = new JPanel(new BorderLayout());
        downPanel.setBackground(Color.white);

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
        leftPanel.setBackground(Color.white);
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.decode("#EBFCFF"));

        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        upPanel.add(leftPanel, gbc);

        gbc.weightx = 0.5;
        gbc.gridx = 1;
        upPanel.add(rightPanel, gbc);

        //Add table for selected question into leftPanel
        JScrollPane sp2 = new JScrollPane(tableSelectedQuest);
        leftPanel.add(sp2, BorderLayout.CENTER);
        
        //Set labels, textfield in rightPanel
        lbID = new JLabel("Creator ID:");
        lbID.setBounds(10, 10, 100, 20);
        rightPanel.add(lbID);
        tfID.setBounds(90, 10, 100, 20);
        tfID.setEditable(false);
        rightPanel.add(tfID);
//        tfID.setText(loginController.transferDataToExamView());


        try {
            lbDate = new JLabel("Create date:");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lbDate.setBounds(10, 40, 100, 20);
        rightPanel.add(lbDate);
        tfDate = new JTextField();
        tfDate.setEditable(false);
        tfDate.setBounds(90, 40, 100, 20);
        rightPanel.add(tfDate);
        // Định dạng ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Lấy ngày hiện tại
        String currentDate = LocalDate.now().format(formatter);
        tfDate.setText(currentDate); // Tự động điền ngày hiện tại vào tfDate
        
        
        lbGrade = new JLabel("Grade:");
        lbGrade.setBounds(10, 70, 100, 20);
        rightPanel.add(lbGrade);
        //create combo box for grade
        String[] combo = {"10","11","12"};
        cb1 = new JComboBox(combo);
        cb1.setBounds(90, 70, 100, 20);
        rightPanel.add(cb1);
        
        lbExamName = new JLabel("Exam name:");
        lbExamName.setBounds(10, 100, 100, 20);
        rightPanel.add(lbExamName);
        tfExamName = new JTextField();
        tfExamName.setBounds(90, 100, 300, 20);
        rightPanel.add(tfExamName);
        
//        lbTopic = new JLabel("Topic");
//        lbTopic.setBounds(10, 100, 100,20);
//        rightPanel.add(lbTopic);
//        
//        String[] combo3 = {"Topic"};
//        cb3 = new JComboBox(combo3);
//        cb3.setBounds(90, 100, 470, 20);
//        rightPanel.add(cb3);
        
        lbDuration = new JLabel("Duration");
        lbDuration.setBounds(10, 130, 100, 20);
        rightPanel.add(lbDuration);
        tfDuration = new JTextField();
        tfDuration.setBounds(90, 130, 100, 20);
        rightPanel.add(tfDuration);
        
        lbExamID = new JLabel("Exam ID:");
        lbExamID.setBounds(10, 160, 100, 20);
        rightPanel.add(lbExamID);
        tfExamID = new JTextField("");
        tfExamID.setBounds(90, 160, 100, 20);
        tfExamID.setEditable(false);
        rightPanel.add(tfExamID);
        
        
        
        
        buttonOK = new JButton("OK");
        buttonOK.setBounds(10, 190, 80, 20);
        rightPanel.add(buttonOK);
        
        buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(100, 190, 80, 20);
        rightPanel.add(buttonCancel);
        
        buttonCreateExam = new JButton("Create");
        buttonCreateExam.setBounds(190, 190, 80, 20);
        rightPanel.add(buttonCreateExam);
        
        lbShowBy = new JLabel("Show by:");
        lbShowBy.setBounds(10, 280, 80, 20);
        rightPanel.add(lbShowBy);
        //Create combo box to choose grade for questions
        String[] combo2 = {"10","11","12"};
        cb2 = new JComboBox(combo2);
        cb2.setBounds(70, 280, 50, 20);
        rightPanel.add(cb2);
        
        tfSearch = new JTextField();
        tfSearch.setBounds(155, 280, 180, 20);
        rightPanel.add(tfSearch);
        
        buttonSearch = new JButton("Search");
        buttonSearch.setBounds(340, 280, 80, 20);
        rightPanel.add(buttonSearch);
        
        buttonRefresh = new JButton("Refresh");
        buttonRefresh.setBounds(425, 280, 80, 20);
        rightPanel.add(buttonRefresh);
        
        
        buttonPublic = new JRadioButton("Public");
        buttonPublic.setBounds(300, 10, 90, 20);
        rightPanel.add(buttonPublic);
        buttonPrivate = new JRadioButton("Private");
        buttonPrivate.setBounds(380, 10, 90, 20);
        rightPanel.add(buttonPrivate);
        
        // Nhóm các radio button lại với nhau, chỉ có thể chọn 1 trong hai radio button
        ButtonGroup group = new ButtonGroup();
        group.add(buttonPublic);
        group.add(buttonPrivate);
        
        //set actionListener for buttonCreateExam
        buttonCreateExam.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                addPerformed(tableQuest);
            }
        });
        
        //set actionListener for buttonCancel
        buttonCancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                refreshInfoPerformed(tableQuest);
                refreshSelectedQuestion(tableSelectedQuest);
            }
        });
        
        //set actionListener for cb2 to display questions base on grade
        cb2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadQuestionList();
            }
        });
        
        //set actionListener for buttonSearch
        buttonSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                searchPerformed(tableQuest);
            }
        });
        
        //set actionListener for buttonRefresh
        buttonRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                refreshSearchPerformed(tableQuest);
            }
        });
        
        //set mouseListener for tableQuest so that when we double click a row, the ID & content of that row appears in tableSelectedQuest
        tableQuest.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){   //Check for double-click
                    int selectedRow = tableQuest.getSelectedRow();
                    if(selectedRow!=-1){
                        int id = (int) tableQuest.getValueAt(selectedRow, 0); //Get ID of the selected quesiton
                        String content = (String)tableQuest.getValueAt(selectedRow,1);  //Get content of the selected question
                        addToSelectedQuestionTable(id, content);
                    }
                }
            }
        });
        
        // ActionListener cho cb1 để cập nhật ExamID khi Grade thay đổi
        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                tfExamID.setText("");
                String teacherID = tfID.getText();
                String grade = cb1.getSelectedItem().toString();
                String duration = tfDuration.getText();
                tfExamID.setText(createExamID(teacherID, grade, duration));
                System.out.println(tfExamID.getText());
            }
        });
        
    }   


    // Data load method
    public void loadQuestionList(){
        // Xóa dữ liệu cũ và cột trong model
        questionModel = new DefaultTableModel(); // Tạo model mới để tránh lặp cột
        tableQuest.setModel(questionModel);
        tableQuest.setDefaultEditor(Object.class, null);    //Khong cho chinh cac cell trong bang
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
        columnModel.getColumn(8).setPreferredWidth(10);
        
        // Lấy giá trị được chọn từ combo box cb2
        String selectedGrade = cb2.getSelectedItem().toString();
        // Lấy danh sách câu hỏi từ Controller
        questArr = questionController.getAllQuestions(selectedGrade);
        for(int i=0;i<questArr.size();i++){
            Question question = questArr.get(i);
            int id = question.getQuestionID();
            String content = question.getQuestionContent();
            String a1 = question.getAnswer1();
            String a2 = question.getAnswer2();
            String a3 = question.getAnswer3();
            String a4 = question.getAnswer4();
            String correctA = question.getCorrectAnswer();
            int createdBy = question.getCreatedBy();
            Date createdDate = question.getCreatedDate();
            
            Object[] row = {id, content, a1, a2, a3, a4, correctA, createdBy, createdDate};
            questionModel.addRow(row);
        }
    }
    
    public void loadSelectedQuestionList(){
        selectedQuestionModel = new DefaultTableModel();
        tableSelectedQuest.setModel(selectedQuestionModel);
        tableSelectedQuest.setDefaultEditor(Object.class, null);
        tableSelectedQuest.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        selectedQuestionModel.addColumn("ID");
        selectedQuestionModel.addColumn("Content");
        
        //Customize size of columns
        TableColumnModel columnModel = tableSelectedQuest.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(900);
    }

    public void addPerformed(JTable tb){
        Exam exam = new Exam();
        exam.setExamId(tfExamID.getText());
        exam.setDuration(tfDuration.getText());
        exam.setGrade(Integer.parseInt(cb1.getSelectedItem().toString()));
        exam.setExamName(tfExamName.getText());
        // Chuyển đổi tfDate sang java.sql.Date
        String dateString = tfDate.getText(); // Lấy giá trị chuỗi từ tfDate
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày tháng
        try {
            java.util.Date parsedDate = dateFormat.parse(dateString); // Parse chuỗi sang java.util.Date
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // Chuyển sang java.sql.Date
            exam.setCreatedDate(sqlDate); // Đặt giá trị vào exam
        } catch (ParseException e) {
            e.printStackTrace();
            // Bạn có thể thêm xử lý lỗi nếu cần, ví dụ hiển thị thông báo lỗi
        }
        // Lấy giá trị của radio button (Public/Private)
        String status = "Private";  //Mặc định là Private
        if(buttonPublic.isSelected())
            status = "Public";
        exam.setStatus(status);
        exam.setCreatorId(Integer.parseInt(tfID.getText()));
        
        if(examController.saveExam(exam, tableSelectedQuest))
            JOptionPane.showMessageDialog(this, "Create Exam successfully!!");
        else
            JOptionPane.showMessageDialog(this, "Create Exam failed!!");
        
    }
    
    public void searchPerformed(JTable tb) {
        String searchContent = tfSearch.getText().trim().toLowerCase();
        DefaultTableModel searchModel = new DefaultTableModel();

        // Tạo model mới cho bảng kết quả tìm kiếm
        searchModel.addColumn("ID");
        searchModel.addColumn("Content");
        searchModel.addColumn("Answer 1");
        searchModel.addColumn("Answer 2");
        searchModel.addColumn("Answer 3");
        searchModel.addColumn("Answer 4");
        searchModel.addColumn("Correct Answer");
        searchModel.addColumn("Created by");
        searchModel.addColumn("Created date");

        // Lọc danh sách câu hỏi dựa trên từ khóa tìm kiếm
        for (Question question : questArr) {
            if (question.getQuestionContent().toLowerCase().contains(searchContent) ||
                question.getAnswer1().toLowerCase().contains(searchContent) ||
                question.getAnswer2().toLowerCase().contains(searchContent) ||
                question.getAnswer3().toLowerCase().contains(searchContent) ||
                question.getAnswer4().toLowerCase().contains(searchContent)) {

                Object[] row = {
                    question.getQuestionID(),
                    question.getQuestionContent(),
                    question.getAnswer1(),
                    question.getAnswer2(),
                    question.getAnswer3(),
                    question.getAnswer4(),
                    question.getCorrectAnswer(),
                    question.getCreatedBy(),
                    question.getCreatedDate()
                };
                searchModel.addRow(row);
            }
        }

        // Cập nhật lại bảng hiển thị với kết quả tìm kiếm
        tb.setModel(searchModel);
    }

    public void refreshSearchPerformed(JTable tb){
        questionModel.setRowCount(0);
        loadQuestionList();
        tfSearch.setText("");
        cb2.setSelectedItem("10");
    }
    
    public void refreshInfoPerformed(JTable tb){
        questionModel.setRowCount(0);
        loadQuestionList();
        tfExamName.setText("");
        tfDuration.setText("");
        tfExamID.setText("");
        cb1.setSelectedItem("10");
        cb2.setSelectedItem("10");
//        cb3.setSelectedItem(topic.get(0)); //Lấy phần tử đầu trong ArrayList topic
        
    }
    
    public void refreshSelectedQuestion(JTable tb){
        selectedQuestionModel.setRowCount(0);
    }
    
    public void addToSelectedQuestionTable(int id, String content){
        //Check if ID already exists in tableSelectedQuest
        for(int i=0;i<selectedQuestionModel.getRowCount();i++){
            if(selectedQuestionModel.getValueAt(i,0).toString().equals(id)){
                JOptionPane.showMessageDialog(this, "Question selected");
                return;
            }
        }
        
        //Add new row to tableSelectedQuest
        Object[] row = {id, content};
        selectedQuestionModel.addRow(row);
    }
    
//    public void loadTopic(){
//        topic = questionDAO.getTopic(); // Lấy danh sách topic từ QuestionDAO
//        cb3.removeAllItems(); // Xóa các mục cũ trong combobox
//        for(String topicName: topic){
//            cb3.addItem(topicName); // Thêm các topic vào combobox
//        }
//    }
    
    public void setTeacherID(String teacherID){
        tfID.setText(teacherID);
        System.out.println(tfID.getText() + ", I'm tfID of CreateExamView, at line 494 of setTeacherID() method");
    }
    
    public String createExamID(String creatorID, String grade, String duration){
         String prefix = "TC" + creatorID + "G" + grade + "D" + duration;
        Random ran = new Random();
        int randomNum = ran.nextInt(1000); // Tạo số ngẫu nhiên trong khoảng [0, 999]
        String examID = prefix + String.format("%3d",randomNum);
        return examID;
    }
}
