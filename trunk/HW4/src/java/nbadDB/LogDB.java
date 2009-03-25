package nbadDB;

import java.util.*;

public class LogDB {

    private static ArrayList<String> log = new ArrayList<String>();

    public static ArrayList<String> getLog() {
        return log;
    }

    public static void setMessage(String message) {
        Date now = Calendar.getInstance().getTime();

        log.add(now + " -- " + message);
    }

}
