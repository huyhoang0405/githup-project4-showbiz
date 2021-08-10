/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Cinemas;
import com.group4.entities.Places;
import com.group4.sesionBeans.CinemasFacadeLocal;
import com.group4.sesionBeans.PlacesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Admin
 */
@Named(value = "cinemaMB")
@SessionScoped
public class CinemaMB implements Serializable {

    @EJB
    private PlacesFacadeLocal placesFacade;

    @EJB
    private CinemasFacadeLocal cinemasFacade;

    private String notice;
    private Cinemas cinema;
    private Integer placeID;
    private String noticePlace;

    public CinemaMB() {
        cinema = new Cinemas();
    }

    //display all cinemas
    public List<Cinemas> showAllCinemas() {
        return cinemasFacade.findAll();
    }

    //display all place
    public List<Places> showAllPlaces() {
        return placesFacade.findAll();
    }

    //show details of place
    public String showDetailsCinema(int id) {
        Cinemas c = cinemasFacade.find(id);
        setCinema(c);
        return "details?faces-redirect=true";
    }

    //reset form
    public void resetForm() {
        cinema.setCinemaName(null);
        cinema.setAddress(null);
        setPlaceID(null);
    }

    //load form create a new cinema
    public String loadFormCreateNew() {
        resetForm();
        return "create?faces-redirect=true";
    }

    //create a new cinema
    public String createNewCinema() {
        try {
            Cinemas c = new Cinemas();

            c.setCinemaName(cinema.getCinemaName());
            c.setAddress(cinema.getAddress());
            if (placeID.equals(0)) {
                noticePlace = "Please chooose a city!";
                return "create?faces-redirect=true";
            } else {
                c.setPlacesID(placesFacade.find(placeID));
                cinemasFacade.create(c);
                resetForm();
                return "index?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }

    //load form edit
    public String loadFormEdit(int id) {
        Cinemas c = cinemasFacade.find(id);
        setCinema(c);
        setPlaceID(c.getPlacesID().getPlacesID());
        return "edit?faces-redirect=true";
    }

    //edit an place
    public String editCinema(int id) {
        try {
            Cinemas c = cinemasFacade.find(id);

            c.setCinemaName(cinema.getCinemaName());
            c.setAddress(cinema.getAddress());
            c.setPlacesID(placesFacade.find(placeID));
            cinemasFacade.edit(c);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }

    //delete a cinema
    public String deleteCinema(int id) {
        try {
            Cinemas c = cinemasFacade.find(id);
            cinemasFacade.remove(c);
        } catch (Exception e) {
            notice = "alert('You can't delete it. An error has occurred!');";
        }
        return "index?faces-redirect=true";
    }

    public Cinemas getCinema() {
        return cinema;
    }

    public void setCinema(Cinemas cinema) {
        this.cinema = cinema;
    }

    public Integer getPlaceID() {
        return placeID;
    }

    public void setPlaceID(Integer placeID) {
        this.placeID = placeID;
    }

    public String getNoticePlace() {
        return noticePlace;
    }

    public void setNoticePlace(String noticePlace) {
        this.noticePlace = noticePlace;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}
