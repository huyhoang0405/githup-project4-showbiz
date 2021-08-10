/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Customers;
import com.group4.entities.MovieTicketBlocks;
import com.group4.entities.OrderMovieDetails;
import com.group4.entities.Orders;
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
public class OrderMovieDetailsFacade extends AbstractFacade<OrderMovieDetails> implements OrderMovieDetailsFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderMovieDetailsFacade() {
        super(OrderMovieDetails.class);
    }

    public List<OrderMovieDetails> orderOfCustomer(Customers customer) {
        Query query = em.createQuery("SELECT odm FROM  OrderMovieDetails odm JOIN odm.movieTicketBlocks m JOIN odm.orders o Where o.customerUsername = :customerUsername");
        query.setParameter("customerUsername", customer);
        return query.getResultList();
    }

    public Orders findOrder(String orders) {
        Query query = em.createQuery("SELECT od FROM  Orders od Where od.orderID = :orders");
        query.setParameter("orders", orders);
        return (Orders) query.getSingleResult();
    }

    public MovieTicketBlocks findBlockMovie(String id) {
        Query query = em.createQuery("SELECT m FROM MovieTicketBlocks m Where m.movieTicketBlockID  = :id");
        query.setParameter("id", id);
        return (MovieTicketBlocks) query.getSingleResult();
    }
}
