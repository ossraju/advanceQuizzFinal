/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epita.iamquizz.quizz.services;






import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.iamquizz.quizz.beans.QuizQuestion;
import fr.epita.iamquizz.quizz.beans.ProfessorUser;
import fr.epita.iamquizz.quizz.beans.Quiz;
import fr.epita.iamquizz.quizz.beans.Result;
import fr.epita.iamquizz.quizz.beans.Student;
import fr.epita.iamquizz.quizz.beans.AppUser;
import fr.epita.iamquizz.quizz.dao.MainDao;




/**
 *
 * @author shivasairajuomkar
 */
@Service
public class MainServiceImpl implements MainService {

    @Autowired
    MainDao dao;

    @Override
    @Transactional
    public AppUser checklogin(AppUser usr) {

        String usrid = usr.getUserLoginId();
        String usrpassword = usr.getUserpassword();
        return dao.getUserstatus(usrid, usrpassword);

    }

    @Override
    @Transactional
    public void saveQuestion(QuizQuestion qstion) {
        dao.saveQuestion(qstion);

    }

    @Override
    @Transactional
    public ArrayList<QuizQuestion> getQuestions(String userId) {

        return dao.getQuestions(userId);
    }

    @Override
    @Transactional
    public void saveQuiz(Quiz qz, String[] questionsids) {
        dao.saveqz(qz, questionsids);

    }

    @Override
    @Transactional
    public ArrayList<Quiz> getallquizList() {
        return dao.getallquizList();
    }

    @Override
    @Transactional
    public ArrayList<QuizQuestion> getQuestionsbyqzid(String qzid) {
        return dao.getQuestionsByid(qzid);
    }

    @Override
    @Transactional
    public boolean insertsubmitedAns(String question_id, String[] answers, String qzid, int uid) {
        return dao.evaluteandinsert(question_id, answers, qzid, uid);
    }

    @Override
    @Transactional
    public void saveResult(String userId, String qzid, int totalquestions, int correct_answers, String quizname) {
        dao.saveResult(userId, qzid, totalquestions, correct_answers, quizname);
    }

    @Override
    @Transactional
    public boolean saveuser(String name, AppUser u) {
        if (u.getUserRole().equalsIgnoreCase("professor")) {
            ProfessorUser p = new ProfessorUser();
            p.setProfessorDesg(u.getUserRole());
            p.setProfessorName(name);

            return dao.saveprofessor(u, p);
        } else {

            Student s = new Student();
            s.setSdgid("student");
            s.setStudentname(name);

            return dao.savestudent(u, s);
        }
    }

    @Override
    @Transactional
    public ArrayList<Result> getDashbardData() {
        return dao.getDashbardData();
    }

}