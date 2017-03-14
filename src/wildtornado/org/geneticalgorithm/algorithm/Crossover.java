package wildtornado.org.geneticalgorithm.algorithm;

import wildtornado.org.geneticalgorithm.objects.Individual;
import wildtornado.org.geneticalgorithm.settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Crossover {

    //Perform uniform crossover to create children from two parents.
    //Uniform crossover is performed by randomly taking bits from either parents.
    public List<Individual> uniformCrossover(List<Individual> parents) {
        Random rand = new Random();
        if(rand.nextDouble() < Settings.CROSSOVER_RATE) {
            List<Individual> children = new ArrayList<Individual>();
            int[] parentOneDNA = parents.get(0).getDNA();
            int[] parentTwoDNA = parents.get(1).getDNA();

            int[] childOneDNA = new int[Settings.DNA_SIZE];
            int[] childTwoDNA = new int[Settings.DNA_SIZE];
            for (int i = 0; i < Settings.DNA_SIZE; i++) {
                int chance = rand.nextInt(2);
                switch (chance) {
                    case 0:
                        childOneDNA[i] = parentOneDNA[i];
                        childTwoDNA[i] = parentTwoDNA[i];
                        break;
                    case 1:
                        childOneDNA[i] = parentTwoDNA[i];
                        childTwoDNA[i] = parentOneDNA[i];
                }
            }
            children.add(new Individual(childOneDNA));
            children.add(new Individual(childTwoDNA));
            return children;
        }
        return parents;
    }

}
