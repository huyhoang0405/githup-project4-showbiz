/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.TicketTypes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
@Stateless
public class TicketTypesFacade extends AbstractFacade<TicketTypes> implements TicketTypesFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketTypesFacade() {
        super(TicketTypes.class);
    }

    @Override
    public TicketTypes findByID(Integer id) {
        Query query = em.createQuery("SELECT DISTINCT t FROM TicketTypes t WHERE t.ticketTypeID = :ticketTypeID");
        query.setParameter("ticketTypeID", id);
        return (TicketTypes) query.getResultList();
    }
}
