package wildtornado.org.genetic.settings;

import wildtornado.org.genetic.constants.Constants;

public class Settings {

    public static final int POPULATION_SIZE = 26;
    public static final int ITERATIONS = 300;
    public static final int DNA_SIZE = 5;
    public static final double CROSSOVER_RATE = 0.9;
    public static final double MUTATION_RATE = 0.05;
    public static final boolean ELITISM = true;

    public static final int SELECTION_METHOD = Constants.TOURNAMENT_SELECTION;
    public static final int TOURNAMENT_SIZE = 5;

    public static final int CROSSOVER_METHOD = Constants.UNIFORM_CROSSOVER;
    public static final int SINGLE_POINT_CROSSOVER = 3;
    public static final int[] TWO_POINT_CROSSOVER = {1, 3};

    public static double FITNESS(int i) {
        return -(Math.pow(i, 2)) + 7 * i;
    }

}
