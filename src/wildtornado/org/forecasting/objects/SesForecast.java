package wildtornado.org.forecasting.objects;

import java.util.ArrayList;
import java.util.List;

public class SesForecast extends Forecast {

    public SesForecast(List<Double> data, double a) {
        forecast = new ArrayList<Double>();
        this.data = data;
        this.alpha = a;
    }

    public void smoothen(int i) {
        double result = alpha * getData(i - 1) + (1 - alpha) * getForecast(i - 1);
        updateForecast(result);
        updateError(Math.pow(getData(i) - result, 2));
    }

    public void predict(int i) {
        try {
            double value = alpha * getData(i - 1) + (1 - alpha) * getForecast(i - 1);
            updateForecast(value);
        } catch (IndexOutOfBoundsException e) {
            updateForecast(getForecast(i - 1));
        }
    }

}
