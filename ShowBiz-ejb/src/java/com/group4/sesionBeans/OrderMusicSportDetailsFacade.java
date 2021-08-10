/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.MusicSportTicketBlocks;
import com.group4.entities.OrderMusicSportDetails;
import com.group4.entities.Orders;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
@Stateless
public class OrderMusicSportDetailsFacade extends AbstractFacade<OrderMusicSportDetails> implements OrderMusicSportDetailsFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderMusicSportDetailsFacade() {
        super(OrderMusicSportDetails.class);
    }
    
    public Orders findOrder(String orders) {
        Query query = em.createQuery("SELECT od FROM  Orders od Where od.orderID = :orders");
        query.setParameter("orders", orders);
        return (Orders) query.getSingleResult();
    }

    public MusicSportTicketBlocks findBlockMusicSport(String id) {
        Query query = em.createQuery("SELECT m FROM MusicSportTicketBlocks m Where m.musicSportTicketBlockID  = :id");
        query.setParameter("id", id);
        return (MusicSportTicketBlocks) query.getSingleResult();
    }
}
