package org.logtest.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.debug("Single line DEBUG");
        LOG.debug("MultiLine DEBUG \n second line \n third line");

        LOG.info("Single line INFO");
        LOG.info("MultiLine INFO \n second line \n third line");

        LOG.error("Single line ERROR");
        LOG.error("MultiLine ERROR \n second line \n third line");

        LOG.warn("Single line WARNING");
        LOG.warn("MultiLine WARNING \n second line \n third line");
    }
}
