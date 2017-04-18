package wildtornado.org.genetic.util;

import wildtornado.org.genetic.objects.Individual;

import java.util.List;

public class Printer {

    public void print(List<Individual> population) {
        System.out.println("Genetic Algorithm:");
        printAverageFitness(population);
        printBestIndividual(population);
    }

    private void printAverageFitness(List<Individual> population) {
        System.out.println("The average fitness is: " + Util.averageFitness(population));
    }

    private void printBestIndividual(List<Individual> population) {
        Individual best = Util.bestIndividual(population);
        System.out.println("The best individual has a fitness of: " + best.getFitness() + ", And a value of: " + best.getRealValue());
        System.out.print("Its DNA sequence is: ");
        for (int i : best.getDNA()) {
            System.out.print(i);
        }
        System.out.println();
    }
}
