package wildtornado.org.forecasting.objects;

import java.util.List;

public abstract class Forecast {
    protected List<Double> data;
    protected List<Double> forecast;
    protected double alpha;
    protected double error = 0;


    public List<Double> getData() {
        return data;
    }

    public Double getData(int i) {
        return data.get(i);
    }

    public int getDataSize() {
        return data.size();
    }

    public List<Double> getForecast() {
        return forecast;
    }

    public Double getForecast(int i) {
        return forecast.get(i);
    }

    public void updateForecast(Double e) {
        this.forecast.add(e);
    }

    public double getAlpha() {
        return alpha;
    }

    public double getError() {
        return error;
    }

    public void updateError(double error) {
        this.error += error;
    }
}
