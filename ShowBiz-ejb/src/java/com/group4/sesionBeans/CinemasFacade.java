/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Cinemas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
@Stateless
public class CinemasFacade extends AbstractFacade<Cinemas> implements CinemasFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CinemasFacade() {
        super(Cinemas.class);
    }
    @Override
    public Cinemas findByID(Integer id){
        Query query = em.createQuery("SELECT DISTINCT c FROM Cinemas c WHERE c.cinemaID = :cinemaID");
        query.setParameter("cinemaID", id);
        return (Cinemas) query.getResultList();
    }
}
