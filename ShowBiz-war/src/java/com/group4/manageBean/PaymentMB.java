/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Payments;
import com.group4.sesionBeans.PaymentsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Admin
 */
@Named(value = "paymentMB")
@SessionScoped
public class PaymentMB implements Serializable {

    @EJB
    private PaymentsFacadeLocal paymentsFacade;
    private Payments payment;
    private String notice;

    public PaymentMB() {
        payment = new Payments();
    }

    //display all payments
    public List<Payments> showAllPayments(){
        return paymentsFacade.findAll();
    }
    
    //details of an area
    public String showDetailsPayment(int id) {
        Payments p = paymentsFacade.find(id);
        setPayment(p);
        return "details?faces-redirect=true";
    }
    
    //reset form
    public void resetForm() {
        payment.setPaymentName(null);
    }

    //display form crate a new payment
    public String loadFormCreateNew() {
        resetForm();
        return "create?faces-redirect=true";
    }
    
     //create a new payment
    public String createNewPayment() {
        try {
            Payments p = new Payments();
            p.setPaymentName(payment.getPaymentName());
            paymentsFacade.create(p);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }
    
    //load form edit 
    public String loadFormEdit(int id) {
        Payments p = paymentsFacade.find(id);
        setPayment(p);
        return "edit?faces-redirect=true";
    }

    //edit an area
    public String editPayment(int id) {
        try {
            Payments p = paymentsFacade.find(id);
            p.setPaymentName(payment.getPaymentName());
            paymentsFacade.edit(p);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }
    
    //delete an area
    public String deletePayment(int id) {
        try {
            Payments p = paymentsFacade.find(id);
            paymentsFacade.remove(p);
        } catch (Exception e) {
            notice = "alert('You can't delete it. An error has occurred!');";
        }
        return "index?faces-redirect=true";
    }
    
    public Payments getPayment() {
        return payment;
    }

    public void setPayment(Payments payment) {
        this.payment = payment;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}
