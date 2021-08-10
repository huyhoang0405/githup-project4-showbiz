/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.manageBean;

import com.group4.entities.Categories;
import com.group4.entities.MovieGenres;
import com.group4.entities.MovieGenresPK;
import com.group4.entities.Movies;
import com.group4.sesionBeans.CategoriesFacadeLocal;
import com.group4.sesionBeans.MovieGenresFacadeLocal;
import com.group4.sesionBeans.MoviesFacadeLocal;
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
@Named(value = "movieMB")
@SessionScoped
public class MovieMB implements Serializable {

    @EJB
    private MovieGenresFacadeLocal movieGenresFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private MoviesFacadeLocal moviesFacade;

    private MovieGenres movieGenres;
    private Categories category;
    private Movies movie;
    private Part fileBanner;
    private Part filePoster;
    private final String UPLOAD_DIRECTORY_BANNER = "resources\\client\\images\\banner";
    private final String UPLOAD_DIRECTORY_POSTER = "resources\\client\\images\\poster";
    private String noticeLength;
    private String notice;

    private String[] categoryID;

    private final Calendar calendar = Calendar.getInstance();

    public MovieMB() {
        movie = new Movies();
        movieGenres = new MovieGenres();
    }

    //display all movies
    public List<Movies> showAllMovies() {
        List<Movies> list = moviesFacade.findAll();
        for (Movies o : list) {
            calendar.setTime(o.getReleaseDate());
            calendar.roll(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            o.setReleaseDate(endDate);
        }

        return list;
    }

    //reset form
    public void resetForm() {
        movie.setMovieName(null);
        movie.setLength(null);
        movie.setStarring(null);
        movie.setReleaseDate(null);
        movie.setContent(null);
        movie.setCountry(null);
        movie.setLanguage(null);
        movie.setDirector(null);
        movie.setTrailer(null);
        movie.setPoster(null);
        movie.setBanner(null);
        movie.setNote(null);
        setCategoryID(null);

    }

    //load form
    public String loadFormCreateNew() {

        resetForm();
//        FacesContext context = FacesContext.getCurrentInstance();
//                ExternalContext ec = context.getExternalContext();
//                HttpServletRequest request = (HttpServletRequest) ec.getRequest();
//                String applicationPath = request.getServletContext().getRealPath("");
//                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY_POSTER;
        movie.setMovieID(setID());
//        movie.setMovieName(uploadFilePath);
        return "create?faces-redirect=true";
    }

    //create a movie
    public String createNewMovie() {
        try {
            if (fileBanner != null && filePoster != null) {
                Movies m = new Movies();
                m.setMovieID(setID());
                m.setMovieName(movie.getMovieName());
                m.setStarring(movie.getStarring());
                m.setReleaseDate(movie.getReleaseDate());
                m.setContent(movie.getContent());
                m.setCountry(movie.getCountry());
                m.setLanguage(movie.getLanguage());
                m.setDirector(movie.getDirector());
                m.setTrailer(movie.getTrailer());
                m.setNote(movie.getNote());

                if (movie.getLength() < 0) {
                    noticeLength = "Length must be greater than 0";
                    return "create?faces-redirect=true";
                } else {
                    m.setLength(movie.getLength());
                    m.setBanner(uploadFileBanner());
                    m.setPoster(uploadFilePoster());
                    moviesFacade.create(m);
                }

                for (String c : categoryID) {
                    int ca = Integer.parseInt(c);
                    MovieGenresPK mgPK = new MovieGenresPK(ca, m.getMovieID());
                    MovieGenres mg = new MovieGenres();
                    mg.setMovieGenresPK(mgPK);
                    mg.setNote(movieGenres.getNote());
                    movieGenresFacade.create(mg);
                }

                resetForm();

                return "index?faces-redirect=true";
            } else {
                notice = "alert('Please fill in full required information!');";
                return "create?faces-redirect=true";
            }

        } catch (Exception e) {
            notice = "alert('Please fill in full required information!');";
            return "create?faces-redirect=true";
        }
    }

    //show details
    public String showDetailsMovie(String id) {
        Movies m = moviesFacade.find(id);
        setMovie(m);
        calendar.setTime(m.getReleaseDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        m.setReleaseDate(endDate);
        List<MovieGenres> list = movieGenresFacade.findByIDMovieID(m.getMovieID());
        String[] arrCa = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrCa[i] = list.get(i).getCategories().getCategoryName();
        }
        setCategoryID(arrCa);
        return "details?faces-redirect=true";
    }

    //load form edit
    public String loadFormEdit(String id) {

        Movies m = moviesFacade.find(id);
        setMovie(m);
        calendar.setTime(m.getReleaseDate());
        calendar.roll(Calendar.DATE, 1);
        Date endDate = calendar.getTime();
        m.setReleaseDate(endDate);
        List<MovieGenres> list = movieGenresFacade.findByIDMovieID(m.getMovieID());
        if (!list.isEmpty()) {

            String[] arrCa = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                try {
                    arrCa[i] = (categoriesFacade.find((list.get(i)).getMovieGenresPK().getCategoryID())).getCategoryName();
                } catch (Exception ex) {
                    return "create?faces-redirect=true";
                }
            }
            setCategoryID(arrCa);
            return "edit?faces-redirect=true";

        } else {

            return "edit?faces-redirect=true";
        }

    }

