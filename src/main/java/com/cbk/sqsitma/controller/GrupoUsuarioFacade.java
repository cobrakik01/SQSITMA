/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.controller;

import com.cbk.sqsitma.entity.GrupoUsuario;
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
public class GrupoUsuarioFacade extends AbstractFacade<GrupoUsuario> {

    @PersistenceContext(unitName = "com.cbk.sqsitma_SQSITMA_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoUsuarioFacade() {
        super(GrupoUsuario.class);
    }

    public GrupoUsuario findByEmail(String email) {
        GrupoUsuario gu = null;
        List<GrupoUsuario> l = getEntityManager().createNamedQuery("GrupoUsuario.findByUsuariosEmail").setParameter("usuariosEmail", email).getResultList();
        if (l.size() > 0) {
            gu = l.get(0);
        }
        return gu;
    }
}
