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
public class OrderMusicSportDetailsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Order_ID")
    private String orderID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MusicSportTicketBlock_ID")
    private String musicSportTicketBlockID;

    public OrderMusicSportDetailsPK() {
    }

    public OrderMusicSportDetailsPK(String orderID, String musicSportTicketBlockID) {
        this.orderID = orderID;
        this.musicSportTicketBlockID = musicSportTicketBlockID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getMusicSportTicketBlockID() {
        return musicSportTicketBlockID;
    }

    public void setMusicSportTicketBlockID(String musicSportTicketBlockID) {
        this.musicSportTicketBlockID = musicSportTicketBlockID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        hash += (musicSportTicketBlockID != null ? musicSportTicketBlockID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderMusicSportDetailsPK)) {
            return false;
        }
        OrderMusicSportDetailsPK other = (OrderMusicSportDetailsPK) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        if ((this.musicSportTicketBlockID == null && other.musicSportTicketBlockID != null) || (this.musicSportTicketBlockID != null && !this.musicSportTicketBlockID.equals(other.musicSportTicketBlockID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.OrderMusicSportDetailsPK[ orderID=" + orderID + ", musicSportTicketBlockID=" + musicSportTicketBlockID + " ]";
    }
    
}
