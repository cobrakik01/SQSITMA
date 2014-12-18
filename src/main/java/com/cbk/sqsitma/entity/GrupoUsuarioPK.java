/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author cobrakik
 */
@Embeddable
public class GrupoUsuarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_grupo")
    private String nombreGrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuarios_email")
    private String usuariosEmail;

    public GrupoUsuarioPK() {
    }

    public GrupoUsuarioPK(String nombreGrupo, String usuariosEmail) {
        this.nombreGrupo = nombreGrupo;
        this.usuariosEmail = usuariosEmail;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getUsuariosEmail() {
        return usuariosEmail;
    }

    public void setUsuariosEmail(String usuariosEmail) {
        this.usuariosEmail = usuariosEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreGrupo != null ? nombreGrupo.hashCode() : 0);
        hash += (usuariosEmail != null ? usuariosEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuarioPK)) {
            return false;
        }
        GrupoUsuarioPK other = (GrupoUsuarioPK) object;
        if ((this.nombreGrupo == null && other.nombreGrupo != null) || (this.nombreGrupo != null && !this.nombreGrupo.equals(other.nombreGrupo))) {
            return false;
        }
        if ((this.usuariosEmail == null && other.usuariosEmail != null) || (this.usuariosEmail != null && !this.usuariosEmail.equals(other.usuariosEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cbk.sqsitma.entity.GrupoUsuarioPK[ nombreGrupo=" + nombreGrupo + ", usuariosEmail=" + usuariosEmail + " ]";
    }
    
}
