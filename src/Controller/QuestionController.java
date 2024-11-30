/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Question;
import View.Grade10;
import DAO.QuestionDAO;
import java.util.ArrayList;
/**
 *
 * @author ACER
 */
public class QuestionController {
    private QuestionDAO questionDAO;
    
    //Dependency Injection through Constructor
    public QuestionController(QuestionDAO quesDAO){
        this.questionDAO = quesDAO;
    }
    
    public ArrayList<Question> getAllQuestions(){
        return questionDAO.getAll();
    }
}
