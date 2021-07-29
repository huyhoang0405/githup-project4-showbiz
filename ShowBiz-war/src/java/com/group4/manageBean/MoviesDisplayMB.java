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
import java.util.Calendar;
import java.util.Date;
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

    private final Calendar calendar = Calendar.getInstance();
    private Movies movie;
    private List<Movies> list;

    public MoviesDisplayMB() {
    }

    //show all movies in website
    public List<Movies> showAllMovies() {
        List<Movies> list = moviesFacade.findAll();
        for (Movies o : list) {
            calendar.setTime(o.getReleaseDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            o.setReleaseDate(endDate);
        }
        return list;
    }

    public List<Movies> show6NewestMovies() {
        List<Movies> list = moviesFacade.select6NewestMovies();
        for (Movies o : list) {
            calendar.setTime(o.getReleaseDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            o.setReleaseDate(endDate);
        }
        return list;
    }

    public List<Movies> show5NewestMovies() {
        List<Movies> list = moviesFacade.select5NewestMovies();
        for (Movies o : list) {
            calendar.setTime(o.getReleaseDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            o.setReleaseDate(endDate);
        }
        return list;
    }

    //show 5 newest movies in website
    public List<Movies> show5MoviesTrailer() {
        List<Movies> l = new ArrayList<>();
        list = moviesFacade.select5NewestMovies();
        for (Movies o : list) {
            calendar.setTime(o.getReleaseDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            o.setReleaseDate(endDate);
        }
        for (int i = 0; i < list.size(); i++) {
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
        calendar.setTime(m.getReleaseDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        m.setReleaseDate(endDate);
        return "details";
    }

    public String showDetailsoutMovie(String id) {
        Movies m = moviesFacade.find(id);
        m.setTrailer((m.getTrailer()).substring(32));
        setMovie(m);
        calendar.setTime(m.getReleaseDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        m.setReleaseDate(endDate);
        return "/client/movies/details";
    }

    //show 8 movies in website
    public List<Movies> show8Movies() {
        List<Movies> list = moviesFacade.select8Movies();
        for (Movies o : list) {
            calendar.setTime(o.getReleaseDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            o.setReleaseDate(endDate);
        }
        return list;
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
