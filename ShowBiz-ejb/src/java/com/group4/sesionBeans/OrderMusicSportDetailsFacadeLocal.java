/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.OrderMusicSportDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface OrderMusicSportDetailsFacadeLocal {

    void create(OrderMusicSportDetails orderMusicSportDetails);

    void edit(OrderMusicSportDetails orderMusicSportDetails);

    void remove(OrderMusicSportDetails orderMusicSportDetails);

    OrderMusicSportDetails find(Object id);

    List<OrderMusicSportDetails> findAll();

    List<OrderMusicSportDetails> findRange(int[] range);

    int count();
    
}
