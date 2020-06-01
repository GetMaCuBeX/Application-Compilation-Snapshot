/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mcbx.test.hibernate.test;

/**
 *
 * @author MaCuBeX
 */
import cfg.hibernate.ConfigHibernate;
import java.util.List;
import static java.util.Objects.isNull;
import cfg.logger.LogSetProperties;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.mcbx.test.hibernate.test.entity.Authors;
import org.mcbx.test.hibernate.test.entity.Members;
import org.mcbx.test.hibernate.test.service.AuthorsService;
import org.mcbx.test.hibernate.test.service.MembersService;

public class Read {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Read.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);

    static SessionFactory factory;
    static Session session;
    static Transaction trx;

    public static void main(String[] args) {
        try {
//------------------------------------------------------------------------------ 1
            Authors author = new Authors("Jojo", "Scooby", "09587931547", "Male");
            AuthorsService.addAuthors(author);
            System.out.println(author.toString());
//------------------------------------------------------------------------------ 2
//            int id = 3;
//            System.out.println(AuthorsService.isExist(id));
//            System.out.println(AuthorsService.readAuthors(id).toString());
//------------------------------------------------------------------------------ 3
//            Query query = AuthorsService.getCreateNamedQuery("Authors.findByGender");
//            query.setParameter("gender", "Female");
//            List<Authors> objList = AuthorsService.readAuthorsList(query);
//            displayResultList(objList);
//------------------------------------------------------------------------------ 4 
//            boolean query = AuthorsService.isCreateQuery("SELECT a FROM Authors a WHERE a.idauthors = 1");
//            System.out.println("Return: " + query);
//------------------------------------------------------------------------------ 5
//            Query query = MembersService.getCreateNamedQuery("Members.findAll");
//            List<Members> objList = MembersService.readMembersList(query);
//            displayResultList(objList);
//------------------------------------------------------------------------------ 6
        } catch (Exception e) {
            LOG.error("EXCEPTION", e);
        }
    }

//------------------------------------------------------------------------------ DISPLAY OBJECT LIST
    private static void displayResultList(List<?> list) {
        if (!list.isEmpty()) {
            LOG.info("DISPLAY RESULT LIST");
            list.forEach((obj) -> {
                System.out.println(obj.toString());
            });
        } else {
            LOG.warn("EMPTY RESULT");
        }
    }

}
