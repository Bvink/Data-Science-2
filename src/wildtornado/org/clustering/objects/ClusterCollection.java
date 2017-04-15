package wildtornado.org.clustering.objects;

import java.util.ArrayList;
import java.util.List;

public class ClusterCollection implements Comparable {

    private List<Cluster> clusters = new ArrayList<Cluster>();
    private double sumOfSquaredErrors;

    public List<Cluster> getClusters() {
        return this.clusters;
    }

    public void addCluster(Cluster cluster) {
        this.clusters.add(cluster);
    }

    public void addClusters(List<Cluster> clusters) {
        for (Cluster cluster : clusters) {
            this.clusters.add(cluster);
        }
    }

    public double getSumOfSquaredErrors() {
        return sumOfSquaredErrors;
    }

    public void setSumOfSquaredErrors(double sumOfSquaredErrors) {
        this.sumOfSquaredErrors = sumOfSquaredErrors;
    }

    @Override
    public int compareTo(Object o) {
        ClusterCollection that = (ClusterCollection) o;
        if (this.getSumOfSquaredErrors() < that.getSumOfSquaredErrors()) return -1;
        if (this.getSumOfSquaredErrors() > that.getSumOfSquaredErrors()) return 1;
        return 0;
    }
}
