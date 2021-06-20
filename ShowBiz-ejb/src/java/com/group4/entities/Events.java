/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Events")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e")
    , @NamedQuery(name = "Events.findByEventID", query = "SELECT e FROM Events e WHERE e.eventID = :eventID")
    , @NamedQuery(name = "Events.findByTitle", query = "SELECT e FROM Events e WHERE e.title = :title")
    , @NamedQuery(name = "Events.findByContent", query = "SELECT e FROM Events e WHERE e.content = :content")
    , @NamedQuery(name = "Events.findByAddress", query = "SELECT e FROM Events e WHERE e.address = :address")
    , @NamedQuery(name = "Events.findByPoster", query = "SELECT e FROM Events e WHERE e.poster = :poster")
    , @NamedQuery(name = "Events.findByBanner", query = "SELECT e FROM Events e WHERE e.banner = :banner")})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Event_ID")
    private String eventID;
    @Size(max = 100)
    @Column(name = "Title")
    private String title;
    @Size(max = 2000)
    @Column(name = "Content")
    private String content;
    @Size(max = 200)
    @Column(name = "Address")
    private String address;
    @Size(max = 100)
    @Column(name = "Poster")
    private String poster;
    @Size(max = 100)
    @Column(name = "Banner")
    private String banner;

    public Events() {
    }

    public Events(String eventID) {
        this.eventID = eventID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventID != null ? eventID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.eventID == null && other.eventID != null) || (this.eventID != null && !this.eventID.equals(other.eventID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Events[ eventID=" + eventID + " ]";
    }
    
}
