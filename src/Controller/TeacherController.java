/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Teacher;
//import
import View.*;

import java.sql.Date;
/**
 *
 * @author ACER
 */
public class TeacherController {
    // declaring the variables model and view  
    private Teacher model;
    private TeacherView Teacherview;
    private AdminView Adminview;
    
    // constructor to initialize for teacher
    public TeacherController(Teacher model, TeacherView Teacherview){
        this.model = model;
        this.Teacherview = Teacherview;
    }
    public TeacherController(){
        
    }


    
    //get, set
    public String getTeacherId(){
        return model.getTeacherId();
    }
    public void setTeacherId(String teacherId){
        model.setTeacherId(teacherId);
    }
    
    public String getTeacherName(){
        return model.getTeacherName();
    }
    public void setTeacherName(String teacherName){
        model.setTeacherName(teacherName);
    }
    
    public Date getTeacherDateOfBirth(){
        return model.getDateOfBirth();
    }
    public void setTeacherDateOfBirth(Date doB){
        model.setDateOfBirth(doB);
    }
    
    public String getTeacherGender(){
        return model.getGender();
    }
    public void setTeacherGender(String gender){
        model.setGender(gender);
    }

    public String getTeacherEmail(){
        return model.getEmail();
    }
    public void setTeacherEmail(String email){
        model.setEmail(email);
    }
    
    public String getTeacherPassword(){
        return model.getPassword();
    }
    public void setTeacherPassword(String password){
        model.setPassword(password);
    }
    
    public String getTeacherRole(){
        return model.getRole();
    }
    public void setTeacherRole(String role){
        model.setRole(role);
    }
    
    public String getTeacherStatus(){
        return model.getStatus();
    }
    public void setTeacherStatus(String status){
        model.setStatus(status);
    }
    
    
    //method to update the data to view
    public void updateTeacherView(){
//        view.TeacherView(model.getTeacherId(), model.getTeacherName(), model.getRole());
        Teacherview.initComponents();
    }
    
    public void transferData(String id) {
        CreateExamView createExamView = new CreateExamView(id);
        createExamView.setTeacherID(id); // Gán giá trị id
        CreateQuestionView createQuestionView = new CreateQuestionView(id);
        createQuestionView.setTeacherID(id);
        ManageExamView manageExamView = new ManageExamView(id);


        System.out.println(id + ", I'm at line 99 of TeacherController");
    }
    

    
}


