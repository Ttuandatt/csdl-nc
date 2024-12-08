/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Exam;
import Model.Question;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


/**
 *
 * @author ACER
 */
public class ExamDAO{

    JDBCConnection jdbc = new JDBCConnection();

    public ArrayList<Exam> getAll(String grade, int creatorID) {
        ArrayList<Exam> examArr = new ArrayList<>();
        if(jdbc.openConnection()){
            try{
                String query = "select * from exam where grade=? and createdBy=? or status!='Private'";
                PreparedStatement ps = jdbc.getConnection().prepareStatement(query);
                ps.setString(1, grade);
                ps.setInt(2, creatorID);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Exam exam = new Exam();
                    exam.setExamId(rs.getString(1));
                    exam.setDuration(rs.getString(2));
                    exam.setGrade(rs.getInt(3));
                    exam.setExamName(rs.getString(4));
                    exam.setCreatedDate(rs.getDate(5));
                    exam.setStatus(rs.getString(6));
                    exam.setCreatorId(rs.getInt(7));

                    examArr.add(exam);

                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                jdbc.closeConnection();
            }
        }
        return examArr;
    }

    public boolean add(Exam exam, List<String> questionIDs) {
        boolean result = false;
        if(jdbc.openConnection()){
            try{
                String query = "insert into exam values (?,?,?,?,?,?,?)";
                PreparedStatement ps = jdbc.getConnection().prepareStatement(query);
                ps.setString(1, exam.getExamId());
                ps.setString(2, exam.getDuration());
                ps.setInt(3, exam.getGrade());
                ps.setString(4, exam.getExamName());
                ps.setDate(5, exam.getCreatedDate());
                ps.setString(6, exam.getStatus());
                ps.setInt(7, exam.getCreatorId());
                
                
                if(ps.executeUpdate()>0){
                    if(addSelectedQuestions(exam.getExamId(), questionIDs))
                        result = true;
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                jdbc.closeConnection();
            }
        }
        return result;
    }

    public boolean addSelectedQuestions(String examID, List<String> questionIDs) {
        boolean result = false;
        if (jdbc.openConnection()) {
            try {
                String query = "INSERT INTO exam_question VALUES (?, ?)";
                PreparedStatement ps = jdbc.getConnection().prepareStatement(query);
                for (String questionID : questionIDs) {
                    ps.setString(1, examID);
                    ps.setString(2, questionID);
                    ps.addBatch(); // Add to batch for efficient execution
                }
                int[] results = ps.executeBatch(); // Execute batch
                for (int res : results) {
                    if (res <= 0) {
                        return false; // If any insert fails, return false
                    }
                }
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jdbc.closeConnection();
            }
        }
        return result;
    }

    
}
