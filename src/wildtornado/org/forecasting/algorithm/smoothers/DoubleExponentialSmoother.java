package wildtornado.org.forecasting.algorithm.smoothers;

import wildtornado.org.forecasting.objects.DesForecast;

import java.util.List;

public class DoubleExponentialSmoother {

    private DesForecast desForecast;

    public DoubleExponentialSmoother(List<Double> data, double a, double b) {
        this.desForecast = new DesForecast(data, a, b);
    }

    //Execute the forecast by smoothing all the existing values,
    //And then predicting the next values until upper bound has been reached.
    public void forecast(int upperBound) {
        int size = desForecast.getDataSize();
        for (int i = 0; i < size; i++) {
            desForecast.smoothen(i);
        }
        predict(size, upperBound);
    }

    //Predict the next values until the upper bound has been reached.
    private void predict(int lowerbound, int upperbound) {
        for (int i = lowerbound; i <= upperbound - 1; i++) {
            desForecast.predict(i);
        }
    }

    //Return the forecast.
    public DesForecast getForecast() {
        return desForecast;
    }

}
