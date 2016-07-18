package hr.fer.zemris.java.hw13.servlets.glasanje;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * {@code GlasanjeXlsServlet} is a {@link HttpServlet} class that generates Excel
 * spreadsheet with list of nominees and their vote count sorted by their vote
 * count.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "glasanje-xls", urlPatterns = { "/glasanje-xls" })
public class GlasanjeXlsServlet extends HttpServlet {

    /** Serial version UID */
    private static final long serialVersionUID = -2719354299915285757L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HSSFWorkbook hwb = new HSSFWorkbook();

        createSheet(req, hwb);

        resp.setContentType("application/xls");
        resp.setHeader("Content-Disposition", "attachment; filename=results.xls");

        hwb.write(resp.getOutputStream());
    }

    /**
     * Creates a new sheet in provided {@code hwb} work book and writes
     * appropriate data to it.
     * 
     * @param req
     *            HTTP servlet request
     * @param hwb
     *            excel spreadsheet
     */
    private void createSheet(HttpServletRequest req, HSSFWorkbook hwb) {
        String nomineesPath = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
        String fileName = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
        List<GlasanjeUtil.Nominee> nominees = GlasanjeUtil.readNomineeVotes(nomineesPath, fileName);

        nominees.sort((n1, n2) -> Integer.compare(n1.getNumOfVotes(), n2.getNumOfVotes()));

        HSSFSheet sheet = hwb.createSheet("Results");

        for (int i = 0, length = nominees.size(); i < length; i++) {
            HSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(nominees.get(i).getName());
            row.createCell(1).setCellValue(nominees.get(i).getNumOfVotes());
        }
    }

}
