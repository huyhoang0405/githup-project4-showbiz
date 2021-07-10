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
public class OrderMovieDetailsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Order_ID")
    private String orderID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MovieTicketBlock_ID")
    private String movieTicketBlockID;

    public OrderMovieDetailsPK() {
    }

    public OrderMovieDetailsPK(String orderID, String movieTicketBlockID) {
        this.orderID = orderID;
        this.movieTicketBlockID = movieTicketBlockID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getMovieTicketBlockID() {
        return movieTicketBlockID;
    }

    public void setMovieTicketBlockID(String movieTicketBlockID) {
        this.movieTicketBlockID = movieTicketBlockID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        hash += (movieTicketBlockID != null ? movieTicketBlockID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderMovieDetailsPK)) {
            return false;
        }
        OrderMovieDetailsPK other = (OrderMovieDetailsPK) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        if ((this.movieTicketBlockID == null && other.movieTicketBlockID != null) || (this.movieTicketBlockID != null && !this.movieTicketBlockID.equals(other.movieTicketBlockID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.OrderMovieDetailsPK[ orderID=" + orderID + ", movieTicketBlockID=" + movieTicketBlockID + " ]";
    }
    
}
