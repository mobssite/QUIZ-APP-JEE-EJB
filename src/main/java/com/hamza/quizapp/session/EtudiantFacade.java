/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.session;

import com.hamza.quizapp.entity.Admin;
import com.hamza.quizapp.entity.Etudiant;
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
public class EtudiantFacade extends AbstractFacade<Etudiant> {

    @PersistenceContext(unitName = "com.hamza_QuizApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtudiantFacade() {
        super(Etudiant.class);
    }

    public Etudiant findByEmail(String email) {
        Query query =  em.createNamedQuery("Etudiant.findByEmail");
        query.setParameter("email", email);
        List<Etudiant> result = query.getResultList();
        
        if( ! result.isEmpty() ) {
            return result.get(0);
        }
        
        return null;    }
    
     
    
}
