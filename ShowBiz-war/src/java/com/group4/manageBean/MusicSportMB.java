/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.MusicSports;
import com.group4.sesionBeans.MusicSportsFacadeLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@Named(value = "musicSportMB")
@SessionScoped
public class MusicSportMB implements Serializable {

    @EJB
    private MusicSportsFacadeLocal musicSportsFacade;

    private MusicSports musicSport;

    final Calendar calendar = Calendar.getInstance();

    private String notice;
    private String noticeCapacity;
    private Part fileBanner;
    private Part filePoster;
    private final String UPLOAD_DIRECTORY_BANNER = "resources\\client\\images\\banner";
    private final String UPLOAD_DIRECTORY_POSTER = "resources\\client\\images\\poster";

    public MusicSportMB() {
        musicSport = new MusicSports();
    }

    public String loadFormCreateNew() {
        resetForm();
        musicSport.setMusicSportID(setIDMS());
        return "create?faces-redirect=true";
    }

    public String loadFormEdit(String id) {
        MusicSports ms = musicSportsFacade.find(id);
        setMusicSport(ms);
        calendar.setTime(ms.getStartDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        ms.setStartDate(endDate);
        return "edit?faces-redirect=true";
    }

    public String createNew() {
        try {
            if (fileBanner != null && filePoster != null) {
                MusicSports m = new MusicSports();
                m.setMusicSportID(setIDMS());
                if (musicSport.getCapacity() < 0) {
                    noticeCapacity = "Capacity must be greater than 0!";
                    return "create?faces-redirect=true";
                } else {
                    m.setMusicSportName(musicSport.getMusicSportName());
                    m.setStartDate(musicSport.getStartDate());
                    m.setStartTime(musicSport.getStartTime());
                    m.setAddress(musicSport.getAddress());
                    m.setCapacity(musicSport.getCapacity());
                    m.setInformation(musicSport.getInformation());
                    m.setType(musicSport.getType());

                    m.setBanner(uploadFileBanner());
                    m.setPoster(uploadFilePoster());
                    musicSportsFacade.create(m);

                    resetForm();

                    return "index?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            notice = "alert('Please fill in full required information!');";
            return "create?faces-redirect=true";
        }
        return "index?faces-redirect=true";
    }

    public String showDetails(String id) {
        MusicSports ms = musicSportsFacade.find(id);
        setMusicSport(ms);
        calendar.setTime(ms.getStartDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        ms.setStartDate(endDate);
        return "details?faces-redirect=true";
    }

    public String edit(String id) {

        try {
            MusicSports m = musicSportsFacade.find(id);
            m.setMusicSportName(musicSport.getMusicSportName());
            m.setStartDate(musicSport.getStartDate());
            m.setStartTime(musicSport.getStartTime());
            m.setAddress(musicSport.getAddress());
            m.setCapacity(musicSport.getCapacity());
            m.setInformation(musicSport.getInformation());
            m.setType(musicSport.getType());

            if (fileBanner != null) {
                deleteFileBanner(m.getBanner());

                m.setBanner(uploadFileBanner());

            } else {
                m.setBanner(m.getBanner());

            }
            if (filePoster != null) {

                deleteFilePoster(m.getPoster());

                m.setPoster(uploadFilePoster());
            } else {

                m.setPoster(m.getPoster());
            }
            musicSportsFacade.edit(m);

            resetForm();

            return "index?faces-redirect=true";
        } catch (Exception e) {
            notice = "alert('Please fill in full required information!');";
            return "create?faces-redirect=true";
        }
    }

    public String delete(String id) {
        try {
            MusicSports m = musicSportsFacade.find(id);
            musicSportsFacade.remove(m);
            deleteFileBanner(m.getBanner());
            deleteFilePoster(m.getPoster());

            return "index?faces-redirect=true";
        } catch (Exception e) {
            notice = "alert('An error has occurred!');";
            return "index?faces-redirect=true";
        }
    }

    public void resetForm() {
        musicSport.setBanner(null);
        musicSport.setCapacity(null);
        musicSport.setAddress(null);
        musicSport.setInformation(null);
        musicSport.setMusicSportName(null);
        musicSport.setPoster(null);
        musicSport.setStartDate(null);
        musicSport.setStartTime(null);
        musicSport.setType(null);
    }

    public String setIDMS() {
        String id = "";
        String year = (calendar.get(Calendar.YEAR) + "").substring(2);
        try {
            if (year.equals((musicSportsFacade.getLastID()).substring(3, 5))) {
                String character = (musicSportsFacade.getLastID()).substring(0, 5);
                int number = Integer.parseInt((musicSportsFacade.getLastID().substring(5))) + 1;
                id = character + (String.format("%04d", number));
            } else {
                id = "MST" + year + "0001";
            }
        } catch (Exception ex) {
            id = "MST" + year + "0001";
        }
        return id;
    }

    public String uploadFilePoster() {
        String fileName = "";
        if (filePoster != null) {
            InputStream content = null;
            try {
                Date date = new Date();
                fileName = filePoster.getSubmittedFileName().substring(0, filePoster.getSubmittedFileName().lastIndexOf("."));
                String extension = filePoster.getSubmittedFileName().substring(filePoster.getSubmittedFileName().lastIndexOf("."), filePoster.getSubmittedFileName().length());
                fileName = fileName + date.getDate() + date.getMonth() + date.getYear() + date.getHours() + date.getMinutes() + date.getSeconds() + date.getTimezoneOffset() + extension;

                content = filePoster.getInputStream();
                // Write content to disk or DB.
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext ec = context.getExternalContext();
                HttpServletRequest request = (HttpServletRequest) ec.getRequest();

                // gets absolute path of the web application
                String applicationPath = request.getServletContext().getRealPath("");

                // constructs path of the directory to save uploaded file
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY_POSTER;

                // creates the save directory if it does not exists
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(uploadFilePath + File.separator + fileName);
                    content = filePoster.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = content.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }

                } catch (Exception e) {
                    e.toString();
                    //fileName = "";
                } finally {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (content != null) {
                        content.close();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(MovieMB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    content.close();
                } catch (IOException ex) {
                    Logger.getLogger(MovieMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return fileName;

    }

    public String uploadFileBanner() {
        String fileName = "";
        if (fileBanner != null) {
            InputStream content = null;
            try {
                Date date = new Date();
                fileName = fileBanner.getSubmittedFileName().substring(0, fileBanner.getSubmittedFileName().lastIndexOf("."));
                String extension = fileBanner.getSubmittedFileName().substring(fileBanner.getSubmittedFileName().lastIndexOf("."), fileBanner.getSubmittedFileName().length());
                fileName = fileName + date.getDate() + date.getMonth() + date.getYear() + date.getHours() + date.getMinutes() + date.getSeconds() + date.getTimezoneOffset() + extension;

                content = fileBanner.getInputStream();
                // Write content to disk or DB.
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext ec = context.getExternalContext();
                HttpServletRequest request = (HttpServletRequest) ec.getRequest();

                // gets absolute path of the web application
                String applicationPath = request.getServletContext().getRealPath("");

                // constructs path of the directory to save uploaded file
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY_BANNER;

                // creates the save directory if it does not exists
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(uploadFilePath + File.separator + fileName);
                    content = fileBanner.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = content.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }

                } catch (Exception e) {
                    e.toString();
                    //fileName = "";
                } finally {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (content != null) {
                        content.close();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(MovieMB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    content.close();
                } catch (IOException ex) {
                    Logger.getLogger(MovieMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return fileName;

    }

    //delete banner
    public void deleteFileBanner(String s) {
        if (fileBanner != null) {
            InputStream content = null;
            try {
                content = fileBanner.getInputStream();
                // Write content to disk or DB.
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext ec = context.getExternalContext();
                HttpServletRequest request = (HttpServletRequest) ec.getRequest();

                // gets absolute path of the web application
                String applicationPath = request.getServletContext().getRealPath("");

                // constructs path of the directory to save uploaded file
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY_BANNER;

                // creates the save directory if it does not exists
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(uploadFilePath + File.separator + s);
                    outputFilePath.delete();
                } catch (Exception e) {
                    e.toString();
                    //fileName = "";
                } finally {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (content != null) {
                        content.close();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(MovieMB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    content.close();
                } catch (IOException ex) {
                    Logger.getLogger(MovieMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    //delete poster
    public void deleteFilePoster(String s) {
        if (filePoster != null) {
            InputStream content = null;
            try {
                content = filePoster.getInputStream();
                // Write content to disk or DB.
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext ec = context.getExternalContext();
                HttpServletRequest request = (HttpServletRequest) ec.getRequest();

                // gets absolute path of the web application
                String applicationPath = request.getServletContext().getRealPath("");

                // constructs path of the directory to save uploaded file
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY_POSTER;

                // creates the save directory if it does not exists
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(uploadFilePath + File.separator + s);
                    outputFilePath.delete();
                } catch (Exception e) {
                    e.toString();
                    //fileName = "";
                } finally {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (content != null) {
                        content.close();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(MovieMB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    content.close();
                } catch (IOException ex) {
                    Logger.getLogger(MovieMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public String showDetailMS(String id) {
        MusicSports ms = musicSportsFacade.find(id);
        setMusicSport(ms);
        calendar.setTime(ms.getStartDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        ms.setStartDate(endDate);
        return "details?faces-redirect=true";
    }

    public String showDetailoutMS(String id) {
        MusicSports ms = musicSportsFacade.find(id);
        setMusicSport(ms);
        calendar.setTime(ms.getStartDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        ms.setStartDate(endDate);
        return "/client/musicsports/details?faces-redirect=true";
    }

    public String showDetailoutMS_Guest(String id) {
        MusicSports ms = musicSportsFacade.find(id);
        setMusicSport(ms);
        calendar.setTime(ms.getStartDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        ms.setStartDate(endDate);
        return "/guest/musicsports/details?faces-redirect=true";
    }
    public List<MusicSports> showAll() {
        List<MusicSports> list = musicSportsFacade.findAll();
        for (MusicSports ms : list) {
            calendar.setTime(ms.getStartDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            ms.setStartDate(endDate);
        }
        return list;
    }

    public List<MusicSports> showAllMusics() {
        List<MusicSports> list = musicSportsFacade.showAllMusics();
        for (MusicSports ms : list) {
            calendar.setTime(ms.getStartDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            ms.setStartDate(endDate);
        }
        return list;
    }

    public List<MusicSports> show6Newest() {
        List<MusicSports> list = musicSportsFacade.show6Newest();
        for (MusicSports ms : list) {
            calendar.setTime(ms.getStartDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            ms.setStartDate(endDate);
        }
        return list;
    }

    public List<MusicSports> showAllSports() {
        List<MusicSports> list = musicSportsFacade.showAllSports();
        for (MusicSports ms : list) {
            calendar.setTime(ms.getStartDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            ms.setStartDate(endDate);
        }
        return list;
    }

    public MusicSports getMusicSport() {
        return musicSport;
    }

    public void setMusicSport(MusicSports musicSport) {
        this.musicSport = musicSport;
    }

    public Part getFileBanner() {
        return fileBanner;
    }

    public void setFileBanner(Part fileBanner) {
        this.fileBanner = fileBanner;
    }

    public Part getFilePoster() {
        return filePoster;
    }

    public void setFilePoster(Part filePoster) {
        this.filePoster = filePoster;
    }

    public String getNoticeCapacity() {
        return noticeCapacity;
    }

    public void setNoticeCapacity(String noticeCapacity) {
        this.noticeCapacity = noticeCapacity;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}
