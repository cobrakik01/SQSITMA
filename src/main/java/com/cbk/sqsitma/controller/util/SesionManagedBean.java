/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.controller.util;

import com.cbk.sqsitma.controller.UsuarioFacade;
import com.cbk.sqsitma.entity.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cobrakik
 */
@Named(value = "sesionManagedBean")
@RequestScoped
public class SesionManagedBean {

    @EJB
    private UsuarioFacade usuarioFacade;

    /**
     * Creates a new instance of SesionManagedBean
     */
    public SesionManagedBean() {
    }

    public String logout() {
        try {
            HttpServletRequest sr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            sr.logout();
        } catch (ServletException ex) {
            Logger.getLogger(SesionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/index";
    }

    public Usuario getUsuario() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        Usuario us = usuarioFacade.findByEmail(nombreUsuario);
        if(us == null){
            us = new Usuario();
        }
        return us;
    }

}
