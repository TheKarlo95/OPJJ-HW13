package hr.fer.zemris.java.hw13.servlets.glasanje;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code GlasanjeRezultatiServlet} is a {@link HttpServlet} class that prepares
 * the data to be displayed on the JSP page that displays result of the votes.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "glasanje-rezultati", urlPatterns = { "/glasanje-rezultati" })
public class GlasanjeRezultatiServlet extends HttpServlet {

    /** Serial version UID */
    private static final long serialVersionUID = 282417096502872018L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nomineesPath = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
        String fileName = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");

        List<GlasanjeUtil.Nominee> nominees = GlasanjeUtil.readNomineeVotes(nomineesPath, fileName);
        List<GlasanjeUtil.Nominee> winners = getWinners(nominees);

        req.setAttribute("nominees", nominees);
        req.setAttribute("winners", winners);
        req.getRequestDispatcher("/WEB-INF/pages/glasanjeRez.jsp").forward(req, resp);
    }

    /**
     * Returns the winner of the votes.
     * <p>
     * <b>Note:</b> more than one winner can exist if they have the same number
     * of votes and therefore the list is returned
     * 
     * @param nominees list of nominees
     * @return list of winners
     */
    private List<GlasanjeUtil.Nominee> getWinners(List<GlasanjeUtil.Nominee> nominees) {
        nominees.sort((n1, n2) -> Integer.compare(n2.getNumOfVotes(), n1.getNumOfVotes()));

        List<GlasanjeUtil.Nominee> winners = new ArrayList<>();

        int maxVotes = nominees.get(0).getNumOfVotes();

        for (int i = 0, length = nominees.size(); i < length; i++) {
            if (maxVotes != nominees.get(i).getNumOfVotes())
                break;

            winners.add(nominees.get(i));
        }

        return winners;
    }

}
