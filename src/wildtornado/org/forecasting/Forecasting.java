package wildtornado.org.forecasting;

import wildtornado.org.forecasting.algorithm.SmoothingFactor;
import wildtornado.org.forecasting.algorithm.smoothers.DoubleExponentialSmoother;
import wildtornado.org.forecasting.algorithm.smoothers.SimpleExponentialSmoother;
import wildtornado.org.forecasting.objects.DesForecast;
import wildtornado.org.forecasting.objects.Forecast;
import wildtornado.org.forecasting.objects.SesForecast;
import wildtornado.org.forecasting.util.Plotter;
import wildtornado.org.util.DataSetParser;

import java.util.ArrayList;
import java.util.List;

public class Forecasting {

    private static final int UPPERBOUND = 48;

    List<Double> data = new ArrayList<Double>();
    private DataSetParser dataSetParser = new DataSetParser("data/Swords.csv");
    private SmoothingFactor smoothingFactor = new SmoothingFactor();

    public void init() throws Exception {
        System.out.println("Forecasting:");
        data = dataSetParser.getValueList(1);
        Plotter plotter = new Plotter(data, forecastSES(), forecastDES());
        plotter.plot();
    }


    private Forecast forecastSES() {
        double alpha = smoothingFactor.getBestSesSmoothingFactor(data);
        SimpleExponentialSmoother SES = new SimpleExponentialSmoother(data, alpha);
        SES.forecast(UPPERBOUND);
        SesForecast forecast = SES.getForecast();
        System.out.println("SES Forecasting:");
        System.out.println("Alpha: " + forecast.getAlpha());
        System.out.println("Standard Error Value: " + forecast.getStandardError());
        return forecast;
    }

    private Forecast forecastDES() {
        double[] factors = smoothingFactor.getBestDesSmoothingFactors(data);
        DoubleExponentialSmoother DES = new DoubleExponentialSmoother(data, factors[0], factors[1]);
        DES.forecast(UPPERBOUND);
        DesForecast forecast = DES.getForecast();
        System.out.println("DES Forecasting:");
        System.out.println("Alpha: " + forecast.getAlpha()+ ", Beta: " + forecast.getBeta());
        System.out.println("Standard Error Value: " + forecast.getStandardError());
        return forecast;
    }


}
