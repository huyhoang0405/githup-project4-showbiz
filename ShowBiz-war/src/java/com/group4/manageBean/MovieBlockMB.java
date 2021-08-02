/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Cinemas;
import com.group4.entities.MovieTicketBlocks;
import com.group4.entities.Movies;
import com.group4.entities.TicketTypes;
import com.group4.sesionBeans.CinemasFacadeLocal;
import com.group4.sesionBeans.MovieTicketBlocksFacadeLocal;
import com.group4.sesionBeans.MoviesFacadeLocal;
import com.group4.sesionBeans.TicketTypesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Admin
 */
@Named(value = "movieBlockMB")
@SessionScoped
public class MovieBlockMB implements Serializable {

    @EJB
    private CinemasFacadeLocal cinemasFacade;

    @EJB
    private TicketTypesFacadeLocal ticketTypesFacade;

    @EJB
    private MoviesFacadeLocal moviesFacade;

    @EJB
    private MovieTicketBlocksFacadeLocal movieTicketBlocksFacade;

    private List<Cinemas> listCinema;
    private List<TicketTypes> listType;

    private List<MovieTicketBlocks> listMTB = new ArrayList<>();

    private String noticeMovie;
    private String noticeQuantity;
    private String noticeDate;
    private String noticeCinema;
    private String noticeTicket;
    private String noticeTime;
    private String notice;
    private String noticePrice;

    private MovieTicketBlocks movieTicketBlock;
    private Integer cinemaID;
    private String movieID;
    private Integer ticketID;
    private final Calendar calendar = Calendar.getInstance();

    public MovieBlockMB() {
        movieTicketBlock = new MovieTicketBlocks();
    }

    public void resetForm() {
        cinemaID = null;
        movieTicketBlock.setDate(null);
        movieID = null;
        movieTicketBlock.setQuantity(null);
        movieTicketBlock.setResidual(null);
        ticketID = null;
        movieTicketBlock.setTime(null);
        movieTicketBlock.setUnitPrice(null);
        setNoticeCinema(null);
        setNoticeDate(null);
        setNoticeQuantity(null);
        setNoticeTicket(null);
        setNoticeTime(null);

    }

