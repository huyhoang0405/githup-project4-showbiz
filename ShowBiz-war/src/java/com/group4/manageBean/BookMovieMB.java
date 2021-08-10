/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Cinemas;
import com.group4.entities.MovieTicketBlocks;
import com.group4.entities.Movies;
import com.group4.entities.OrderMovieDetails;
import com.group4.entities.OrderMovieDetailsPK;
import com.group4.entities.Orders;
import com.group4.entities.Payments;
import com.group4.entities.TicketTypes;
import com.group4.sesionBeans.CinemasFacadeLocal;
import com.group4.sesionBeans.CustomersFacadeLocal;
import com.group4.sesionBeans.MovieTicketBlocksFacadeLocal;
import com.group4.sesionBeans.MoviesFacadeLocal;
import com.group4.sesionBeans.OrderMovieDetailsFacadeLocal;
import com.group4.sesionBeans.OrdersFacadeLocal;
import com.group4.sesionBeans.PaymentsFacadeLocal;
import com.group4.sesionBeans.TicketTypesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Admin
 */
@Named(value = "bookMovieMB")
@SessionScoped
public class BookMovieMB implements Serializable {

    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private MoviesFacadeLocal moviesFacade;

    @EJB
    private PaymentsFacadeLocal paymentsFacade;

    @EJB
    private OrderMovieDetailsFacadeLocal orderMovieDetailsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private TicketTypesFacadeLocal ticketTypesFacade;

    @EJB
    private CinemasFacadeLocal cinemasFacade;

    @EJB
    private MovieTicketBlocksFacadeLocal movieTicketBlocksFacade;

    @Inject
    private LoginMB loginMB;
    private Orders order;
    private OrderMovieDetails book;

    private Movies movie;
    private MovieTicketBlocks movieTicketBlock;

    private List<MovieTicketBlocks> listMTB = new ArrayList<>();
    private List<String> listTime = new ArrayList<>();
    private List<TicketTypes> listTicket = new ArrayList<>();
    private Integer paymentID;
    private Integer cinemaID;
    private String movieID;
    private Integer ticketID;
    private boolean check;
    final Calendar calendar = Calendar.getInstance();
    final Calendar id = Calendar.getInstance();
    final Calendar c = Calendar.getInstance();
    private Integer quanlity = new Integer(1);
    private Long price = new Long(0);
    private String time;
    private Date date;

    private String notice = "";
    private String noticeCinema;
    private String noticeDate;
    private String noticeTime;
    private String noticeTicket;
    private String noticePrice;
    private String noticePayment;

    public BookMovieMB() {
        movieTicketBlock = new MovieTicketBlocks();
        movie = new Movies();
    }

    public String home(){
        return "index";
    }
    public Long unitPrice(Movies movieID, Cinemas cinemaID, TicketTypes ticketID, Date date, String time) {
        List<MovieTicketBlocks> mtb = movieTicketBlocksFacade.selectType(movieID, cinemaID, ticketID, date, time);
        Long unit = movieTicketBlocksFacade.find(mtb.get(0).getMovieTicketBlockID()).getUnitPrice();
        return unit;
    }

