/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.controller;

import com.cbk.sqsitma.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cobrakik
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.cbk.sqsitma_SQSITMA_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findByEmail(String nombreUsuario) {
        Usuario us = null;
        List l = this.getEntityManager().createNamedQuery("Usuario.findByEmail").setParameter("email", nombreUsuario).getResultList();
        if (l.size() > 0) {
            us = (Usuario) l.get(0);
        }
        return us;
    }

}
