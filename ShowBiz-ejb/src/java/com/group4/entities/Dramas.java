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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Dramas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dramas.findAll", query = "SELECT d FROM Dramas d")
    , @NamedQuery(name = "Dramas.findByDramaID", query = "SELECT d FROM Dramas d WHERE d.dramaID = :dramaID")
    , @NamedQuery(name = "Dramas.findByDramaName", query = "SELECT d FROM Dramas d WHERE d.dramaName = :dramaName")
    , @NamedQuery(name = "Dramas.findByLength", query = "SELECT d FROM Dramas d WHERE d.length = :length")
    , @NamedQuery(name = "Dramas.findByReleaseDate", query = "SELECT d FROM Dramas d WHERE d.releaseDate = :releaseDate")
    , @NamedQuery(name = "Dramas.findByContent", query = "SELECT d FROM Dramas d WHERE d.content = :content")
    , @NamedQuery(name = "Dramas.findByCountry", query = "SELECT d FROM Dramas d WHERE d.country = :country")
    , @NamedQuery(name = "Dramas.findByLanguage", query = "SELECT d FROM Dramas d WHERE d.language = :language")
    , @NamedQuery(name = "Dramas.findByDirector", query = "SELECT d FROM Dramas d WHERE d.director = :director")
    , @NamedQuery(name = "Dramas.findByPoster", query = "SELECT d FROM Dramas d WHERE d.poster = :poster")
    , @NamedQuery(name = "Dramas.findByBanner", query = "SELECT d FROM Dramas d WHERE d.banner = :banner")
    , @NamedQuery(name = "Dramas.findByLike", query = "SELECT d FROM Dramas d WHERE d.like = :like")
    , @NamedQuery(name = "Dramas.findByUrl", query = "SELECT d FROM Dramas d WHERE d.url = :url")
    , @NamedQuery(name = "Dramas.findByEpisodes", query = "SELECT d FROM Dramas d WHERE d.episodes = :episodes")})
public class Dramas implements Serializable {

    @Size(max = 200)
    @Column(name = "Trailer")
    private String trailer;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Drama_ID")
    private String dramaID;
    @Size(max = 100)
    @Column(name = "Drama_Name")
    private String dramaName;
    @Column(name = "Length")
    private Integer length;
    @Column(name = "ReleaseDate")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Size(max = 2000)
    @Column(name = "Content")
    private String content;
    @Size(max = 100)
    @Column(name = "Country")
    private String country;
    @Size(max = 100)
    @Column(name = "Language")
    private String language;
    @Size(max = 100)
    @Column(name = "Director")
    private String director;
    @Size(max = 100)
    @Column(name = "Poster")
    private String poster;
    @Size(max = 100)
    @Column(name = "Banner")
    private String banner;
    @Column(name = "Like")
    private Integer like;
    @Size(max = 250)
    @Column(name = "URL")
    private String url;
    @Column(name = "Episodes")
    private Integer episodes;
    @JoinTable(name = "DramaStarringes", joinColumns = {
        @JoinColumn(name = "Drama_ID", referencedColumnName = "Drama_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "Starring_ID", referencedColumnName = "Starring_ID")})
    @ManyToMany
    private Collection<Starringes> starringesCollection;
    @ManyToMany(mappedBy = "dramasCollection")
    private Collection<Categories> categoriesCollection;
    @OneToMany(mappedBy = "dramaID")
    private Collection<DramaCommentaries> dramaCommentariesCollection;

    public Dramas() {
    }

    public Dramas(String dramaID) {
        this.dramaID = dramaID;
    }

    public String getDramaID() {
        return dramaID;
    }

    public void setDramaID(String dramaID) {
        this.dramaID = dramaID;
    }

    public String getDramaName() {
        return dramaName;
    }

    public void setDramaName(String dramaName) {
        this.dramaName = dramaName;
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

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    @XmlTransient
    public Collection<Starringes> getStarringesCollection() {
        return starringesCollection;
    }

    public void setStarringesCollection(Collection<Starringes> starringesCollection) {
        this.starringesCollection = starringesCollection;
    }

    @XmlTransient
    public Collection<Categories> getCategoriesCollection() {
        return categoriesCollection;
    }

    public void setCategoriesCollection(Collection<Categories> categoriesCollection) {
        this.categoriesCollection = categoriesCollection;
    }

    @XmlTransient
    public Collection<DramaCommentaries> getDramaCommentariesCollection() {
        return dramaCommentariesCollection;
    }

    public void setDramaCommentariesCollection(Collection<DramaCommentaries> dramaCommentariesCollection) {
        this.dramaCommentariesCollection = dramaCommentariesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dramaID != null ? dramaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dramas)) {
            return false;
        }
        Dramas other = (Dramas) object;
        if ((this.dramaID == null && other.dramaID != null) || (this.dramaID != null && !this.dramaID.equals(other.dramaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Dramas[ dramaID=" + dramaID + " ]";
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
    
}
