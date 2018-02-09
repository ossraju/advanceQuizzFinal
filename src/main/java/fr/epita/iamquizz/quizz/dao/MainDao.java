/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epita.iamquizz.quizz.dao;


import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import fr.epita.iamquizz.quizz.beans.AppUser;
import fr.epita.iamquizz.quizz.beans.Student;
import fr.epita.iamquizz.quizz.beans.Result;
import fr.epita.iamquizz.quizz.beans.Quiz;
import fr.epita.iamquizz.quizz.beans.ProfessorUser;
import fr.epita.iamquizz.quizz.beans.QuizQuestion;


/**
 *
 * @author shivasairajuomkar
 */
public interface MainDao {
    public AppUser getUserstatus(String userid,String password);

    public void saveQuestion(QuizQuestion qstion);

    public ArrayList<QuizQuestion> getQuestions(String userId);

    public void saveqz(Quiz qz, String[] questionsids);

    public ArrayList<Quiz> getallquizList();

    public ArrayList<QuizQuestion> getQuestionsByid(String qzid);

    public boolean evaluteandinsert(String question_id, String[] answers,String qzid,int uid);
    public void saveResult(String userId, String qzid, int totalquestions, int correct_answers,String quizname);

    public boolean saveprofessor(AppUser u, ProfessorUser p);

    public boolean savestudent(AppUser u, Student s);

    public ArrayList<Result> getDashbardData();
}
