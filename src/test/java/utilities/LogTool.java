package utilities;

import org.apache.log4j.Logger;

public class LogTool {

    private static Logger LogTool = Logger.getLogger(LogTool.class.getName());

    public static void startLog(String testClassName) {
        LogTool.info("Testing Started");
    }

    public static void endLog(String testClassName) {
        LogTool.info("Testing Finished");
    }

    public static void info(String message) {
        LogTool.info(message);
    }

    public static void warn(String message) {
        LogTool.warn(message);
    }

    public static void error(String message) {
        LogTool.error(message);
    }

    public static void fatal(String message) {
        LogTool.fatal(message);
    }

    public static void debug(String message) {
        LogTool.debug(message);
    }
}
