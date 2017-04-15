package wildtornado.org;

import wildtornado.org.clustering.Clustering;
import wildtornado.org.forecasting.Forecasting;
import wildtornado.org.genetic.Genetic;

public class Main {

    public static void main(String[] args) {

        Genetic g = new Genetic();
        g.init();

        Clustering c = new Clustering();
        c.init();

        Forecasting f = new Forecasting();
        try {
            f.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
