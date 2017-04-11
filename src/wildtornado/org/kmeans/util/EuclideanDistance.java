package wildtornado.org.kmeans.util;

import wildtornado.org.kmeans.objects.Point;

public class EuclideanDistance {

    public double calculate(Point pointOne, Point pointTwo) {
        double distance = 0;

        if (pointOne.size() == pointTwo.size()) {
            for (int i = 0; i < pointOne.size(); i++) {
                distance += distance(pointOne.get(i), pointTwo.get(i));
            }
        }

        return Math.sqrt(distance);
    }

    private double distance(double a, double b) {
        return Math.pow((a - b), 2);
    }
}
