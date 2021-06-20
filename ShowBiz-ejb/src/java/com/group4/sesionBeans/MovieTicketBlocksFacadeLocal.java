/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.MovieTicketBlocks;
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
    
}
