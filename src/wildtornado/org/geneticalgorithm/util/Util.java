package wildtornado.org.geneticalgorithm.util;

import wildtornado.org.geneticalgorithm.objects.Individual;
import wildtornado.org.geneticalgorithm.settings.Settings;

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
