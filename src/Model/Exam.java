/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author ACER
 */
public class Exam {
    private String examId;
    private String duration;
    private int grade;
    private String examName;
    private Date createdDate;
    private int creatorId;
    private String status;
    
    //Constructor
    public Exam(){}

    public Exam(String examId, String duration, int grade, String examName, Date createdDate, int creatorId, String status) {
        this.examId = examId;
        this.duration = duration;
        this.grade = grade;
        this.examName = examName;
        this.createdDate = createdDate;
        this.status = status;
        this.creatorId = creatorId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
    
    
    
}
