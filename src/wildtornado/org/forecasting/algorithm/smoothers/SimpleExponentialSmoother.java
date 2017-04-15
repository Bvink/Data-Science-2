package wildtornado.org.forecasting.algorithm.smoothers;

import wildtornado.org.forecasting.algorithm.MovingAverage;
import wildtornado.org.forecasting.objects.SesForecast;

import java.util.List;

public class SimpleExponentialSmoother {

    private static final int MOVING_AVERAGE_K = 12;

    private SesForecast sesForecast;
    private MovingAverage movingAverage = new MovingAverage();

    public SimpleExponentialSmoother(List<Double> data, double a) {
        this.sesForecast = new SesForecast(data, a);
    }

    //Execute the forecast by smoothing all the existing values,
    //And then predicting the next values until upper bound has been reached.
    public void forecast(int upperBound) {
        int size = sesForecast.getDataSize();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                sesForecast.updateForecast(movingAverage.get(sesForecast, MOVING_AVERAGE_K));
                continue;
            }
            sesForecast.smoothen(i);
        }
        predict(size, upperBound);
    }

    //Predict the next values until the upper bound has been reached.
    private void predict(int lowerBound, int upperBound) {
        for (int i = lowerBound; i <= upperBound - 1; i++) {
            sesForecast.predict(i);
        }
    }

    //Return the forecast.
    public SesForecast getForecast() {
        return sesForecast;
    }
}