    public void changeQuanlity(Movies movieID) {
        price = unitPrice(movieID, cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID), date, time) * quanlity;
    }

    public Long displayPrice() {
        return price;
    }

    public String bookMovie(Movies movie) {
        if (cinemaID.equals(0)) {
            noticeCinema = "Please choose a cinema!";
            return "book?faces-redirect=true";
        } else if (time.equals("Null")) {
            noticeTime = "Please choose a timezone!";
            noticeCinema = "";
            return "book?faces-redirect=true";
        } else if (ticketID.equals(0)) {
            noticeCinema = "";
            noticeTime = "";
            noticeTicket = "Please choose ticket type!";
            return "book?faces-redirect=true";
        } else {
            Orders od = new Orders();
            MovieTicketBlocks m = movieTicketBlocksFacade.findaMovieByTicketID(movie, cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID), date, time);
            
            if (m.getResidual() < 1) {
                noticePrice = "Movie tickets sold out!";
                notice = "alert ('Sorry, we've sold out of these tickets!');";
                return "book?faces-redirect=true";
            } else {
                try {
                    od.setOrderID(setIDOrder());
                    od.setDateOfPurchase(c.getTime());
                    if (price.equals(0)) {
                        noticePrice = "Total price can't be 0!";
                        return "book?faces-redirect=true";
                    } else {
                        od.setTotalPrice(price);
                        od.setCustomerUsername(customersFacade.find(loginMB.getCustomer().getCustomerUsername()));
                        if (paymentID.equals(0)) {
                            noticePayment = "Please choose payment!";
                            return "book?faces-redirect=true";
                        } else {
                            od.setPaymentID(paymentsFacade.find(paymentID));
                            ordersFacade.create(od);
                        }

                    }

                    OrderMovieDetailsPK odtPK = new OrderMovieDetailsPK(od.getOrderID(), movieTicketBlocksFacade.find(m.getMovieTicketBlockID()).getMovieTicketBlockID());
                    OrderMovieDetails odt = new OrderMovieDetails();
                    odt.setOrderMovieDetailsPK(odtPK);
                    odt.setQuantity(quanlity);
                    orderMovieDetailsFacade.create(odt);

                    m.setResidual(m.getResidual() - quanlity);
                    movieTicketBlocksFacade.edit(m);
                    notice = "alert ('Thank you! You have successfully booked your ticket!');";
                } catch (Exception ex) {
                      notice = "alert ('An error occurred during the booking process!');";
                    return "book?faces-redirect=true";
                }
            }
            return "index?faces-redirect=true";

        }
    }

//    public boolean checkType(Movies movie){
//        if( movieTicketBlocksFacade.checkType(movie, cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID), movieTicketBlock.getDate(), movieTicketBlock.getTime())){
//            return true;
//        }else return false;
//    }
    public List<Cinemas> showByMovieID(Movies id) {
        return movieTicketBlocksFacade.findByMovieID(id);
    }

    public List<TicketTypes> findTicketID(Movies movieID, Cinemas cinemaID, TicketTypes ticketID, Date date, String time) {
        List<TicketTypes> mtb = movieTicketBlocksFacade.findByTicketID(movieID, cinemaID, ticketID, date, time);
        // TicketTypes ti = ticketTypesFacade.find(mtb.get(0).getTicketTypeID().getTicketTypeID());
        //return (List<TicketTypes>) ti.getMovieTicketBlocksCollection();
        return mtb;
    }

    public List<String> findDate(Movies movieID, Cinemas cinemaID, Date date) {
        return movieTicketBlocksFacade.findByDate(movieID, cinemaID, date);
    }

    public List<TicketTypes> findTime(Movies movieID, Cinemas cinemaID, Date date, String time) {
        //List<MovieTicketBlocks> listMTBB = new ArrayList<>();
        //  List<TicketTypes>  list = movieTicketBlocksFacade.findByTime(movieID, cinemaID, date,time);
        //  for (TicketTypes ti : list){
        //     listMTBB.add(movieTicketBlocksFacade.find(ti));
        // }
        List<TicketTypes> list = movieTicketBlocksFacade.findByTime(movieID, cinemaID, date, time);
        return list;
    }

    public List<MovieTicketBlocks> findCinemaID(Movies movieID, Cinemas cinemaID) {
        List<MovieTicketBlocks> mtb = movieTicketBlocksFacade.findByCinemaID(movieID, cinemaID);
        Cinemas ci = cinemasFacade.find(mtb.get(0).getCinemaID().getCinemaID());
        return (List<MovieTicketBlocks>) ci.getMovieTicketBlocksCollection();
    }

