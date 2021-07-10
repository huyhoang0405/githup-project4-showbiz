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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "Orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findByOrderID", query = "SELECT o FROM Orders o WHERE o.orderID = :orderID")
    , @NamedQuery(name = "Orders.findByDateOfPurchase", query = "SELECT o FROM Orders o WHERE o.dateOfPurchase = :dateOfPurchase")
    , @NamedQuery(name = "Orders.findByTotalPrice", query = "SELECT o FROM Orders o WHERE o.totalPrice = :totalPrice")})
public class Orders implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Collection<OrderMusicSportDetails> orderMusicSportDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Collection<OrderMovieDetails> orderMovieDetailsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Order_ID")
    private String orderID;
    @Column(name = "DateOfPurchase")
    @Temporal(TemporalType.DATE)
    private Date dateOfPurchase;
    @Column(name = "TotalPrice")
    private Long totalPrice;
    @ManyToMany(mappedBy = "ordersCollection")
    private Collection<MusicSportTicketBlocks> musicSportTicketBlocksCollection;
    @ManyToMany(mappedBy = "ordersCollection")
    private Collection<MovieTicketBlocks> movieTicketBlocksCollection;
    @JoinColumn(name = "CustomerUsername", referencedColumnName = "CustomerUsername")
    @ManyToOne
    private Customers customerUsername;
    @JoinColumn(name = "Payment_ID", referencedColumnName = "Payment_ID")
    @ManyToOne
    private Payments paymentID;

    public Orders() {
    }

    public Orders(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @XmlTransient
    public Collection<MusicSportTicketBlocks> getMusicSportTicketBlocksCollection() {
        return musicSportTicketBlocksCollection;
    }

    public void setMusicSportTicketBlocksCollection(Collection<MusicSportTicketBlocks> musicSportTicketBlocksCollection) {
        this.musicSportTicketBlocksCollection = musicSportTicketBlocksCollection;
    }

    @XmlTransient
    public Collection<MovieTicketBlocks> getMovieTicketBlocksCollection() {
        return movieTicketBlocksCollection;
    }

    public void setMovieTicketBlocksCollection(Collection<MovieTicketBlocks> movieTicketBlocksCollection) {
        this.movieTicketBlocksCollection = movieTicketBlocksCollection;
    }

    public Customers getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(Customers customerUsername) {
        this.customerUsername = customerUsername;
    }

    public Payments getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Payments paymentID) {
        this.paymentID = paymentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Orders[ orderID=" + orderID + " ]";
    }

    @XmlTransient
    public Collection<OrderMusicSportDetails> getOrderMusicSportDetailsCollection() {
        return orderMusicSportDetailsCollection;
    }

    public void setOrderMusicSportDetailsCollection(Collection<OrderMusicSportDetails> orderMusicSportDetailsCollection) {
        this.orderMusicSportDetailsCollection = orderMusicSportDetailsCollection;
    }

    @XmlTransient
    public Collection<OrderMovieDetails> getOrderMovieDetailsCollection() {
        return orderMovieDetailsCollection;
    }

    public void setOrderMovieDetailsCollection(Collection<OrderMovieDetails> orderMovieDetailsCollection) {
        this.orderMovieDetailsCollection = orderMovieDetailsCollection;
    }
    
}
