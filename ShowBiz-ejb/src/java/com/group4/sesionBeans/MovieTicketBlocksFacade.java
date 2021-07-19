/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Cinemas;
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
    public Collection showAllBlock(){
        Query query = em.createQuery("SELECT b From MovieTicketBlocks b join b.movieID m ");
        return query.getResultList();
    }
    
    @Override
    public List<Cinemas> findByMovieID(Movies id){
        Query query = em.createQuery("SELECT DISTINCT (m.cinemaID)  From MovieTicketBlocks m WHERE m.movieID =:movieID");
        query.setParameter("movieID", id);
        return query.getResultList();
    }
    @Override
    public MovieTicketBlocks findByMovieID(String id){
        Query query = em.createQuery("SELECT DISTINCT m.movieID From MovieTicketBlocks m WHERE m.movieID =:movieID");
        query.setParameter("movieID", id);
        return (MovieTicketBlocks) query.getSingleResult();
    }
    @Override
    public List<MovieTicketBlocks> findByCinemaID(Cinemas id){
        Query query = em.createQuery("SELECT m From MovieTicketBlocks m WHERE m.cinemaID =:cinemaID");
        query.setParameter("cinemaID", id);
        return query.getResultList();
    }
    @Override
    public List<MovieTicketBlocks> findByCinemaID(Movies movieID,Cinemas cinemaID){
        Query query = em.createQuery("SELECT m From MovieTicketBlocks m WHERE m.movieID = :movieID AND m.cinemaID =:cinemaID ");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
       // return query.getSingleResult();
        return query.setMaxResults(1).getResultList();
    }
    @Override
    public List<MovieTicketBlocks> findByTicketID(Movies movieID,Cinemas cinemaID,TicketTypes ticketID,Date date,String time){
        Query query = em.createQuery("SELECT m From MovieTicketBlocks m WHERE m.cinemaID =:cinemaID AND m.movieID = :movieID and m.ticketTypeID =:ticketTypeID and m.date =:date and m.time =:time");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("ticketTypeID", ticketID);
        query.setParameter("date", date);
        query.setParameter("time", time);
        return query.getResultList();
    }
    @Override
     public List<MovieTicketBlocks> selectType(Movies movieID,Cinemas cinemaID,TicketTypes ticketID,Date date,String time){
        Query query = em.createQuery("SELECT DISTINCT m From MovieTicketBlocks m WHERE m.cinemaID =:cinemaID AND m.movieID = :movieID and m.ticketTypeID =:ticketTypeID and m.date =:date and m.time =:time");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("date", date);
        query.setParameter("time", time);
        query.setParameter("ticketTypeID", ticketID);
        return  query.getResultList();
    }
    @Override
    public List<MovieTicketBlocks> findByDate(Movies movieID,Cinemas cinemaID,Date id){
        Query query = em.createQuery("SELECT DISTINCT m From MovieTicketBlocks m WHERE m.movieID = :movieID AND m.cinemaID =:cinemaID and m.date =:date");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("date", id);
        return query.getResultList();
    }
    @Override
    public List<MovieTicketBlocks> findByTime(Movies movieID,Cinemas cinemaID,Date date,String time){
        Query query = em.createQuery("SELECT DISTINCT m From MovieTicketBlocks m WHERE m.movieID = :movieID AND m.cinemaID =:cinemaID AND m.date =:date AND m.time =:time");
        query.setParameter("cinemaID", cinemaID);
        query.setParameter("movieID", movieID);
        query.setParameter("date", date);
        query.setParameter("time", time);
        return query.getResultList();
    }
}
