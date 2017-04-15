package wildtornado.org.forecasting.objects;

import java.util.ArrayList;
import java.util.List;

public class SesForecast extends Forecast {

    public SesForecast(List<Double> data, double a) {
        forecast = new ArrayList<Double>();
        this.data = data;
        this.alpha = a;
    }

    //Smoothen an existing value.
    public void smoothen(int i) {
        double res = sesCalculation(i);
        updateForecast(res);
        updateError(Math.pow(getData(i) - res, 2));
    }

    //Predict the value next value in a data set
    public void predict(int i) {
        try {
            updateForecast(sesCalculation(i));
        } catch (IndexOutOfBoundsException e) {
            updateForecast(getForecast(i - 1));
        }
    }

    //Calculate st by combining the alpha times the previous known value with the 1-alpha times the last forecasted value.
    private double sesCalculation(int i) {
        return alpha * getData(i - 1) + (1 - alpha) * getForecast(i - 1);
    }

    //Returns the Standard Error value
    public double getStandardError() {
        return Math.sqrt(error / (data.size() - 1));
    }

}
