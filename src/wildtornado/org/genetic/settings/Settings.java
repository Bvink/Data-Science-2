package wildtornado.org.genetic.settings;

public class Settings {

    public static final int POPULATION_SIZE = 26;
    public static final int ITERATIONS = 300;
    public static final int DNA_SIZE = 5;
    public static final double CROSSOVER_RATE = 0.9;
    public static final double MUTATION_RATE = 0.05;
    public static final boolean ELITISM = true;

    public static final int TOURNAMENT_SIZE = 5;

    public static double FITNESS(int i) {
        return -(Math.pow(i, 2)) + 7 * i;
    }

}
