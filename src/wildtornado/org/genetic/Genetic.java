package wildtornado.org.genetic;

import wildtornado.org.genetic.algorithm.GeneticAlgorithm;
import wildtornado.org.genetic.objects.Individual;
import wildtornado.org.genetic.settings.Settings;
import wildtornado.org.genetic.util.Printer;

import java.util.ArrayList;
import java.util.List;

public class Genetic {

    private List<Individual> population = new ArrayList<Individual>();
    private Printer printer = new Printer();

    public void init() {
        if ((Settings.POPULATION_SIZE & 1) == 0) {
            populate();
            iterate();
        }
        printer.print(population);
    }

    //Create the initial population.
    private void populate() {
        for (int i = 0; i < Settings.POPULATION_SIZE; i++) {
            population.add(new Individual());
        }
    }

    //Perform the genetic algorithm a set amount of times.
    private void iterate() {
        for (int i = 0; i < Settings.ITERATIONS; i++) {
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(population);
            this.population = geneticAlgorithm.getChildren();
        }
    }
}
