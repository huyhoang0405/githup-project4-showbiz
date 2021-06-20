/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Cinemas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface CinemasFacadeLocal {

    void create(Cinemas cinemas);

    void edit(Cinemas cinemas);

    void remove(Cinemas cinemas);

    Cinemas find(Object id);

    List<Cinemas> findAll();

    List<Cinemas> findRange(int[] range);

    int count();
    
}
