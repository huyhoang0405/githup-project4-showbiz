/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Embeddable
public class MovieGenresPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Category_ID")
    private int categoryID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Movie_ID")
    private String movieID;

    public MovieGenresPK() {
    }

    public MovieGenresPK(int categoryID, String movieID) {
        this.categoryID = categoryID;
        this.movieID = movieID;
    }

    public MovieGenresPK(String movieID) {
        this.movieID = movieID;
    }
    
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) categoryID;
        hash += (movieID != null ? movieID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieGenresPK)) {
            return false;
        }
        MovieGenresPK other = (MovieGenresPK) object;
        if (this.categoryID != other.categoryID) {
            return false;
        }
        if ((this.movieID == null && other.movieID != null) || (this.movieID != null && !this.movieID.equals(other.movieID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.MovieGenresPK[ categoryID=" + categoryID + ", movieID=" + movieID + " ]";
    }
    
}
