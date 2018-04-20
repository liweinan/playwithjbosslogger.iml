package io.weli;


import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class PlayWithJBossLogger {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
        System.out.println(java.util.logging.Logger.getLogger("test"));
        Logger logger = java.util.logging.Logger.getLogger("test");
        logger.addHandler(new ConsoleHandler());
        logger.warning("前方高能！");
    }
}
