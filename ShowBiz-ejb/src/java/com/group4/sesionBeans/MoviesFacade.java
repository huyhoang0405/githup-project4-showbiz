/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Movies;
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
        Query query = em.createQuery("SELECT DISTINCT m FROM Movies m ORDER BY m.releaseDate DESC");
        query.setMaxResults(5);
        return query.getResultList();
    }
    @Override
    public List<Movies> select8Movies(){
        Query query = em.createQuery("SELECT DISTINCT m FROM Movies m ORDER BY m.movieID DESC");
        query.setMaxResults(8);
        return query.getResultList();
    }
//    
//     @Override
//     public List<MovieGenres> findByIDMovieName(String id){
//        Query query = em.createQuery("SELECT m FROM MovieGenres m WHERE m.movieGenresPK.movieID = :movieID");
//        query.setParameter("movieID", id);
//        return query.getResultList();
//    }
//    SELECT m FROM Movies m WHERE m.movieName = :movieName
}