    public List<MovieTicketBlocks> showAllMovieTicketBlocks() {
        List<MovieTicketBlocks> list = movieTicketBlocksFacade.findAll();
        for (MovieTicketBlocks o : list) {
            calendar.setTime(o.getDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            o.setDate(endDate);
        }
        return list;
    }

    public String loadFormCreateNew() {
        resetForm();
        movieTicketBlock.setMovieTicketBlockID(createID());
        return "create?faces-redirect=true";
    }

    public String loadFormEdit(String id) {
        MovieTicketBlocks mtb = movieTicketBlocksFacade.find(id);
        setMovieTicketBlock(mtb);

//        calendar.setTime(mtb.getDate());
//        calendar.roll(Calendar.DATE, 1);
//        Date endDate = calendar.getTime();
//        mtb.setDate(endDate);
        setCinemaID(mtb.getCinemaID().getCinemaID());
        setMovieID(mtb.getMovieID().getMovieID());
        setTicketID(mtb.getTicketTypeID().getTicketTypeID());

        return "edit?faces-redirect=true";
    }

    public String createNewBlock() {
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        if (!movieTicketBlocksFacade.checkBlock(movieTicketBlock.getDate(), movieTicketBlock.getTime(), movieTicketBlock.getUnitPrice(), cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID), moviesFacade.find(movieID))) {
            try {
                MovieTicketBlocks mbt = new MovieTicketBlocks();
                Movies m = moviesFacade.find(movieID);
                if (movieID == null) {
                    noticeMovie = "Movie is invalid!";
                    return "create?faces-redirect=true";
                }
                if (movieTicketBlock.getTime().equals(null)) {
                    noticeTime = "Time can't be left empty!";
                    return "create?faces-redirect=true";
                }
                if (m.getReleaseDate().compareTo(movieTicketBlock.getDate()) > 0) {
                    noticeDate = "Date must be greater than the release date of movie(" + simpleDateFormat.format(m.getReleaseDate()) + ")";
                    return "create?faces-redirect=true";
                }
                if (movieTicketBlock.getQuantity() < 1) {
                    noticeQuantity = "The quantity must be more than 0";
                    return "create?faces-redirect=true";
                }
                if (movieTicketBlock.getUnitPrice() < 1) {
                    noticePrice = "The unit price must be more than 0";
                    return "create?faces-redirect=true";
                }
                if (cinemaID.equals(0)) {
                    noticeCinema = "Please choose a cinema!";
                    return "create?faces-redirect=true";
                }
                if (ticketID.equals(0)) {
                    noticeTicket = "Please choose a ticket type!";
                    return "create?faces-redirect=true";
                } else {
                    mbt.setMovieTicketBlockID(createID());
                    mbt.setDate(movieTicketBlock.getDate());
                    mbt.setTime(movieTicketBlock.getTime());
                    mbt.setQuantity(movieTicketBlock.getQuantity());
                    mbt.setResidual(movieTicketBlock.getQuantity());
                    mbt.setUnitPrice(movieTicketBlock.getUnitPrice());
                    mbt.setCinemaID(cinemasFacade.find(cinemaID));
                    mbt.setTicketTypeID(ticketTypesFacade.find(ticketID));
                    mbt.setMovieID(moviesFacade.find(movieID));

                    movieTicketBlocksFacade.create(mbt);
                }

            } catch (Exception e) {

                notice = "alert('Please fill in full information!');";
                return "create?faces-redirect=true";
            }
            return "index?faces-redirect=true";
        } else {
            try {
                MovieTicketBlocks mbt = movieTicketBlocksFacade.findBlock(movieTicketBlock.getDate(), movieTicketBlock.getTime(), movieTicketBlock.getUnitPrice(), cinemasFacade.find(cinemaID), ticketTypesFacade.find(ticketID), moviesFacade.find(movieID));
                Integer result = Math.abs(mbt.getQuantity() - movieTicketBlock.getQuantity());

                if (mbt.getQuantity() > movieTicketBlock.getQuantity()) {
                    if (result > mbt.getResidual()) {
                        noticeQuantity = "The new quantity is less than the sold quantity";
                        return "create?faces-redirect=true";
                    } else {
                        mbt.setQuantity(movieTicketBlock.getQuantity());
                        mbt.setResidual(mbt.getResidual() - result);
                    }
                } else {
                    mbt.setQuantity(movieTicketBlock.getQuantity());
                    mbt.setResidual(mbt.getResidual() + result);
                }
                movieTicketBlocksFacade.edit(mbt);

            } catch (Exception e) {
                notice = "alert('Please fill in full information!');";
                return "create?faces-redirect=true";
            }
            return "index?faces-redirect=true";
        }
    }

    public String editBlock(String id) {
        try {
            MovieTicketBlocks mbt = movieTicketBlocksFacade.find(id);

            mbt.setDate(movieTicketBlock.getDate());
            mbt.setTime(movieTicketBlock.getTime());
            mbt.setUnitPrice(movieTicketBlock.getUnitPrice());
            mbt.setCinemaID(cinemasFacade.find(cinemaID));
            mbt.setTicketTypeID(ticketTypesFacade.find(ticketID));
            mbt.setMovieID(moviesFacade.find(movieID));

            Integer result = Math.abs(mbt.getQuantity() - movieTicketBlock.getQuantity());
            if (mbt.getQuantity() > movieTicketBlock.getQuantity()) {
                if (result > mbt.getResidual()) {
                    noticeQuantity = "The new quantity is less than the sold quantity";
                    return "edit?faces-redirect=true";
                } else {
                    mbt.setQuantity(movieTicketBlock.getQuantity());
                    mbt.setResidual(mbt.getResidual() - result);
                }
            } else {
                mbt.setQuantity(movieTicketBlock.getQuantity());
                mbt.setResidual(mbt.getResidual() + result);
            }

            movieTicketBlocksFacade.edit(mbt);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            notice = "alert('Please fill in full information!');";
            return "edit?faces-redirect=true";
        }
    }

    public String deleteBlock(String id) {
        try {
            MovieTicketBlocks mtb = movieTicketBlocksFacade.find(id);
            movieTicketBlocksFacade.remove(mtb);
            return "index?faces-redirect=true";
        } catch (Exception e) {

        }
        return "index?faces-redirect=true";
    }

