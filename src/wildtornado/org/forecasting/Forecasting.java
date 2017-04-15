package wildtornado.org.forecasting;

import wildtornado.org.forecasting.algorithm.SmoothingFactor;
import wildtornado.org.forecasting.algorithm.smoothers.SimpleExponentialSmoother;
import wildtornado.org.forecasting.objects.SesForecast;
import wildtornado.org.util.DataSetParser;

import java.util.ArrayList;
import java.util.List;

public class Forecasting {

    private static final int UPPERBOUND = 48;

    List<Double> data = new ArrayList<Double>();
    private DataSetParser dataSetParser = new DataSetParser("data/Swords.csv");
    private SmoothingFactor smoothingFactor = new SmoothingFactor();

    public void init() {
        data = dataSetParser.getValueList(1);

        forecastSES();
    }

    private void forecastSES() {
        double alpha = smoothingFactor.getBestSesSmoothingFactor(data);
        SimpleExponentialSmoother SES = new SimpleExponentialSmoother(data, alpha);
        SES.forecast(UPPERBOUND);
        SesForecast forecast = SES.getForecast();
    }

    private void forecastDES() {
        double[] factors = smoothingFactor.getBestDesSmoothingFactors(data);
    }

}
