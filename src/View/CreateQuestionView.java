package View;

import Controller.QuestionController;
import DAO.QuestionDAO;
import Model.Question;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CreateQuestionView extends JPanel {
    JTable questionTable = new JTable();
    QuestionController questionController = new QuestionController();
    QuestionDAO questionDAO = new QuestionDAO();
    JTable tableQuest = new JTable();
    DefaultTableModel questionModel;
    JComboBox cb1 = new JComboBox();
    JComboBox cb2 = new JComboBox();
    JComboBox cb3 = new JComboBox();
    JComboBox cb4 = new JComboBox();
    ArrayList<Question> questArr = new ArrayList<Question>();
    ArrayList<String> topic = new ArrayList<>();
    JLabel lbCreatorID, lbExamID, lbDate, lbGrade, lbSource, lbQuestion, lbA,lbB,lbC,lbD, lbCorrect, lbTopic;
    JTextField tfCreatorID, tfQuestionID, tfDate, tfSource, tfQuestion, tfA, tfB, tfC, tfD, tfSearch;
    JRadioButton buttonMP, buttonTF;
    JButton buttonSearch, buttonRefresh, buttonUpdate, buttonDelete, buttonCreate;

    public CreateQuestionView(String teacherID) {
        initComponents();
        questionController = new QuestionController(questionDAO);
        loadQuestionList();
        loadTopic();
        setTeacherID(teacherID);
        setExamID();
    }

    public void initComponents() {
        this.setLayout(new GridBagLayout());

        //Create panels
        JPanel topPanel, bottomPanel;
        topPanel = new JPanel(null);
        topPanel.setBackground(Color.decode("#EBFCFF"));
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.white);

        // GridBagConstraints for upPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 0.4; // Ratio 40%
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(topPanel, gbc);

        // GridBagConstraints for downPanel
        gbc.weighty = 0.6; // Ratio 60%
        gbc.gridy = 1;
        add(bottomPanel, gbc);

        // Add table into JScrollPane and customize
        JScrollPane sp1 = new JScrollPane(tableQuest);
        bottomPanel.add(sp1, BorderLayout.CENTER);

        lbCreatorID = new JLabel("Creator ID:");
        lbCreatorID.setBounds(10,10,80,20);
        topPanel.add(lbCreatorID);
        tfCreatorID = new JTextField();
        tfCreatorID.setEditable(false);
        tfCreatorID.setBounds(75,10,50,20);
        topPanel.add(tfCreatorID);

        lbExamID = new JLabel("Exam ID:");
        lbExamID.setBounds(150,10,100,20);
        topPanel.add(lbExamID);
        tfQuestionID = new JTextField();
        tfQuestionID.setEditable(false);
        tfQuestionID.setBounds(200,10,50,20);
        setExamID();
        topPanel.add(tfQuestionID);

        lbDate = new JLabel("Create date:");
        lbDate.setBounds(10,35,100,20);
        topPanel.add(lbDate);
        tfDate = new JTextField();
        tfDate.setEditable(false);
        tfDate.setBounds(75,35,100,20);
        topPanel.add(tfDate);
        // Định dạng ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Lấy ngày hiện tại
        String currentDate = LocalDate.now().format(formatter);
        tfDate.setText(currentDate); // Tự động điền ngày hiện tại vào tfDate


        lbGrade = new JLabel("Grade:");
        lbGrade.setBounds(10,60,50,20);
        topPanel.add(lbGrade);
        String[] combo1 = {"10","11","12"};
        cb1 = new JComboBox(combo1);
        cb1.setBounds(75,60,100,20);
        topPanel.add(cb1);

        buttonMP = new JRadioButton("MP");
        buttonMP.setBounds(10,110,50,20);
        topPanel.add(buttonMP);
        buttonTF = new JRadioButton("TF");
        buttonTF.setBounds(50,110,50,20);
        topPanel.add(buttonTF);

        // Nhóm các radio button lại với nhau, chỉ có thể chọn 1 trong hai radio button
        ButtonGroup group = new ButtonGroup();
        group.add(buttonMP);
        group.add(buttonTF);

        lbSource = new JLabel("Source:");
        lbSource.setBounds(10,85,100,20);
        topPanel.add(lbSource);
        tfSource = new JTextField();
        tfSource.setBounds(75,85,200,20);
        topPanel.add(tfSource);

        //Add Question,A,B,C,D textfields
        lbQuestion = new JLabel("Question:");
        lbQuestion.setBounds(360,10,100,20);
        topPanel.add(lbQuestion);
        tfQuestion = new JTextField();
        tfQuestion.setBounds(415,10,300,20);
        topPanel.add(tfQuestion);
        lbA = new JLabel("Answer 1");
        lbA.setBounds(360,35,100,20);
        topPanel.add(lbA);
        tfA = new JTextField();
        tfA.setBounds(415,35,300,20);
        topPanel.add(tfA);
        lbB = new JLabel("Answer 2");
        lbB.setBounds(360,60,100,20);
        topPanel.add(lbB);
        tfB = new JTextField();
        tfB.setBounds(415,60,300,20);
        topPanel.add(tfB);
        lbC = new JLabel("Answer 3");
        lbC.setBounds(360,85,100,20);
        topPanel.add(lbC);
        tfC = new JTextField();
        tfC.setBounds(415,85,300,20);
        topPanel.add(tfC);
        lbD = new JLabel("Answer 4");
        lbD.setBounds(360,110,100,20);
        topPanel.add(lbD);
        tfD = new JTextField();
        tfD.setBounds(415,110,300,20);
        topPanel.add(tfD);

        lbCorrect = new JLabel("Correct Answer:");
        lbCorrect.setBounds(800,10,100,20);
        topPanel.add(lbCorrect);
        String[] combo4 = {""};
        cb4 = new JComboBox(combo4);
        cb4.setBounds(885, 10, 80, 20);
        topPanel.add(cb4);

        lbTopic = new JLabel("Topic:");
        lbTopic.setBounds(800,35,100,20);
        topPanel.add(lbTopic);
        String[] combo3 = {""};
        cb3 = new JComboBox(combo3);
        cb3.setBounds(835, 35, 200, 20);
        topPanel.add(cb3);

        buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(800, 60,80,20);
        topPanel.add(buttonUpdate);

        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(880, 60,70,20);
        topPanel.add(buttonDelete);

        buttonCreate = new JButton("Create");
        buttonCreate.setBounds(950, 60,80,20);
        topPanel.add(buttonCreate);

        tfSearch = new JTextField();
        tfSearch.setBounds(800,110,200,20);
        topPanel.add(tfSearch);

        buttonSearch = new JButton("Search");
        buttonSearch.setBounds(1000, 110, 70,20);
        topPanel.add(buttonSearch);

        buttonRefresh = new JButton("Refresh");
        buttonRefresh.setBounds(1070, 110, 80,20);
        topPanel.add(buttonRefresh);

        String[] combo2 = {"Approve","Reject"};
        cb2 = new JComboBox(combo2);
        cb2.setBounds(1170,110,90,20);
        topPanel.add(cb2);

        cb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadQuestionList();
            }
        });

        //Nếu nút TF được chọn, tức là đây là câu hỏi true/false thì disable 2 textfield Answer 3&4 và set correct answer là True/False
        buttonTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buttonTF.isSelected()){
                    cb4.removeAllItems();
                    tfC.setEditable(false);
                    tfD.setEditable(false);
                    cb4.addItem("Đúng");
                    cb4.addItem("Sai");
                }
            }
        });

        //Nếu nút MP được chọn, tức là đây là câu hỏi Multiple Choice, thì set correct answer là "A","B", "C", "D"
        buttonMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buttonMP.isSelected()){
                    tfC.setEditable(true);
                    tfD.setEditable(true);
                    cb4.removeAllItems();
                    cb4.addItem("A");
                    cb4.addItem("B");
                    cb4.addItem("C");
                    cb4.addItem("D");
                }
            }
        });

        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addQuestionPerformed(tableQuest);
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePerformed(tableQuest);
            }
        });

        buttonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh(tableQuest);
            }
        });
    }
    public void loadQuestionList() {
        // Xóa dữ liệu cũ và cột trong model
        questionModel = new DefaultTableModel(); // Tạo model mới để tránh lặp cột
        tableQuest.setModel(questionModel);
        tableQuest.setDefaultEditor(Object.class, null);    //Khong cho chinh cac cell trong bang
        questionModel.addColumn("ID");
        questionModel.addColumn("Content");
        questionModel.addColumn("AnswerA");
        questionModel.addColumn("AnswerB");
        questionModel.addColumn("AnswerC");
        questionModel.addColumn("AnswerD");
        questionModel.addColumn("CorrectAnswer");
        questionModel.addColumn("Grade");
        questionModel.addColumn("Type");
        questionModel.addColumn("CreatedDate");
        questionModel.addColumn("Source");
        questionModel.addColumn("Status");
        questionModel.addColumn("CreatedBy");
        questionModel.addColumn("VerifiedBy");
        questionModel.addColumn("TopicID");

        // Lấy giá trị được chọn từ combo box cb2
        String selectedGrade = cb1.getSelectedItem().toString();
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
            int grade = question.getGrade();
            String type = question.getQuestionType();
            Date date = question.getCreatedDate();
            String source = question.getDataSource();
            String status = question.getStatus();
            int createdBy = question.getCreatedBy();
            int verifiedBy = question.getVerifiedBy();
            int topicID = question.getTopicID();

            Object[] row = {id, content, a1,a2,a3,a4,correctA,grade,type,date,source,status,createdBy,verifiedBy,topicID};
            questionModel.addRow(row);
        }
    }
    public void loadTopic(){
        topic = questionDAO.getTopic(); // Lấy danh sách topic từ QuestionDAO
        cb3.removeAllItems(); // Xóa các mục cũ trong combobox
        for(String topicName: topic){
            cb3.addItem(topicName); // Thêm các topic vào combobox
        }
    }
    public void setTeacherID(String teacherID){
        tfCreatorID.setText(teacherID);
        System.out.println(tfCreatorID.getText() + ", I'm tfID of CreateQuestionView at setTeacherID");
    }

    public void setExamID(){
        tfQuestionID.setText(String.valueOf(questionController.getQuestionOrder()));
        System.out.println(tfQuestionID.getText() + ", I'm tfID of CreateQuestionView at setExamID method");
    }

    public void addQuestionPerformed(JTable table){
        Question question = new Question();
        question.setQuestionID(Integer.parseInt(tfQuestionID.getText()));
        question.setQuestionContent(tfQuestion.getText());
        question.setAnswer1(tfA.getText());
        question.setAnswer2(tfB.getText());
        question.setAnswer3(tfC.getText());
        question.setAnswer4(tfD.getText());
        if(cb4.getSelectedItem().equals("Đúng"))
            question.setCorrectAnswer("A");
        else
            question.setCorrectAnswer("B");
        question.setGrade(Integer.parseInt(cb1.getSelectedItem().toString()));
        if(buttonMP.isSelected())
            question.setQuestionType("MP");
        else
            question.setQuestionType("TF");
        question.setCreatedDate(Date.valueOf(tfDate.getText()));
        question.setDataSource(tfSource.getText());
        question.setStatus("wait");
        question.setCreatedBy(Integer.parseInt(tfCreatorID.getText()));
        question.setVerifiedBy(0);
        question.setTopicID(1);

        if(questionController.saveQuestion(question))
            JOptionPane.showMessageDialog(this, "Question Saved Successfully");
        else
            JOptionPane.showMessageDialog(this, "Question Saving Failed");

    }

    public void updatePerformed(JTable tb){

    }

    public void refresh(JTable tb){
        questionModel.setRowCount(0);
        loadQuestionList();
        tfQuestionID.setText("");
        tfSource.setText("");
        tfQuestion.setText("");
        tfA.setText("");
        tfB.setText("");
        tfC.setText("");
        tfD.setText("");
        cb4.setSelectedIndex(0);
        tfSearch.setText("");
    }

}
