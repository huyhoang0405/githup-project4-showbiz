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
@Table(name = "Administrators")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrators.findAll", query = "SELECT a FROM Administrators a")
    , @NamedQuery(name = "Administrators.findByAdminUsername", query = "SELECT a FROM Administrators a WHERE a.adminUsername = :adminUsername")
    , @NamedQuery(name = "Administrators.findByFirstName", query = "SELECT a FROM Administrators a WHERE a.firstName = :firstName")
    , @NamedQuery(name = "Administrators.findByLastName", query = "SELECT a FROM Administrators a WHERE a.lastName = :lastName")
    , @NamedQuery(name = "Administrators.findByPassword", query = "SELECT a FROM Administrators a WHERE a.password = :password")
    , @NamedQuery(name = "Administrators.findByDateOfBirth", query = "SELECT a FROM Administrators a WHERE a.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "Administrators.findByGender", query = "SELECT a FROM Administrators a WHERE a.gender = :gender")
    , @NamedQuery(name = "Administrators.findByPhone", query = "SELECT a FROM Administrators a WHERE a.phone = :phone")
    , @NamedQuery(name = "Administrators.findByEmail", query = "SELECT a FROM Administrators a WHERE a.email = :email")
    , @NamedQuery(name = "Administrators.findByAddress", query = "SELECT a FROM Administrators a WHERE a.address = :address")})
public class Administrators implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AdminUsername")
    private String adminUsername;
    @Size(max = 100)
    @Column(name = "FirstName")
    private String firstName;
    @Size(max = 100)
    @Column(name = "LastName")
    private String lastName;
    @Size(max = 100)
    @Column(name = "Password")
    private String password;
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "Gender")
    private Boolean gender;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "Phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "Email")
    private String email;
    @Size(max = 200)
    @Column(name = "Address")
    private String address;

    public Administrators() {
    }

    public Administrators(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminUsername != null ? adminUsername.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrators)) {
            return false;
        }
        Administrators other = (Administrators) object;
        if ((this.adminUsername == null && other.adminUsername != null) || (this.adminUsername != null && !this.adminUsername.equals(other.adminUsername))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group4.entities.Administrators[ adminUsername=" + adminUsername + " ]";
    }
    
}
