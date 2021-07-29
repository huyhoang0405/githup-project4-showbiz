/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Cinemas;
import com.group4.entities.Customers;
import com.group4.entities.MovieTicketBlocks;
import com.group4.entities.Movies;
import com.group4.entities.TicketTypes;
import java.util.Collection;
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
public class MovieTicketBlocksFacade extends AbstractFacade<MovieTicketBlocks> implements MovieTicketBlocksFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovieTicketBlocksFacade() {
        super(MovieTicketBlocks.class);
    }

    @Override
    public String getLastID() {
        Query query = em.createQuery("SELECT MAX (m.movieTicketBlockID) FROM MovieTicketBlocks m");
        return query.getSingleResult().toString();
    }

    @Override
    public Collection showAllBlock() {
        Query query = em.createQuery("SELECT b From MovieTicketBlocks b join b.movieID m ");
        return query.getResultList();
    }

    @Override
    public List<Cinemas> findByMovieID(Movies id) {
        Query query = em.createQuery("SELECT DISTINCT (m.cinemaID)  From MovieTicketBlocks m WHERE m.movieID =:movieID");
        query.setParameter("movieID", id);
        return query.getResultList();
    }

    @Override
    public MovieTicketBlocks findByMovieID(String id) {
        Query query = em.createQuery("SELECT DISTINCT m.movieID From MovieTicketBlocks m WHERE m.movieID =:movieID");
        query.setParameter("movieID", id);
        return (MovieTicketBlocks) query.getSingleResult();
    }

    @Override
    public List<MovieTicketBlocks> findByCinemaID(Cinemas id) {
        Query query = em.createQuery("SELECT m From MovieTicketBlocks m WHERE m.cinemaID =:cinemaID");
        query.setParameter("cinemaID", id);
        return query.getResultList();
    }

    @Override
    public List<MovieTicketBlocks> findByCinemaID(Movies movieID, Cinemas cinemaID) {
        Query query = em.createQuery("SELECT m From MovieTicketBlocks m WHERE m.movieID = :movieID AND m.cinemaID =:cinemaID ");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        // return query.getSingleResult();
        return query.setMaxResults(1).getResultList();
    }

    @Override
    public List<TicketTypes> findByTicketID(Movies movieID, Cinemas cinemaID, TicketTypes ticketID, Date date, String time) {
        Query query = em.createQuery("SELECT Distinct(m.ticketTypeID) From MovieTicketBlocks m WHERE m.cinemaID =:cinemaID AND m.movieID = :movieID and m.ticketTypeID =:ticketTypeID and m.date =:date and m.time =:time");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("ticketTypeID", ticketID);
        query.setParameter("date", date);
        query.setParameter("time", time);
        return query.getResultList();
    }

    @Override
    public MovieTicketBlocks findaMovieByTicketID(Movies movieID, Cinemas cinemaID, TicketTypes ticketID, Date date, String time) {
        Query query = em.createQuery("SELECT Distinct m From MovieTicketBlocks m WHERE m.cinemaID =:cinemaID AND m.movieID = :movieID and m.ticketTypeID =:ticketTypeID and m.date =:date and m.time =:time");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("ticketTypeID", ticketID);
        query.setParameter("date", date);
        query.setParameter("time", time);
        return (MovieTicketBlocks) query.getSingleResult();
    }

    @Override
    public List<MovieTicketBlocks> selectType(Movies movieID, Cinemas cinemaID, TicketTypes ticketID, Date date, String time) {
        Query query = em.createQuery("SELECT DISTINCT m From MovieTicketBlocks m WHERE m.cinemaID =:cinemaID AND m.movieID = :movieID and m.ticketTypeID =:ticketTypeID and m.date =:date and m.time =:time");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("date", date);
        query.setParameter("time", time);
        query.setParameter("ticketTypeID", ticketID);
        return query.getResultList();
    }

    @Override
    public List<String> findByDate(Movies movieID, Cinemas cinemaID, Date id) {
        Query query = em.createQuery("SELECT DISTINCT (m.time) From MovieTicketBlocks m WHERE m.movieID = :movieID AND m.cinemaID =:cinemaID and m.date =:date");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("date", id);
        return query.getResultList();
    }

    @Override
    public List<TicketTypes> findByTime(Movies movieID, Cinemas cinemaID, Date date, String time) {
        Query query = em.createQuery("SELECT DISTINCT (m.ticketTypeID) From MovieTicketBlocks m WHERE m.movieID = :movieID AND m.cinemaID =:cinemaID AND m.date =:date AND m.time =:time");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("date", date);
        query.setParameter("time", time);
        return query.getResultList();
    }

    @Override
    public MovieTicketBlocks findaMovieByTime(Movies movieID, Cinemas cinemaID, Date date, String time) {
        Query query = em.createQuery("SELECT DISTINCT m From MovieTicketBlocks m WHERE m.movieID = :movieID AND m.cinemaID =:cinemaID AND m.date =:date AND m.time =:time");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("date", date);
        query.setParameter("time", time);
        return (MovieTicketBlocks) query.getSingleResult();
    }

    @Override
    public MovieTicketBlocks findTicket(Movies movieID, Cinemas cinemaID, TicketTypes ticketID) {
        Query query = em.createQuery("SELECT m From MovieTicketBlocks m WHERE m.movieID = :movieID AND m.cinemaID =:cinemaID and m.ticketTypeID =:ticketTypeID");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("ticketTypeID", ticketID);
        // return query.getSingleResult();
        return (MovieTicketBlocks) query.getSingleResult();
    }

    @Override
    public List<TicketTypes> findByTicketID(Movies movieID, Cinemas cinemaID) {
        Query query = em.createQuery("SELECT DISTINCT (m.ticketTypeID) From MovieTicketBlocks m WHERE m.movieID = :movieID AND m.cinemaID =:cinemaID ");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        // return query.getSingleResult();
        return query.getResultList();
    }

    public boolean checkBlock(Date date, String time, Long price, Cinemas cinema, TicketTypes ticket, Movies movie) {
        Query query = em.createQuery("SELECT m FROM MovieTicketBlocks m WHERE m.cinemaID = :cinemaID AND m.date = :date AND m.movieID = :movieID AND m.ticketTypeID = :ticketTypeID AND m.time = :time AND m.unitPrice = :unitPrice");
        query.setParameter("cinemaID", cinema);
        query.setParameter("date", date);
        query.setParameter("movieID", movie);
        query.setParameter("ticketTypeID", ticket);
        query.setParameter("time", time);
        query.setParameter("unitPrice", price);
        List<MovieTicketBlocks> list = query.getResultList();
        return list.size() > 0;
    }

    public MovieTicketBlocks findBlock(Date date, String time, Long price, Cinemas cinema, TicketTypes ticket, Movies movie) {
        Query query = em.createQuery("SELECT m FROM MovieTicketBlocks m WHERE m.cinemaID = :cinemaID AND m.date = :date AND m.movieID = :movieID AND m.ticketTypeID = :ticketTypeID AND m.time = :time AND m.unitPrice = :unitPrice");
        query.setParameter("cinemaID", cinema);
        query.setParameter("date", date);
        query.setParameter("movieID", movie);
        query.setParameter("ticketTypeID", ticket);
        query.setParameter("time", time);
        query.setParameter("unitPrice", price);

        return (MovieTicketBlocks) query.getSingleResult();
    }

    public List<Movies> findMovieInBlock() {
        Query query = em.createQuery("SELECT DISTINCT (m.movieID) FROM MovieTicketBlocks m");
        return query.getResultList();
    }

    public Object ticketStatistics(Movies movie) {
        Query query = em.createQuery("SELECT SUM(m.quantity-m.residual) FROM MovieTicketBlocks m Where m.movieID = :movieID");
        query.setParameter("movieID", movie);
        return query.getSingleResult();
    }

    public Object statisticMovie(Date startdate, Date enddate) {
        Query query = em.createQuery("SELECT SUM(odm.quantity) FROM  OrderMovieDetails odm JOIN odm.movieTicketBlocks m JOIN odm.orders o Where o.dateOfPurchase BETWEEN :startdate And :enddate");
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        return query.getSingleResult();
    }
    
    public List<OrderMovieDetailsFacade> orderOfCustomer(Customers customer) {
        Query query = em.createQuery("SELECT odm FROM  OrderMovieDetails odm JOIN odm.movieTicketBlocks m JOIN odm.orders o Where o.customerUsername = :customerUsername");
        query.setParameter("customerUsername", customer);
        return query.getResultList();
    }
}
