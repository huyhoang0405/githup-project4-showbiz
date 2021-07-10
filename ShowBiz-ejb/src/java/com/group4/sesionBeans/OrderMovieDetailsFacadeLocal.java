/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.OrderMovieDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface OrderMovieDetailsFacadeLocal {

    void create(OrderMovieDetails orderMovieDetails);

    void edit(OrderMovieDetails orderMovieDetails);

    void remove(OrderMovieDetails orderMovieDetails);

    OrderMovieDetails find(Object id);

    List<OrderMovieDetails> findAll();

    List<OrderMovieDetails> findRange(int[] range);

    int count();
    
}
