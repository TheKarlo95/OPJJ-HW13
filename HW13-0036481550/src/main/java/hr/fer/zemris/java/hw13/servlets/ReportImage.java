package hr.fer.zemris.java.hw13.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code ReportImage} is a {@link HttpServlet} class that forawrds to JSP file
 * that displays the chart.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "reportImage", urlPatterns = { "/reportImage" })
public class ReportImage extends HttpServlet {

    /** Serial version UID */
    private static final long serialVersionUID = 4189345324687275293L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/reportImage.jsp").forward(req, resp);
    }

}
