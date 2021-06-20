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
import javax.persistence.Id;
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
@Table(name = "TicketTypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketTypes.findAll", query = "SELECT t FROM TicketTypes t")
    , @NamedQuery(name = "TicketTypes.findByTicketTypeID", query = "SELECT t FROM TicketTypes t WHERE t.ticketTypeID = :ticketTypeID")
    , @NamedQuery(name = "TicketTypes.findByTicketTypeName", query = "SELECT t FROM TicketTypes t WHERE t.ticketTypeName = :ticketTypeName")})
public class TicketTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TicketType_ID")
    private Integer ticketTypeID;
    @Size(max = 100)
    @Column(name = "TicketType_Name")
    private String ticketTypeName;
    @OneToMany(mappedBy = "ticketTypeID")
    private Collection<MovieTicketBlocks> movieTicketBlocksCollection;
    @OneToMany(mappedBy = "ticketTypeID")
    private Collection<MusicSportTicketBlocks> musicSportTicketBlocksCollection;

    public TicketTypes() {
    }

    public TicketTypes(Integer ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    public Integer getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(Integer ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    @XmlTransient
    public Collection<MovieTicketBlocks> getMovieTicketBlocksCollection() {
        return movieTicketBlocksCollection;
    }

    public void setMovieTicketBlocksCollection(Collection<MovieTicketBlocks> movieTicketBlocksCollection) {
        this.movieTicketBlocksCollection = movieTicketBlocksCollection;
    }

    @XmlTransient
    public Collection<MusicSportTicketBlocks> getMusicSportTicketBlocksCollection() {
        return musicSportTicketBlocksCollection;
    }

    public void setMusicSportTicketBlocksCollection(Collection<MusicSportTicketBlocks> musicSportTicketBlocksCollection) {
        this.musicSportTicketBlocksCollection = musicSportTicketBlocksCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketTypeID != null ? ticketTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketTypes)) {
            return false;
        }
        TicketTypes other = (TicketTypes) object;
        if ((this.ticketTypeID == null && other.ticketTypeID != null) || (this.ticketTypeID != null && !this.ticketTypeID.equals(other.ticketTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.TicketTypes[ ticketTypeID=" + ticketTypeID + " ]";
    }
    
}
