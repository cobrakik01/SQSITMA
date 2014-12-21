/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cobrakik
 */
@Entity
@Table(name = "personal_atencion", catalog = "sqsitma", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalAtencion.findAll", query = "SELECT p FROM PersonalAtencion p"),
    @NamedQuery(name = "PersonalAtencion.findById", query = "SELECT p FROM PersonalAtencion p WHERE p.id = :id"),
    @NamedQuery(name = "PersonalAtencion.findByCreatedAt", query = "SELECT p FROM PersonalAtencion p WHERE p.createdAt = :createdAt")})
public class PersonalAtencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "estatus_peticion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstatusPeticion estatusPeticionId;
    @JoinColumn(name = "peticion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Peticion peticionId;
    @JoinColumn(name = "personal_email", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario personalEmail;

    public PersonalAtencion() {
    }

    public PersonalAtencion(Integer id) {
        this.id = id;
    }

    public PersonalAtencion(Integer id, Date createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public EstatusPeticion getEstatusPeticionId() {
        return estatusPeticionId;
    }

    public void setEstatusPeticionId(EstatusPeticion estatusPeticionId) {
        this.estatusPeticionId = estatusPeticionId;
    }

    public Peticion getPeticionId() {
        return peticionId;
    }

    public void setPeticionId(Peticion peticionId) {
        this.peticionId = peticionId;
    }

    public Usuario getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(Usuario personalEmail) {
        this.personalEmail = personalEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalAtencion)) {
            return false;
        }
        PersonalAtencion other = (PersonalAtencion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cbk.sqsitma.entity.PersonalAtencion[ id=" + id + " ]";
    }
    
}
