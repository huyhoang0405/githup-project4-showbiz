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

    private Integer paymentID;
    private Integer cinemaID;
    private String movieID;
    private Integer ticketID;
    private String notice;
    private boolean check;
    final Calendar calendar = Calendar.getInstance();
    private Integer quanlity = new Integer(1);
    private Long price;
    private String time;

    public BookMovieMB() {
        movieTicketBlock = new MovieTicketBlocks();
        movie = new Movies();
    }

    public Long unitPrice(Movies movieID, Cinemas cinemaID, TicketTypes ticketID, Date date, String time) {
        List<MovieTicketBlocks> mtb = movieTicketBlocksFacade.selectType(movieID, cinemaID, ticketID, date, time);
        Long unit = movieTicketBlocksFacade.find(mtb.get(0).getMovieTicketBlockID()).getUnitPrice();
        return unit;
    }

    public void changeQuanlity(Movies movieID) {
        price = unitPrice(movieID, cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID), movieTicketBlock.getDate(), time) * quanlity;
    }

    public Long displayPrice() {
        return price;
    }

    public String bookMovie(Movies movie) {
        Orders od = new Orders();
        List<MovieTicketBlocks> list = movieTicketBlocksFacade.selectType(movie,cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID), movieTicketBlock.getDate(), time);
        try {
            od.setOrderID(setIDOrder());
            od.setDateOfPurchase(calendar.getTime());
            od.setTotalPrice(price);
            od.setCustomerUsername(customersFacade.find(loginMB.getCustomer().getCustomerUsername()));
            od.setPaymentID(paymentsFacade.find(paymentID));
            ordersFacade.create(od);

            OrderMovieDetailsPK odtPK = new OrderMovieDetailsPK(od.getOrderID(), movieTicketBlocksFacade.find(list.get(0).getMovieTicketBlockID()).getMovieTicketBlockID());
            OrderMovieDetails odt = new OrderMovieDetails();
            odt.setOrderMovieDetailsPK(odtPK);
            odt.setQuantity(quanlity);
            orderMovieDetailsFacade.create(odt);
            MovieTicketBlocks mtb = movieTicketBlocksFacade.find(list.get(0).getMovieTicketBlockID());
            mtb.setResidual(mtb.getQuantity() - quanlity);
            movieTicketBlocksFacade.edit(mtb);
        } catch (Exception ex) {
            return "detail";
        }

        return "index?faces-redirect=true";

    }

//    public boolean checkType(Movies movie){
//        if( movieTicketBlocksFacade.checkType(movie, cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID), movieTicketBlock.getDate(), movieTicketBlock.getTime())){
//            return true;
//        }else return false;
//    }
    public List<Cinemas> showByMovieID(Movies id) {
        return movieTicketBlocksFacade.findByMovieID(id);
    }

    public List<MovieTicketBlocks> findTicketID(Movies movieID, Cinemas cinemaID, TicketTypes ticketID) {
        List<MovieTicketBlocks> mtb = movieTicketBlocksFacade.findByTicketID(movieID, cinemaID, ticketID);
        TicketTypes ti = ticketTypesFacade.find(mtb.get(0).getTicketTypeID().getTicketTypeID());
        return (List<MovieTicketBlocks>) ti.getMovieTicketBlocksCollection();
    }

    public List<MovieTicketBlocks> findDate(Movies movieID, Cinemas cinemaID, Date date) {
        return movieTicketBlocksFacade.findByDate(movieID, cinemaID, date);
    }

    public List<MovieTicketBlocks> findTime(Movies movieID, Cinemas cinemaID, Date date, String time) {
        //List<MovieTicketBlocks> listMTBB = new ArrayList<>();
        //  List<TicketTypes>  list = movieTicketBlocksFacade.findByTime(movieID, cinemaID, date,time);
        //  for (TicketTypes ti : list){
        //     listMTBB.add(movieTicketBlocksFacade.find(ti));
        // }
        return movieTicketBlocksFacade.findByTime(movieID, cinemaID, date, time);
    }

    public List<MovieTicketBlocks> findCinemaID(Movies movieID, Cinemas cinemaID) {
        List<MovieTicketBlocks> mtb = movieTicketBlocksFacade.findByCinemaID(movieID, cinemaID);
        Cinemas ci = cinemasFacade.find(mtb.get(0).getCinemaID().getCinemaID());
        return (List<MovieTicketBlocks>) ci.getMovieTicketBlocksCollection();
    }

//    public List<MovieTicketBlocks> showTicket(){
//        List<MovieTicketBlocks> list = movieTicketBlocksFacade.
//    }
    public void changeCinema(Movies id) {
        listMTB = findCinemaID(id, cinemasFacade.find(cinemaID));
    }

    public void changeTicket(Movies movieID) {
        listMTB = findTicketID(movieID, cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID));
    }

    public void changeDate(Movies movie) {
        listMTB = findDate(movie, cinemasFacade.find(cinemaID), movieTicketBlock.getDate());
    }

    public void changeTime(Movies movie) {
        listMTB = findTime(movie, cinemasFacade.find(cinemaID), movieTicketBlock.getDate(), time);
    }

    public List<MovieTicketBlocks> blockChoosen() {
        return listMTB;
    }

    public String bookDetail(String id) {
        Movies mov = moviesFacade.find(id);
        setMovie(mov);
        return "book?faces-redirect=true";
    }

    public String setIDOrder() {
        String orderID = "";

        try {
            String character = (ordersFacade.getLastID()).substring(0, 2);
            String year = (calendar.get(Calendar.YEAR) + "").substring(2);
            int number = Integer.parseInt((movieTicketBlocksFacade.getLastID().substring(4))) + 1;
            orderID = character + year + (String.format("%05d", number));
        } catch (Exception ex) {
            orderID = "OD2100001";
        }

        return orderID;
    }

    public List<Payments> showAllPayments() {
        return paymentsFacade.findAll();
    }

    public List<MovieTicketBlocks> getListMTB() {
        return listMTB;
    }

    public void setListMTB(List<MovieTicketBlocks> listMTB) {
        this.listMTB = listMTB;
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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
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

}
