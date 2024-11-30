/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author ACER
 */
public class Teacher {
    private String teacherId;
    private String teacherName;
    private Date dateOfBirth;
    private String gender;
    private String email;
    private String password;
    private String role;
    private String status;
    
    //Constructor
    public Teacher(){
        
    }

    public Teacher(String teacherId, String teacherName, Date dateOfBirth, String gender, String email, String password, String role, String status) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    
    
    //get, set

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}


//    Trường hợp input vào là 1 chuỗi
//    public void setDateOfBirth(String dateStr) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");    //khi nhập ngày tháng vào thì kdl sẽ là String, ta cần convert nó sang dạng Date trước khi truyền xuống database. Kiểu dữ liệu trả về là Date thuộc java.util.Date, chưa thể dùng ngay mà ta cần phải chuyển tiếp về Date của java.sql.Date
//        try{
//            java.util.Date utilDate = formatter.parse(dateStr);
//            this.dateOfBirth = new Date(utilDate.getTime()); // Chuyển từ java.util.Date sang java.sql.Date
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }

