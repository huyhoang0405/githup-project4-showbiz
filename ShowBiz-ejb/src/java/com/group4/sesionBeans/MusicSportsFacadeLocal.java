/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.MusicSports;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface MusicSportsFacadeLocal {

    void create(MusicSports musicSports);

    void edit(MusicSports musicSports);

    void remove(MusicSports musicSports);

    MusicSports find(Object id);

    List<MusicSports> findAll();

    List<MusicSports> findRange(int[] range);

    int count();

    String getLastID();

    List<MusicSports> showAllMusics();

    List<MusicSports> showAllSports();

    List<MusicSports> show6Newest();
}
