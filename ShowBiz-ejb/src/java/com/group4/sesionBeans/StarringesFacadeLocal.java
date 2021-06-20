/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Starringes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface StarringesFacadeLocal {

    void create(Starringes starringes);

    void edit(Starringes starringes);

    void remove(Starringes starringes);

    Starringes find(Object id);

    List<Starringes> findAll();

    List<Starringes> findRange(int[] range);

    int count();
    
}
