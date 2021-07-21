/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Categories;
import com.group4.sesionBeans.CategoriesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Admin
 */
@Named(value = "categoryMB")
@SessionScoped
public class CategoryMB implements Serializable {

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    private Categories category;
    public CategoryMB() {
        category = new Categories();
    }

    public List<Categories> showAll(){
        return categoriesFacade.findAll();
    }
    
    public String loadFormCreateNew(){
        resetForm();
        return "create?faces-redirect=true";
    }
    
    public String create(){
        try {
            Categories c = new Categories();
            c.setCategoryName(category.getCategoryName());
            categoriesFacade.create(c);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }
    public String details(Integer id){
        Categories c = categoriesFacade.find(id);
        setCategory(c);
        return "details?faces-redirect=true";
    }
    public String loadFormEdit(Integer id){
        Categories c = categoriesFacade.find(id);
        setCategory(c);
        return "edit?faces-redirect=true";
    }
    public String edit(Integer id){
         try {
            Categories c = categoriesFacade.find(id);
            c.setCategoryName(category.getCategoryName());
            categoriesFacade.edit(c);
            resetForm();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }
    public void delete(Integer id){
        try {
            Categories c = categoriesFacade.find(id);
            categoriesFacade.remove(c);
        } catch (Exception e) {
        }
    }
    public void resetForm(){
        category.setCategoryName(null);
    }
    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
    
}
