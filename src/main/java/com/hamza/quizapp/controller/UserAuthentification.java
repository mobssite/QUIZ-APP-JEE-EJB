package com.hamza.quizapp.controller;

import com.hamza.quizapp.entity.Admin;
import com.hamza.quizapp.entity.Etudiant;
import java.io.IOException;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;


@Named(value = "userAuthentification")
@RequestScoped
public class UserAuthentification implements Serializable{

    /**
     * Creates a new instance of UserAuthentification
     */
    @EJB
    private com.hamza.quizapp.session.AdminFacade adminFacade;
    
    @EJB
    private com.hamza.quizapp.session.EtudiantFacade etudiantFacede;
    
    private Admin admin;
    private Etudiant etudiant;
    
    
    public UserAuthentification() {
        admin = new Admin();
        etudiant = new Etudiant();
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant entudiant) {
        this.etudiant = entudiant;
    }
    
    
    
    public void doLoginAdmin() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;

        Admin adminFound = adminFacade.findByEmail(admin.getEmail());
        if(adminFound != null) {
            if(adminFound.getMdp().equals(admin.getMdp())) {
                try {
                    
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    session.setAttribute("ADMIN", adminFound);
                    
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/espaceEnseignat/quiz/List.xhtml");
                    
                } catch (IOException ex) {
                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Problem, try later", "Problem, try later");
                    context.addMessage(null, msg); 
                    return ;
                }
            }
        }
        
        msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong Login or Password", "Wrong Login or Password");
        context.addMessage(null, msg);  
    }
    
    
    public void doLogoutAdmin() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/QuizApp");
        } catch (IOException ex) {
            Logger.getLogger(UserAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void regesterAdmin() {
        adminFacade.create(admin);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("ADMIN", admin);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/espaceEnseignat/quiz/List.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void doLoginStudent() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;

        Etudiant etudiantFound = etudiantFacede.findByEmail(etudiant.getEmail());
        if(etudiantFound != null) {
            if(etudiantFound.getMdp().equals(etudiant.getMdp())) {
                try {
                    
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    session.setAttribute("ETUDIANT", etudiantFound);
                    
                    FacesContext.getCurrentInstance().getExternalContext().redirect("ListQuiz.xhtml");
                    
                } catch (IOException ex) {
                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Problem, try later", "Problem, try later");
                    context.addMessage(null, msg); 
                    return ;
                }
            }
        }
        
        msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong Login or Password", "Wrong Login or Password");
        context.addMessage(null, msg);  
    }
    
    
    public void doLogoutStudent() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void regesterStudent() {
        etudiantFacede.create(etudiant);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("ETUDIANT", etudiant);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ListQuiz.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
