/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Customers;
import com.group4.entities.Orders;
import com.group4.sesionBeans.OrderMovieDetailsFacadeLocal;
import com.group4.sesionBeans.OrderMusicSportDetailsFacadeLocal;
import com.group4.sesionBeans.OrdersFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Admin
 */
@Named(value = "orderMB")
@SessionScoped
public class OrderMB implements Serializable {

    @EJB
    private OrderMusicSportDetailsFacadeLocal orderMusicSportDetailsFacade;

    @EJB
    private OrderMovieDetailsFacadeLocal orderMovieDetailsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    final Calendar calStart = Calendar.getInstance();
    final Calendar calEnd = Calendar.getInstance();
    private Orders orders;

    public OrderMB() {
        orders = new Orders();
    }
    
    public Object weeklyStatistics(){
        LocalDate initial = LocalDate.of(calStart.get(Calendar.YEAR),calStart.get(Calendar.MONTH) +1,calStart.get(Calendar.DAY_OF_MONTH));
        LocalDate start = initial.withDayOfMonth(1);
        Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        calEnd.setTime(startDate);
        calEnd.roll(Calendar.DATE, 30);
        Date endDate = calEnd.getTime();
        Calendar c = Calendar.getInstance();
        
        return  ordersFacade.weeklyStatistics(startDate, endDate);
      //return (Calendar.DAY_OF_MONTH)+""; dung DAY_OF_MONTH de hien thi danh sach
    }
    
    
    public List<Customers> displayCustomer(){
        return ordersFacade.findCustomersInOrder();
    }
    
    public Object findPriceByCustomer(Customers un){
        return ordersFacade.totalOfCustomer(un);
    }
    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
    
}
