/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.ExamDAO;
import Model.Exam;
import Model.Question;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class ExamController {
    private ExamDAO examDAO;

    public ExamController() {}
    public ExamController(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    public ArrayList<Exam> getAllExam(String grade, int creatorID){
        return examDAO.getAll(grade, creatorID);
    }

    public boolean saveExam(Exam exam, JTable tableSelectedQuest) {
        // Collect question IDs from the selected table
        List<String> selectedQuestionIDs = new ArrayList<>();
        for (int i = 0; i < tableSelectedQuest.getRowCount(); i++) {
            String questionID = tableSelectedQuest.getValueAt(i, 0).toString(); // Assuming column 0 contains questionID
            selectedQuestionIDs.add(questionID);
        }

        // Call DAO to save exam and selected questions
        ExamDAO examDAO = new ExamDAO();
        return examDAO.add(exam, selectedQuestionIDs);
    }
}
