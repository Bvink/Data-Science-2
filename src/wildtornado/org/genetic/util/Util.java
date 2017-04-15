package wildtornado.org.genetic.util;

import wildtornado.org.genetic.objects.Individual;
import wildtornado.org.genetic.settings.Settings;

import java.util.Collections;
import java.util.List;

public class Util {

    public double averageFitness(List<Individual> population) {
        double sum = 0;
        for (Individual ind : population) {
            sum += ind.getFitness();
        }
        return (sum / Settings.POPULATION_SIZE);
    }

    public Individual bestIndividual(List<Individual> population) {
        Collections.sort(population);
        return population.get(0);
    }

}
