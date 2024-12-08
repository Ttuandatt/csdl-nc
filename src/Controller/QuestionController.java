/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ExamDAO;
import Model.Exam;
import Model.Question;
import DAO.QuestionDAO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class QuestionController {
    private QuestionDAO questionDAO;

    public QuestionController(){}

    //Dependency Injection through Constructor
    public QuestionController(QuestionDAO quesDAO){
        this.questionDAO = quesDAO;
    }
    
    public ArrayList<Question> getAllQuestions(String grade){
        return questionDAO.getAll(grade);
    }

    public int getQuestionOrder(){
        int order = 0;
        questionDAO = new QuestionDAO();
        order = questionDAO.getQuestionOrder();
        return order;
    }

    public boolean saveQuestion(Question question) {
        return questionDAO.add(question);
    }

}
