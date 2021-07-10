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
    public List<MovieTicketBlocks> findByMovieID(Movies id){
        Query query = em.createQuery("SELECT DISTINCT m  From MovieTicketBlocks m WHERE m.movieID =:movieID");
        query.setParameter("movieID", id);
        return query.getResultList();
    }
    @Override
    public MovieTicketBlocks findByMovieID(String id){
        Query query = em.createQuery("SELECT DISTINCT m From MovieTicketBlocks m WHERE m.movieID =:movieID");
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
    public MovieTicketBlocks findByCinemaID(Integer id){
        Query query = em.createQuery("SELECT DISTINCT m From MovieTicketBlocks m WHERE m.cinemaID =:cinemaID");
        query.setParameter("cinemaID", id);
        return (MovieTicketBlocks) query.getSingleResult();
    }
    @Override
    public List<MovieTicketBlocks> findByTicketID(TicketTypes id){
        Query query = em.createQuery("SELECT m From MovieTicketBlocks m WHERE m.ticketTypeID =:ticketTypeID");
        query.setParameter("ticketTypeID", id);
        return query.getResultList();
    }
    @Override
     public MovieTicketBlocks findByTicketID(Integer id){
        Query query = em.createQuery("SELECT DISTINCT m From MovieTicketBlocks m WHERE m.ticketTypeID =:ticketTypeID");
        query.setParameter("ticketTypeID", id);
        return (MovieTicketBlocks) query.getSingleResult();
    }
    @Override
    public List<MovieTicketBlocks> findByDate(Date id){
        Query query = em.createQuery("SELECT DISTINCT m From MovieTicketBlocks m WHERE m.date =:date");
        query.setParameter("date", id);
        return query.getResultList();
    }
}
