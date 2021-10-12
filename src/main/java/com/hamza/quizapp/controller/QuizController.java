package com.hamza.quizapp.controller;

import com.hamza.quizapp.entity.Quiz;
import com.hamza.quizapp.controller.util.JsfUtil;
import com.hamza.quizapp.controller.util.JsfUtil.PersistAction;
import com.hamza.quizapp.entity.Admin;
import com.hamza.quizapp.entity.Etudiant;
import com.hamza.quizapp.session.QuizFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

@Named("quizController")
@SessionScoped
public class QuizController implements Serializable {

    @EJB
    private com.hamza.quizapp.session.QuizFacade ejbFacade;
    
    private List<Quiz> items = null;
    private List<Quiz> itemsForStudent = null;

    private Quiz selected;
    
    private Admin admin;
    private Etudiant etudiant;   

    public QuizController() {
        
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        admin = (Admin) session.getAttribute("ADMIN");
        etudiant = (Etudiant) session.getAttribute("ETUDIANT");

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

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    
    
    public Quiz getSelected() {
        return selected;
    }

    public void setSelected(Quiz selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private QuizFacade getFacade() {
        return ejbFacade;
    }

    public Quiz prepareCreate() {
        selected = new Quiz();
        selected.setIdAdmin(admin);
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("QuizCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("QuizUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("QuizDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Quiz> getItems() {
        if (items == null) {
            items = getFacade().findByAdmin(admin);
        }
        return items;
    }
    
     public List<Quiz> getItemsForStudent() {
         if (itemsForStudent == null) {
            itemsForStudent = getFacade().findBySection(etudiant.getIdSection());
        }
        return itemsForStudent;
     }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Quiz getQuiz(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Quiz> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Quiz> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public void redirectToAddQuestion() {
       HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
       session.setAttribute("CURRENT_QUIZ", this.selected);
       
        try {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("../question/List.xhtml");
            
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void redirectToPassQuiz() {
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("QUIZ", this.selected);
       
        try {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("passQuiz.xhtml");
            
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FacesConverter(forClass = Quiz.class)
    public static class QuizControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            QuizController controller = (QuizController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "quizController");
            return controller.getQuiz(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Quiz) {
                Quiz o = (Quiz) object;
                return getStringKey(o.getIdQuiz());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Quiz.class.getName()});
                return null;
            }
        }

    }

}
