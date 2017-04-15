package wildtornado.org.clustering.util;

import wildtornado.org.clustering.generators.OfferGenerator;
import wildtornado.org.clustering.objects.Cluster;
import wildtornado.org.clustering.objects.ClusterCollection;
import wildtornado.org.clustering.objects.Offer;

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
        System.out.println("Clustering:");
        System.out.println("Sum of Squared Errors: " + best.getSumOfSquaredErrors());
    }

    private void writeSortedOffers(List<Offer> offers, PrintWriter writer) {
        Collections.sort(offers);
        for (Offer offer : offers) {
            writer.println(SPACING + offer.getName() + ", Value: " + offer.getValue());
        }
    }
}
