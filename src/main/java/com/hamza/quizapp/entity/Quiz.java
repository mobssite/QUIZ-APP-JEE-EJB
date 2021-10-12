/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hamza
 */
@Entity
@Table(name = "quiz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quiz.findAll", query = "SELECT q FROM Quiz q")
    , @NamedQuery(name = "Quiz.findByIdQuiz", query = "SELECT q FROM Quiz q WHERE q.idQuiz = :idQuiz")
    , @NamedQuery(name = "Quiz.findByNomQuiz", query = "SELECT q FROM Quiz q WHERE q.nomQuiz = :nomQuiz")
    , @NamedQuery(name = "Quiz.findByDateDebut", query = "SELECT q FROM Quiz q WHERE q.dateDebut = :dateDebut")
    , @NamedQuery(name = "Quiz.findByDuree", query = "SELECT q FROM Quiz q WHERE q.duree = :duree")
    ,@NamedQuery(name = "Quiz.findByAdmin", query = "SELECT q FROM Quiz q WHERE q.idAdmin.idAdmin = :idAdmin")
    ,@NamedQuery(name = "Quiz.findBySection", query = "SELECT q FROM Quiz q WHERE q.idSujet.idSection.idSection = :idSection")})
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idQuiz")
    private Integer idQuiz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nomQuiz")
    private String nomQuiz;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duree")
    private int duree;
    @JoinColumn(name = "idSujet", referencedColumnName = "idSujet")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sujet idSujet;
    @JoinColumn(name = "idAdmin", referencedColumnName = "idAdmin")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Admin idAdmin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuiz", fetch = FetchType.LAZY)
    private List<Question> questionList;

    public Quiz() {
    }

    public Quiz(Integer idQuiz) {
        this.idQuiz = idQuiz;
    }

    public Quiz(Integer idQuiz, String nomQuiz, Date dateDebut, int duree) {
        this.idQuiz = idQuiz;
        this.nomQuiz = nomQuiz;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    public Integer getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Integer idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getNomQuiz() {
        return nomQuiz;
    }

    public void setNomQuiz(String nomQuiz) {
        this.nomQuiz = nomQuiz;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Sujet getIdSujet() {
        return idSujet;
    }

    public void setIdSujet(Sujet idSujet) {
        this.idSujet = idSujet;
    }

    public Admin getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Admin idAdmin) {
        this.idAdmin = idAdmin;
    }

    @XmlTransient
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuiz != null ? idQuiz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quiz)) {
            return false;
        }
        Quiz other = (Quiz) object;
        if ((this.idQuiz == null && other.idQuiz != null) || (this.idQuiz != null && !this.idQuiz.equals(other.idQuiz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nomQuiz;
    }
    
}
