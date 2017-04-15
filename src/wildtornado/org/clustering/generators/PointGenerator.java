package wildtornado.org.clustering.generators;

import wildtornado.org.clustering.objects.Point;
import wildtornado.org.util.Util;

import java.util.ArrayList;
import java.util.List;

public class PointGenerator {

    private List<Point> points;

    public PointGenerator() {
        this.points = new ArrayList<Point>();
    }

    public List<Point> create(Object[][] dataSet) {
        int setSize = dataSet[0].length;
        initialize(setSize);

        for (Object[] row : dataSet) {
            populate(row, setSize);
        }

        return points;
    }

    //Initialize every client and give 'm a name.
    private void initialize(int size) {
        for (int i = 1; i <= size; i++) {
            Point p = new Point("Client " + i);
            points.add(p);
        }
    }

    //Add all offers from a given row to their respective clients.
    private void populate(Object[] row, int size) {
        for (int i = 0; i < size; i++) {
            points.get(i).addOffer(Util.getVal(row[i]));
        }
    }
}
