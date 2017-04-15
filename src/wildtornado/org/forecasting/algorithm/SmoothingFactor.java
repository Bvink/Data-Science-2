package wildtornado.org.forecasting.algorithm;

import wildtornado.org.forecasting.algorithm.smoothers.SimpleExponentialSmoother;
import wildtornado.org.forecasting.objects.Forecast;

import java.util.List;

public class SmoothingFactor {

    public double getBestSesSmoothingFactor(List<Double> data) {
        double bestSmoothingFactor = 0.0;
        double lowestSesError = Double.MAX_VALUE;

        for (double i = 0.005; i < 1; i += 0.005) {
            SimpleExponentialSmoother SES = new SimpleExponentialSmoother(data, i);
            SES.forecast(0);
            Forecast f = SES.getForecast();
            if (f.getError() < lowestSesError) {
                lowestSesError = f.getError();
                bestSmoothingFactor = i;
            }
        }

        return bestSmoothingFactor;
    }
}
