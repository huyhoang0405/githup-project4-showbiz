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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "OrderMovieDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderMovieDetails.findAll", query = "SELECT o FROM OrderMovieDetails o")
    , @NamedQuery(name = "OrderMovieDetails.findByOrderID", query = "SELECT o FROM OrderMovieDetails o WHERE o.orderMovieDetailsPK.orderID = :orderID")
    , @NamedQuery(name = "OrderMovieDetails.findByMovieTicketBlockID", query = "SELECT o FROM OrderMovieDetails o WHERE o.orderMovieDetailsPK.movieTicketBlockID = :movieTicketBlockID")
    , @NamedQuery(name = "OrderMovieDetails.findByQuantity", query = "SELECT o FROM OrderMovieDetails o WHERE o.quantity = :quantity")})
public class OrderMovieDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderMovieDetailsPK orderMovieDetailsPK;
    @Column(name = "Quantity")
    private Integer quantity;
    @JoinColumn(name = "MovieTicketBlock_ID", referencedColumnName = "MovieTicketBlock_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MovieTicketBlocks movieTicketBlocks;
    @JoinColumn(name = "Order_ID", referencedColumnName = "Order_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;

    public OrderMovieDetails() {
    }

    public OrderMovieDetails(OrderMovieDetailsPK orderMovieDetailsPK) {
        this.orderMovieDetailsPK = orderMovieDetailsPK;
    }

    public OrderMovieDetails(String orderID, String movieTicketBlockID) {
        this.orderMovieDetailsPK = new OrderMovieDetailsPK(orderID, movieTicketBlockID);
    }

    public OrderMovieDetailsPK getOrderMovieDetailsPK() {
        return orderMovieDetailsPK;
    }

    public void setOrderMovieDetailsPK(OrderMovieDetailsPK orderMovieDetailsPK) {
        this.orderMovieDetailsPK = orderMovieDetailsPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MovieTicketBlocks getMovieTicketBlocks() {
        return movieTicketBlocks;
    }

    public void setMovieTicketBlocks(MovieTicketBlocks movieTicketBlocks) {
        this.movieTicketBlocks = movieTicketBlocks;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderMovieDetailsPK != null ? orderMovieDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderMovieDetails)) {
            return false;
        }
        OrderMovieDetails other = (OrderMovieDetails) object;
        if ((this.orderMovieDetailsPK == null && other.orderMovieDetailsPK != null) || (this.orderMovieDetailsPK != null && !this.orderMovieDetailsPK.equals(other.orderMovieDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.OrderMovieDetails[ orderMovieDetailsPK=" + orderMovieDetailsPK + " ]";
    }
    
}
