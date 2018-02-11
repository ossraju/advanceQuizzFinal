/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epita.iamquizz.quizz.controllers;


import fr.epita.iamquizz.quizz.services.MainService;
import javax.servlet.http.HttpSession;
import static javax.swing.text.StyleConstants.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static javax.swing.text.StyleConstants.ModelAttribute;



import fr.epita.iamquizz.quizz.beans.QuizQuestion;
import fr.epita.iamquizz.quizz.beans.Quiz;
import fr.epita.iamquizz.quizz.beans.Result;
import fr.epita.iamquizz.quizz.beans.AppUser;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;



/**
 *
 * @author shivasairajuomkar
 */


@Controller
public class maincontroller {
    
    
    @Autowired
    MainService service;
    
    
       @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewHome(Model model) {
        model.addAttribute("User", new AppUser());
        return "homePage";
    }

   @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String loginCheck(Model model, @ModelAttribute("User") AppUser user, HttpSession session) {
        if (user.getUserLoginId().equalsIgnoreCase("") || user.getUserpassword().equalsIgnoreCase("")) {
            model.addAttribute("error", "please enter username and password");
            model.addAttribute("User", new AppUser());
            return "homePage";
        }

        if(user.getUserLoginId().equalsIgnoreCase("omkar")&&user.getUserpassword().equalsIgnoreCase("123456")){
            session.setAttribute("user", user);
                model.addAttribute("User", user);
               model.addAttribute("quizlist", service.getallquizList());

               return "homes";
              
        }else if(user.getUserLoginId().equalsIgnoreCase("thomas")&&user.getUserpassword().equalsIgnoreCase("123456")){
             session.setAttribute("user", user);
               model.addAttribute("User", user);
               ArrayList<Result> resultarray = service.getDashbardData();
              model.addAttribute("resultarray", resultarray);
               return "homeP";
        }else{
              model.addAttribute("error", "please enter username and password");
            model.addAttribute("User", new AppUser());
            return "homePage";
        }
        
        
        
        


        
    }

    @RequestMapping(value = "/addq", method = RequestMethod.GET)
    public String addQuestion(Model model, HttpSession session) {
        AppUser usr = (AppUser) session.getAttribute("user");

        model.addAttribute("User", usr);
        model.addAttribute("question", new QuizQuestion());
        return "addQuestion";
    }

    @RequestMapping(value = "/submitquestion", method = RequestMethod.POST)
    public String submitquestion(Model model, @ModelAttribute("question") QuizQuestion qstion, HttpSession session) {
        AppUser usr = (AppUser) session.getAttribute("user");
        qstion.setCreated_by(usr.getUserLoginId());
        qstion.setCreated_timestamp(new Date());
        service.saveQuestion(qstion);
        model.addAttribute("question", new QuizQuestion());
        model.addAttribute("message", "Question submited sucessfully");
        return "message";
    }

    @RequestMapping(value = "createqz", method = RequestMethod.GET)
    public String createQuiz(Model model, HttpSession session) {
        AppUser usr = (AppUser) session.getAttribute("user");
        ArrayList<QuizQuestion> questions = service.getQuestions(usr.getUserLoginId());
        model.addAttribute("questionsList", questions);

        model.addAttribute("User", usr);

        return "createQuizz";
    }

    @RequestMapping(value = "submitqz", method = RequestMethod.GET)
    public String submitqz(Model model, HttpSession session, @RequestParam("quizname") String quizname, @RequestParam("questions") String[] questionsids) {
        AppUser usr = (AppUser) session.getAttribute("user");

        Quiz q = new Quiz();
        q.setQuizname(quizname);
        q.setCreated_by(usr.getUserLoginId());
        q.setCreated_at(new Date());

        service.saveQuiz(q, questionsids);
        model.addAttribute("message", "Quiz submited sucessfully");
        return "message";
    }

    @RequestMapping(value = "takeqz", method = RequestMethod.GET)
    public String takeqz(Model model, @RequestParam("qzid") String qzid, @RequestParam("qzname") String qzname, HttpSession session) {
        ArrayList<QuizQuestion> qstions = service.getQuestionsbyqzid(qzid);
        ArrayList<QuizQuestion> qstions2 = new ArrayList<QuizQuestion>();
        for (QuizQuestion qs : qstions) {
            qs.setOption_one(qs.getOption_one().substring(1, qs.getOption_one().length()));
            qs.setOption_two(qs.getOption_two().substring(1, qs.getOption_two().length()));
            qs.setOption_three(qs.getOption_three().substring(1, qs.getOption_three().length()));
            qs.setOption_four(qs.getOption_four().substring(1, qs.getOption_four().length()));


            qstions2.add(qs);
        }

        AppUser usr = (AppUser) session.getAttribute("user");
        model.addAttribute("username", usr.getUserLoginId());

        model.addAttribute("questionsList", qstions2);

        model.addAttribute("quizname", qzname);
        model.addAttribute("qzid", qzid);
        
        model.addAttribute("User", usr);

        return "task";
    }

    @RequestMapping(value = "submittakenquiz", method = RequestMethod.GET)
    public String submittaketQz(Model model, HttpServletRequest request, HttpSession session, @RequestParam("sid") String sid, @RequestParam("qid") String qzid, @RequestParam("quizname") String quizname) {

        try {
            String[] question_ids = request.getParameterValues("questions");
            AppUser usr = (AppUser) session.getAttribute("user");
            int totalquestions = question_ids.length;
            int correct_answers = 0;

            for (int i = 0; i < question_ids.length; i++) {
                String[] answers = request.getParameterValues(question_ids[i]);

                if (service.insertsubmitedAns(question_ids[i], answers, qzid, usr.getUserId())) {
                    correct_answers++;
                }

            }
            service.saveResult(usr.getUserLoginId(), qzid, totalquestions, correct_answers, quizname);

            model.addAttribute("correct_answers", correct_answers);
            model.addAttribute("message", "You have scored :" + correct_answers + "/" + totalquestions);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "un able to submit because you have completed already a chance ");
        }
        return "message";
    }

    @RequestMapping("pdashboard")
    public String showDashboard(Model model, HttpSession session) {

        ArrayList<Result> resultarray = service.getDashbardData();
        model.addAttribute("resultarray", resultarray);
        AppUser usr = (AppUser) session.getAttribute("user");

        model.addAttribute("User", usr);
        session.setAttribute("user", usr);
        return "homeP";
    }

    @RequestMapping("shome")
    public String showstudentDashboard(Model model, HttpSession session) {
        AppUser usr = (AppUser) session.getAttribute("user");
        model.addAttribute("User", usr);
        model.addAttribute("quizlist", service.getallquizList());
        return "homes";
    }

}