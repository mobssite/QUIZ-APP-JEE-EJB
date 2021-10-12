/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.session;

import com.hamza.quizapp.entity.Admin;
import com.hamza.quizapp.entity.Etudiant;
import com.hamza.quizapp.entity.Quiz;
import com.hamza.quizapp.entity.Section;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hamza
 */
@Stateless
public class QuizFacade extends AbstractFacade<Quiz> {

    @PersistenceContext(unitName = "com.hamza_QuizApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 

    public QuizFacade() {
        super(Quiz.class);
    }
    
    public List<Quiz> findByAdmin(Admin admin) {
        Query query =  em.createNamedQuery("Quiz.findByAdmin");
        query.setParameter("idAdmin", admin.getIdAdmin());
        return query.getResultList();
    }

    public List<Quiz> findBySection(Section section) {
        Query query =  em.createNamedQuery("Quiz.findBySection");
        query.setParameter("idSection", section.getIdSection());
        return query.getResultList();
    }
    
}
