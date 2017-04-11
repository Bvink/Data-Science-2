package wildtornado.org.kmeans;

import wildtornado.org.kmeans.generators.ClusterGenerator;
import wildtornado.org.kmeans.generators.PointGenerator;
import wildtornado.org.kmeans.objects.ClusterCollection;
import wildtornado.org.kmeans.objects.Point;
import wildtornado.org.kmeans.util.PostProcessor;
import wildtornado.org.util.DataSetParser;

import java.util.Collections;
import java.util.List;

public class KMeansAssignment {

    public static final int CLUSTER_AMOUNT = 4;
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
