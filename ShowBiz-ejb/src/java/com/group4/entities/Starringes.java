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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "Starringes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Starringes.findAll", query = "SELECT s FROM Starringes s")
    , @NamedQuery(name = "Starringes.findByStarringID", query = "SELECT s FROM Starringes s WHERE s.starringID = :starringID")
    , @NamedQuery(name = "Starringes.findByStarringName", query = "SELECT s FROM Starringes s WHERE s.starringName = :starringName")})
public class Starringes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Starring_ID")
    private Integer starringID;
    @Size(max = 100)
    @Column(name = "Starring_Name")
    private String starringName;
    @ManyToMany(mappedBy = "starringesCollection")
    private Collection<Dramas> dramasCollection;

    public Starringes() {
    }

    public Starringes(Integer starringID) {
        this.starringID = starringID;
    }

    public Integer getStarringID() {
        return starringID;
    }

    public void setStarringID(Integer starringID) {
        this.starringID = starringID;
    }

    public String getStarringName() {
        return starringName;
    }

    public void setStarringName(String starringName) {
        this.starringName = starringName;
    }

    @XmlTransient
    public Collection<Dramas> getDramasCollection() {
        return dramasCollection;
    }

    public void setDramasCollection(Collection<Dramas> dramasCollection) {
        this.dramasCollection = dramasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (starringID != null ? starringID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Starringes)) {
            return false;
        }
        Starringes other = (Starringes) object;
        if ((this.starringID == null && other.starringID != null) || (this.starringID != null && !this.starringID.equals(other.starringID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Starringes[ starringID=" + starringID + " ]";
    }
    
}
