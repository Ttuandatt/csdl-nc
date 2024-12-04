/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.ExamDAO;
import Model.Exam;
/**
 *
 * @author ACER
 */
public class ExamController {
    private ExamDAO examDAO = new ExamDAO();
    
    public boolean addExam(Exam exam, String questionID){
        return examDAO.add(exam, questionID);
    }
}
