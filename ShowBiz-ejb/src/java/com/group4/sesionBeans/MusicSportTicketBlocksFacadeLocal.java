/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.MusicSportTicketBlocks;
import com.group4.entities.MusicSports;
import com.group4.entities.TicketTypes;
import java.util.Date;
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

    String getLastID();

    List<TicketTypes> findTicketTypeByMusicSportID(MusicSports id);

    MusicSportTicketBlocks findByTikcetTypenMusicSportID(MusicSports msID, TicketTypes ticketID);

    List<MusicSportTicketBlocks> findListByTikcetTypenMusicSportID(MusicSports msID, TicketTypes ticketID);
    
    boolean checkBlock(TicketTypes ticket, MusicSports musicsport);
    
    MusicSportTicketBlocks findBlock(TicketTypes ticket,MusicSports musicsport);
    
    List<MusicSports> findMusicSportInBlock();
    
    Object ticketStatistics(MusicSports musicsport);
    
    Object statisticMusicSport(Date startdate, Date enddate);
}
