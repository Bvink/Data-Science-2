package wildtornado.org.forecasting.objects;

import java.util.ArrayList;
import java.util.List;

public class DesForecast extends Forecast{

    private List<Double> smoothedSequence;
    private List<Double> trend;
    private double beta;

    public DesForecast(List<Double> data, double a, double b) {
        smoothedSequence = new ArrayList<Double>();
        trend = new ArrayList<Double>();
        forecast = new ArrayList<Double>();
        this.data = data;
        this.alpha = a;
        this.beta = b;
    }

    public List<Double> getSmoothedSequence() {
        return smoothedSequence;
    }

    public void updateSmoothedSequence(Double e) {
        smoothedSequence.add(e);
    }

    public List<Double> getTrend() {
        return trend;
    }

    public void updateTrend(Double e) {
        trend.add(e);
    }

    public double getBeta() {
        return beta;
    }

}
