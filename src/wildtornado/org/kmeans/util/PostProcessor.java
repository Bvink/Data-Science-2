package wildtornado.org.kmeans.util;

import wildtornado.org.kmeans.generators.OfferGenerator;
import wildtornado.org.kmeans.objects.Cluster;
import wildtornado.org.kmeans.objects.ClusterCollection;
import wildtornado.org.kmeans.objects.Offer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class PostProcessor {

    private ClusterCollection best;
    private OfferGenerator offerGenerator;

    public static final String LOCATION = "res/";
    public static final String SPACING = "\t";


    public PostProcessor(ClusterCollection best) {
        this.best = best;
        offerGenerator = new OfferGenerator();
    }

    public void Write() {
        try {
            PrintWriter writer = new PrintWriter(LOCATION + "K-Means", "UTF-8");
            int count = 1;
            for (Cluster cluster : best.getClusters()) {
                writer.println("Cluster: " + count++);
                List<Offer> offers = offerGenerator.create(cluster);
                writeSortedOffers(offers, writer);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("K Means:");
        System.out.println("Sum of Squared Errors: " + best.getSumOfSquaredErrors());
    }

    private void writeSortedOffers(List<Offer> offers, PrintWriter writer) {
        Collections.sort(offers);
        for (Offer offer : offers) {
            writer.println(SPACING + offer.getName() + ", Value: " + offer.getValue());
        }
    }
}
