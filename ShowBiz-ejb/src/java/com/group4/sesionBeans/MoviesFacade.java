/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Movies;
import java.util.Calendar;
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
public class MoviesFacade extends AbstractFacade<Movies> implements MoviesFacadeLocal {

    Calendar c = Calendar.getInstance();
    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MoviesFacade() {
        super(Movies.class);
    }
    
     @Override
    public String getLastID(){
        Query query = em.createQuery("SELECT MAX (m.movieID) FROM Movies m");
        return query.getSingleResult().toString();
    }
    @Override
    public List<Movies> select5NewestMovies(){
        Query query = em.createQuery("SELECT DISTINCT m FROM Movies m Where m.releaseDate > :date ORDER BY m.releaseDate");
        query.setParameter("date", c.getTime());
        query.setMaxResults(5);
        return query.getResultList();
    }
    
    @Override
    public List<Movies> select6NewestMovies(){
        Query query = em.createQuery("SELECT DISTINCT m FROM Movies m ORDER BY m.releaseDate DESC");
        query.setMaxResults(6);
        return query.getResultList();
    }
    @Override
    public List<Movies> select8NewsetMovies(Date date){
        Query query = em.createQuery("SELECT DISTINCT m FROM Movies m Where m.releaseDate > :date ORDER BY m.releaseDate");
        query.setParameter("date", date);
        query.setMaxResults(8);
        return query.getResultList();
    }
    public List<Movies> select8PlayMovies(Date start, Date end){
        Query query = em.createQuery("SELECT DISTINCT m FROM Movies m Where m.releaseDate BETWEEN :start AND :end ORDER BY m.releaseDate");
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setMaxResults(8);
        return query.getResultList();
    }
    
    public List<Movies> searchMovieName(String keyword){
        Query query = em.createQuery("SELECT DISTINCT m FROM Movies m Where m.movieName LIKE :keyword ORDER BY m.releaseDate DESC");
        query.setParameter("keyword", "%"+ keyword + "%");
        return query.getResultList();
    }
}
