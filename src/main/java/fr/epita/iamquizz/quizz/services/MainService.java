/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epita.iamquizz.quizz.services;

import java.util.ArrayList;
import fr.epita.iamquizz.quizz.beans.QuizQuestion;
import fr.epita.iamquizz.quizz.beans.Quiz;
import fr.epita.iamquizz.quizz.beans.Result;
import fr.epita.iamquizz.quizz.beans.AppUser;
/**
 *
 * @author shivasairajuomkar
 */
public interface MainService {

    public AppUser checklogin(AppUser user);

    public void saveQuestion(QuizQuestion qstion);

    public ArrayList<QuizQuestion> getQuestions(String userId);

    public void saveQuiz(Quiz q, String[] questionsids);

    public ArrayList<Quiz> getallquizList();

    public ArrayList<QuizQuestion> getQuestionsbyqzid(String qzid);

    public boolean insertsubmitedAns(String question_id, String[] answers, String qzid, int uid);

    public void saveResult(String userId, String qzid, int totalquestions, int correct_answers, String quizname);
   

    public boolean saveuser(String name, AppUser u);

    public ArrayList<Result> getDashbardData();

}