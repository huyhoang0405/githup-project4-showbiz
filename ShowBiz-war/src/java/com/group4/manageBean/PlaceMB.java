/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Areas;
import com.group4.entities.Places;
import com.group4.sesionBeans.AreasFacadeLocal;
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
@Named(value = "placeMB")
@SessionScoped
public class PlaceMB implements Serializable {
    
    @EJB
    private AreasFacadeLocal areasFacade;
    
    @EJB
    private PlacesFacadeLocal placesFacade;
    
    private String notice;
    private Places place;
    private List<Places> list;
    private Integer areaID;
    
    public PlaceMB() {
        place = new Places();
    }

    //display all palces
    public List<Places> showAllPlaces() {
        list = placesFacade.findAll();
        return list;
    }

    //display all areas
    public List<Areas> showAllAreas() {
        return areasFacade.findAll();
    }

    //show details of place
    public String showDetailsPlace(int id) {
        Places p = placesFacade.find(id);
        setPlace(p);
        return "details?faces-redirect=true";
    }

    //reset form
    public void resetForm() {
        place.setCity(null);
        areaID=0;
        
    }

    //load form create a new place
    public String loadFormCreateNew() {
        resetForm();
        return "create?faces-redirect=true";
    }

    //create a new area
    public String createNewPlace() {
        try {
            Places p = new Places();
            
            p.setCity(place.getCity());
            p.setAreaID(areasFacade.find(areaID));
            placesFacade.create(p);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }

    //load form edit
    public String loadFormEdit(int id) {
        Places p = placesFacade.find(id);
        setPlace(p);
        setAreaID(p.getAreaID().getAreaID());
        return "edit?faces-redirect=true";
    }

    //edit an place
    public String editPlace(int id) {
        try {
            Places p = placesFacade.find(id);

            p.setCity(place.getCity());
            p.setAreaID(areasFacade.find(areaID));
            placesFacade.edit(p);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }
    
    //delete a place
    public String deletePlace(int id) {
        try {
            Places p = placesFacade.find(id);
            placesFacade.remove(p);
        } catch (Exception e) {
            notice = "alert('You can't delete it. An error has occurred!');";
        }
        return "index?faces-redirect=true";
    }

    public Places getPlace() {
        return place;
    }
    
    public void setPlace(Places place) {
        this.place = place;
    }
    
    public List<Places> getList() {
        return list;
    }
    
    public void setList(List<Places> list) {
        this.list = list;
    }
    
    public Integer getAreaID() {
        return areaID;
    }
    
    public void setAreaID(Integer areaID) {
        this.areaID = areaID;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
    
}
