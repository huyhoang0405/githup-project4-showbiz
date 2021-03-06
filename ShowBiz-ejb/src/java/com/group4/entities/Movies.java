/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Movies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movies.findAll", query = "SELECT m FROM Movies m")
    , @NamedQuery(name = "Movies.findByMovieID", query = "SELECT m FROM Movies m WHERE m.movieID = :movieID")
    , @NamedQuery(name = "Movies.findByMovieName", query = "SELECT m FROM Movies m WHERE m.movieName = :movieName")
    , @NamedQuery(name = "Movies.findByStarring", query = "SELECT m FROM Movies m WHERE m.starring = :starring")
    , @NamedQuery(name = "Movies.findByLength", query = "SELECT m FROM Movies m WHERE m.length = :length")
    , @NamedQuery(name = "Movies.findByReleaseDate", query = "SELECT m FROM Movies m WHERE m.releaseDate = :releaseDate")
    , @NamedQuery(name = "Movies.findByContent", query = "SELECT m FROM Movies m WHERE m.content = :content")
    , @NamedQuery(name = "Movies.findByCountry", query = "SELECT m FROM Movies m WHERE m.country = :country")
    , @NamedQuery(name = "Movies.findByLanguage", query = "SELECT m FROM Movies m WHERE m.language = :language")
    , @NamedQuery(name = "Movies.findByDirector", query = "SELECT m FROM Movies m WHERE m.director = :director")
    , @NamedQuery(name = "Movies.findByTrailer", query = "SELECT m FROM Movies m WHERE m.trailer = :trailer")
    , @NamedQuery(name = "Movies.findByPoster", query = "SELECT m FROM Movies m WHERE m.poster = :poster")
    , @NamedQuery(name = "Movies.findByBanner", query = "SELECT m FROM Movies m WHERE m.banner = :banner")
    , @NamedQuery(name = "Movies.findByNote", query = "SELECT m FROM Movies m WHERE m.note = :note")})
public class Movies implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movies")
    private Collection<MovieGenres> movieGenresCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Movie_ID")
    private String movieID;
    @NotNull(message = "Movie name can't be left empty!")
    @Size(min = 5,max = 100, message = "Movie name must be between 5 and 100 characters.!")
    @Column(name = "Movie_Name")
    private String movieName;
    @NotNull(message = "Starring can't be left empty!")
    @Size(min = 5,max = 200, message = "Starring must be between 5 and 100 characters.!")
    @Column(name = "Starring")
    private String starring;
    @NotNull(message = "Length can't be left empty!")
    @Column(name = "Length")
    private Integer length;
    @NotNull(message = "Release date can't be left empty!")
    @Column(name = "ReleaseDate")
    @Temporal(TemporalType.DATE)
    @Future(message = "Date must be in the future!")
    private Date releaseDate;
    @Size(min = 5,max = 2000, message = "Content must be between 5 and 2000 characters.!")
    @Column(name = "Content")
    private String content;
    @NotNull(message = "Country can't be left empty!")
    @Size(min = 3, max = 100, message = "Country must be between 3 and 100 characters.!")
    @Column(name = "Country")
    private String country;
    @NotNull(message = "Language can't be left empty!")
    @Size(min = 3, max = 100, message = "Language must be between 3 and 100 characters.!")
    @Column(name = "Language")
    private String language;
    @NotNull(message = "Director can't be left empty!")
    @Size(min = 3, max = 100, message = "Director must be between 3 and 100 characters.!")
    @Column(name = "Director")
    private String director;
    @NotNull(message = "Trailer can't be left empty!")
    @Size(min = 5, max = 200, message = "Trailer must be between 5 and 200 characters.!")
    @Column(name = "Trailer")
    private String trailer;
    @NotNull(message = "Poster can't be left empty!")
    @Size(max = 100)
    @Column(name = "Poster")
    private String poster;
    @NotNull(message = "Banner can't be left empty!")
    @Size(max = 100)
    @Column(name = "Banner")
    private String banner;
    @Size(max = 500)
    @Column(name = "Note")
    private String note;
    @ManyToMany(mappedBy = "moviesCollection")
    private Collection<Categories> categoriesCollection;
    @OneToMany(mappedBy = "movieID")
    private Collection<MovieTicketBlocks> movieTicketBlocksCollection;

    public Movies() {
    }

    public Movies(String movieID) {
        this.movieID = movieID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @XmlTransient
    public Collection<Categories> getCategoriesCollection() {
        return categoriesCollection;
    }

    public void setCategoriesCollection(Collection<Categories> categoriesCollection) {
        this.categoriesCollection = categoriesCollection;
    }

    @XmlTransient
    public Collection<MovieTicketBlocks> getMovieTicketBlocksCollection() {
        return movieTicketBlocksCollection;
    }

    public void setMovieTicketBlocksCollection(Collection<MovieTicketBlocks> movieTicketBlocksCollection) {
        this.movieTicketBlocksCollection = movieTicketBlocksCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieID != null ? movieID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movies)) {
            return false;
        }
        Movies other = (Movies) object;
        if ((this.movieID == null && other.movieID != null) || (this.movieID != null && !this.movieID.equals(other.movieID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Movies[ movieID=" + movieID + " ]";
    }

    @XmlTransient
    public Collection<MovieGenres> getMovieGenresCollection() {
        return movieGenresCollection;
    }

    public void setMovieGenresCollection(Collection<MovieGenres> movieGenresCollection) {
        this.movieGenresCollection = movieGenresCollection;
    }
    
}
