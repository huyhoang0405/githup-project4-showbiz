/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Cinemas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cinemas.findAll", query = "SELECT c FROM Cinemas c")
    , @NamedQuery(name = "Cinemas.findByCinemaID", query = "SELECT c FROM Cinemas c WHERE c.cinemaID = :cinemaID")
    , @NamedQuery(name = "Cinemas.findByCinemaName", query = "SELECT c FROM Cinemas c WHERE c.cinemaName = :cinemaName")})
public class Cinemas implements Serializable {

    @Size(max = 200)
    @Column(name = "Address")
    private String address;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "Cinema_ID")
    private Integer cinemaID;
    @Size(max = 100)
    @Column(name = "Cinema_Name")
    private String cinemaName;
    @OneToMany(mappedBy = "cinemaID")
    private Collection<MovieTicketBlocks> movieTicketBlocksCollection;
    @JoinColumn(name = "Places_ID", referencedColumnName = "Places_ID")
    @ManyToOne
    private Places placesID;

    public Cinemas() {
    }

//    public Cinemas(Integer cinemaID) {
//        this.cinemaID = cinemaID;
//    }

    public Integer getCinemaID() {
        return cinemaID;
    }

//    public void setCinemaID(Integer cinemaID) {
//        this.cinemaID = cinemaID;
//    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    @XmlTransient
    public Collection<MovieTicketBlocks> getMovieTicketBlocksCollection() {
        return movieTicketBlocksCollection;
    }

    public void setMovieTicketBlocksCollection(Collection<MovieTicketBlocks> movieTicketBlocksCollection) {
        this.movieTicketBlocksCollection = movieTicketBlocksCollection;
    }

    public Places getPlacesID() {
        return placesID;
    }

    public void setPlacesID(Places placesID) {
        this.placesID = placesID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cinemaID != null ? cinemaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cinemas)) {
            return false;
        }
        Cinemas other = (Cinemas) object;
        if ((this.cinemaID == null && other.cinemaID != null) || (this.cinemaID != null && !this.cinemaID.equals(other.cinemaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Cinemas[ cinemaID=" + cinemaID + " ]";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
