package hr.fer.zemris.java.hw13.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * {@code PowerExcel} is a {@link HttpServlet} class that generates Excel
 * spreadsheet with numbers and their i-th power.
 * <p>
 * In order to use this servlet you need to provided 3 parameters: {@code a},
 * {@code b} and {@code n}. Parameters {@code a} and {@code b} must be integers
 * between -100(inclusive) and 100(inclusive) and parameter {@code n} must be an
 * integer between 1(inclusive) and 5(inclusive).
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "power", urlPatterns = { "/power" })
public class PowerExcel extends HttpServlet {

    /** Serial version UID */
    private static final long serialVersionUID = 5127870918020594736L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer a = getParameter(req, resp, "a", -100, 100);
        if (a == null)
            return;

        Integer b = getParameter(req, resp, "b", -100, 100);
        if (b == null)
            return;

        Integer n = getParameter(req, resp, "n", 1, 5);
        if (n == null)
            return;

        if (Integer.compare(a, b) > 0) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        HSSFWorkbook hwb = new HSSFWorkbook();

        for (int i = 1; i <= n; i++) {
            createSheet(hwb, a, b, i);
        }

        resp.setContentType("application/xls");
        resp.setHeader("Content-Disposition", "attachment; filename=powers.xls");

        hwb.write(resp.getOutputStream());
    }

    /**
     * Returns the parameter with specified name and checks if it's an integer
     * between minimum and maximum boundries.
     * 
     * @param req
     *            HTTP servlet request
     * @param resp
     *            HTTP servlet response
     * @param name
     *            name(key) of the parameter
     * @param min
     *            minimum value parameter can be
     * @param max
     *            maximum value parameter can be
     * @return parameter value
     * @throws ServletException
     *             if some servlet exception occurs
     * @throws IOException
     *             if some I/O exception occurs
     */
    private static Integer getParameter(HttpServletRequest req, HttpServletResponse resp, String name, int min, int max)
            throws ServletException, IOException {
        String str = req.getParameter(name);

        if (str == null) {
            req.getRequestDispatcher("/WEB-INF/pages/power_fail.jsp?name=" + name + "&cause=null").forward(req, resp);
            return null;
        }

        int number = 0;

        try {
            number = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            req.getRequestDispatcher("/WEB-INF/pages/power_fail.jsp?name=" + name + "&cause=nfe").forward(req, resp);
            return null;
        }

        if (number < min) {
            req.getRequestDispatcher("/WEB-INF/pages/power_fail.jsp?name=" + name + "&cause=tooSmall&bound=" + min)
                    .forward(req, resp);
            return null;
        } else if (number > max) {
            req.getRequestDispatcher("/WEB-INF/pages/power_fail.jsp?name=" + name + "&cause=tooBig&bound=" + max)
                    .forward(req, resp);
            return null;
        }

        return number;
    }

    /**
     * Creates a new sheet in provided {@code hwb} work book and writes
     * appropriate data to it.
     * 
     * @param hwb
     *            excel spreadsheet
     * @param a
     *            first number
     * @param b
     *            last number
     * @param power
     *            power of the number
     */
    private static void createSheet(HSSFWorkbook hwb, int a, int b, int power) {
        HSSFSheet sheet = hwb.createSheet("Sheet " + power);

        for (int i = 0; i <= b - a; i++) {
            HSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(i + a);
            row.createCell(1).setCellValue(Double.valueOf(Math.pow(i + a, power)).intValue());
        }
    }
}
