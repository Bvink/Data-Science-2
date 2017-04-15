package wildtornado.org.forecasting.algorithm.smoothers;

import wildtornado.org.forecasting.objects.DesForecast;

import java.util.List;

public class DoubleExponentialSmoother {

    private DesForecast desForecast;

    public DoubleExponentialSmoother(List<Double> data, double a, double b) {
        this.desForecast = new DesForecast(data, a, b);
    }

    public void forecast(int upperBound) {

    }

    private void predict(int lowerbound, int upperbound) {
        for (int i = lowerbound; i <= upperbound - 1; i++) {
            desForecast.predict(i);
        }
    }

    public DesForecast getForecast() {
        return desForecast;
    }

}
