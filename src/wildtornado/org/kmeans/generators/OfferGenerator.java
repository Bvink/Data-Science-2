package wildtornado.org.kmeans.generators;

import wildtornado.org.kmeans.objects.Cluster;
import wildtornado.org.kmeans.objects.Offer;
import wildtornado.org.kmeans.objects.Point;

import java.util.ArrayList;
import java.util.List;

public class OfferGenerator {

    public List<Offer> create(Cluster cluster) {
        List<Offer> offers = new ArrayList<Offer>();
        int setSize = cluster.getPoints().get(0).size();

        for (int i = 0; i < setSize; i++) {
            Offer offer = new Offer("Offer " + i, 0);
            for (Point point : cluster.getPoints()) {
                offer.addValue(point.get(i));
            }
            offers.add(offer);
        }

        return offers;
    }

}
