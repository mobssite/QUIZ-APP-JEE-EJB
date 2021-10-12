/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.quizapp.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hamza
 */
@Entity
@Table(name = "sujet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sujet.findAll", query = "SELECT s FROM Sujet s")
    , @NamedQuery(name = "Sujet.findByIdSujet", query = "SELECT s FROM Sujet s WHERE s.idSujet = :idSujet")
    , @NamedQuery(name = "Sujet.findByLibele", query = "SELECT s FROM Sujet s WHERE s.libele = :libele")})
public class Sujet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSujet")
    private Integer idSujet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "libele")
    private String libele;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSujet", fetch = FetchType.LAZY)
    private List<Quiz> quizList;
    @JoinColumn(name = "idSection", referencedColumnName = "idSection")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Section idSection;

    public Sujet() {
    }

    public Sujet(Integer idSujet) {
        this.idSujet = idSujet;
    }

    public Sujet(Integer idSujet, String libele) {
        this.idSujet = idSujet;
        this.libele = libele;
    }

    public Integer getIdSujet() {
        return idSujet;
    }

    public void setIdSujet(Integer idSujet) {
        this.idSujet = idSujet;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    @XmlTransient
    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    public Section getIdSection() {
        return idSection;
    }

    public void setIdSection(Section idSection) {
        this.idSection = idSection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSujet != null ? idSujet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sujet)) {
            return false;
        }
        Sujet other = (Sujet) object;
        if ((this.idSujet == null && other.idSujet != null) || (this.idSujet != null && !this.idSujet.equals(other.idSujet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.libele;
    }
    
}
