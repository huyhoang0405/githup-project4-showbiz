/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.MusicSportTicketBlocks;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface MusicSportTicketBlocksFacadeLocal {

    void create(MusicSportTicketBlocks musicSportTicketBlocks);

    void edit(MusicSportTicketBlocks musicSportTicketBlocks);

    void remove(MusicSportTicketBlocks musicSportTicketBlocks);

    MusicSportTicketBlocks find(Object id);

    List<MusicSportTicketBlocks> findAll();

    List<MusicSportTicketBlocks> findRange(int[] range);

    int count();
    
}
