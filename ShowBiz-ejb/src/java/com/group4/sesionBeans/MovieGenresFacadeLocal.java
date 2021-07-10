/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.MovieGenres;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface MovieGenresFacadeLocal {

    void create(MovieGenres movieGenres);

    void edit(MovieGenres movieGenres);

    void remove(MovieGenres movieGenres);

    MovieGenres find(Object id);

    List<MovieGenres> findAll();

    List<MovieGenres> findRange(int[] range);

    int count();
    
    List<MovieGenres> findByIDMovieID(String id);
}
