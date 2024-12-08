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
                    question.setQuestionID(rs.getInt(1));
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
                    question.setCreatedBy(rs.getInt(13));
                    question.setVerifiedBy(rs.getInt(14));
                    question.setTopicID(rs.getInt(15));
                    
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

    public int getQuestionOrder(){
        int order = 0;
        if(jdbc.openConnection()){
            try{
                String query = "SELECT questionID FROM questions ORDER BY questionID DESC LIMIT 1";
                ResultSet rs = jdbc.getConnection().prepareStatement(query).executeQuery();
                if(rs.next()){
                    order = rs.getInt(1) + 1;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                jdbc.closeConnection();
            }
        }
        return order;
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
        boolean result = false;
        if(jdbc.openConnection()){
            try{
                String query = "insert into questions values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = jdbc.getConnection().prepareStatement(query);
                ps.setInt(1, d.getQuestionID());
                ps.setString(2, d.getQuestionContent());
                ps.setString(3, d.getAnswer1());
                ps.setString(4, d.getAnswer2());
                ps.setString(5, d.getAnswer3());
                ps.setString(6, d.getAnswer4());
                ps.setString(7, d.getCorrectAnswer());
                ps.setInt(8, d.getGrade());
                ps.setString(9, d.getQuestionType());
                ps.setDate(10, d.getCreatedDate());
                ps.setString(11, d.getDataSource());
                ps.setString(12, d.getStatus());
                ps.setInt(13, d.getCreatedBy());
                ps.setNull(14, java.sql.Types.INTEGER);
                ps.setInt(15, d.getTopicID());

                if(ps.executeUpdate()>0)
                    result = true;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                jdbc.closeConnection();
            }
        }
        return result;
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
