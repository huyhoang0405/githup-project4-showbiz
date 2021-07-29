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
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import java.util.ArrayList;
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
    final Calendar c = Calendar.getInstance();
    private Orders orders;

    private int CURRENT_MONTH;
    private int month = c.get(Calendar.MONTH) + 1;

    private int year = c.get(Calendar.YEAR);
    private int CURRENT_YEAR;
    private Object WEEKLY_REVENUE = null;

    public OrderMB() {
        orders = new Orders();
        CURRENT_MONTH = c.get(Calendar.MONTH) + 1;
        CURRENT_YEAR = c.get(Calendar.YEAR);
    }

    public List<Orders> showAllOrders() {
        List<Orders> list = ordersFacade.showAll();
        for (Orders o : list) {
            calEnd.setTime(o.getDateOfPurchase());
            calEnd.roll(Calendar.DATE, 1);
            Date endDate = calEnd.getTime();
            o.setDateOfPurchase(endDate);
        }
        return list;
    }

    public Object displayMONTHLY_REVENUE(int month) {
        WEEKLY_REVENUE = monthlyStatistics(CURRENT_YEAR, month);
        return WEEKLY_REVENUE;
    }

    public Object monthlyStatistics(int year, int month) {
        LocalDate initial = LocalDate.of(year, month,1);
        LocalDate start = initial.with(firstDayOfMonth());
        LocalDate end = initial.with(lastDayOfMonth());
        Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();

        return ordersFacade.statisticOrder(startDate, endDate);
        //return (Calendar.DAY_OF_MONTH)+""; dung DAY_OF_MONTH de hien thi danh sach
    }

    public List<Object> demo(int month) {
        List<Object> list = new ArrayList<>();

        for (int i = 0; i <= c.get(Calendar.MONTH); i++) {
            LocalDate initial = LocalDate.of(calStart.get(Calendar.YEAR), month, calStart.get(Calendar.DAY_OF_MONTH));
            LocalDate start;
            Date startDate;
//            if (i != c.get(Calendar.MONTH)) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    start = initial.withDayOfMonth(1);
                    startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
                } else if (j == 1) {
                    start = initial.withDayOfMonth(8);
                    startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
                } else if (j == 2) {
                    start = initial.withDayOfMonth(15);
                    startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
                } else {
                    start = initial.withDayOfMonth(23);
                    startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
                }
                calEnd.setTime(startDate);
                calEnd.roll(Calendar.DATE, 7);
                Date endDate = calEnd.getTime();

                list.add(ordersFacade.statisticOrder(startDate, endDate));
            }
//            }else{
//                for (int j = 0; j < c.get(Calendar.DAY_OF_WEEK_IN_MONTH); j++) {
//                    
//                }
//            }

        }

        return list;
    }

    public List<Object> demo1(int month) {
        List<Object> list = new ArrayList<>();

        LocalDate initial = LocalDate.of(calStart.get(Calendar.YEAR), month, calStart.get(Calendar.DAY_OF_MONTH));
        LocalDate start;
        Date startDate;
//            if (i != c.get(Calendar.MONTH)) {
        for (int j = 0; j < 4; j++) {
            if (j == 0) {
                start = initial.withDayOfMonth(1);
                startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else if (j == 1) {
                start = initial.withDayOfMonth(8);
                startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else if (j == 2) {
                start = initial.withDayOfMonth(15);
                startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else {
                start = initial.withDayOfMonth(23);
                startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
            }
            calEnd.setTime(startDate);
            calEnd.roll(Calendar.DATE, 6);
            Date endDate = calEnd.getTime();

            list.add(ordersFacade.statisticOrder(startDate, endDate));
        }
//            }else{
//                for (int j = 0; j < c.get(Calendar.DAY_OF_WEEK_IN_MONTH); j++) {
//                    
//                }
//            }

        return list;
    }

    public Object displayWEEKLY_REVENUE(int week) {
        WEEKLY_REVENUE = weeklyStatistics(CURRENT_YEAR, CURRENT_MONTH, week);
        return WEEKLY_REVENUE;
    }

    public void changeMonth() {
        CURRENT_MONTH = month;
    }

    public void changeYear() {
        CURRENT_YEAR = year;
    }

    public Object weeklyStatistics(int year, int month, int week) {

        LocalDate initial = LocalDate.of(year, month, calStart.get(Calendar.DAY_OF_MONTH));
        LocalDate start = null;
        Date startDate;
//            if (i != c.get(Calendar.MONTH)) {
        for (int j = 0; j < week; j++) {
            if (j == 0) {
                start = initial.withDayOfMonth(1);
            } else if (j == 1) {
                start = initial.withDayOfMonth(8);
            } else if (j == 2) {
                start = initial.withDayOfMonth(15);
            } else {
                start = initial.withDayOfMonth(23);
            }
        }
        startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        calEnd.setTime(startDate);
        calEnd.roll(Calendar.DATE, 7);
        Date endDate = calEnd.getTime();
        return ordersFacade.statisticOrder(startDate, endDate);
    }

    public List<Integer> WEEK_IN_MONTH() {
        List<Integer> listWeek = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            listWeek.add(i);
        }
        return listWeek;
    }

    public List<Integer> MONTH_IN_YEAR() {
        List<Integer> listMonth = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            listMonth.add(i);
        }
        return listMonth;
    }

    public String displayPageWeek() {
        month = c.get(Calendar.MONTH) + 1;
        year = c.get(Calendar.YEAR);
        CURRENT_MONTH = c.get(Calendar.MONTH) + 1;
        CURRENT_YEAR = c.get(Calendar.YEAR);
        return "week_revenue?faces-redirect=true";
    }

    public String displayPageMonth() {
        month = c.get(Calendar.MONTH) + 1;
        year = c.get(Calendar.YEAR);
        CURRENT_MONTH = c.get(Calendar.MONTH) + 1;
        CURRENT_YEAR = c.get(Calendar.YEAR);
        return "month_revenue?faces-redirect=true";
    }

    public List<Customers> displayCustomer() {
        return ordersFacade.findCustomersInOrder();
    }

    public Object findPriceByCustomer(Customers un) {
        return ordersFacade.totalOfCustomer(un);
    }

    public int maxYear() {
        return c.get(Calendar.YEAR) + 1;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public int getCURRENT_MONTH() {
        return CURRENT_MONTH;
    }

    public void setCURRENT_MONTH(int CURRENT_MONTH) {
        this.CURRENT_MONTH = CURRENT_MONTH;
    }

    public Object getWEEKLY_REVENUE() {
        return WEEKLY_REVENUE;
    }

    public void setWEEKLY_REVENUE(Object WEEKLY_REVENUE) {
        this.WEEKLY_REVENUE = WEEKLY_REVENUE;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCURRENT_YEAR() {
        return CURRENT_YEAR;
    }

    public void setCURRENT_YEAR(int CURRENT_YEAR) {
        this.CURRENT_YEAR = CURRENT_YEAR;
    }

}
