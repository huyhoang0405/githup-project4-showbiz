/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Areas;
import com.group4.sesionBeans.AreasFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Admin
 */
@Named(value = "areaMB")
@SessionScoped
public class AreaMB implements Serializable {

    @EJB
    private AreasFacadeLocal areasFacade;

    private Areas area;
    private List<Areas> list;

    public AreaMB() {
        area = new Areas();
    }

    public List<Areas> showAllAreas() {
        list = areasFacade.findAll();
        return list;
    }

    //reset form
    public void resetForm() {
        area.setAreaName(null);
    }

    //display form create a new area
    public String loadFormCreateNew() {
        resetForm();
        return "create?faces-redirect=true";
    }

    //create a new area
    public String createNewArea() {
        try {
            Areas a = new Areas();
            a.setAreaName(area.getAreaName());
            areasFacade.create(a);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }

    //details of an area
    public String showDetailsArea(int id) {
        Areas a = areasFacade.find(id);
        setArea(a);
        return "details?faces-redirect=true";
    }

    //load form edit 
    public String loadFormEdit(int id) {
        Areas a = areasFacade.find(id);
        setArea(a);
        return "edit?faces-redirect=true";
    }

    //edit an area
    public String editArea(int id) {
        try {
            Areas a = areasFacade.find(id);
            a.setAreaName(area.getAreaName());
            areasFacade.edit(a);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }

    //delete an area
    public void deleteArea(int id) {
        try {
            Areas a = areasFacade.find(id);
            areasFacade.remove(a);
        } catch (Exception e) {
        }
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }

    public List<Areas> getList() {
        return list;
    }

    public void setList(List<Areas> list) {
        this.list = list;
    }

}
