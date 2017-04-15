package wildtornado.org.clustering;

import wildtornado.org.clustering.generators.ClusterGenerator;
import wildtornado.org.clustering.generators.PointGenerator;
import wildtornado.org.clustering.objects.ClusterCollection;
import wildtornado.org.clustering.objects.Point;
import wildtornado.org.clustering.util.PostProcessor;
import wildtornado.org.util.DataSetParser;

import java.util.Collections;
import java.util.List;

public class Clustering {

    public static final int CLUSTER_AMOUNT = 4; //(int) Math.floor(Math.sqrt(100/2));
    private static final int ITERATIONS = 10;

    private DataSetParser dataSetParser = new DataSetParser("data/WineData.csv");

    public void init() {
        Object[][] dataSet = dataSetParser.getData();

        PointGenerator pointGenerator = new PointGenerator();
        List<Point> points = pointGenerator.create(dataSet);

        ClusterGenerator clusterGenerator = new ClusterGenerator(CLUSTER_AMOUNT, ITERATIONS, points);
        List<ClusterCollection> clusterCollections = clusterGenerator.create();

        ClusterCollection best = getBestClusterCollection(clusterCollections);

        PostProcessor postProcessor = new PostProcessor(best);
        postProcessor.print();
        postProcessor.Write();
    }

    //Find the cluster collection with the lowest SSE.
    private ClusterCollection getBestClusterCollection(List<ClusterCollection> clusterCollections) {
        Collections.sort(clusterCollections);
        return clusterCollections.get(0);
    }

}
