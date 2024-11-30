/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ACER
 */
public class UserDAO implements DAOInterface<Teacher>{
    // Create a JDBCConnection onject to connect to database
    JDBCConnection jdbc = new JDBCConnection();
    
    @Override
    public ArrayList<Teacher> getAll() {
        ArrayList<Teacher> teacherArr = new ArrayList<>();
        if(jdbc.openConnection()){
            try{
                String query = "select * from user";
                PreparedStatement ps = jdbc.getConnection().prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Teacher tc = new Teacher();
                    tc.setTeacherId(rs.getString(1));
                    tc.setTeacherName(rs.getString(2));
                    tc.setDateOfBirth(rs.getDate(3));
                    tc.setGender(rs.getString(4));
                    tc.setEmail(rs.getString(5));
                    tc.setPassword(rs.getString(6));
                    tc.setRole(rs.getString(7));
                    tc.setStatus(rs.getString(8));
                    
                    teacherArr.add(tc);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return teacherArr;
    }

    @Override
    public Teacher getByID(String d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean has(String d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Teacher d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Teacher d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Teacher> search(String d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
