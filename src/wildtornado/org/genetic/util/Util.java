package wildtornado.org.genetic.util;

import wildtornado.org.genetic.objects.Individual;
import wildtornado.org.genetic.settings.Settings;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Util {

    public static double averageFitness(List<Individual> population) {
        double sum = 0;
        for (Individual ind : population) {
            sum += ind.getFitness();
        }
        return (sum / Settings.POPULATION_SIZE);
    }

    public static Individual bestIndividual(List<Individual> population) {
        Collections.sort(population);
        return population.get(0);
    }

    //Generate a random double.
    public static double randomDouble(double upperBound) {
        Random rng = new Random();
        return rng.nextDouble() * upperBound;
    }

}
