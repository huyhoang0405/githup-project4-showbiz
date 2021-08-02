/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.MusicSports;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
@Stateless
public class MusicSportsFacade extends AbstractFacade<MusicSports> implements MusicSportsFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MusicSportsFacade() {
        super(MusicSports.class);
    }
    
        @Override
    public String getLastID(){
        Query query = em.createQuery("SELECT MAX (m.musicSportID) FROM MusicSports m");
        return query.getSingleResult().toString();
    }
    
    @Override
    public List<MusicSports> showAllMusics(){
        Query query = em.createQuery("SELECT m FROM MusicSports m WHERE m.type = :type ORDER BY m.startDate DESC");
        query.setParameter("type", false);
        return query.getResultList();
    }
    
    @Override
     public List<MusicSports> showAllSports(){
        Query query = em.createQuery("SELECT m FROM MusicSports m WHERE m.type = :type ORDER BY m.startDate DESC");
        query.setParameter("type", true);
        return query.getResultList();
    }
     
    public List<MusicSports> show6Newest(){
        Query query = em.createQuery("SELECT DISTINCT m FROM MusicSports m ORDER BY m.startDate DESC");
        query.setMaxResults(6);
        return query.getResultList();
    }
}
