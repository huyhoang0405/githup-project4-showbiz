/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.MusicSportTicketBlocks;
import com.group4.entities.MusicSports;
import com.group4.entities.OrderMovieDetails;
import com.group4.entities.OrderMovieDetailsPK;
import com.group4.entities.OrderMusicSportDetails;
import com.group4.entities.OrderMusicSportDetailsPK;
import com.group4.entities.Orders;
import com.group4.entities.Payments;
import com.group4.entities.TicketTypes;
import com.group4.sesionBeans.CustomersFacadeLocal;
import com.group4.sesionBeans.MusicSportTicketBlocksFacadeLocal;
import com.group4.sesionBeans.MusicSportsFacadeLocal;
import com.group4.sesionBeans.OrderMusicSportDetailsFacadeLocal;
import com.group4.sesionBeans.OrdersFacadeLocal;
import com.group4.sesionBeans.PaymentsFacadeLocal;
import com.group4.sesionBeans.TicketTypesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.constraints.Null;

/**
 *
 * @author Admin
 */
@Named(value = "bookMusicSportMB")
@SessionScoped
public class BookMusicSportMB implements Serializable {

    @EJB
    private OrderMusicSportDetailsFacadeLocal orderMusicSportDetailsFacade1;

    @EJB
    private CustomersFacadeLocal customersFacade;

    @Inject
    private LoginMB loginMB;
    @EJB
    private TicketTypesFacadeLocal ticketTypesFacade;

    @EJB
    private PaymentsFacadeLocal paymentsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private MusicSportsFacadeLocal musicSportsFacade;

    @EJB
    private MusicSportTicketBlocksFacadeLocal musicSportTicketBlocksFacade;

    private MusicSports musicsport;
    private Orders order;

    private String notice = "";
    private String noticeTicket = "";
    private String noticePrice = "";
    private String noticePayment = "";
    private Integer ticketID;
    private Integer paymentID;
    private Integer quanlity = new Integer(1);
    private Long price = new Long(0);
    final Calendar calendar = Calendar.getInstance();

    public BookMusicSportMB() {
        musicsport = new MusicSports();
    }

    public String bookMusicSport(MusicSports ms) {
        if (quanlity == 0 || ticketID.equals(0)) {
            noticeTicket = "Please choose ticket typeee!";
            return "book";
        } else {
            Orders od = new Orders();

            try {
                MusicSportTicketBlocks msp = musicSportTicketBlocksFacade.findByTikcetTypenMusicSportID(ms, ticketTypesFacade.find(ticketID));
                od.setOrderID(setIDOrder());
                od.setDateOfPurchase(calendar.getTime());
                if (price.equals(0)) {
                    noticePrice="Total price can't be 0!";
                     return "book";
                } else {
                    od.setTotalPrice(price);
                    od.setCustomerUsername(customersFacade.find(loginMB.getCustomer().getCustomerUsername()));
                    if(paymentID.equals(0)){
                        noticePayment="Please choose payment!";
                         return "book";
                    }else{
                        od.setPaymentID(paymentsFacade.find(paymentID));
                        ordersFacade.create(od);
                    }
                }
                OrderMusicSportDetailsPK odtPK = new OrderMusicSportDetailsPK(od.getOrderID(), (musicSportTicketBlocksFacade.find(msp.getMusicSportTicketBlockID())).getMusicSportTicketBlockID());
                OrderMusicSportDetails odt = new OrderMusicSportDetails();
                odt.setOrderMusicSportDetailsPK(odtPK);
                odt.setQuantity(quanlity);
                orderMusicSportDetailsFacade1.create(odt);
                MusicSportTicketBlocks mssp = musicSportTicketBlocksFacade.find(msp.getMusicSportTicketBlockID());
                mssp.setResidual(mssp.getQuantity() - quanlity);
                musicSportTicketBlocksFacade.edit(mssp);
                notice ="alert ('Thank you! You have successfully booked your ticket!');";
            } catch (Exception ex) {
                notice ="alert ('An error occurred during the booking process!');";
                return "book";
            }

            return "index?faces-redirect=true";
        }
    }

    public List<TicketTypes> showTicketTypes(MusicSports id) {
        return musicSportTicketBlocksFacade.findTicketTypeByMusicSportID(id);
    }

    public Long unitPrice(MusicSports msID, TicketTypes ticketID) {
        Long unit;
        MusicSportTicketBlocks ms = musicSportTicketBlocksFacade.findByTikcetTypenMusicSportID(msID, ticketID);
        unit = ms.getUnitPrice();
        return unit;
    }

    public Long displayPrice() {
        return price;
    }

    public void changeQuanlity(MusicSports msID) {
        if (ticketID.equals(0)) {
            price = new Long(0 * quanlity);
//            notice = "Please choose ticket type!";
        } else {
            price = unitPrice(msID, ticketTypesFacade.find(ticketID)) * quanlity;
        }
    }

    public String showBookPage(String id) {
        MusicSports ms = musicSportsFacade.find(id);
        setMusicsport(ms);
        return "book";
    }

    public List<Payments> showAllPayments() {
        return paymentsFacade.findAll();
    }

    public String setIDOrder() {
        String orderID = "";
        String year = (calendar.get(Calendar.YEAR) + "").substring(2);

        try {
            if (year.equals((ordersFacade.getLastID()).substring(2,4))) {
                String character = (ordersFacade.getLastID()).substring(0, 4);
                int number = Integer.parseInt((ordersFacade.getLastID().substring(4))) + 1;
                orderID = character + (String.format("%05d", number));
            } else {
                orderID = "OD" + year + "00001";
            }
        } catch (Exception ex) {
            orderID = "OD" + year + "00001";
        }

        return orderID;
    }

    public MusicSports getMusicsport() {
        return musicsport;
    }

    public void setMusicsport(MusicSports musicsport) {
        this.musicsport = musicsport;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Integer paymentID) {
        this.paymentID = paymentID;
    }

    public Integer getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(Integer quanlity) {
        this.quanlity = quanlity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getNoticeTicket() {
        return noticeTicket;
    }

    public void setNoticeTicket(String noticeTicket) {
        this.noticeTicket = noticeTicket;
    }

    public String getNoticePrice() {
        return noticePrice;
    }

    public void setNoticePrice(String noticePrice) {
        this.noticePrice = noticePrice;
    }

    public String getNoticePayment() {
        return noticePayment;
    }

    public void setNoticePayment(String noticePayment) {
        this.noticePayment = noticePayment;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}
