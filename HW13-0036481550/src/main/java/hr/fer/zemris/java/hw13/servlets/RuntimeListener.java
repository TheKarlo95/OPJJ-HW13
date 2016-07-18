package hr.fer.zemris.java.hw13.servlets;

import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * {@code RuntimeListener} writes the {@link LocalDateTime} everytime servlet
 * context gets initialized.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see ServletContextListener
 */
@WebListener
public final class RuntimeListener implements ServletContextListener {

    /** Context of servlet this listener listens to. */
    private ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();

        context.setAttribute("timeStarted", LocalDateTime.now());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        context.removeAttribute("timeStarted");
    }

}
