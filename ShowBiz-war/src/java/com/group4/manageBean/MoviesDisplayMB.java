/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.MovieGenres;
import com.group4.entities.Movies;
import com.group4.sesionBeans.MovieGenresFacadeLocal;
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
    private MovieGenresFacadeLocal movieGenresFacade;

    @EJB
    private MoviesFacadeLocal moviesFacade;

    private final Calendar calendar = Calendar.getInstance();
    final Calendar c = Calendar.getInstance();
    final Calendar cal = Calendar.getInstance();
    private String[] categoryID;
    private Movies movie;
    private List<Movies> list;
    private String keyword;

    public MoviesDisplayMB() {
    }

    public String demo() {
        return "search?faces-redirect=true";
    }

    public String search(String keyword) {
        searchKeyword(keyword);
        return "search?faces-redirect=true";
    }

    public void searchKeyword(String keyword) {
        list = moviesFacade.searchMovieName(keyword);
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
    public List<Movies> show5Movies() {
        List<Movies> l = new ArrayList<>();
        list = moviesFacade.select5NewestMovies();
        for (Movies o : list) {
            calendar.setTime(o.getReleaseDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            o.setReleaseDate(endDate);

            List<MovieGenres> list = movieGenresFacade.findByIDMovieID(o.getMovieID());
            String[] arrCa = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arrCa[i] = list.get(i).getCategories().getCategoryName();
            }
            setCategoryID(arrCa);
        }
        for (int i = 0; i < list.size(); i++) {
            Movies mov = list.get(i);
          
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

        List<MovieGenres> list = movieGenresFacade.findByIDMovieID(m.getMovieID());
        String[] arrCa = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrCa[i] = list.get(i).getCategories().getCategoryName();
        }
        setCategoryID(arrCa);
        return "details?faces-redirect=true";
    }

    public String showDetailsoutMovie(String id) {
        Movies m = moviesFacade.find(id);
        m.setTrailer((m.getTrailer()).substring(32));
        setMovie(m);
        calendar.setTime(m.getReleaseDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        m.setReleaseDate(endDate);
        return "/client/movies/details?faces-redirect=true";
    }
    
    public String showDetailsoutMovie_Guest(String id) {
        Movies m = moviesFacade.find(id);
        m.setTrailer((m.getTrailer()).substring(32));
        setMovie(m);
        calendar.setTime(m.getReleaseDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        m.setReleaseDate(endDate);
        return "/guest/movies/details?faces-redirect=true";
    }

    //show 8 movies in website
    public List<Movies> show8NewestMovies() {
        Date date = c.getTime();
        List<Movies> list = moviesFacade.select8NewsetMovies(date);
        for (Movies o : list) {
            calendar.setTime(o.getReleaseDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            o.setReleaseDate(endDate);
        }
        return list;
    }

    public List<Movies> show8PlayMovies() {
        Date end = c.getTime();
        cal.setTime(end);
        cal.roll(Calendar.MONTH, -1);
        Date start = cal.getTime();
        List<Movies> list = moviesFacade.select8PlayMovies(start, end);
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String[] getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String[] categoryID) {
        this.categoryID = categoryID;
    }

}