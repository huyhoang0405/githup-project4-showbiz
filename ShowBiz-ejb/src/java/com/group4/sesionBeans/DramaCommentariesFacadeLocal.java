/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.DramaCommentaries;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface DramaCommentariesFacadeLocal {

    void create(DramaCommentaries dramaCommentaries);

    void edit(DramaCommentaries dramaCommentaries);

    void remove(DramaCommentaries dramaCommentaries);

    DramaCommentaries find(Object id);

    List<DramaCommentaries> findAll();

    List<DramaCommentaries> findRange(int[] range);

    int count();
    
}
