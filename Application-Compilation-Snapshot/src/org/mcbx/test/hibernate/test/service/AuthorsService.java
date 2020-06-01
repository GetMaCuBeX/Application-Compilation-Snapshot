package org.mcbx.test.hibernate.test.service;

import cfg.hibernate.ConfigHibernate;
import cfg.logger.LogSetProperties;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mcbx.test.hibernate.test.entity.Authors;
import org.springframework.stereotype.Service;

@Service
public class AuthorsService {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AuthorsService.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);

    private final static Class ENTITY_CLASS = Authors.class;
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
                LOG.warn("TRANSACTION, STATUS: [" + trx.getStatus() + "]");
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
            LOG.error("HIBERNATE EXCEPTION, OPEN SESSION EXCEPTION", e);
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

//------------------------------------------------------------------------------ CREATE OBJECT
    public static void addAuthors(Authors obj) {
        try {
            createSessionFactory();
            session.save(obj);
            LOG.info("TRANSACTION COMMIT, CREATED OBJECT: ID [" + obj.getIdauthors() + "]");
            trx.commit();
            if ("COMMITTED".equals(trx.getStatus().toString())) {
                LOG.info("OBJECT SAVED TO DATABASE: ID [" + obj.getIdauthors() + "]");
                LOG.warn("TRANSACTION, STATUS: [" + trx.getStatus() + "]");
            } else {
                LOG.error("TRANSACTION WAS !COMMITTED, STATUS: [" + trx.getStatus() + "]");
            }
        } catch (Exception e) {
            LOG.error("ERROR SAVE: " + obj.toString(), e);
            if (trx != null) {
                trx.rollback();
                LOG.warn("TRANSACTION: [" + trx.getStatus() + "]");
            }
        } finally {
            closeSessionFactory();
        }
    }

//------------------------------------------------------------------------------ READ OBJECT
    public static Authors readAuthors(int objID) {
        try {
            createSessionFactory();
            Authors obj = session.get(Authors.class, objID);
            LOG.info("FINDING OBJECT: ID [" + objID + "]");
            trx.commit();
            if (obj != null) {
                LOG.info("OBJECT FOUND: ID [" + objID + "]");
            } else {
                LOG.warn("OBJECT !FOUND: ID [" + objID + "]");
            }
            return obj;
        } catch (Exception e) {
            LOG.error("EXCEPTION", e);
            if (trx != null) {
                trx.rollback();
                LOG.warn("TRANSACTION: [" + trx.getStatus() + "]");
            }
        } finally {
            closeSessionFactory();
        }
        LOG.warn("OBJECT !FOUND: ID [" + objID + "]");
        return null;
    }

//------------------------------------------------------------------------------ RETURN OBJECT LIST
    public static List<Authors> readAuthorsList(Query query) {
        try {
            createSessionFactory();
            LOG.info("EXECUTE NAMED QUERY");
            LOG.warn(query.getQueryString());
            @SuppressWarnings("unchecked")
            List<Authors> objList = query.getResultList();
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

//------------------------------------------------------------------------------ UPDATE OBJECT
    public static void updateAuthors(Authors obj) {
        try {
            createSessionFactory();
            session.update(obj);
            LOG.info("TRANSACTION COMMIT, UPDATED OBJECT: ID [" + obj.getIdauthors() + "]");
            trx.commit();
            if ("COMMITTED".equals(trx.getStatus().toString())) {
                LOG.info("OBJECT UPDATED: ID [" + obj.getIdauthors() + "]");
                LOG.warn("TRANSACTION, STATUS: [" + trx.getStatus() + "]");
            } else {
                LOG.error("TRANSACTION WAS !COMMITTED, STATUS: [" + trx.getStatus() + "]");
            }
        } catch (Exception e) {
            LOG.error("ERROR UPDAET: ID [" + obj.getIdauthors() + "]");
            if (trx != null) {
                trx.rollback();
                LOG.warn("TRANSACTION: [" + trx.getStatus() + "]");
            }
        } finally {
            closeSessionFactory();
        }
    }

//------------------------------------------------------------------------------ DELETE OBJECT
    public static void deleteAuthors(Authors obj) {
        try {
            createSessionFactory();
            session.delete(obj);
            LOG.info("TRANSACTION COMMIT, DELETING OBJECT: ID [" + obj.getIdauthors() + "]");
            trx.commit();
            LOG.info("OBJECT DELETED: ID [" + obj.getIdauthors() + "]");
            LOG.warn("TRANSACTION: [" + trx.getStatus() + "]");
        } catch (Exception e) {
            LOG.error("ERROR DELETE: ID [" + obj.getIdauthors() + "]");
            if (trx != null) {
                trx.rollback();
                LOG.warn("TRANSACTION: [" + trx.getStatus() + "]");
            }
        } finally {
            closeSessionFactory();
        }
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
    public static boolean isCreateQuery(String query) {
        try {
            createSessionFactory();
            LOG.info("EXECUTE QUERY");
            LOG.warn(query);
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
            return session.find(Authors.class, objID) != null;
        } catch (Exception e) {
            LOG.error("EXCEPTION", e);
        } finally {
            closeSessionFactory();
        }
        return false;
    }

}
