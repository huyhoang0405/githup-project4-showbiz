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

    private Cinemas cinema;
    private Integer placeID;

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
        return "details";
    }

    //reset form
    public void resetForm() {
        cinema.setCinemaName(null);
        cinema.setPlacesID(null);
    }

    //load form create a new cinema
    public String loadFormCreateNew() {
        resetForm();
        return "create";
    }

    //create a new cinema
    public String createNewCinema() {
        try {
            Cinemas c = new Cinemas();

            c.setCinemaName(cinema.getCinemaName());
            c.setPlacesID(placesFacade.find(placeID));

            cinemasFacade.create(c);
            resetForm();
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    //load form edit
    public String loadFormEdit(int id) {
        Cinemas c = cinemasFacade.find(id);
        setCinema(c);
        setPlaceID(c.getPlacesID().getPlacesID());
        return "edit";
    }
    
    //edit an place
    public String editCinema(int id) {
        try {
            Cinemas c = cinemasFacade.find(id);
            c.setCinemaName(cinema.getCinemaName());
            
            c.setPlacesID(placesFacade.find(placeID));
            cinemasFacade.edit(c);
            resetForm();
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    
    //delete a cinema
    public void deleteCinema(int id) {
        try {
            Cinemas c = cinemasFacade.find(id);
            cinemasFacade.remove(c);
        } catch (Exception e) {
        }
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

}
