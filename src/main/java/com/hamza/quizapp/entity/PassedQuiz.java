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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hamza
 */
@Entity
@Table(name = "passedQuiz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PassedQuiz.findAll", query = "SELECT p FROM PassedQuiz p")
    , @NamedQuery(name = "PassedQuiz.findByIdQuiz", query = "SELECT p FROM PassedQuiz p WHERE p.passedQuizPK.idQuiz = :idQuiz")
    , @NamedQuery(name = "PassedQuiz.findByIdEtudiant", query = "SELECT p FROM PassedQuiz p WHERE p.passedQuizPK.idEtudiant = :idEtudiant")
    , @NamedQuery(name = "PassedQuiz.findByScore", query = "SELECT p FROM PassedQuiz p WHERE p.score = :score")})
public class PassedQuiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PassedQuizPK passedQuizPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private int score;
    @JoinColumn(name = "idQuiz", referencedColumnName = "idQuiz", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Quiz quiz;
    @JoinColumn(name = "idEtudiant", referencedColumnName = "idEtudiant", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Etudiant etudiant;

    public PassedQuiz() {
    }

    public PassedQuiz(PassedQuizPK passedQuizPK) {
        this.passedQuizPK = passedQuizPK;
    }

    public PassedQuiz(PassedQuizPK passedQuizPK, int score) {
        this.passedQuizPK = passedQuizPK;
        this.score = score;
    }

    public PassedQuiz(int idQuiz, int idEtudiant) {
        this.passedQuizPK = new PassedQuizPK(idQuiz, idEtudiant);
    }

    public PassedQuizPK getPassedQuizPK() {
        return passedQuizPK;
    }

    public void setPassedQuizPK(PassedQuizPK passedQuizPK) {
        this.passedQuizPK = passedQuizPK;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passedQuizPK != null ? passedQuizPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassedQuiz)) {
            return false;
        }
        PassedQuiz other = (PassedQuiz) object;
        if ((this.passedQuizPK == null && other.passedQuizPK != null) || (this.passedQuizPK != null && !this.passedQuizPK.equals(other.passedQuizPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hamza.test.entity.PassedQuiz[ passedQuizPK=" + passedQuizPK + " ]";
    }
    
}
