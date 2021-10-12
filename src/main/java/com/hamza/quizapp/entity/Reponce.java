/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hamza
 */
@Entity
@Table(name = "reponce")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reponce.findAll", query = "SELECT r FROM Reponce r")
    , @NamedQuery(name = "Reponce.findByIdQuestion", query = "SELECT r FROM Reponce r WHERE r.reponcePK.idQuestion = :idQuestion")
    , @NamedQuery(name = "Reponce.findByIdChoix", query = "SELECT r FROM Reponce r WHERE r.reponcePK.idChoix = :idChoix")})
public class Reponce implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReponcePK reponcePK;
    @JoinColumn(name = "idQuestion", referencedColumnName = "idQuestion", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Question question;
    @JoinColumn(name = "idChoix", referencedColumnName = "idChoix", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Choix choix;

    public Reponce() {
    }

    public Reponce(ReponcePK reponcePK) {
        this.reponcePK = reponcePK;
    }


    public Reponce(int idQuestion, int idChoix) {
        this.reponcePK = new ReponcePK(idQuestion, idChoix);
    }

    public ReponcePK getReponcePK() {
        return reponcePK;
    }

    public void setReponcePK(ReponcePK reponcePK) {
        this.reponcePK = reponcePK;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Choix getChoix() {
        return choix;
    }

    public void setChoix(Choix choix) {
        this.choix = choix;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reponcePK != null ? reponcePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reponce)) {
            return false;
        }
        Reponce other = (Reponce) object;
        if ((this.reponcePK == null && other.reponcePK != null) || (this.reponcePK != null && !this.reponcePK.equals(other.reponcePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hamza.test.entity.Reponce[ reponcePK=" + reponcePK + " ]";
    }
    
}
