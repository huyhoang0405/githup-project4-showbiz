/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Dramas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface DramasFacadeLocal {

    void create(Dramas dramas);

    void edit(Dramas dramas);

    void remove(Dramas dramas);

    Dramas find(Object id);

    List<Dramas> findAll();

    List<Dramas> findRange(int[] range);

    int count();
    
}
