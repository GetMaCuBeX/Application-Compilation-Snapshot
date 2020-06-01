package cfg.logger;

public class LogSetProperties {

    private final String dir = "/cfg/logger/";

    public LogSetProperties(org.apache.log4j.Logger LOG) {
        org.apache.log4j.PropertyConfigurator.configure(getClass().getResource(dir + "log4j.properties"));
    }

    public LogSetProperties(java.util.logging.Logger LOG) {
    }

//------------------------------------------------------------------------------ RETURN COLORED STRING
//    public String debug(String message) {
//        return ConsoleColor.CYAN + message + ConsoleColor.RESET;
//    }
//
//    public String info(String message) {
//        return ConsoleColor.GREEN + message + ConsoleColor.RESET;
//    }
//
//    public String warn(String message) {
//        return ConsoleColor.YELLOW + message + ConsoleColor.RESET;
//    }
//
//    public String error(String message) {
//        return ConsoleColor.RED + message + ConsoleColor.RESET;
//    }
//
//    public String fatal(String message) {
//        return ConsoleColor.PURPLE + message + ConsoleColor.RESET;
//    }
}
