package wildtornado.org.geneticalgorithm.algorithm;

import wildtornado.org.geneticalgorithm.objects.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Elitism {

    //Perform Elitism.
    //This is done by returning the (in this case) two individuals with the highest fitness.
    //Then ensuring that those are put back into the population.
    public List<Individual> getHighestXElites(List<Individual> population, int x) {
        List<Individual> highestXElites = new ArrayList<Individual>();
        Collections.sort(population);
        for (int i = 0; i < x; i++) {
            highestXElites.add(population.get(i));
        }
        return highestXElites;
    }
}
