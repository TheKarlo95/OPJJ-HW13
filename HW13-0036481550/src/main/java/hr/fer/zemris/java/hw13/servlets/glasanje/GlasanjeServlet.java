package hr.fer.zemris.java.hw13.servlets.glasanje;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code GlasanjeServlet} is a {@link HttpServlet} class that reads and
 * prepares data about nominees and redirects to JSP file that does the rest.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "glasanje", urlPatterns = "/glasanje")
public class GlasanjeServlet extends HttpServlet {

    /** Serial version UID */
    private static final long serialVersionUID = 931402056533826761L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String fileName = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");

        List<GlasanjeUtil.Nominee> nominees = GlasanjeUtil.readNominees(fileName);

        req.setAttribute("nominees", nominees);
        req.getRequestDispatcher("/WEB-INF/pages/glasanjeIndex.jsp").forward(req, resp);
    }

}