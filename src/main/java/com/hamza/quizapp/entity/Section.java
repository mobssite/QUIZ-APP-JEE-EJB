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
@Table(name = "section")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s")
    , @NamedQuery(name = "Section.findByIdSection", query = "SELECT s FROM Section s WHERE s.idSection = :idSection")
    , @NamedQuery(name = "Section.findByLibele", query = "SELECT s FROM Section s WHERE s.libele = :libele")})
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSection")
    private Integer idSection;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "libele")
    private String libele;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSection", fetch = FetchType.LAZY)
    private List<Sujet> sujetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSection", fetch = FetchType.LAZY)
    private List<Etudiant> etudiantList;

    public Section() {
    }

    public Section(Integer idSection) {
        this.idSection = idSection;
    }

    public Section(Integer idSection, String libele) {
        this.idSection = idSection;
        this.libele = libele;
    }

    public Integer getIdSection() {
        return idSection;
    }

    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    @XmlTransient
    public List<Sujet> getSujetList() {
        return sujetList;
    }

    public void setSujetList(List<Sujet> sujetList) {
        this.sujetList = sujetList;
    }

    @XmlTransient
    public List<Etudiant> getEtudiantList() {
        return etudiantList;
    }

    public void setEtudiantList(List<Etudiant> etudiantList) {
        this.etudiantList = etudiantList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSection != null ? idSection.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.idSection == null && other.idSection != null) || (this.idSection != null && !this.idSection.equals(other.idSection))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.libele;
    }
    
}
