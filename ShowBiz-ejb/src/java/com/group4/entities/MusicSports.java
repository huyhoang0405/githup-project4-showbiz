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
@Table(name = "MusicSports")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MusicSports.findAll", query = "SELECT m FROM MusicSports m")
    , @NamedQuery(name = "MusicSports.findByMusicSportID", query = "SELECT m FROM MusicSports m WHERE m.musicSportID = :musicSportID")
    , @NamedQuery(name = "MusicSports.findByMusicSportName", query = "SELECT m FROM MusicSports m WHERE m.musicSportName = :musicSportName")
    , @NamedQuery(name = "MusicSports.findByStartDate", query = "SELECT m FROM MusicSports m WHERE m.startDate = :startDate")
    , @NamedQuery(name = "MusicSports.findByStartTime", query = "SELECT m FROM MusicSports m WHERE m.startTime = :startTime")
    , @NamedQuery(name = "MusicSports.findByAddress", query = "SELECT m FROM MusicSports m WHERE m.address = :address")
    , @NamedQuery(name = "MusicSports.findByCapacity", query = "SELECT m FROM MusicSports m WHERE m.capacity = :capacity")
    , @NamedQuery(name = "MusicSports.findByInformation", query = "SELECT m FROM MusicSports m WHERE m.information = :information")
    , @NamedQuery(name = "MusicSports.findByType", query = "SELECT m FROM MusicSports m WHERE m.type = :type")
    , @NamedQuery(name = "MusicSports.findByPoster", query = "SELECT m FROM MusicSports m WHERE m.poster = :poster")
    , @NamedQuery(name = "MusicSports.findByBanner", query = "SELECT m FROM MusicSports m WHERE m.banner = :banner")})
public class MusicSports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MusicSport_ID")
    private String musicSportID;
    @Size(max = 100)
    @Column(name = "MusicSport_Name")
    private String musicSportName;
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "StartTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Size(max = 200)
    @Column(name = "Address")
    private String address;
    @Column(name = "Capacity")
    private Integer capacity;
    @Size(max = 2000)
    @Column(name = "Information")
    private String information;
    @Column(name = "Type")
    private Boolean type;
    @Size(max = 100)
    @Column(name = "Poster")
    private String poster;
    @Size(max = 100)
    @Column(name = "Banner")
    private String banner;
    @OneToMany(mappedBy = "musicSportID")
    private Collection<MusicSportTicketBlocks> musicSportTicketBlocksCollection;

    public MusicSports() {
    }

    public MusicSports(String musicSportID) {
        this.musicSportID = musicSportID;
    }

    public String getMusicSportID() {
        return musicSportID;
    }

    public void setMusicSportID(String musicSportID) {
        this.musicSportID = musicSportID;
    }

    public String getMusicSportName() {
        return musicSportName;
    }

    public void setMusicSportName(String musicSportName) {
        this.musicSportName = musicSportName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
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
        hash += (musicSportID != null ? musicSportID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MusicSports)) {
            return false;
        }
        MusicSports other = (MusicSports) object;
        if ((this.musicSportID == null && other.musicSportID != null) || (this.musicSportID != null && !this.musicSportID.equals(other.musicSportID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.MusicSports[ musicSportID=" + musicSportID + " ]";
    }
    
}
