/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.controller;

import com.cbk.sqsitma.entity.PersonalAtencion;
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
public class PersonalAtencionFacade extends AbstractFacade<PersonalAtencion> {

    @PersistenceContext(unitName = "com.cbk.sqsitma_SQSITMA_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalAtencionFacade() {
        super(PersonalAtencion.class);
    }

    public List<PersonalAtencion> findAll(Peticion peticion) {
        return getEntityManager().createNamedQuery("PersonalAtencion.findByPeticion").setParameter("peticion", peticion).getResultList();
    }

}
