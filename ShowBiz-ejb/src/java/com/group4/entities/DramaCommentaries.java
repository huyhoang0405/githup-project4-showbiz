/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "DramaCommentaries")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DramaCommentaries.findAll", query = "SELECT d FROM DramaCommentaries d")
    , @NamedQuery(name = "DramaCommentaries.findByDramaCommentaryID", query = "SELECT d FROM DramaCommentaries d WHERE d.dramaCommentaryID = :dramaCommentaryID")
    , @NamedQuery(name = "DramaCommentaries.findByContent", query = "SELECT d FROM DramaCommentaries d WHERE d.content = :content")
    , @NamedQuery(name = "DramaCommentaries.findByDate", query = "SELECT d FROM DramaCommentaries d WHERE d.date = :date")})
public class DramaCommentaries implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DramaCommentary_ID")
    private Integer dramaCommentaryID;
    @Size(max = 2000)
    @Column(name = "Content")
    private String content;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "CustomerUsername", referencedColumnName = "CustomerUsername")
    @ManyToOne
    private Customer customerUsername;
    @JoinColumn(name = "Drama_ID", referencedColumnName = "Drama_ID")
    @ManyToOne
    private Dramas dramaID;

    public DramaCommentaries() {
    }

    public DramaCommentaries(Integer dramaCommentaryID) {
        this.dramaCommentaryID = dramaCommentaryID;
    }

    public Integer getDramaCommentaryID() {
        return dramaCommentaryID;
    }

    public void setDramaCommentaryID(Integer dramaCommentaryID) {
        this.dramaCommentaryID = dramaCommentaryID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(Customer customerUsername) {
        this.customerUsername = customerUsername;
    }

    public Dramas getDramaID() {
        return dramaID;
    }

    public void setDramaID(Dramas dramaID) {
        this.dramaID = dramaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dramaCommentaryID != null ? dramaCommentaryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DramaCommentaries)) {
            return false;
        }
        DramaCommentaries other = (DramaCommentaries) object;
        if ((this.dramaCommentaryID == null && other.dramaCommentaryID != null) || (this.dramaCommentaryID != null && !this.dramaCommentaryID.equals(other.dramaCommentaryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.DramaCommentaries[ dramaCommentaryID=" + dramaCommentaryID + " ]";
    }
    
}
