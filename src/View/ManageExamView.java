package View;

import Controller.ExamController;
import Controller.TeacherController;
import DAO.ExamDAO;
import Model.Exam;
import Model.Question;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.Date;


public class ManageExamView extends JPanel {
    JTable tableQuest = new JTable();
    JTextField tfSearch;
    JButton buttonSearch, buttonRefresh;
    DefaultTableModel examModel;
    ArrayList<Exam> examArr = new ArrayList<Exam>();
    private ExamDAO examDAO = new ExamDAO();
    private ExamController examController;
    JComboBox cb1;
    String teacherID;

    public ManageExamView(String teacherID) {
        initComponents();
        examController = new ExamController(examDAO);
        this.teacherID = teacherID;
        setTeacherID(teacherID);
        System.out.println(teacherID);
        loadExamList();


    }

    public void initComponents() {
        this.setLayout(new GridBagLayout());

        JPanel topPanel, bottomPanel;
        topPanel = new JPanel(null);
        topPanel.setBackground(Color.decode("#EBFCFF"));
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(topPanel, gbc);
        gbc.weighty = 0.8;
        gbc.gridy = 1;
        this.add(bottomPanel, gbc);

        // Add table into JScrollPane and customize
        JScrollPane sp = new JScrollPane(tableQuest);
        bottomPanel.add(sp, BorderLayout.CENTER);

        JLabel lbGrade = new JLabel("Grade:");
        lbGrade.setBounds(10, 45, 50, 20);
        topPanel.add(lbGrade);

        //Create combo box to choose grade for questions
        String[] combo1 = {"10","11","12"};
        cb1 = new JComboBox(combo1);
        cb1.setBounds(45,45,50,20);
        topPanel.add(cb1);

        //Add search box
        tfSearch = new JTextField();
        tfSearch.setBounds(10, 10,200, 20);
        topPanel.add(tfSearch);
        buttonSearch = new JButton("Search");
        buttonSearch.setBounds(210, 10, 80, 20);
        topPanel.add(buttonSearch);
        buttonRefresh = new JButton("Refresh");
        buttonRefresh.setBounds(290, 10, 80, 20);
        topPanel.add(buttonRefresh);

        //Set actionListener for cb2 to display questions base on grade
        cb1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadExamList();
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
    }

    public void loadExamList(){
        examModel = new DefaultTableModel(); // Tạo model mới để tránh lặp cột
        tableQuest.setModel(examModel);
        tableQuest.setDefaultEditor(Object.class, null);    //Không cho edit các cell trong bảng
        examModel.addColumn("ExamID");
        examModel.addColumn("Duration");
        examModel.addColumn("Grade");
        examModel.addColumn("ExamName");
        examModel.addColumn("CreatedDate");
        examModel.addColumn("Status");
        examModel.addColumn("CreatedBy");


        // Lấy giá trị được chọn từ combo box cb2
        String selectedGrade = cb1.getSelectedItem().toString();
        // Lấy danh sách câu hỏi từ Controller
        TeacherController teacherController = new TeacherController();
        examArr = examController.getAllExam(selectedGrade, Integer.parseInt(teacherID));
        for (int i=0;i<examArr.size();i++) {
            Exam exam = examArr.get(i);
            String examID = exam.getExamId();
            String duration = exam.getDuration();
            int grade = exam.getGrade();
            String examName = exam.getExamName();
            Date createdDate = exam.getCreatedDate();
            String status = exam.getStatus();
            int createdBy = exam.getCreatorId();

            Object[] row = {examID,duration,grade,examName,createdDate,status,createdBy};
            examModel.addRow(row);
        }
    }

    public void searchPerformed(JTable tb) {
        String searchContent = tfSearch.getText().trim().toLowerCase();
        DefaultTableModel searchModel = new DefaultTableModel();

        // Tạo model mới cho bảng kết quả tìm kiếm
        searchModel.addColumn("ExamID");
        searchModel.addColumn("Duration");
        searchModel.addColumn("Grade");
        searchModel.addColumn("ExamName");
        searchModel.addColumn("CreatedDate");
        searchModel.addColumn("Status");
        searchModel.addColumn("Created by");

        // Lọc danh sách câu hỏi dựa trên từ khóa tìm kiếm
        for (Exam exam : examArr) {
            if (exam.getExamId().toLowerCase().contains(searchContent) ||
                    exam.getExamName().toLowerCase().contains(searchContent) ||
                    exam.getDuration().toLowerCase().contains(searchContent) ||
                    exam.getGrade()==Integer.parseInt(searchContent) ||
                    exam.getCreatorId()==Integer.parseInt(searchContent)) {

                Object[] row = {
                        exam.getExamId(),
                        exam.getDuration(),
                        exam.getGrade(),
                        exam.getExamName(),
                        exam.getCreatedDate(),
                        exam.getStatus(),
                        exam.getCreatorId()


                };
                searchModel.addRow(row);
            }
        }

        // Cập nhật lại bảng hiển thị với kết quả tìm kiếm
        tb.setModel(searchModel);
    }

    public void refreshSearchPerformed(JTable tb){
        examModel.setRowCount(0);
        loadExamList();
        tfSearch.setText("");
        cb1.setSelectedItem("10");
    }


    public void setTeacherID(String teacherID){
        this.teacherID = teacherID;
    }
}
