package org.mcbx.test.hibernate.test;

import cfg.logger.LogSetProperties;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mcbx.test.hibernate.test.entity.Authors;
import org.mcbx.test.hibernate.test.service.AuthorsService;

public class Create {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Create.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);

    static SessionFactory factory;
    static Session session;
    static Transaction trx;

    public static void main(String[] args) {
        try {
//------------------------------------------------------------------------------ 1
            Authors author = new Authors("Jojo", "Scooby", "09587931547", "Male");
            AuthorsService.addAuthors(author);
//------------------------------------------------------------------------------ 2
        } catch (Exception e) {
            LOG.error("EXCEPTION", e);
        }
    }

}
