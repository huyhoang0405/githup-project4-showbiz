/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Customers;
import com.group4.entities.Orders;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface OrdersFacadeLocal {

    void create(Orders orders);

    void edit(Orders orders);

    void remove(Orders orders);

    Orders find(Object id);

    List<Orders> findAll();

    List<Orders> findRange(int[] range);

    int count();
    
    String getLastID();
    
    Object totalOfCustomer(Customers un);
    
    List<Customers> findCustomersInOrder();
    
    Object statisticOrder(Date startdate, Date enddate);
    
    List<Orders> showAll();
}
