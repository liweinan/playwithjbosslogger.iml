package io.weli;


import org.jboss.logmanager.LogManager;

import java.util.logging.Logger;

public class PlayWithJBossLogger {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
//        System.setProperty("java.util.logging.config.file", PlayWithJBossLogger.class.getResource("/").getPath() + "logging.jboss.properties");
        LogManager.getLogManager().readConfiguration(UndertowWithLogger.class.getClassLoader().getResourceAsStream("logging.jboss.properties"));
        Logger logger = Logger.getLogger("test");
        System.out.println(logger);
        logger.warning("前方高能！");
        logger.info("普通消息");
        logger.fine("调试消息");
    }
}
