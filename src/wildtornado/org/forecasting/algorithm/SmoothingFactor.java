package wildtornado.org.forecasting.algorithm;

import wildtornado.org.forecasting.algorithm.smoothers.DoubleExponentialSmoother;
import wildtornado.org.forecasting.algorithm.smoothers.SimpleExponentialSmoother;
import wildtornado.org.forecasting.objects.Forecast;

import java.util.List;

public class SmoothingFactor {

    //Find the best smoothing factor alpha for SES.
    public double getBestSesSmoothingFactor(List<Double> data) {
        double bestAlpha = 0d;
        double lowestSesError = Double.MAX_VALUE;

        for (double alpha = 0.005; alpha < 1; alpha += 0.005) {
            SimpleExponentialSmoother SES = new SimpleExponentialSmoother(data, alpha);
            SES.forecast(0);
            Forecast f = SES.getForecast();
            if (f.getError() < lowestSesError) {
                lowestSesError = f.getError();
                bestAlpha = alpha;
            }
        }

        return bestAlpha;
    }

    //Find the best smoothing factors alpha and bet for DES.
    public double[] getBestDesSmoothingFactors(List<Double> data) {
        double bestAlpha = 0d;
        double bestBeta = 0d;
        double lowestDesError = Double.MAX_VALUE;

        for (double alpha = 0.005; alpha < 1; alpha += 0.005) {
            for (double beta = 0.005; alpha < 1; alpha += 0.005) {
                DoubleExponentialSmoother DES = new DoubleExponentialSmoother(data, alpha, beta);
                DES.forecast(0);
                Forecast f = DES.getForecast();
                if (f.getError() < lowestDesError) {
                    lowestDesError = f.getError();
                    bestAlpha = alpha;
                    bestBeta = beta;
                }
            }
        }
        return new double[]{bestAlpha, bestBeta};
    }
}
