/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epita.iamquizz.quizz.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import fr.epita.iamquizz.quizz.beans.QuizQuestion;
import fr.epita.iamquizz.quizz.beans.QuizAnswerRecorede;
import fr.epita.iamquizz.quizz.beans.ProfessorUser;
import fr.epita.iamquizz.quizz.beans.Quiz;
import fr.epita.iamquizz.quizz.beans.QuizidtoQuestionMapping;
import fr.epita.iamquizz.quizz.beans.Result;
import fr.epita.iamquizz.quizz.beans.Student;
import fr.epita.iamquizz.quizz.beans.AppUser;


/**
 *
 * @author shivasairajuomkar
 */
@Repository
public class MainDaoImpl implements MainDao {

    @Autowired
    SessionFactory factory;

    @Override
    public AppUser getUserstatus(String userid, String password) {
        System.out.println("fr.epita.iamquizz.quizz.dao.HomeDao.getUserstatus()");

        Session session = factory.getCurrentSession();

        Criteria cr = session.createCriteria(AppUser.class).add(Restrictions.and(Restrictions.eq("userLoginId", userid), Restrictions.eq("userpassword", password)));
        System.out.println("==============" + cr.list());
        List<AppUser> mlist = cr.list();
        if (mlist.size() > 0) {
            return mlist.get(0);
        } else {
            return null;
        }


    }

    @Override
    public void saveQuestion(QuizQuestion qstion) {
        int x = 0;
        Session session = factory.getCurrentSession();
        Criteria cr = session.createCriteria(QuizQuestion.class).setProjection(Projections.max("question_id"));
        if (cr.uniqueResult() != null) {
            x = (Integer) cr.uniqueResult();
        } else {
            x = 0;
        }
        qstion.setQuestion_id(x + 1);
        

        session.save(qstion);
        
    }

    @Override
    public ArrayList<QuizQuestion> getQuestions(String userId) {
        Session session = factory.getCurrentSession();
        Criteria cr = session.createCriteria(QuizQuestion.class).add(Restrictions.and(Restrictions.eq("created_by", userId)));
        System.out.println("==============" + cr.list());
        ArrayList<QuizQuestion> mlist = (ArrayList<QuizQuestion>) cr.list();
        return mlist;
    }

    @Override
    public void saveqz(Quiz qz, String[] questionsids) {

        int x = 0;
        Session session = factory.getCurrentSession();
        Criteria cr = session.createCriteria(Quiz.class).setProjection(Projections.max("quizid"));
        if (cr.uniqueResult() != null) {
            x = (Integer) cr.uniqueResult();
        } else {
            x = 0;
        }
        x = x + 1;
        qz.setQuizid(x);
        session.save(qz);

        Criteria cr1 = session.createCriteria(QuizidtoQuestionMapping.class).setProjection(Projections.max("mappingid"));

        int t = 0;
        if (cr1.uniqueResult() != null) {
            t = (Integer) cr1.uniqueResult();
        } else {
            t = 0;
        }
        for (int i = 0; i < questionsids.length; i++) {
            t++;

            QuizidtoQuestionMapping map = new QuizidtoQuestionMapping();
            map.setMappingid(t);
            map.setQuestionid(questionsids[i]);
            map.setQuizid(x + "");
            session.save(map);
        }

    }