    //edit movie
    public String editMovie(String id) {
        try {
            Movies m = moviesFacade.find(id);
            m.setMovieName(movie.getMovieName());
            m.setStarring(movie.getStarring());

            m.setReleaseDate(movie.getReleaseDate());
            m.setContent(movie.getContent());
            m.setCountry(movie.getCountry());
            m.setLanguage(movie.getLanguage());
            m.setDirector(movie.getDirector());
            m.setTrailer(movie.getTrailer());
            m.setNote(movie.getNote());

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
            if (movie.getLength() < 0) {
                noticeLength = "Length must be greater than 0";
                return "create?faces-redirect=true";
            } else {
                m.setLength(movie.getLength());
                moviesFacade.edit(m);
            }

            for (String c : categoryID) {
                int ca = Integer.parseInt(c);
                MovieGenresPK mgPK = new MovieGenresPK(ca, m.getMovieID());
                MovieGenres mg = new MovieGenres();
                mg.setMovieGenresPK(mgPK);
                mg.setNote(movieGenres.getNote());
                movieGenresFacade.create(mg);
            }
            resetForm();

            return "index?faces-redirect=true";
        } catch (Exception e) {

        }
        return "index?faces-redirect=true";
    }

    //delete movie
    public String deleteMovie(String id) {
        try {
            Movies m = moviesFacade.find(id);
            moviesFacade.remove(m);
            deleteFileBanner(m.getBanner());
            deleteFilePoster(m.getPoster());

            return "index?faces-redirect=true";
        } catch (Exception e) {
            notice = "alert('You can't delete it. An error has occurred!');";
            return "index?faces-redirect=true";
        }

    }

    //upload banner
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

    //upload poster
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

    public String setID() {
        String movieID = "";
        String year = (calendar.get(Calendar.YEAR) + "").substring(2);
        try {
            if (year.equals((moviesFacade.getLastID()).substring(3, 5))) {
                String character = (moviesFacade.getLastID()).substring(0, 5);
                int number = Integer.parseInt((moviesFacade.getLastID().substring(5))) + 1;
                movieID = character + (String.format("%04d", number));
            } else {
                movieID = "MOV" + year + "0001";
            }
        } catch (Exception e) {
            movieID = "MOV" + year + "0001";
        }

        return movieID;
    }

    //show all Categories
    public List<Categories> showAllCategories() {
        return categoriesFacade.findAll();
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
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

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String[] getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String[] categoryID) {
        this.categoryID = categoryID;
    }

    public MovieGenres getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(MovieGenres movieGenres) {
        this.movieGenres = movieGenres;
    }

    public String getNoticeLength() {
        return noticeLength;
    }

    public void setNoticeLength(String noticeLength) {
        this.noticeLength = noticeLength;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}
