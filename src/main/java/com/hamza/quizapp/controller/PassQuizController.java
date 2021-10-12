/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.controller;

import com.hamza.quizapp.entity.Choix;
import com.hamza.quizapp.entity.Etudiant;
import com.hamza.quizapp.entity.Question;
import com.hamza.quizapp.entity.Quiz;
import com.hamza.quizapp.entity.Reponce;
import com.hamza.quizapp.entity.ReponcePK;
import com.hamza.quizapp.session.ReponceFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hamza
 */
@Named(value = "passQuizController")
@SessionScoped
public class PassQuizController implements Serializable {

    /**
     * Creates a new instance of PassQuizController
     */
    
    @EJB
    private ReponceFacade reponseFacade;
    
    private Etudiant etudiant;
    private Quiz quiz;
    
    private List<Question> questionList;
    private Question currentQuestion;
    private List<Choix> currentChoiseList;
    
    private int currentQuestionIndex;
    
    private int score;
    
    private String action;
    
    
    public PassQuizController() {
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        etudiant = (Etudiant) session.getAttribute("ETUDIANT");
        quiz =  (Quiz) session.getAttribute("QUIZ");
        
        questionList = quiz.getQuestionList();
        currentQuestion = questionList.get(0);
        currentQuestionIndex = 0;
        action = "Suivant";
    }
    
        public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public List<Choix> getCurrentChoiseList() {
        return currentChoiseList;
    }

    public void setCurrentChoiseList(List<Choix> currentChoiseList) {
        this.currentChoiseList = currentChoiseList;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    
    public void moveToNextQuestion() {
        if(isLastQuestion()) {
            action = "Valider";
        }
        
        if(isFinished()) {
            validate();
        }
        else {
            computeScore();
            saveResponse();
            currentQuestion = questionList.get(currentQuestionIndex);
            currentChoiseList.clear();
            currentQuestionIndex ++;
        }

        
    }

    private void computeScore() {
        for (Choix choice : currentChoiseList) {
            if(! choice.getIsCorrect())
                return;
        }
        score ++;
    }

    private void saveResponse() {
        for (int i = 0 ; i < currentChoiseList.size() ; i++) {
            Choix choice = currentChoiseList.get(i);
            
            ReponcePK reponsePK = new ReponcePK();
            reponsePK.setIdChoix(choice.getIdChoix());
            reponsePK.setIdQuestion(choice.getIdQuestion().getIdQuestion());
            Reponce reponse = new Reponce(reponsePK);
            reponse.setChoix(choice);
            reponse.setQuestion(choice.getIdQuestion());
            
            reponseFacade.create(reponse);
        }
    }

    private void validate() {
        
    }

    private boolean isLastQuestion() {
        return currentQuestionIndex == questionList.size() - 1;
    }

    private boolean isFinished() {
        return currentQuestionIndex >= questionList.size();
    }
    
    
    
    
}
