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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "MovieTicketBlocks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieTicketBlocks.findAll", query = "SELECT m FROM MovieTicketBlocks m")
    , @NamedQuery(name = "MovieTicketBlocks.findByMovieTicketBlockID", query = "SELECT m FROM MovieTicketBlocks m WHERE m.movieTicketBlockID = :movieTicketBlockID")
    , @NamedQuery(name = "MovieTicketBlocks.findByDate", query = "SELECT m FROM MovieTicketBlocks m WHERE m.date = :date")
    , @NamedQuery(name = "MovieTicketBlocks.findByTime", query = "SELECT m FROM MovieTicketBlocks m WHERE m.time = :time")
    , @NamedQuery(name = "MovieTicketBlocks.findByQuantity", query = "SELECT m FROM MovieTicketBlocks m WHERE m.quantity = :quantity")
    , @NamedQuery(name = "MovieTicketBlocks.findByResidual", query = "SELECT m FROM MovieTicketBlocks m WHERE m.residual = :residual")
    , @NamedQuery(name = "MovieTicketBlocks.findByUnitPrice", query = "SELECT m FROM MovieTicketBlocks m WHERE m.unitPrice = :unitPrice")})
public class MovieTicketBlocks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MovieTicketBlock_ID")
    private String movieTicketBlockID;
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "Time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Residual")
    private Integer residual;
    @Column(name = "UnitPrice")
    private Long unitPrice;
    @JoinTable(name = "OrderMovieDetails", joinColumns = {
        @JoinColumn(name = "MovieTicketBlock_ID", referencedColumnName = "MovieTicketBlock_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "Order_ID", referencedColumnName = "Order_ID")})
    @ManyToMany
    private Collection<Orders> ordersCollection;
    @JoinColumn(name = "Cinema_ID", referencedColumnName = "Cinema_ID")
    @ManyToOne
    private Cinemas cinemaID;
    @JoinColumn(name = "Movie_ID", referencedColumnName = "Movie_ID")
    @ManyToOne
    private Movies movieID;
    @JoinColumn(name = "TicketType_ID", referencedColumnName = "TicketType_ID")
    @ManyToOne
    private TicketTypes ticketTypeID;

    public MovieTicketBlocks() {
    }

    public MovieTicketBlocks(String movieTicketBlockID) {
        this.movieTicketBlockID = movieTicketBlockID;
    }

    public String getMovieTicketBlockID() {
        return movieTicketBlockID;
    }

    public void setMovieTicketBlockID(String movieTicketBlockID) {
        this.movieTicketBlockID = movieTicketBlockID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getResidual() {
        return residual;
    }

    public void setResidual(Integer residual) {
        this.residual = residual;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    public Cinemas getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(Cinemas cinemaID) {
        this.cinemaID = cinemaID;
    }

    public Movies getMovieID() {
        return movieID;
    }

    public void setMovieID(Movies movieID) {
        this.movieID = movieID;
    }

    public TicketTypes getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(TicketTypes ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieTicketBlockID != null ? movieTicketBlockID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieTicketBlocks)) {
            return false;
        }
        MovieTicketBlocks other = (MovieTicketBlocks) object;
        if ((this.movieTicketBlockID == null && other.movieTicketBlockID != null) || (this.movieTicketBlockID != null && !this.movieTicketBlockID.equals(other.movieTicketBlockID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.MovieTicketBlocks[ movieTicketBlockID=" + movieTicketBlockID + " ]";
    }
    
}
