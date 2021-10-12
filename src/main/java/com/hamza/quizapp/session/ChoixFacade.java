/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.session;

import com.hamza.quizapp.entity.Choix;
import com.hamza.quizapp.entity.Question;
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
public class ChoixFacade extends AbstractFacade<Choix> {

    @PersistenceContext(unitName = "com.hamza_QuizApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChoixFacade() {
        super(Choix.class);
    }
    
    public List<Choix> findByQuestion(int questionID) {
        Query query =  em.createNamedQuery("Choix.findByQuestion");
        query.setParameter("idQuestion", questionID);
        return query.getResultList();
    }
    
}
