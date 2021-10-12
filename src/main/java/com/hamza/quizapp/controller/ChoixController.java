package com.hamza.quizapp.controller;

import com.hamza.quizapp.entity.Choix;
import com.hamza.quizapp.controller.util.JsfUtil;
import com.hamza.quizapp.controller.util.JsfUtil.PersistAction;
import com.hamza.quizapp.entity.Question;
import com.hamza.quizapp.session.ChoixFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

@Named("choixController")
@ViewScoped
public class ChoixController implements Serializable {

    @EJB
    private com.hamza.quizapp.session.ChoixFacade ejbFacade;
    private List<Choix> items = null;
    
    private Choix selected;
    
    private Question question;

    public ChoixController() {
    }

    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        question =  (Question) session.getAttribute("CURRENT_QUESTION");
    }
    
    public Choix getSelected() {
        return selected;
    }

    public void setSelected(Choix selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ChoixFacade getFacade() {
        return ejbFacade;
    }

    public Choix prepareCreate() {
        selected = new Choix();
        selected.setIdQuestion(question);
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ChoixCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ChoixUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ChoixDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Choix> getItems() {
        if (items == null) {
            items = getFacade().findByQuestion(question.getIdQuestion());
        }
        return items;
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

    public Choix getChoix(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Choix> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Choix> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    

}
