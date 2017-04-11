package wildtornado.org.kmeans.objects;

public class Offer implements Comparable {

    private String name;
    private double value;

    public Offer(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public void addValue(double i) {
        value += i;
    }

    @Override
    public int compareTo(Object o) {
        Offer that = (Offer) o;
        if (this.getValue() > that.getValue()) return -1;
        if (this.getValue() < that.getValue()) return 1;
        return 0;
    }
}
