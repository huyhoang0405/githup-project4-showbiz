/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.TicketTypes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface TicketTypesFacadeLocal {

    void create(TicketTypes ticketTypes);

    void edit(TicketTypes ticketTypes);

    void remove(TicketTypes ticketTypes);

    TicketTypes find(Object id);

    List<TicketTypes> findAll();

    List<TicketTypes> findRange(int[] range);

    int count();
    
    TicketTypes findByID(Integer id);
}
