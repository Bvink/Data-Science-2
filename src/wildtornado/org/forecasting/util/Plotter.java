package wildtornado.org.forecasting.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import wildtornado.org.forecasting.objects.Forecast;

import java.io.File;
import java.util.List;

public class Plotter {

    private List<Double> data;
    private Forecast SES;
    private Forecast DES;

    public Plotter(List<Double> data, Forecast SES, Forecast DES) {
        this.data = data;
        this.SES = SES;
        this.DES = DES;
    }

    public void plot() throws Exception {
        DefaultCategoryDataset lineChart = new DefaultCategoryDataset();

        displayOriginalData(lineChart);
        displayData(lineChart, SES, "SES");
        displayData(lineChart, DES, "DES");

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Swords Vs Demand", "Demand",
                "Swords",
                lineChart, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1280;    /* Width of the image */
        int height = 480;   /* Height of the image */
        File file = new File("res/Forecasting.jpeg");
        ChartUtilities.saveChartAsJPEG(file, lineChartObject, width, height);
    }


    private void displayOriginalData(DefaultCategoryDataset lineChart) {
        for (int i = 1; i <= data.size(); i++) {
            lineChart.addValue(data.get(i-1), "Original Data", i + "");
        }
    }

    private void displayData(DefaultCategoryDataset lineChart, Forecast forecast, String message) {
        int size = forecast.getForecastSize();
        for (int i = 1; i <= size; i++) {
            lineChart.addValue(forecast.getForecast(i-1), message + " forecast", i + "");
        }
    }
}
