package nbadDB;

import javax.servlet.*;

public class AdminStartStopDB implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        
        Admin admin = new Admin();

    }

    public void contextDestroyed(ServletContextEvent sce) {

        Admin.shutDownDB();

    }
}