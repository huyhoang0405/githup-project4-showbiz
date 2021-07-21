/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c")
    , @NamedQuery(name = "Categories.findByCategoryID", query = "SELECT c FROM Categories c WHERE c.categoryID = :categoryID")
    , @NamedQuery(name = "Categories.findByCategoryName", query = "SELECT c FROM Categories c WHERE c.categoryName = :categoryName")})
public class Categories implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private Collection<MovieGenres> movieGenresCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "Category_ID")
    private Integer categoryID;
    @Size(max = 100)
    @Column(name = "Category_Name")
    private String categoryName;
    @JoinTable(name = "MovieGenres", joinColumns = {
        @JoinColumn(name = "Category_ID", referencedColumnName = "Category_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "Movie_ID", referencedColumnName = "Movie_ID")})
    @ManyToMany
    private Collection<Movies> moviesCollection;
    @JoinTable(name = "DramaGenres", joinColumns = {
        @JoinColumn(name = "Category_ID", referencedColumnName = "Category_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "Drama_ID", referencedColumnName = "Drama_ID")})
    @ManyToMany
    private Collection<Dramas> dramasCollection;

    public Categories() {
    }

//    public Categories(Integer categoryID) {
//        this.categoryID = categoryID;
//    }

    public Integer getCategoryID() {
        return categoryID;
    }

//    public void setCategoryID(Integer categoryID) {
//        this.categoryID = categoryID;
//    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @XmlTransient
    public Collection<Movies> getMoviesCollection() {
        return moviesCollection;
    }

    public void setMoviesCollection(Collection<Movies> moviesCollection) {
        this.moviesCollection = moviesCollection;
    }

    @XmlTransient
    public Collection<Dramas> getDramasCollection() {
        return dramasCollection;
    }

    public void setDramasCollection(Collection<Dramas> dramasCollection) {
        this.dramasCollection = dramasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Categories[ categoryID=" + categoryID + " ]";
    }

    @XmlTransient
    public Collection<MovieGenres> getMovieGenresCollection() {
        return movieGenresCollection;
    }

    public void setMovieGenresCollection(Collection<MovieGenres> movieGenresCollection) {
        this.movieGenresCollection = movieGenresCollection;
    }
    
}
