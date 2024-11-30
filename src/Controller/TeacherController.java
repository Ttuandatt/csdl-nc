/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Teacher;
import View.TeacherView;
import java.sql.Date;
/**
 *
 * @author ACER
 */
public class TeacherController {
    // declaring the variables model and view  
    private Teacher model;
    private TeacherView view;

    // constructor to initialize  
    public TeacherController(Teacher model, TeacherView view){
        this.model = model;
        this.view = view;
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
    public void updateView(){
//        view.TeacherView(model.getTeacherId(), model.getTeacherName(), model.getRole());
        view.initComponents();
    }
}


