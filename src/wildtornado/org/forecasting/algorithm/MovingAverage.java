package wildtornado.org.forecasting.algorithm;

import wildtornado.org.forecasting.objects.Forecast;

public class MovingAverage {

    public double get(Forecast forecast, int k) {

        if(k <= forecast.getDataSize()) {
            double sum = 0.0;
            for (int i = 0; i < k; i++) {
                sum += forecast.getData(i);
            }

            return sum / k;
        }
        System.out.println("k cannot be bigger than the set size. ");
        return 0;
    }
}
