package hr.fer.zemris.java.hw13.servlets.glasanje;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code GlasanjeGlasajServlet} is a {@link HttpServlet} class that handles
 * user votes.
 * <p>
 * In order to use this servlet you need to provided parameter {@code id} which
 * indicates the id of the artist user voted for.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "glasanje-glasaj", urlPatterns = { "/glasanje-glasaj" })
public class GlasanjeGlasajServlet extends HttpServlet {

    /** Serial version UID */
    private static final long serialVersionUID = 6088801003461120273L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nomineesPath = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
        String fileName = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
        List<GlasanjeUtil.Nominee> nominees = GlasanjeUtil.readNomineeVotes(nomineesPath, fileName);

        String idString = req.getParameter("id");
        if (idString == null || idString.isEmpty())
            return;

        int index = Integer.parseInt(idString) - 1;
        nominees.get(index).incrementNumOfVotes();

        GlasanjeUtil.writeVotes(fileName, nominees);

        resp.sendRedirect(req.getContextPath() + "/glasanje-rezultati");
    }

}
