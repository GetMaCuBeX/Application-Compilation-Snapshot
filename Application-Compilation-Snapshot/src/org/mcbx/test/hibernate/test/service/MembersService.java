package org.mcbx.test.hibernate.test.service;

import cfg.hibernate.ConfigHibernate;
import cfg.logger.LogSetProperties;
import java.util.List; 
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mcbx.test.hibernate.test.entity.Members;

public class MembersService {



    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MembersService.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);
    
    
    private final static Class ENTITY_CLASS = Members.class;
    public static SessionFactory factory;
    public static Session session;
    public static Transaction trx;

//------------------------------------------------------------------------------ CREATE SESSION FACTORY
    public static void createSessionFactory() {
        try {
            if (factory == null || factory.isClosed()) {
                LOG.warn("SESSION FACTORY: [INIT]");
                factory = ConfigHibernate.createSessionFactory(ConfigHibernate.HIBERNATE_CONFIG, ENTITY_CLASS);
                session = factory.getCurrentSession();
                LOG.warn("SESSION FACTORY: [CREATED]");
                trx = session.beginTransaction();
                LOG.warn("TRANSACTION: [" + trx.getStatus() + "]");
            }
        } catch (HibernateException e) {
            LOG.error("HIBERNATE EXCEPTION, SESSION FACTORY FAILED", e);
        }
    }

//------------------------------------------------------------------------------ OPEN SESSION
    public static void openSession() {
        try {
            session = factory.getCurrentSession();
            if (!trx.isActive()) {
                trx = session.beginTransaction();
                LOG.warn("TRANSACTION, STATUS: [" + trx.getStatus() + "]");
            } else {
                LOG.warn("TRANSACTION IS ALREADY SET, STATUS: [" + trx.getStatus() + "]");
            }
        } catch (HibernateException e) {
            LOG.error("HIBERNATE EXCEPTION", e);
        }
    }

//------------------------------------------------------------------------------ CLOSE SESSION FACTORY
    public static void closeSessionFactory() {
        try {
            if (factory.isOpen()) {
                session.close();
                factory.close();
                LOG.warn("SESSION FACTORY: [CLOSED]");
                System.out.println();
            } else {
                LOG.warn("SESSION FACTORY IS ALREADY CLOSED, STATUS: [CLOSED]");
            }
        } catch (HibernateException e) {
            LOG.error("HIBERNATE EXCEPTION, CLOSING SESSION FACTORY EXCEPTION", e);
        }
    }

//------------------------------------------------------------------------------ RETURN OBJECT LIST
    public static List<Members> readMembersList(Query query) {
        try {
            createSessionFactory();
            LOG.info("EXECUTE QUERY");
            LOG.warn(query.getQueryString());
            @SuppressWarnings("unchecked")
            List<Members> objList = query.getResultList();
            return objList;
        } catch (Exception e) {
            LOG.error("EXCEPTION", e);
            if (trx != null) {
                trx.rollback();
                LOG.warn("TRANSACTION: [" + trx.getStatus() + "]");
            }
        } finally {
            closeSessionFactory();
        }
        return null;
    }

//------------------------------------------------------------------------------ RETURN QUERY
    public static Query getCreateNamedQuery(String query) {
        try {
            createSessionFactory();
            /*Query q = session.createNamedQuery(query);*/
            return session.createNamedQuery(query);
        } catch (Exception e) {
            LOG.error("EXCEPTION", e);
        }
        return null;
    }

//------------------------------------------------------------------------------ RETURN BOOLEAN
    public static boolean isFoundQuery(String query) {
        try {
            createSessionFactory();
            /*Query q = session.createQuery(query);*/
            return !session.createQuery(query).getResultList().isEmpty();
        } catch (Exception e) {
            LOG.error("EXCEPTION", e);
        } finally {
            closeSessionFactory();
        }
        return false;
    }

//------------------------------------------------------------------------------ RETURN BOOLEAN
    public static boolean isExist(int objID) {
        try {
            createSessionFactory();
            return session.find(Members.class, objID) != null;

        } catch (Exception e) {
            LOG.error("EXCEPTION", e);
        } finally {
            closeSessionFactory();
        }
        return false;
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
