/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "MovieGenres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieGenres.findAll", query = "SELECT m FROM MovieGenres m")
    , @NamedQuery(name = "MovieGenres.findByCategoryID", query = "SELECT m FROM MovieGenres m WHERE m.movieGenresPK.categoryID = :categoryID")
    , @NamedQuery(name = "MovieGenres.findByMovieID", query = "SELECT m FROM MovieGenres m WHERE m.movieGenresPK.movieID = :movieID")
    , @NamedQuery(name = "MovieGenres.findByNote", query = "SELECT m FROM MovieGenres m WHERE m.note = :note")})
public class MovieGenres implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovieGenresPK movieGenresPK;
    @Size(max = 200)
    @Column(name = "Note")
    private String note;
    @JoinColumn(name = "Category_ID", referencedColumnName = "Category_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categories categories;
    @JoinColumn(name = "Movie_ID", referencedColumnName = "Movie_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movies movies;

    public MovieGenres() {
    }

    public MovieGenres(MovieGenresPK movieGenresPK) {
        this.movieGenresPK = movieGenresPK;
    }

    public MovieGenres(int categoryID, String movieID) {
        this.movieGenresPK = new MovieGenresPK(categoryID, movieID);
    }

    public MovieGenresPK getMovieGenresPK() {
        return movieGenresPK;
    }

    public void setMovieGenresPK(MovieGenresPK movieGenresPK) {
        this.movieGenresPK = movieGenresPK;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieGenresPK != null ? movieGenresPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieGenres)) {
            return false;
        }
        MovieGenres other = (MovieGenres) object;
        if ((this.movieGenresPK == null && other.movieGenresPK != null) || (this.movieGenresPK != null && !this.movieGenresPK.equals(other.movieGenresPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.MovieGenres[ movieGenresPK=" + movieGenresPK + " ]";
    }
    
}
