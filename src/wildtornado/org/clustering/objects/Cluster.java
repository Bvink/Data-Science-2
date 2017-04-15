package wildtornado.org.clustering.objects;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private Point centroid;
    private List<Point> points;

    public Cluster(Point centroid) {
        this.centroid = centroid;
        points = new ArrayList<Point>();
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void clear() {
        this.points.clear();
    }
}
