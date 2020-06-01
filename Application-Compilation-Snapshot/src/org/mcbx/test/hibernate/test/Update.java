package org.mcbx.test.hibernate.test;

import cfg.logger.LogSetProperties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mcbx.test.hibernate.test.entity.Authors;
import org.mcbx.test.hibernate.test.service.AuthorsService;

public class Update {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Update.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);

    static SessionFactory factory;
    static Session session;
    static Transaction trx;
    static int randomNumber = getRandomNumber(100, 1);

    public static void main(String[] args) {
        try {
//------------------------------------------------------------------------------ 1
            Authors author = new Authors("Jojo", "Scooby", "09587931547", "Male");
            AuthorsService.addAuthors(author);
            author.setFirstname("New update test");
            author.setContactinformation(randomNumber + "");
            AuthorsService.updateAuthors(author);
//------------------------------------------------------------------------------ 2 
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

//------------------------------------------------------------------------------ GET RANDOM NUMBER   
    private static int getRandomNumber(int min, int max) {
        int range = max - min + 1;
        int ran = (int) (Math.random() * range) + min;
        return ran;
    }
}
