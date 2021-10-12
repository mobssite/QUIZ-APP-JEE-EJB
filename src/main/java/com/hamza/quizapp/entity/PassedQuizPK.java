/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hamza
 */
@Embeddable
public class PassedQuizPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idQuiz")
    private int idQuiz;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEtudiant")
    private int idEtudiant;

    public PassedQuizPK() {
    }

    public PassedQuizPK(int idQuiz, int idEtudiant) {
        this.idQuiz = idQuiz;
        this.idEtudiant = idEtudiant;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idQuiz;
        hash += (int) idEtudiant;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassedQuizPK)) {
            return false;
        }
        PassedQuizPK other = (PassedQuizPK) object;
        if (this.idQuiz != other.idQuiz) {
            return false;
        }
        if (this.idEtudiant != other.idEtudiant) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hamza.test.entity.PassedQuizPK[ idQuiz=" + idQuiz + ", idEtudiant=" + idEtudiant + " ]";
    }
    
}
