/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epita.iamquizz.quizz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author shivasairajuomkar
 */
@Entity
@Table(name = "	quizidquestionmapping")
public class QuizidtoQuestionMapping {
    @Id
    @Column(name = "mapid")
    private int mappingid;
      @Column(name = "quizid")
    private String quizid;
        @Column(name = "questionid")
    private String questionid;

    public int getMappingid() {
        return mappingid;
    }

    public void setMappingid(int mappingid) {
        this.mappingid = mappingid;
    }

    public String getQuizid() {
        return quizid;
    }

    public void setQuizid(String quizid) {
        this.quizid = quizid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }
    
}