    public String showDetailsBlock(String id) {
        MovieTicketBlocks mtb = movieTicketBlocksFacade.find(id);
        setMovieTicketBlock(mtb);
        calendar.setTime(mtb.getDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        mtb.setDate(endDate);
        return "details?faces-redirect=true";
    }

    public List<Cinemas> showAllCinemas() {
        return cinemasFacade.findAll();
    }

    public List<TicketTypes> showAllTicketTypes() {
        return ticketTypesFacade.findAll();
    }

    public List<Movies> showAllMovies() {
        return moviesFacade.findAll();
    }

    public String createID() {
        String movieBlockID = "";
        String year = (calendar.get(Calendar.YEAR) + "").substring(2);

        try {
            if (year.equals((movieTicketBlocksFacade.getLastID()).substring(3, 5))) {
                String character = (movieTicketBlocksFacade.getLastID()).substring(0, 5);
                int number = Integer.parseInt((movieTicketBlocksFacade.getLastID().substring(5))) + 1;
                movieBlockID = character + (String.format("%05d", number));
            } else {
                movieBlockID = "MTB" + year + "00001";
            }
        } catch (Exception ex) {
            movieBlockID = "MTB" + year + "00001";
        }

        return movieBlockID;
    }

//zz    public List<MovieTicketBlocks> showByMovieID(Movies id) {
//        return movieTicketBlocksFacade.findByMovieID(id);
//    }
//    public List<MovieTicketBlocks> findCinemaID(Integer cinemaID) {
//        Cinemas ci = cinemasFacade.find(cinemaID);
//        return (List<MovieTicketBlocks>) ci.getMovieTicketBlocksCollection();
//    }
//    public List<MovieTicketBlocks> findTicketID(Integer ticketID) {
//        TicketTypes ti = ticketTypesFacade.find(ticketID);
//        return (List<MovieTicketBlocks>) ti.getMovieTicketBlocksCollection();
//    }
//    public List<MovieTicketBlocks> findDate(Date date) {
//        return movieTicketBlocksFacade.findByDate(date);
//
//    }
//    public List<MovieTicketBlocks> blockChoosen() {
//        return listMTB;
//    }
//    public void changeCinema() {
//        listMTB = findCinemaID(cinemaID);
//    }
//    public void changeTicket() {
//        listMTB = findTicketID(ticketID);
//    }
//    public void changeDate() {
//        listMTB = findDate(movieTicketBlock.getDate());
//    }
    public Collection showAll() {
        return movieTicketBlocksFacade.showAllBlock();
    }

    public MovieTicketBlocks getMovieTicketBlock() {
        return movieTicketBlock;
    }

    public void setMovieTicketBlock(MovieTicketBlocks movieTicketBlock) {
        this.movieTicketBlock = movieTicketBlock;
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

    public List<Cinemas> getListCinema() {
        return listCinema;
    }

    public void setListCinema(List<Cinemas> listCinema) {
        this.listCinema = listCinema;
    }

    public List<TicketTypes> getListType() {
        return listType;
    }

    public void setListType(List<TicketTypes> listType) {
        this.listType = listType;
    }

    public List<MovieTicketBlocks> getListMTB() {
        return listMTB;
    }

    public void setListMTB(List<MovieTicketBlocks> listMTB) {
        this.listMTB = listMTB;
    }

    public String getNoticeQuantity() {
        return noticeQuantity;
    }

    public void setNoticeQuantity(String noticeQuantity) {
        this.noticeQuantity = noticeQuantity;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeCinema() {
        return noticeCinema;
    }

    public void setNoticeCinema(String noticeCinema) {
        this.noticeCinema = noticeCinema;
    }

    public String getNoticeTicket() {
        return noticeTicket;
    }

    public void setNoticeTicket(String noticeTicket) {
        this.noticeTicket = noticeTicket;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNoticeMovie() {
        return noticeMovie;
    }

    public void setNoticeMovie(String noticeMovie) {
        this.noticeMovie = noticeMovie;
    }

    public String getNoticePrice() {
        return noticePrice;
    }

    public void setNoticePrice(String noticePrice) {
        this.noticePrice = noticePrice;
    }

}
