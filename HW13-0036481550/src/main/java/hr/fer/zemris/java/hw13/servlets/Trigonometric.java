package hr.fer.zemris.java.hw13.servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code Trigonometric} is a {@link HttpServlet} class that calculates sine and
 * cosine for values(in degrees) between parameters {@code a}(inclusive) and {@code b}
 * (inclusive) and shows it to user in a HTML table.
 * <p>
 * If any of the parameter {@code a} is omitted it will be set to default value
 * of 0 and if parameter {@code b} is omitted it will be set to default value of
 * 360.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "trigonometric", urlPatterns = { "/trigonometric" })
public class Trigonometric extends HttpServlet {

    /** Serial version UID */
    private static final long serialVersionUID = -4260318320609554857L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String aString = req.getParameter("a");
        String bString = req.getParameter("b");

        int a = Integer.valueOf(aString != null ? aString : "0");
        int b = Integer.valueOf(bString != null ? bString : "360");

        if (Integer.compare(a, b) > 0) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        if (b > a + 720) {
            b = a + 720;
        }

        List<TrigValues> values = new ArrayList<>();

        for (int i = a; i <= b; i++) {
            values.add(new TrigValues(i));
        }

        req.setAttribute("values", values);
        req.getRequestDispatcher("/WEB-INF/pages/trigonometric.jsp").forward(req, resp);
    }

    /**
     * {@code TrigValues} is a class that calculates sine and cosine for any
     * given {@code x} value.
     * 
     * @author Karlo Vrbić
     * @version 1.0
     */
    public static class TrigValues {
        
        /** Formatter for formatting numbers to six decimals. */
        private static final DecimalFormat FORMAT = new DecimalFormat("#.######");

        /** Value from which sine and cosine will be calculated. */
        private int x;

        /**
         * Constructs a new {@code Function} with specified {@code x}.
         * 
         * @param x
         *            value from which sine and cosine will be calculated.
         */
        public TrigValues(int x) {
            this.x = x;
        }

        /**
         * Returns the value from which sine and cosine will be calculated.
         * 
         * @return the value from which sine and cosine will be calculated
         */
        public String getX() {
            return FORMAT.format(x);
        }

        /**
         * Returns the value of sine function at the specified x.
         * 
         * @return the value of sine function at the specified x
         */
        public String getSin() {
            return FORMAT.format(Math.sin(Math.toRadians(x)));
        }

        /**
         * Returns the value of cosine function at the specified x.
         * 
         * @return the value of cosine function at the specified x
         */
        public String getCos() {
            return FORMAT.format(Math.cos(Math.toRadians(x)));
        }

    }

}