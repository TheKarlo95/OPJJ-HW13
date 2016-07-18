package hr.fer.zemris.java.hw13.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code BackgroundColorSetter} is a {@link HttpServlet} class that sets the
 * background color to the one specified by parameter {@code backgroundColor}.
 * <p>
 * If parameter {@code backgroundColor} is omitted it will be set to default
 * value "white".
 * <p>
 * <b>Note:</b>For best experience choose "GRAY".
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "setBackgroundColor", urlPatterns = { "/setBackgroundColor" })
public class BackgroundColorSetter extends HttpServlet {

    /** Serial version UID */
    private static final long serialVersionUID = 1718556589221010966L;

    /** Default background color */
    public static final String DEFAULT_BACKGROUND = "white";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("backgroundColor");
        req.getSession().setAttribute("backgroundColor", color);
        resp.sendRedirect("index.jsp");
    }

}