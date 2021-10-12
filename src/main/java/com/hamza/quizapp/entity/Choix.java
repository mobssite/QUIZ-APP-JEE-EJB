/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hamza
 */
@Entity
@Table(name = "choix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Choix.findAll", query = "SELECT c FROM Choix c")
    , @NamedQuery(name = "Choix.findByIdChoix", query = "SELECT c FROM Choix c WHERE c.idChoix = :idChoix")
    , @NamedQuery(name = "Choix.findByChoix", query = "SELECT c FROM Choix c WHERE c.choix = :choix")
    , @NamedQuery(name = "Choix.findByIsCorrect", query = "SELECT c FROM Choix c WHERE c.isCorrect = :isCorrect")
    , @NamedQuery(name = "Choix.findByQuestion", query = "SELECT c FROM Choix c WHERE c.idQuestion.idQuestion = :idQuestion")})
public class Choix implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idChoix")
    private Integer idChoix;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "choix")
    private String choix;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isCorrect")
    private boolean isCorrect;
    @JoinColumn(name = "idQuestion", referencedColumnName = "idQuestion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Question idQuestion;

    public Choix() {
    }

    public Choix(Integer idChoix) {
        this.idChoix = idChoix;
    }

    public Choix(Integer idChoix, String choix, boolean isCorrect) {
        this.idChoix = idChoix;
        this.choix = choix;
        this.isCorrect = isCorrect;
    }

    public Integer getIdChoix() {
        return idChoix;
    }

    public void setIdChoix(Integer idChoix) {
        this.idChoix = idChoix;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Question getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Question idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChoix != null ? idChoix.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Choix)) {
            return false;
        }
        Choix other = (Choix) object;
        if ((this.idChoix == null && other.idChoix != null) || (this.idChoix != null && !this.idChoix.equals(other.idChoix))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.choix;
    }
    
}
