/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Question;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ACER
 */
public class QuestionDAO implements DAOInterface<Question>{

    JDBCConnection jdbc = new JDBCConnection();
    
    public ArrayList<Question> getAll(String grade) {
        ArrayList<Question> quesArr = new ArrayList<>();
        if(jdbc.openConnection()){
            try{
                String query = "select * from questions where grade=?";
                PreparedStatement ps = jdbc.getConnection().prepareStatement(query);
                ps.setString(1, grade);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Question question = new Question();
                    question.setQuestionID(rs.getString(1));
                    question.setQuestionContent(rs.getString(2));
                    question.setAnswer1(rs.getString(3));
                    question.setAnswer2(rs.getString(4));
                    question.setAnswer3(rs.getString(5));
                    question.setAnswer4(rs.getString(6));
                    question.setCorrectAnswer(rs.getString(7));
                    question.setGrade(rs.getInt(8));
                    question.setQuestionType(rs.getString(9));
                    question.setCreatedDate(rs.getDate(10));
                    question.setDataSource(rs.getString(11));
                    question.setStatus(rs.getString(12));
                    question.setCreatedBy(rs.getString(13));
                    question.setVerifiedBy(rs.getString(14));
                    question.setTopicID(rs.getString(15));
                    
                    quesArr.add(question);
                
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                jdbc.closeConnection();
            }
        }
        return quesArr;
    }

    public ArrayList<String> getTopic(){
        ArrayList<String> topicArr = new ArrayList<>();
        if(jdbc.openConnection()){
            try{
                String query = "select topicName from topics";
                PreparedStatement ps = jdbc.getConnection().prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    topicArr.add(rs.getString("topicName"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                jdbc.closeConnection();
            }
        }
        return topicArr;
    }
    @Override
    public Question getByID(String d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean has(String d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Question d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Question d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Question> search(String d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
