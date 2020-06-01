package org.mcbx.test.log4j;

import cfg.logger.LogSetProperties;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Level;

public class Log4jTest {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Log4jTest.class);
    private final static LogSetProperties lp = new LogSetProperties(LOG);

    public static void main(String[] args) {
        LOG.setLevel(Level.WARN);
        LOG.debug("This is debug : " + "parameter/exception object");
        LOG.info("This is info : " + "parameter/exception object");
        LOG.warn("This is warn : " + "parameter/exception object");
        LOG.error("This is error : " + "parameter/exception object");
        LOG.fatal("This is fatal : " + "parameter/exception object");
        try {
            Desktop desktop = Desktop.getDesktop();
            File dirToOpen = null;
            dirToOpen = new File("Desktop");
            desktop.open(dirToOpen);
        } catch (IOException e) {
            LOG.error("This is error : " + "parameter/exception object", e);
        }

    }

}
