/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.controller;

import com.cbk.sqsitma.entity.Peticion;
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

    @PersistenceContext(unitName = "com.cbk.sqsitma_SQSITMA_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeticionFacade() {
        super(Peticion.class);
    }

    public Peticion ultimaPeticion() {
        Peticion p = null;
        List list = em.createNamedQuery("Peticion.findByLast").getResultList();
        if (list.size() > 0) {
            p = (Peticion) list.get(0);
        }
        return p;
    }

}
