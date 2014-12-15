/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.entity;

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
 * @author cobrakik
 */
@Entity
@Table(name = "peticiones", catalog = "sqsitma", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peticion.findAll", query = "SELECT p FROM Peticion p"),
    @NamedQuery(name = "Peticion.findById", query = "SELECT p FROM Peticion p WHERE p.id = :id"),
    @NamedQuery(name = "Peticion.findByAsunto", query = "SELECT p FROM Peticion p WHERE p.asunto = :asunto"),
    @NamedQuery(name = "Peticion.findByPeticion", query = "SELECT p FROM Peticion p WHERE p.peticion = :peticion"),
    @NamedQuery(name = "Peticion.findByCreatedAt", query = "SELECT p FROM Peticion p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "Peticion.findByUpdatedAt", query = "SELECT p FROM Peticion p WHERE p.updatedAt = :updatedAt"),
    @NamedQuery(name = "Peticion.findByLast", query = "SELECT p FROM Peticion p ORDER BY p.id ASC LIMIT 1")})
public class Peticion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "asunto")
    private String asunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "peticion")
    private String peticion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "usuario_email", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peticionId", fetch = FetchType.LAZY)
    private List<PersonalAtencion> personalAtencionList;

    public Peticion() {
    }

    public Peticion(Integer id) {
        this.id = id;
    }

    public Peticion(Integer id, String asunto, String peticion, Date createdAt, Date updatedAt) {
        this.id = id;
        this.asunto = asunto;
        this.peticion = peticion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getPeticion() {
        return peticion;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Usuario getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(Usuario usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    @XmlTransient
    public List<PersonalAtencion> getPersonalAtencionList() {
        return personalAtencionList;
    }

    public void setPersonalAtencionList(List<PersonalAtencion> personalAtencionList) {
        this.personalAtencionList = personalAtencionList;
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
        if (!(object instanceof Peticion)) {
            return false;
        }
        Peticion other = (Peticion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cbk.sqsitma.entity.Peticion[ id=" + id + " ]";
    }

}
