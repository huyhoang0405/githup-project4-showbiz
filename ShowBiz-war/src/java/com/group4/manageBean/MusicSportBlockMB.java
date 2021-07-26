/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.MusicSportTicketBlocks;
import com.group4.entities.MusicSports;
import com.group4.entities.TicketTypes;
import com.group4.sesionBeans.MusicSportTicketBlocksFacadeLocal;
import com.group4.sesionBeans.MusicSportsFacadeLocal;
import com.group4.sesionBeans.TicketTypesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Admin
 */
@Named(value = "musicSportBlockMB")
@SessionScoped
public class MusicSportBlockMB implements Serializable {

    @EJB
    private TicketTypesFacadeLocal ticketTypesFacade;

    @EJB
    private MusicSportsFacadeLocal musicSportsFacade;

    @EJB
    private MusicSportTicketBlocksFacadeLocal musicSportTicketBlocksFacade;

    private MusicSportTicketBlocks musicSportBlock;

    private Integer ticketID;
    private String msID;
    private String noticeQuantity;
    private final Calendar calendar = Calendar.getInstance();

    public MusicSportBlockMB() {
        musicSportBlock = new MusicSportTicketBlocks();
    }

    public String createNewBlock() {
        MusicSportTicketBlocks m;
        if (!musicSportTicketBlocksFacade.checkBlock(ticketTypesFacade.find(ticketID), musicSportsFacade.find(msID))) {
            try {
                m = new MusicSportTicketBlocks();
                m.setMusicSportTicketBlockID(createID());
                m.setMusicSportID(musicSportsFacade.find(msID));
                m.setTicketTypeID(ticketTypesFacade.find(ticketID));
                m.setQuantity(musicSportBlock.getQuantity());
                m.setResidual(musicSportBlock.getQuantity());
                m.setUnitPrice(musicSportBlock.getUnitPrice());

                musicSportTicketBlocksFacade.create(m);
            } catch (Exception e) {
            }

        } else {
            m = musicSportTicketBlocksFacade.findBlock(ticketTypesFacade.find(ticketID), musicSportsFacade.find(msID));
            Integer result = Math.abs(m.getQuantity() - musicSportBlock.getQuantity());

            if (m.getQuantity() > musicSportBlock.getQuantity()) {
                if (result > m.getResidual()) {
                    noticeQuantity = "So ve  cua so luong moi it hon ve con lai";
                } else {
                    m.setQuantity(musicSportBlock.getQuantity());
                    m.setResidual(m.getResidual() - result);
                }
            } else {
                m.setQuantity(musicSportBlock.getQuantity());
                m.setResidual(m.getResidual() + result);
            }
            musicSportTicketBlocksFacade.edit(m);

        }

        return "index";
    }

    public String showDetails(String id) {
        MusicSportTicketBlocks msb = musicSportTicketBlocksFacade.find(id);
        setMusicSportBlock(msb);
        return "details";
    }

    public String delete(String id) {
        MusicSportTicketBlocks m = musicSportTicketBlocksFacade.find(id);
        musicSportTicketBlocksFacade.remove(m);
        return "index";
    }

    public List<MusicSports> showAllMusicSports() {
        return musicSportsFacade.findAll();
    }

    public String loadFormCreateNew() {
        resetForm();
        musicSportBlock.setMusicSportTicketBlockID(createID());
        return "create";
    }

    public String edit(String id) {
        try {
            MusicSportTicketBlocks m = musicSportTicketBlocksFacade.find(id);

            m.setMusicSportID(musicSportsFacade.find(msID));
            m.setTicketTypeID(ticketTypesFacade.find(ticketID));
            m.setUnitPrice(musicSportBlock.getUnitPrice());

            Integer result = Math.abs(m.getQuantity() - musicSportBlock.getQuantity());
            if (m.getQuantity() > musicSportBlock.getQuantity()) {
                if (result > m.getResidual()) {
                    noticeQuantity = "So ve  cua so luong moi it hon ve con lai";
                } else {
                    m.setQuantity(musicSportBlock.getQuantity());
                    m.setResidual(m.getResidual() - result);
                }
            } else {
                m.setQuantity(musicSportBlock.getQuantity());
                m.setResidual(m.getResidual() + result);
            }
            
            musicSportTicketBlocksFacade.edit(m);
            resetForm();
            return "index";
        } catch (Exception e) {
        }

        return "index";
    }

    public String loadFormEdit(String id) {
        MusicSportTicketBlocks m = musicSportTicketBlocksFacade.find(id);
        setMusicSportBlock(m);
        setMsID(m.getMusicSportID().getMusicSportID());
        setTicketID(m.getTicketTypeID().getTicketTypeID());

        return "edit";
    }

    public void resetForm() {
        msID = null;
        musicSportBlock.setMusicSportID(null);
        musicSportBlock.setQuantity(null);
        musicSportBlock.setResidual(null);
        ticketID = null;
        musicSportBlock.setUnitPrice(null);
    }

    public List<MusicSportTicketBlocks> showAll() {
        return musicSportTicketBlocksFacade.findAll();
    }

    public List<TicketTypes> showAllTicketTypes() {
        return ticketTypesFacade.findAll();
    }

    public String createID() {
        String msBlockID = "";
        String year = (calendar.get(Calendar.YEAR) + "").substring(2);
        try {
            if (year.equals((musicSportTicketBlocksFacade.getLastID()).substring(3, 5))) {
                String character = (musicSportTicketBlocksFacade.getLastID()).substring(0, 5);
                int number = Integer.parseInt((musicSportTicketBlocksFacade.getLastID().substring(5))) + 1;
                msBlockID = character + (String.format("%05d", number));
            } else {
                msBlockID = "MSB" + year + "00001";
            }
        } catch (Exception ex) {
            msBlockID = "MSB" + year + "00001";
        }

        return msBlockID;
    }

    public MusicSportTicketBlocks getMusicSportBlock() {
        return musicSportBlock;
    }

    public void setMusicSportBlock(MusicSportTicketBlocks musicSportBlock) {
        this.musicSportBlock = musicSportBlock;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public String getMsID() {
        return msID;
    }

    public void setMsID(String msID) {
        this.msID = msID;
    }

    public String getNoticeQuantity() {
        return noticeQuantity;
    }

    public void setNoticeQuantity(String noticeQuantity) {
        this.noticeQuantity = noticeQuantity;
    }

}
