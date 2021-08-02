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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "MusicSportTicketBlocks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MusicSportTicketBlocks.findAll", query = "SELECT m FROM MusicSportTicketBlocks m")
    , @NamedQuery(name = "MusicSportTicketBlocks.findByMusicSportTicketBlockID", query = "SELECT m FROM MusicSportTicketBlocks m WHERE m.musicSportTicketBlockID = :musicSportTicketBlockID")
    , @NamedQuery(name = "MusicSportTicketBlocks.findByQuantity", query = "SELECT m FROM MusicSportTicketBlocks m WHERE m.quantity = :quantity")
    , @NamedQuery(name = "MusicSportTicketBlocks.findByResidual", query = "SELECT m FROM MusicSportTicketBlocks m WHERE m.residual = :residual")
    , @NamedQuery(name = "MusicSportTicketBlocks.findByUnitPrice", query = "SELECT m FROM MusicSportTicketBlocks m WHERE m.unitPrice = :unitPrice")})
public class MusicSportTicketBlocks implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "musicSportTicketBlocks")
    private Collection<OrderMusicSportDetails> orderMusicSportDetailsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MusicSportTicketBlock_ID")
    private String musicSportTicketBlockID;
    @NotNull(message = "Quantity can't be left empty!")
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Residual")
    private Integer residual;
    @NotNull(message = "Unit price can't be left empty!")
    @Column(name = "UnitPrice")
    private Long unitPrice;
    @JoinTable(name = "OrderMusicSportDetails", joinColumns = {
        @JoinColumn(name = "MusicSportTicketBlock_ID", referencedColumnName = "MusicSportTicketBlock_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "Order_ID", referencedColumnName = "Order_ID")})
    @ManyToMany
    private Collection<Orders> ordersCollection;
    @JoinColumn(name = "MusicSport_ID", referencedColumnName = "MusicSport_ID")
    @ManyToOne
    private MusicSports musicSportID;
    @JoinColumn(name = "TicketType_ID", referencedColumnName = "TicketType_ID")
    @ManyToOne
    private TicketTypes ticketTypeID;

    public MusicSportTicketBlocks() {
    }

    public MusicSportTicketBlocks(String musicSportTicketBlockID) {
        this.musicSportTicketBlockID = musicSportTicketBlockID;
    }

    public String getMusicSportTicketBlockID() {
        return musicSportTicketBlockID;
    }

    public void setMusicSportTicketBlockID(String musicSportTicketBlockID) {
        this.musicSportTicketBlockID = musicSportTicketBlockID;
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

    public MusicSports getMusicSportID() {
        return musicSportID;
    }

    public void setMusicSportID(MusicSports musicSportID) {
        this.musicSportID = musicSportID;
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
        hash += (musicSportTicketBlockID != null ? musicSportTicketBlockID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MusicSportTicketBlocks)) {
            return false;
        }
        MusicSportTicketBlocks other = (MusicSportTicketBlocks) object;
        if ((this.musicSportTicketBlockID == null && other.musicSportTicketBlockID != null) || (this.musicSportTicketBlockID != null && !this.musicSportTicketBlockID.equals(other.musicSportTicketBlockID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.MusicSportTicketBlocks[ musicSportTicketBlockID=" + musicSportTicketBlockID + " ]";
    }

    @XmlTransient
    public Collection<OrderMusicSportDetails> getOrderMusicSportDetailsCollection() {
        return orderMusicSportDetailsCollection;
    }

    public void setOrderMusicSportDetailsCollection(Collection<OrderMusicSportDetails> orderMusicSportDetailsCollection) {
        this.orderMusicSportDetailsCollection = orderMusicSportDetailsCollection;
    }
    
}
