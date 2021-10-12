/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.session;

import com.hamza.quizapp.entity.Reponce;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hamza
 */
@Stateless
public class ReponceFacade extends AbstractFacade<Reponce> {

    @PersistenceContext(unitName = "com.hamza_QuizApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReponceFacade() {
        super(Reponce.class);
    }
    
}
