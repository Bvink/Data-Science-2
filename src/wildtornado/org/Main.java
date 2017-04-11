package wildtornado.org;

import wildtornado.org.geneticalgorithm.GeneticAlgorithmAssignment;
import wildtornado.org.kmeans.KMeansAssignment;

public class Main {

    public static void main(String[] args) {

        GeneticAlgorithmAssignment ga = new GeneticAlgorithmAssignment();
        ga.init();

        KMeansAssignment kma = new KMeansAssignment();
        kma.init();
    }
}
