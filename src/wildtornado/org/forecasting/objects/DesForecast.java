package wildtornado.org.forecasting.objects;

import wildtornado.org.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DesForecast extends Forecast {

    private List<Double> smoothed;
    private List<Double> trend;
    private double beta;

    public DesForecast(List<Double> data, double a, double b) {
        smoothed = new ArrayList<Double>();
        trend = new ArrayList<Double>();
        forecast = new ArrayList<Double>();
        this.data = data;
        this.alpha = a;
        this.beta = b;
    }

    public List<Double> getSmoothed() {
        return smoothed;
    }

    public double getSmoothed(int i) {
        return smoothed.get(i);
    }

    public int getSmoothedSize() {
        return smoothed.size();
    }

    public void updateSmoothed(Double e) {
        smoothed.add(e);
    }

    public List<Double> getTrend() {
        return trend;
    }

    public double getTrend(int i) {
        return trend.get(i);
    }

    public int getTrendSize() {
        return trend.size();
    }

    public void updateTrend(Double e) {
        trend.add(e);
    }

    public double getBeta() {
        return beta;
    }

    //Smoothen an existing value.
    //As well as update all related fields.
    public void smoothen(int i) {
        switch (i) {
            case 0:
                updateSmoothed(null);
                updateTrend(null);
                updateForecast(null);
                break;
            case 1:
                updateSmoothed(getData(i));
                updateTrend(getData(i) - getData(i - 1));
                updateForecast(null);
                break;
            default:
                updateSmoothed(calculateSmoothing(i));
                updateTrend(calculateTrend(i));
                updateForecast(calculateForecast(i));
                updateError(Math.pow(getData(i) - getForecast(i), 2));
                break;
        }
    }

    //Predict the value next value in a data set
    public void predict(int i) {
        if (i == getDataSize()) {
            forecast.add(getSmoothed(i - 1) + getTrend(i - 1));
        } else {
            updateForecast(calculateUpdate(i));
        }
    }

    //Calculate st by combining the alpha times the current value and 1-alpha times the previous smoothing combined with the trend.
    private double calculateSmoothing(int i) {
        return alpha * getData(i) + (1 - alpha) * (getSmoothed(i - 1) + getTrend(i - 1));
    }

    //Calculate bt by combinating the beta times smoothed minus the previous smoothed and 1-beta times the previous trend.
    private double calculateTrend(int i) {
        return beta * (getSmoothed(i) - getSmoothed(i - 1)) + (1 - beta) * getTrend(i - 1);
    }

    //Calculate the forecast by adding the previous smoothed and previous trend.
    private double calculateForecast(int i) {
        return getSmoothed(i - 1) + getTrend(i - 1);
    }

    //Calculate the update by adding the previous smoothed to the difference between the current index and the smoothed size times the last trend.
    private double calculateUpdate(int i) {
        return getSmoothed(getSmoothedSize() - 1) + (i + 1 - getSmoothedSize()) * getTrend(getTrendSize() - 1);
    }

    //Returns the Standard Error value
    public double getStandardError() {
        return Math.sqrt(error / Util.getLength(getForecast()) - 2);
    }

}
