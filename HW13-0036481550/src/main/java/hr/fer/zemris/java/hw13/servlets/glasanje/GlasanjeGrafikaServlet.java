package hr.fer.zemris.java.hw13.servlets.glasanje;

import java.awt.Color;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 * {@code CreateImage} is a {@link HttpServlet} class that generates 3d pie
 * chart image of vote count.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 * @see HttpServlet
 */
@WebServlet(name = "glasanje-grafika", urlPatterns = { "/glasanje-grafika" })
public class GlasanjeGrafikaServlet extends HttpServlet {

    /** Serial version UID */
    private static final long serialVersionUID = -1758457747813761483L;

    /** Background color of the pie chart. */
    private static final Paint BACKGROUND_COLOR = new Color(0xFF, 0xFF, 0xFF, 0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nomineesPath = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
        String fileName = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
        List<GlasanjeUtil.Nominee> nominees = GlasanjeUtil.readNomineeVotes(nomineesPath, fileName);

        JFreeChart chart = createChart(createDataset(nominees), "");
        BufferedImage image = chart.createBufferedImage(400, 400);

        resp.setContentType("image/png");
        ImageIO.write(image, "png", resp.getOutputStream());
    }

    /**
     * Creates and returns the set of data that will be displayed on the chart.
     * 
     * @param nominees
     *            list of nominees that will displayed in chart
     * @return the set of data that will be displayed on the chart
     */
    private static PieDataset createDataset(List<GlasanjeUtil.Nominee> nominees) {
        DefaultPieDataset result = new DefaultPieDataset();

        nominees.forEach(n -> {
            result.setValue(n.getName(), n.getNumOfVotes());
        });

        return result;
    }

    /**
     * Creates and returns the 3d pie chart from provided dataset and title.
     * 
     * @param dataset
     *            data to be displayed on chart
     * @param title
     *            the chart title
     * @return the 3d pie chart
     */
    private static JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                title,
                dataset,
                true,
                true,
                false);

        chart.setBackgroundPaint(BACKGROUND_COLOR);
        chart.setBackgroundImageAlpha(0f);

        chart.getTitle().setBackgroundPaint(BACKGROUND_COLOR);

        chart.getLegend().setBackgroundPaint(BACKGROUND_COLOR);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setBackgroundPaint(BACKGROUND_COLOR);
        plot.setBackgroundAlpha(0f);

        return chart;
    }
}
