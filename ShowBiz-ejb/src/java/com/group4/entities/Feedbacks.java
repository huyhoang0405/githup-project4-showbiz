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
@Table(name = "Feedbacks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedbacks.findAll", query = "SELECT f FROM Feedbacks f")
    , @NamedQuery(name = "Feedbacks.findByFeedbackID", query = "SELECT f FROM Feedbacks f WHERE f.feedbackID = :feedbackID")
    , @NamedQuery(name = "Feedbacks.findByTitle", query = "SELECT f FROM Feedbacks f WHERE f.title = :title")
    , @NamedQuery(name = "Feedbacks.findByContent", query = "SELECT f FROM Feedbacks f WHERE f.content = :content")
    , @NamedQuery(name = "Feedbacks.findByEmail", query = "SELECT f FROM Feedbacks f WHERE f.email = :email")
    , @NamedQuery(name = "Feedbacks.findByFirstName", query = "SELECT f FROM Feedbacks f WHERE f.firstName = :firstName")
    , @NamedQuery(name = "Feedbacks.findByLastName", query = "SELECT f FROM Feedbacks f WHERE f.lastName = :lastName")})
public class Feedbacks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Feedback_ID")
    private Integer feedbackID;
    @Size(max = 100)
    @Column(name = "Title")
    private String title;
    @Size(max = 2000)
    @Column(name = "Content")
    private String content;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "Email")
    private String email;
    @Size(max = 100)
    @Column(name = "FirstName")
    private String firstName;
    @Size(max = 100)
    @Column(name = "LastName")
    private String lastName;

    public Feedbacks() {
    }

    public Feedbacks(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Integer getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Integer feedbackID) {
        this.feedbackID = feedbackID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackID != null ? feedbackID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedbacks)) {
            return false;
        }
        Feedbacks other = (Feedbacks) object;
        if ((this.feedbackID == null && other.feedbackID != null) || (this.feedbackID != null && !this.feedbackID.equals(other.feedbackID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Feedbacks[ feedbackID=" + feedbackID + " ]";
    }
    
}
