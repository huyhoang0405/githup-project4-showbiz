/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Movies;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface MoviesFacadeLocal {

    void create(Movies movies);

    void edit(Movies movies);

    void remove(Movies movies);

    Movies find(Object id);

    List<Movies> findAll();

    List<Movies> findRange(int[] range);

    int count();

    String getLastID();

    List<Movies> select5NewestMovies();

    List<Movies> select8NewsetMovies(Date date);

    List<Movies> select6NewestMovies();

    List<Movies> select8PlayMovies(Date start, Date end);
    
    List<Movies> searchMovieName(String keyword);
    
    
}