//    public List<TicketTypes> findCinemaID(Movies movieID, Cinemas cinemaID) {
//        
//        return movieTicketBlocksFacade.findByTicketID(movieID, cinemaID);
//    }
//    public List<MovieTicketBlocks> showTicket(){
//        List<MovieTicketBlocks> list = movieTicketBlocksFacade.
//    }
    public void changeCinema(Movies id) {
        if (cinemaID.equals(0)) {

        } else {
            listMTB = findCinemaID(id, cinemasFacade.find(cinemaID));
        }
    }

//        public void changeCinema(Movies id) {
//            List<TicketTypes> list = findCinemaID(id, cinemasFacade.find(cinemaID));
//            for(TicketTypes t : list){
//                
//            }
//    }
    public void changeTicket(Movies movieID) {

        List<TicketTypes> list = findTicketID(movieID, cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID), date, time);
        for (TicketTypes t : list) {
            MovieTicketBlocks m = movieTicketBlocksFacade.findaMovieByTicketID(movieID, cinemasFacade.find(cinemaID), ticketTypesFacade.find(t.getTicketTypeID()), date, time);
            listMTB.add(m);

        }
    }

    public void changeDate(Movies movie) {
//        for (int i = 0; i < listMTB.size(); i++) {
//            for (int j = i + 1; j < listMTB.size(); j++) {
//                if (listMTB.get(i).equals(listMTB.get(j))) {
//                    listMTB.remove(j);
//                }
//            }
//
//        }
//        listMTB = findDate(movie, cinemasFacade.find(cinemaID), date);
        listTime = findDate(movie, cinemasFacade.find(cinemaID), date);
    }

    public void changeTime(Movies movie) {
//        List<MovieTicketBlocks> list1 = findTime(movie, cinemasFacade.find(cinemaID), date, time);
//        for (MovieTicketBlocks mtb : list1) {
//            List<MovieTicketBlocks> list2 = movieTicketBlocksFacade.findByTime(movie, cinemasFacade.find(cinemaID), date, time);
//            for (MovieTicketBlocks m : list2) {
//                listMTB.add(m);
//            }
//        }
        listTicket = findTime(movie, cinemasFacade.find(cinemaID), date, time);
    }

    public List<MovieTicketBlocks> blockChoosen() {
        for (int i = 0; i < listMTB.size(); i++) {
            for (int j = i + 1; j < listMTB.size(); j++) {
                if (listMTB.get(i).equals(listMTB.get(j))) {
                    listMTB.remove(j);
                }
            }
        }
        return listMTB;
    }

    public String bookDetail(String id) {
        Movies mov = moviesFacade.find(id);
        setMovie(mov);
        return "book?faces-redirect=true";
    }

    public String setIDOrder() {
        String orderID = "";
        String year = (id.get(Calendar.YEAR) + "").substring(2);

        try {
            if (year.equals((ordersFacade.getLastID()).substring(2, 4))) {
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

    public List<Payments> showAllPayments() {
        return paymentsFacade.findAll();
    }

    public Integer getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(Integer cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public MovieTicketBlocks getMovieTicketBlock() {
        return movieTicketBlock;
    }

    public void setMovieTicketBlock(MovieTicketBlocks movieTicketBlock) {
        this.movieTicketBlock = movieTicketBlock;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public OrderMovieDetails getBook() {
        return book;
    }

    public void setBook(OrderMovieDetails book) {
        this.book = book;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

    public Integer getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(Integer quanlity) {
        this.quanlity = quanlity;
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Integer paymentID) {
        this.paymentID = paymentID;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNoticeCinema() {
        return noticeCinema;
    }

    public void setNoticeCinema(String noticeCinema) {
        this.noticeCinema = noticeCinema;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<MovieTicketBlocks> getListMTB() {
        return listMTB;
    }

    public void setListMTB(List<MovieTicketBlocks> listMTB) {
        this.listMTB = listMTB;
    }

    public List<String> getListTime() {
        return listTime;
    }

    public void setListTime(List<String> listTime) {
        this.listTime = listTime;
    }

    public List<TicketTypes> getListTicket() {
        return listTicket;
    }

    public void setListTicket(List<TicketTypes> listTicket) {
        this.listTicket = listTicket;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}
