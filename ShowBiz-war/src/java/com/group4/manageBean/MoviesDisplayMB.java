/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Movies;
import com.group4.sesionBeans.MoviesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Admin
 */
@Named(value = "moviesDisplayMB")
@SessionScoped
public class MoviesDisplayMB implements Serializable {

    @EJB
    private MoviesFacadeLocal moviesFacade;

    private Movies movie;
    private List<Movies> list;
    public MoviesDisplayMB() {
    }
    
    //show all movies in website
    public List<Movies> showAllMovies(){
        return moviesFacade.findAll();
    }
   
    public List<Movies> show6NewestMovies(){
        return moviesFacade.select6NewestMovies();
    }
    
     public List<Movies> show5NewestMovies(){
        return moviesFacade.select5NewestMovies();
    }
     
    //show 5 newest movies in website
    public List<Movies> show5MoviesTrailer(){
        List<Movies> l = new ArrayList<>();
        list = moviesFacade.select5NewestMovies();
        for(int i =0; i< list.size();i++){
            Movies mov = list.get(i);
             mov.setTrailer((list.get(i).getTrailer()).substring(32));
             l.add(mov);
        } 
        return l;
    }
    
   // show details movie 
    public String showDetailsMovie(String id) {
        Movies m = moviesFacade.find(id);
        m.setTrailer((m.getTrailer()).substring(32));
        setMovie(m);
        return "details";
    }
    
     public String showDetailsoutMovie(String id) {
        Movies m = moviesFacade.find(id);
        m.setTrailer((m.getTrailer()).substring(32));
        setMovie(m);
        return "/client/movies/details";
    }
     
    //show 8 movies in website
    public List<Movies> show8Movies(){       
        return moviesFacade.select8Movies();
    }
    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public List<Movies> getList() {
        return list;
    }

    public void setList(List<Movies> list) {
        this.list = list;
    }
    
}
