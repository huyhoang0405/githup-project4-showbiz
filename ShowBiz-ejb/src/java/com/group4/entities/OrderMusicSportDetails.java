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
@Table(name = "OrderMusicSportDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderMusicSportDetails.findAll", query = "SELECT o FROM OrderMusicSportDetails o")
    , @NamedQuery(name = "OrderMusicSportDetails.findByOrderID", query = "SELECT o FROM OrderMusicSportDetails o WHERE o.orderMusicSportDetailsPK.orderID = :orderID")
    , @NamedQuery(name = "OrderMusicSportDetails.findByMusicSportTicketBlockID", query = "SELECT o FROM OrderMusicSportDetails o WHERE o.orderMusicSportDetailsPK.musicSportTicketBlockID = :musicSportTicketBlockID")
    , @NamedQuery(name = "OrderMusicSportDetails.findByQuantity", query = "SELECT o FROM OrderMusicSportDetails o WHERE o.quantity = :quantity")})
public class OrderMusicSportDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderMusicSportDetailsPK orderMusicSportDetailsPK;
    @Column(name = "Quantity")
    private Integer quantity;
    @JoinColumn(name = "MusicSportTicketBlock_ID", referencedColumnName = "MusicSportTicketBlock_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MusicSportTicketBlocks musicSportTicketBlocks;
    @JoinColumn(name = "Order_ID", referencedColumnName = "Order_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;

    public OrderMusicSportDetails() {
    }

    public OrderMusicSportDetails(OrderMusicSportDetailsPK orderMusicSportDetailsPK) {
        this.orderMusicSportDetailsPK = orderMusicSportDetailsPK;
    }

    public OrderMusicSportDetails(String orderID, String musicSportTicketBlockID) {
        this.orderMusicSportDetailsPK = new OrderMusicSportDetailsPK(orderID, musicSportTicketBlockID);
    }

    public OrderMusicSportDetailsPK getOrderMusicSportDetailsPK() {
        return orderMusicSportDetailsPK;
    }

    public void setOrderMusicSportDetailsPK(OrderMusicSportDetailsPK orderMusicSportDetailsPK) {
        this.orderMusicSportDetailsPK = orderMusicSportDetailsPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MusicSportTicketBlocks getMusicSportTicketBlocks() {
        return musicSportTicketBlocks;
    }

    public void setMusicSportTicketBlocks(MusicSportTicketBlocks musicSportTicketBlocks) {
        this.musicSportTicketBlocks = musicSportTicketBlocks;
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
        hash += (orderMusicSportDetailsPK != null ? orderMusicSportDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderMusicSportDetails)) {
            return false;
        }
        OrderMusicSportDetails other = (OrderMusicSportDetails) object;
        if ((this.orderMusicSportDetailsPK == null && other.orderMusicSportDetailsPK != null) || (this.orderMusicSportDetailsPK != null && !this.orderMusicSportDetailsPK.equals(other.orderMusicSportDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.OrderMusicSportDetails[ orderMusicSportDetailsPK=" + orderMusicSportDetailsPK + " ]";
    }
    
}
