package wildtornado.org.clustering.objects;

import java.util.Vector;

public class Point {

    private String name;
    private Vector<Double> offers;

    public Point(String name) {
        this.name = name;
        offers = new Vector<>();
    }

    public String getName() {
        return name;
    }

    public Vector<Double> getOffers() {
        return offers;
    }

    public void addOffer(double i) {
        this.offers.add(i);
    }

    public double get(int i) {
        return this.offers.get(i);
    }

    public int size() {
        return this.offers.size();
    }

}
