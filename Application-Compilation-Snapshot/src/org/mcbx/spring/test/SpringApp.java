package org.mcbx.spring.test;

import cfg.logger.LogSetProperties;
import org.apache.log4j.Logger;

public class SpringApp {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SpringApp.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG); 

    public static void main(String[] args) { 
        try {
            LOG.info("TESTING SPRING");
        } catch (Exception e) {
            LOG.error("SQLEception", e);
        }
    }

}
