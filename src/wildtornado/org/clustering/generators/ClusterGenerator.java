package wildtornado.org.clustering.generators;

import wildtornado.org.clustering.objects.Cluster;
import wildtornado.org.clustering.objects.ClusterCollection;
import wildtornado.org.clustering.objects.Point;
import wildtornado.org.clustering.util.EuclideanDistance;
import wildtornado.org.clustering.util.RandomIntegers;

import java.util.ArrayList;
import java.util.List;

public class ClusterGenerator {

    private int maximumIterations;
    private int currentIteration;
    private double sumOfSquaredErrors;
    private int setSize;
    private List<Point> points;
    private List<Cluster> clusters;
    private List<ClusterCollection> clusterCollections;

    private final EuclideanDistance euclideanDistance = new EuclideanDistance();
    private final RandomIntegers randomIntegers = new RandomIntegers();

    //Initialize the generator and assign each cluster its initial centroid.
    public ClusterGenerator(int clusterAmount, int maximumIterations, List<Point> points) {
        clusterCollections = new ArrayList<ClusterCollection>();
        clusters = new ArrayList<Cluster>();
        this.maximumIterations = maximumIterations;
        this.points = points;
        this.setSize = points.get(0).size();
        initRandomCentroids(clusterAmount);

        sumOfSquaredErrors = 0d;
    }

    //Initiate x amount of clusters with unique centroids.
    private void initRandomCentroids(int amountOfClusters) {
        clusters.clear();
        Integer[] arr = randomIntegers.rng(points.size());
        for (int i = 0; i < amountOfClusters; i++) {
            clusters.add(new Cluster(points.get(arr[i])));
        }
    }

    //Main loop of the program.
    //Assigns all points to the original cluster, after which it's saved.
    //Afterwards, loops until the maximum amount of iterations has been reached.
    //The centroids are recalculated for each cluster collection after the initial.
    public List<ClusterCollection> create() {
        assignPoints();
        ClusterCollection clusterCollection = generateClusterCollection();
        clusterCollections.add(clusterCollection);
        if (currentIteration < maximumIterations) {
            recalculateCentroids();
            cleanup();
            create();
        }
        return clusterCollections;
    }

    //Clears out the clusters, sets SSE back to 0 and increments the current iteration.
    private void cleanup() {
        clearClusters();
        sumOfSquaredErrors = 0d;
        currentIteration++;
    }

    //Assign all points to their closest cluster.
    private void assignPoints() {
        for (Point point : points) {
            assignToClosestCluster(point);
        }
    }

    //Assigns a single point to the cluster it is closest to.
    private void assignToClosestCluster(Point point) {
        Cluster closestCluster = null;
        double lowestDistance = Double.MAX_VALUE;
        double distance;

        for (Cluster cluster : clusters) {
            distance = euclideanDistance.calculate(point, cluster.getCentroid());
            if (distance < lowestDistance) {
                closestCluster = cluster;
                lowestDistance = distance;
            }
        }
        if (closestCluster != null) {
            closestCluster.addPoint(point);
            sumOfSquaredErrors += Math.pow(lowestDistance, 2);
        }
    }

    //Recalculate all clusters' centroids.
    private void recalculateCentroids() {
        for (Cluster cluster : clusters) {
            recalculateCentroid(cluster);
        }
    }

    //Recalculate a single cluster's centroid.
    private void recalculateCentroid(Cluster cluster) {
        Point centroid = new Point("Centroid");
        double sum;

        for (int i = 0; i < setSize; i++) {
            sum = 0d;
            for (Point point : cluster.getPoints()) {
                sum += point.get(i);
            }
            centroid.addOffer(sum / setSize);
        }
        cluster.setCentroid(centroid);
    }

    //Removes all current points from a cluster.
    private void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    //Puts all the current relevant information inside a cluster collection.
    //Before cleaning up and moving on to the next one.
    private ClusterCollection generateClusterCollection() {
        ClusterCollection clusterCollection = new ClusterCollection();
        clusterCollection.addClusters(clusters);
        clusterCollection.setSumOfSquaredErrors(sumOfSquaredErrors);
        return clusterCollection;
    }
}
