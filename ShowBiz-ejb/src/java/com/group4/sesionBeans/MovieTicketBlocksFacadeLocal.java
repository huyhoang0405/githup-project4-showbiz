/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Cinemas;
import com.group4.entities.MovieTicketBlocks;
import com.group4.entities.Movies;
import com.group4.entities.TicketTypes;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface MovieTicketBlocksFacadeLocal {

    void create(MovieTicketBlocks movieTicketBlocks);

    void edit(MovieTicketBlocks movieTicketBlocks);

    void remove(MovieTicketBlocks movieTicketBlocks);

    MovieTicketBlocks find(Object id);

    List<MovieTicketBlocks> findAll();

    List<MovieTicketBlocks> findRange(int[] range);

    int count();
 
        String getLastID();

    Collection showAllBlock();

    List<Cinemas> findByMovieID(Movies id);

    MovieTicketBlocks findByMovieID(String id);

    List<MovieTicketBlocks> findByCinemaID(Cinemas id);

    List<MovieTicketBlocks> findByCinemaID(Movies movieID, Cinemas cinemaID);

    List<TicketTypes> findByTicketID(Movies movieID,Cinemas cinemaID,TicketTypes ticketID,Date date,String time);
    
    MovieTicketBlocks findaMovieByTicketID(Movies movieID,Cinemas cinemaID,TicketTypes ticketID,Date date,String time);

    List<MovieTicketBlocks> selectType(Movies movieID,Cinemas cinemaID,TicketTypes ticketID,Date date,String time);

    List<String> findByDate(Movies movieID, Cinemas cinemaID, Date id);
    
    List<TicketTypes> findByTime(Movies movieID,Cinemas cinemaID,Date date,String time);
    
    MovieTicketBlocks findaMovieByTime(Movies movieID,Cinemas cinemaID,Date date,String time);

    MovieTicketBlocks findTicket(Movies movieID,Cinemas cinemaID,TicketTypes ticketID);
    
    List<TicketTypes> findByTicketID(Movies movieID,Cinemas cinemaID);
    
    boolean checkBlock(Date date, String time, Long price, Cinemas cinema, TicketTypes ticket, Movies movie);
    
    MovieTicketBlocks findBlock(Date date, String time, Long price, Cinemas cinema, TicketTypes ticket, Movies movie);
}
