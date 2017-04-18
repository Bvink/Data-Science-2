package wildtornado.org.genetic.algorithm;

import wildtornado.org.genetic.objects.Individual;
import wildtornado.org.genetic.settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {

    private List<Individual> children = new ArrayList<Individual>();
    private PairSelector pairSelector = new PairSelector();
    private Crossover crossover = new Crossover();
    private Mutator mutator = new Mutator();

    private static final int ELITISM_COUNT = 2;


    public GeneticAlgorithm(List<Individual> population) {
        createPairings(population);
        if (Settings.ELITISM) {
            addElites(population);
        }
    }

    public List<Individual> getChildren() {
        return children;
    }

    //Create the children to be added to the new population.
    private void createPairings(List<Individual> population) {
        int pairings = calcPairings();

        for (int i = 0; i < pairings; i++) {
            List<Individual> pair = pairSelector.select(population);
            List<Individual> pairChildren = crossover.crossover(pair);
            for (Individual pairChild : pairChildren) {
                children.add(mutator.mutate(pairChild));
            }
        }
    }

    //Calculate how many pairings there will be.
    //This is dependant on Elitism, as to not increase the initial population size.
    private int calcPairings() {
        int pairings = Settings.POPULATION_SIZE / 2;
        if (Settings.ELITISM) {
            pairings--;
        }
        return pairings;
    }

    //Add, in this case, the two elites from the previous population to the new population.
    private void addElites(List<Individual> population) {
        Elitism elitism = new Elitism();
        children.addAll(elitism.getHighestXElites(population, ELITISM_COUNT));
    }
}
