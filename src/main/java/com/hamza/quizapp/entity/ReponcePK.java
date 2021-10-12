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
public class ReponcePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idQuestion")
    private int idQuestion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idChoix")
    private int idChoix;

    public ReponcePK() {
    }

    public ReponcePK(int idQuestion, int idChoix) {
        this.idQuestion = idQuestion;
        this.idChoix = idChoix;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdChoix() {
        return idChoix;
    }

    public void setIdChoix(int idChoix) {
        this.idChoix = idChoix;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idQuestion;
        hash += (int) idChoix;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReponcePK)) {
            return false;
        }
        ReponcePK other = (ReponcePK) object;
        if (this.idQuestion != other.idQuestion) {
            return false;
        }
        if (this.idChoix != other.idChoix) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hamza.test.entity.ReponcePK[ idQuestion=" + idQuestion + ", idChoix=" + idChoix + " ]";
    }
    
}
