package Controller;

import Model.Teacher;
import View.*;

import java.sql.Date;

public class AdminController {
    // declaring the variables model and view
    private Teacher model;
    private AdminView Adminview;

    // constructor to initialize for teacher
    public AdminController(Teacher model, AdminView Adminview){
        this.model = model;
        this.Adminview = Adminview;
    }
    public AdminController(){

    }

    //get, set
    public String getAdminId(){
        return model.getTeacherId();
    }
    public void setAdminId(String teacherId){
        model.setTeacherId(teacherId);
    }

    public String getAdminName(){
        return model.getTeacherName();
    }
    public void setAdminName(String teacherName){
        model.setTeacherName(teacherName);
    }

    public Date getAdminDateOfBirth(){
        return model.getDateOfBirth();
    }
    public void setAdminDateOfBirth(Date doB){
        model.setDateOfBirth(doB);
    }

    public String getAdminGender(){
        return model.getGender();
    }
    public void setAdminGender(String gender){
        model.setGender(gender);
    }

    public String getAdminEmail(){
        return model.getEmail();
    }
    public void setAdminEmail(String email){
        model.setEmail(email);
    }

    public String getAdminPassword(){
        return model.getPassword();
    }
    public void setAdminPassword(String password){
        model.setPassword(password);
    }

    public String getAdminRole(){
        return model.getRole();
    }
    public void setAdminRole(String role){
        model.setRole(role);
    }

    public String getAdminStatus(){
        return model.getStatus();
    }
    public void setAdminStatus(String status){
        model.setStatus(status);
    }

    public void transferData(String id) {
        CreateExamView createExamView = new CreateExamView(id);
        createExamView.setTeacherID(id); // Gán giá trị id
        CreateQuestionView createQuestionView = new CreateQuestionView(id);
        createQuestionView.setTeacherID(id);
        System.out.println(id + ", I'm at line 84 in transferData(String id) method of AdminController");
        ManageExamView manageExamView = new ManageExamView(id);

    }

}
