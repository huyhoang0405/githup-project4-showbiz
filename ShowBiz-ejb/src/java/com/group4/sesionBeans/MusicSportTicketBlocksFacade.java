/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.MusicSportTicketBlocks;
import com.group4.entities.MusicSports;
import com.group4.entities.TicketTypes;
import java.util.Date;
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
public class MusicSportTicketBlocksFacade extends AbstractFacade<MusicSportTicketBlocks> implements MusicSportTicketBlocksFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MusicSportTicketBlocksFacade() {
        super(MusicSportTicketBlocks.class);
    }

    @Override
    public String getLastID() {
        Query query = em.createQuery("SELECT MAX (m.musicSportTicketBlockID) FROM MusicSportTicketBlocks m");
        return query.getSingleResult().toString();
    }

    @Override
    public List<TicketTypes> findTicketTypeByMusicSportID(MusicSports id) {
        Query query = em.createQuery("SELECT DISTINCT (m.ticketTypeID)  From MusicSportTicketBlocks m WHERE m.musicSportID = :musicSportID");
        query.setParameter("musicSportID", id);
        return query.getResultList();
    }

    public MusicSportTicketBlocks findByTikcetTypenMusicSportID(MusicSports msID, TicketTypes ticketID) {
        Query query = em.createQuery("SELECT m From MusicSportTicketBlocks m WHERE m.musicSportID = :musicSportID and m.ticketTypeID = :ticketTypeID");
        query.setParameter("musicSportID", msID);
        query.setParameter("ticketTypeID", ticketID);
        return (MusicSportTicketBlocks) query.getSingleResult();
    }

    public List<MusicSportTicketBlocks> findListByTikcetTypenMusicSportID(MusicSports msID, TicketTypes ticketID) {
        Query query = em.createQuery("SELECT m From MusicSportTicketBlocks m WHERE m.musicSportID = :musicSportID and m.ticketTypeID = :ticketTypeID");
        query.setParameter("musicSportID", msID);
        query.setParameter("ticketTypeID", ticketID);
        return query.getResultList();
    }

    public boolean checkBlock(TicketTypes ticket,MusicSports musicsport) {
        Query query = em.createQuery("SELECT m FROM MusicSportTicketBlocks m WHERE m.ticketTypeID = :ticketTypeID and m.musicSportID = :musicSportID");
        query.setParameter("ticketTypeID", ticket);
        query.setParameter("musicSportID", musicsport);
        List<MusicSportTicketBlocks> list = query.getResultList();
        return list.size() > 0;
    }
    
     public MusicSportTicketBlocks findBlock(TicketTypes ticket,MusicSports musicsport) {
        Query query = em.createQuery("SELECT m FROM MusicSportTicketBlocks m WHERE m.ticketTypeID = :ticketTypeID and m.musicSportID = :musicSportID");
        query.setParameter("ticketTypeID", ticket);
        query.setParameter("musicSportID", musicsport);
        List<MusicSportTicketBlocks> list = query.getResultList();
        return (MusicSportTicketBlocks) query.getSingleResult();
    }
     
      public List<MusicSports> findMusicSportInBlock() {
        Query query = em.createQuery("SELECT DISTINCT (m.musicSportID) FROM MusicSportTicketBlocks m");
        return query.getResultList();
    }

    public Object ticketStatistics(MusicSports musicsport) {
        Query query = em.createQuery("SELECT SUM(m.quantity-m.residual) FROM MusicSportTicketBlocks m Where m.musicSportID = :musicSportID");
        query.setParameter("musicSportID", musicsport);
        return query.getSingleResult();
    }
    
     public Object statisticMusicSport(Date startdate, Date enddate) {
        Query query = em.createQuery("SELECT SUM(odm.quantity) FROM  OrderMusicSportDetails odm JOIN odm.musicSportTicketBlocks m JOIN odm.orders o Where o.dateOfPurchase BETWEEN :startdate And :enddate");
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        return query.getSingleResult();
    }
}
