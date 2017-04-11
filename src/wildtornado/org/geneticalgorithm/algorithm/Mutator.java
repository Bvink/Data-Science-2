package wildtornado.org.geneticalgorithm.algorithm;

import wildtornado.org.geneticalgorithm.objects.Individual;
import wildtornado.org.geneticalgorithm.settings.Settings;

import java.util.Random;

public class Mutator {

    //Mutate an individuals gene at a set percentage chance.
    public Individual mutate(Individual ind) {
        Random rand = new Random();
        int[] DNA = ind.getDNA();

        for (int i = 0; i < DNA.length; i++) {
            if (rand.nextDouble() < Settings.MUTATION_RATE) {
                DNA[i] = DNA[i] ^ 1;
            }
        }
        return new Individual(DNA);
    }
}
