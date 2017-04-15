package wildtornado.org.forecasting.algorithm;

import wildtornado.org.forecasting.objects.Forecast;

public class MovingAverage {

    //Calculate the moving average given a data set and k.
    public double get(Forecast forecast, int k) {
        double sum = 0d;
        int size = forecast.getDataSize();

        k = k <= size ? k : size;

        for (int i = 0; i < k; i++) {
            sum += forecast.getData(i);
        }

        return sum / k;
    }
}
