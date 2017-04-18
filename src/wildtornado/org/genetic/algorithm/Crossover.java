package wildtornado.org.genetic.algorithm;

import wildtornado.org.genetic.constants.Constants;
import wildtornado.org.genetic.objects.Individual;
import wildtornado.org.genetic.settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Crossover {


    public List<Individual> crossover(List<Individual> parents) {
        Random rand = new Random();
        if (rand.nextDouble() <= Settings.CROSSOVER_RATE) {
            List<int[]> childDNA = new ArrayList<int[]>();
            childDNA.add(new int[Settings.DNA_SIZE]);
            childDNA.add(new int[Settings.DNA_SIZE]);

            switch (Settings.CROSSOVER_METHOD) {
                case Constants.SINGLE_POINT_CROSSOVER:
                    singlePointCrossover(parents, childDNA);
                    break;
                case Constants.DOUBLE_POINT_CROSSOVER:
                    twoPointCrossover(parents, childDNA);
                    break;
                default:
                case Constants.UNIFORM_CROSSOVER:
                    uniformCrossover(parents, childDNA, rand);
                    break;
            }

            List<Individual> children = new ArrayList<Individual>();
            children.add(new Individual(childDNA.get(0)));
            children.add(new Individual(childDNA.get(1)));
            return children;
        }
        return parents;
    }

    //Perform uniform crossover to create children from two parents.
    //Uniform crossover is performed by randomly taking bits from either parents.
    private void uniformCrossover(List<Individual> parents, List<int[]> childDNA, Random rand) {
        for (int i = 0; i < Settings.DNA_SIZE; i++) {
            boolean test = rand.nextInt(2) == 0;
            cross(parents, childDNA, i, test);
        }
    }

    //Perform single point crossover to create children from two parents.
    //Single point crossover is performed by taking bits from either parents based on the crossover point.
    private void singlePointCrossover(List<Individual> parents, List<int[]> childDNA) {
        for (int i = 0; i < Settings.DNA_SIZE; i++) {
            boolean test = i < Settings.SINGLE_POINT_CROSSOVER;
            cross(parents, childDNA, i, test);
        }
    }

    //Perform single point crossover to create children from two parents.
    //Single point crossover is performed by taking bits from either parents based on the crossover points.
    private void twoPointCrossover(List<Individual> parents, List<int[]> childDNA) {
        for (int i = 0; i < Settings.DNA_SIZE; i++) {
            boolean test = i < Settings.TWO_POINT_CROSSOVER[0] || i > Settings.TWO_POINT_CROSSOVER[1];
            cross(parents, childDNA, i, test);
        }
    }

    //Perform the crossover on a single bit of DNA.
    private void cross(List<Individual> parents, List<int[]> childDNA, int i, boolean test) {
        if (test) {
            childDNA.get(0)[i] = parents.get(0).getDNA()[i];
            childDNA.get(1)[i] = parents.get(1).getDNA()[i];
        } else {
            childDNA.get(0)[i] = parents.get(1).getDNA()[i];
            childDNA.get(1)[i] = parents.get(0).getDNA()[i];
        }
    }


}
