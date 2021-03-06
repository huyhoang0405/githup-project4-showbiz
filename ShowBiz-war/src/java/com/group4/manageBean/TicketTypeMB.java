/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.TicketTypes;
import com.group4.sesionBeans.TicketTypesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Admin
 */
@Named(value = "ticketTypeMB")
@SessionScoped
public class TicketTypeMB implements Serializable {

     @EJB
    private TicketTypesFacadeLocal ticketTypesFacade;

    private TicketTypes ticketTypes;
    private String notice;
    
    public TicketTypeMB() {
        ticketTypes = new TicketTypes();
    }

    //display all payments
    public List<TicketTypes> showAllTicketTypes(){
        return ticketTypesFacade.findAll();
    }
    
    //details of an area
    public String showDetailsTicketType(int id) {
        TicketTypes p = ticketTypesFacade.find(id);
        setTicketTypes(p);
        return "details?faces-redirect=true";
    }
    
    //reset form
    public void resetForm() {
        ticketTypes.setTicketTypeName(null);
    }

    //display form crate a new payment
    public String loadFormCreateNew() {
        resetForm();
        return "create?faces-redirect=true";
    }
    
     //create a new payment
    public String createNewTicketType() {
        try {
            TicketTypes t = new TicketTypes();
            t.setTicketTypeName(ticketTypes.getTicketTypeName());
            ticketTypesFacade.create(t);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }
    
    //load form edit 
    public String loadFormEdit(int id) {
        TicketTypes t = ticketTypesFacade.find(id);
        setTicketTypes(t);
        return "edit?faces-redirect=true";
    }

    //edit an area
    public String editTicketType(int id) {
        try {
            TicketTypes t = ticketTypesFacade.find(id);
            t.setTicketTypeName(ticketTypes.getTicketTypeName());
            ticketTypesFacade.edit(t);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }
    
    //delete a ticket type
    public String deleteTicketType(int id) {
        try {
            TicketTypes t = ticketTypesFacade.find(id);
            ticketTypesFacade.remove(t);
        } catch (Exception e) {
            notice = "alert('You can't delete it. An error has occurred!');";
        }
        return "index?faces-redirect=true";
    }
    
    public TicketTypes getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(TicketTypes ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
    
}
