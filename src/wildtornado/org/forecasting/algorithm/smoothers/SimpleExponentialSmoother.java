package wildtornado.org.forecasting.algorithm.smoothers;

import wildtornado.org.forecasting.algorithm.MovingAverage;
import wildtornado.org.forecasting.objects.SesForecast;

import java.util.List;

public class SimpleExponentialSmoother {

    private SesForecast forecastSes;
    private MovingAverage movingAverage = new MovingAverage();

    public SimpleExponentialSmoother(List<Double> data, double a) {
        this.forecastSes = new SesForecast(data, a);
    }

    public void forecast(int upperBound) {
        int size = forecastSes.getDataSize();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                forecastSes.updateForecast(movingAverage.get(forecastSes, 12));
                continue;
            }
            forecastSes.smoothen(i);
        }
        predict(size, upperBound);
    }

    private void predict(int lowerbound, int upperbound) {
        for (int i = lowerbound; i <= upperbound - 1; i++) {
            forecastSes.predict(i);
        }
    }

    public SesForecast getForecast() {
        return forecastSes;
    }
}
