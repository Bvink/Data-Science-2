package wildtornado.org.geneticalgorithm.algorithm;

import wildtornado.org.geneticalgorithm.objects.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Elitism {

    //Perform Elitism.
    //This is done by returning the (in this case) two individuals with the highest fitness.
    //Then ensuring that those are put back into the population.
    public List<Individual> getHighestTwoElites(List<Individual> population) {
        List<Individual> highestTwoElites = new ArrayList<Individual>();
        Collections.sort(population);
        for(int i = 0; i < 2; i++) {
            highestTwoElites.add(population.get(i));
        }
        return highestTwoElites;
    }
}
