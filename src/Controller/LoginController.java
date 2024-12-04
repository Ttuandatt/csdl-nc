/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Teacher;
import DAO.UserDAO;
import View.ExamView;
import View.LoginView;
import View.TeacherView;
/**
 *
 * @author ACER
 */
public class LoginController {
    private Teacher teacher;
    private UserDAO userDAO;
    private LoginView loginView;
    private TeacherView view;
    private ExamView examView;
    public LoginController(){
        userDAO = new UserDAO();
    }
    
    //trả kết quả login
    public int login(String email, String password){
        return userDAO.checkLoginAndGetInfo(email, password);
    }
    
    public Teacher getUserInfo(String email, String password){
        return userDAO.getUserInfo(email, password);
    }
    
    //Set user id vào creator ID cho chức năng tạo exam
    public void transferDataToExamView(String id) {
        if (view != null) {
//            String teacherID = view.getTeacherID();  // Không bị nullPointerException nữa
            // Tiếp tục với việc sử dụng teacherID
            examView = new ExamView(id);
            examView.setTeacherID(id);
            System.out.println("I'm at transferDataToExamView method of LoginController");
        } else {
            System.out.println("View is null, cannot transfer data.");
        }
    }
}
