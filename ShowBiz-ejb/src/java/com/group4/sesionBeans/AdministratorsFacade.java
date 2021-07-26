/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group4.sesionBeans;

import com.group4.entities.Administrators;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
@Stateless
public class AdministratorsFacade extends AbstractFacade<Administrators> implements AdministratorsFacadeLocal {

    @PersistenceContext(unitName = "ShowBiz-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorsFacade() {
        super(Administrators.class);
    }

    @Override
    public boolean login(String un, String pw) {
        Query query = em.createQuery("SELECT a FROM Administrators a WHERE a.adminUsername = :un AND a.password = :pw");
        query.setParameter("un", un);
        query.setParameter("pw", pw);
        List<Administrators> list = query.getResultList();
        return list.size() > 0;
    }
}
