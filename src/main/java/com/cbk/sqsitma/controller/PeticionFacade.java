/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.controller;

import com.cbk.sqsitma.entity.Peticion;
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
public class PeticionFacade extends AbstractFacade<Peticion> {
    @PersistenceContext(unitName = "com.cbk.sqsitma_SQSITMA_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeticionFacade() {
        super(Peticion.class);
    }
    
    public List<Peticion> findAll(Usuario usuario) {
        return this.findAll(usuario.getEmail());
    }
    
    public List<Peticion> findAll(String email) {
        return getEntityManager().createNamedQuery("Peticion.findByEmail").setParameter("email", email).getResultList();
    }
    
}
