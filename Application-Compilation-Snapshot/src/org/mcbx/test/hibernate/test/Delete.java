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
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import cfg.logger.LogSetProperties;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mcbx.test.hibernate.test.entity.Authors;
import org.mcbx.test.hibernate.test.service.AuthorsService;

public class Delete {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Delete.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);

    static SessionFactory factory;
    static Session session;
    static Transaction trx;

    public static void main(String[] args) {
        try {
//------------------------------------------------------------------------------ 1 
            Authors author = new Authors("Jojo", "Scooby", "09587931547", "Male");
            AuthorsService.addAuthors(author);
            AuthorsService.deleteAuthors(author);
//------------------------------------------------------------------------------ 2
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
    }
/*
//------------------------------------------------------------------------------ DELETE OBJECT VIA ID
    public static void deleteData_Authors(Authors authorsObj) {
        if (!isNull(authorsObj)) {
            int authorsID = authorsObj.getIdauthors();
            if (isEntityIDExist(Authors.class, authorsID)) {
                session.createQuery("delete from Authors where idauthors=" + authorsID).executeUpdate();
                System.out.println("AUTHORS ID: " + authorsID + " IS DELETED");
                session.getTransaction().commit();
            }
        }
    }

//------------------------------------------------------------------------------ DELETE OBJECTS VIA LIST OF INSTANCE
    public static void deleteData_Authors(List<Authors> authorsObj) {
        if (!isNull(authorsObj)) {
            for (Authors list : authorsObj) {
                if (isEntityIDExist(Authors.class, list.getIdauthors())) {
                    session.createQuery("delete from Authors where idauthors=" + list.getIdauthors()).executeUpdate();
                }
            }
            session.getTransaction().commit();
        }

    }

//------------------------------------------------------------------------------ DISPLAY OBJECTS INFO VIA LIST OF OF INSTANCE
    private static void displayAuthorsList(List<Authors> authorsList) {
        if (!isNull(authorsList)) {
            for (Authors list : authorsList) {
//                if (isEntityIDExist(Authors.class, list.getIdauthors())) {
                System.out.println(list.toString());
//                }
            }
        }
    }

//------------------------------------------------------------------------------ RETURN BOOLEAN
    private static boolean isEntityIDExist(Class<Authors> entityClass, int classID) {
        openSession();
        return session.find(entityClass, classID) != null;
    }

//------------------------------------------------------------------------------ START SESSION
    private static void openSession() {
        session = factory.getCurrentSession();
        if (!trx.isActive()) {
            trx = session.beginTransaction();
            LOG.info("Begin Transaction: [" + trx.getStatus() + "]");
        } else {
            LOG.warn("Transaction is already: [" + trx.getStatus() + "]");
        }
    }

//------------------------------------------------------------------------------ TESTING
    private static void testAddDelete() {
        System.out.println("CREATE NEW AUTHORS DATA-----------------------------");
        int count = 1;
        Authors author1 = new Authors(count++ + "", "lastname", "contactnumber", "Male");
        session.save(author1);
        session.getTransaction().commit();

        openSession();
        Authors author2 = new Authors(count++ + "", "lastname", "contactnumber", "Male");
        session.save(author2);
        session.getTransaction().commit();

        openSession();
        Authors author4 = new Authors(count++ + "", "lastname", "contactnumber", "Male");
        session.save(author4);
        session.getTransaction().commit();

        List<Authors> lisa = new ArrayList<>();
        lisa.add(author1);
        lisa.add(author2);
        lisa.add(author4);

        System.out.println("DISPLAY NEW CREATED DATA----------------------------");
        openSession();
        displayAuthorsList(lisa);

        System.out.println("DELETE NEW CREATED DATA-----------------------------");
        openSession();
        deleteData_Authors(lisa);
    }
*/
}
