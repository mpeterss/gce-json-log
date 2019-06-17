package org.logtest.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.debug("Single line debug");
        LOG.debug("MultiLine debug \n second line \n third line");

        LOG.info("Single line debug");
        LOG.info("MultiLine debug \n second line \n third line");

        LOG.error("Single line debug");
        LOG.error("MultiLine debug \n second line \n third line");

        LOG.warn("Single line warn");
        LOG.warn("MultiLine warn \n second line \n third line");
    }
}
