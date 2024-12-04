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


/**
 *
 * @author ACER
 */
public class ExamDAO{

    JDBCConnection jdbc = new JDBCConnection();
    

    public boolean add(Exam exam, String questionID) {
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
                    if(addSelectedQuestion(exam.getExamId(), questionID))
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
    
    public boolean addSelectedQuestion(String examID, String questionID){
        boolean result = false;
        if(jdbc.openConnection()){
            try{
                String query = "insert into exam_question values(?,?)";
                PreparedStatement ps = jdbc.getConnection().prepareStatement(query);
                ps.setString(1, examID);
                ps.setString(2, questionID);
                if(ps.executeUpdate()>0)
                    return true;
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                jdbc.closeConnection();
            }   
        }
        return result;
    }

    
}