    @Override
    public ArrayList<Quiz> getallquizList() {
        Session session = factory.getCurrentSession();
        Criteria cr = session.createCriteria(Quiz.class);
        ArrayList<Quiz> mlist = (ArrayList) cr.list();
        if (mlist.size() > 0) {
            return mlist;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<QuizQuestion> getQuestionsByid(String qzid) {
        Session session = factory.getCurrentSession();
        Criteria cr1 = session.createCriteria(QuizidtoQuestionMapping.class).add(Restrictions.eq("quizid", qzid));
        ArrayList<QuizidtoQuestionMapping> listqids = (ArrayList<QuizidtoQuestionMapping>) cr1.list();

        ArrayList<QuizQuestion> retlist = new ArrayList<QuizQuestion>();

        for (QuizidtoQuestionMapping map : listqids) {
            try {
                Criteria cr = session.createCriteria(QuizQuestion.class);
                ArrayList<QuizQuestion> qtnlist = (ArrayList<QuizQuestion>) cr.list();
                for (QuizQuestion qn : qtnlist) {
                    if (qn.getQuestion_id() == Integer.parseInt(map.getQuestionid())) {
                        retlist.add(qn);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return retlist;
    }

    @Override
    public boolean evaluteandinsert(String question_id, String[] answers, String qzid, int uid) {
        Session session = factory.getCurrentSession();
        QuizQuestion qstn = null;

        Criteria cr = session.createCriteria(QuizQuestion.class);
        ArrayList<QuizQuestion> qtnlist = (ArrayList<QuizQuestion>) cr.list();
        for (QuizQuestion qn : qtnlist) {
            if (qn.getQuestion_id() == Integer.parseInt(question_id)) {
                
                qstn = qn;
            }
        }
        boolean retvalue = true;
        for (int i = 0; i < answers.length; i++) {
          
               int ansid = 0;
      
        Criteria cr_getmaxans_id = session.createCriteria(QuizAnswerRecorede.class).setProjection(Projections.max("answerid"));
        
        
        if (cr_getmaxans_id.uniqueResult() != null) {
            ansid = (Integer) cr_getmaxans_id.uniqueResult();
        } else {
           ansid = 0;
        }
        ansid = ansid + 1;
            
            
            
            
            String ans = "+" + answers[i];
            if (ans.equalsIgnoreCase(qstn.getOption_one()) || ans.equalsIgnoreCase(qstn.getOption_two()) || ans.equalsIgnoreCase(qstn.getOption_three()) || ans.equalsIgnoreCase(qstn.getOption_four())) {
               
            } else {
                retvalue = false;
            }
            QuizAnswerRecorede ansobj = new QuizAnswerRecorede();
            ansobj.setAnswerid(ansid);
            ansobj.setQuiz_id(qzid);
            ansobj.setStudent_id(uid + "");
            ansobj.setSaved_answer(answers[i]);
            ansobj.setAnswertimestamp(new Date());
            session.save(ansobj);

        }
        return retvalue;

    }

    @Override
    public void saveResult(String userId, String qzid, int totalquestions, int correct_answers,String quizname) {
        
       Session session=factory.getCurrentSession();
        Result res=new Result();
        res.setResultid(1);
      
        
        res.setUser_id(userId); 
        res.setQuiz_id(qzid);
        res.setQuiz_submited_at(new Date());
        res.setTotal_answered(totalquestions); 
        res.setTotal_no_questions(totalquestions);
        res.setCorrect_answers(correct_answers);
        res.setQuiz_name(quizname); 
        session.save(res);
       
    }
    @Override
    public boolean saveprofessor(AppUser u, ProfessorUser p) {
        try{
        int x = 0;
        Session session = factory.getCurrentSession();
        Criteria cr = session.createCriteria(ProfessorUser.class).setProjection(Projections.max("professorId"));
        if (cr.uniqueResult() != null) {
            x = (Integer) cr.uniqueResult();
        } else {
            x = 0;
        }
        x = x + 1;
        p.setProfessorId(x);
        p.setPregisteredAt(new Date());
        u.setRoleId(x + "");
        session.save(p);
         
        
        
        
        
        
        
          int y= 0;
       
        Criteria cr2 = session.createCriteria(AppUser.class).setProjection(Projections.max("userId"));
        if (cr2.uniqueResult() != null) {
            y = (Integer) cr2.uniqueResult();
        } else {
            y = 0;
        }
        y = y+ 1;
        
        
        
        
        
        u.setUserId(y); 
        
        
        
        session.save(u);
                return true;
        }catch(Exception e){
            return  false;
        }
    }

    @Override
    public boolean savestudent(AppUser u, Student s) {
           try{
        int x = 0;
        Session session = factory.getCurrentSession();
        Criteria cr = session.createCriteria(Student.class).setProjection(Projections.max("studentid"));
        if (cr.uniqueResult() != null) {
            x = (Integer) cr.uniqueResult();
        } else {
            x = 0;
        }
        x = x + 1;
       s.setStudentid(x);
      
        u.setRoleId(x + "");
        session.save(s);
         
        
        
        
        
        
        
          int y= 0;
       
        Criteria cr2 = session.createCriteria(AppUser.class).setProjection(Projections.max("userId"));
        if (cr2.uniqueResult() != null) {
            y = (Integer) cr2.uniqueResult();
        } else {
            y = 0;
        }
        y = y+ 1;
        
        
        
        
        
        u.setUserId(y); 
        
        
        
        session.save(u);
                return true;
        }catch(Exception e){
            return  false;
        }
    }

    @Override
    public ArrayList<Result> getDashbardData() {
        Session session = factory.getCurrentSession();
        Criteria cr=session.createCriteria(Result.class);
        return (ArrayList<Result>)cr.list();
    }

}

