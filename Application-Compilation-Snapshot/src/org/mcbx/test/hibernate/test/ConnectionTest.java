package org.mcbx.test.hibernate.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import cfg.logger.LogSetProperties;
import org.apache.log4j.Logger;

public class ConnectionTest {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ConnectionTest.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);

    public static void main(String[] args) throws Exception { 
        try {
            LOG.warn("Initializing Connection...");
            String jdbcUrl = "jdbc:mysql://localhost:3306/booksreservation?useSSL=false";
            String user = "root";
            String pass = "";
            LOG.info("Connecting to database: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            LOG.info("Database Connected! " + myConn.toString());
        } catch (SQLException e) {
            LOG.error("SQLEception", e);  
        }
    }

}
