/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.MovieGenres;
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
public class MovieGenresFacade extends AbstractFacade<MovieGenres> implements MovieGenresFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovieGenresFacade() {
        super(MovieGenres.class);
    }
    
    @Override
    public List<MovieGenres> findByIDMovieID(String id){
        Query query = em.createQuery("SELECT m FROM MovieGenres m WHERE m.movieGenresPK.movieID = :movieID");
        query.setParameter("movieID", id);
        return query.getResultList();
    }
    
}
