/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Customers;
import com.group4.entities.Orders;
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
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    public String getLastID() {
        Query query = em.createQuery("SELECT MAX (o.orderID) FROM Orders o");
        return query.getSingleResult().toString();
    }

    public List<Orders> showAll(){
        Query query = em.createQuery("SELECT o FROM Orders o ORDER BY o.dateOfPurchase DESC");
        return query.getResultList();
    }
    
    public Object totalOfCustomer(Customers un) {
        Query query = em.createQuery("SELECT SUM(o.totalPrice) FROM Orders o Where o.customerUsername = :customerUsername");
        query.setParameter("customerUsername", un);
        return query.getSingleResult();
    }

    public List<Customers> findCustomersInOrder() {
        Query query = em.createQuery("SELECT DISTINCT (o.customerUsername) FROM Orders o");
        return query.getResultList();
    }

    public Object statisticOrder(Date startdate, Date enddate) {
        Query query = em.createQuery("SELECT SUM(o.totalPrice) FROM Orders o Where o.dateOfPurchase BETWEEN :startdate And :enddate");
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        return query.getSingleResult();
    }

}
