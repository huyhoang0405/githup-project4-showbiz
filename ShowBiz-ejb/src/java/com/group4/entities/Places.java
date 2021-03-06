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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "Places")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Places.findAll", query = "SELECT p FROM Places p")
    , @NamedQuery(name = "Places.findByPlacesID", query = "SELECT p FROM Places p WHERE p.placesID = :placesID")
    , @NamedQuery(name = "Places.findByCity", query = "SELECT p FROM Places p WHERE p.city = :city")})
public class Places implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
   // @NotNull
    @Column(name = "Places_ID")
    private Integer placesID;
    @Size(min = 3 ,max = 100,message = "The city must be between 3 and 100 characters.")
    @Column(name = "City")
    private String city;
    @OneToMany(mappedBy = "placesID")
    private Collection<Cinemas> cinemasCollection;
    @JoinColumn(name = "Area_ID", referencedColumnName = "Area_ID")
    @ManyToOne
    private Areas areaID;

    public Places() {
    }

//    public Places(Integer placesID) {
//        this.placesID = placesID;
//    }

    public Integer getPlacesID() {
        return placesID;
    }

//    public void setPlacesID(Integer placesID) {
//        this.placesID = placesID;
//    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlTransient
    public Collection<Cinemas> getCinemasCollection() {
        return cinemasCollection;
    }

    public void setCinemasCollection(Collection<Cinemas> cinemasCollection) {
        this.cinemasCollection = cinemasCollection;
    }

    public Areas getAreaID() {
        return areaID;
    }

    public void setAreaID(Areas areaID) {
        this.areaID = areaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placesID != null ? placesID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Places)) {
            return false;
        }
        Places other = (Places) object;
        if ((this.placesID == null && other.placesID != null) || (this.placesID != null && !this.placesID.equals(other.placesID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Places[ placesID=" + placesID + " ]";
    }
    
}
