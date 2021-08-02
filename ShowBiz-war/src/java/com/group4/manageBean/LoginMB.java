/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Administrators;
import com.group4.entities.Customers;
import com.group4.sesionBeans.AdministratorsFacadeLocal;
import com.group4.sesionBeans.CustomersFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@Named(value = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {
    
    @EJB
    private CustomersFacadeLocal customersFacade;
    
    @EJB
    private AdministratorsFacadeLocal administratorsFacade;
    
    private String username;
    private String password;
    private boolean isLogin;
    private boolean isAdmin;
    private Administrators admin;
    private Customers customer;
    private String notice;
    final Calendar c = Calendar.getInstance();
    private String year = c.get(Calendar.YEAR) +"";
    
    public LoginMB() {
        admin = new Administrators();
        customer = new Customers();
    }
    
    public String login() {
        if (customersFacade.login(username, password)) {
            setIsLogin(true);
            setIsAdmin(false);
            Customers c = customersFacade.find(username);
            setCustomer(c);
            notice = "alert ('Welcome "+c.getFirstName()+" "+ c.getLastName()+" has logged into the website!');";
            return "/client/home/index?faces-redirect=true";
        } else if (administratorsFacade.login(username, password)) {
            setIsLogin(true);
            setIsAdmin(true);
            Administrators a = administratorsFacade.find(username);
            setAdmin(a);
            return "/admin/orders/index?faces-redirect=true";
        } else {
             notice = "alert ('Username or password is incorrect!');";
            return "index?faces-redirect=true";
        }
    }
    
    public String logout() {
        setUsername(null);
        setPassword(null);
        setCustomer(null);
        setAdmin(null);
        setIsAdmin(false);
        setIsLogin(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/client/home/index?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isIsLogin() {
        return isLogin;
    }
    
    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
    
    public boolean isIsAdmin() {
        return isAdmin;
    }
    
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public Administrators getAdmin() {
        return admin;
    }
    
    public void setAdmin(Administrators admin) {
        this.admin = admin;
    }
    
    public Customers getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
    
    public String getNotice() {
        return notice;
    }
    
    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
}
