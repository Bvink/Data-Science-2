package wildtornado.org.geneticalgorithm.algorithm;

import wildtornado.org.geneticalgorithm.objects.Individual;
import wildtornado.org.geneticalgorithm.settings.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PairSelector {

    //Perform Tournament selection.
    //This is done by randomly picking a set amount of individuals from the population.
    //Then finding the best individual from that group and returning it.
    public List<Individual> tournamentSelector(List<Individual> population) {
        List<Individual> parents = new ArrayList<Individual>();
        parents.add(createTournament(createTournamentPool(population)));
        parents.add(createTournament(createTournamentPool(population)));
        return parents;
    }

    //Find the individual with the highest fitness from among a group.
    //This is usually done by comparing them one by one, instead we just sort it by fitness because it's easier.
    private Individual createTournament(List<Individual> tournamentPool) {
        Collections.sort(tournamentPool);
        return tournamentPool.get(0);
    }

    //Create a random group of individuals who will be put into a tournament.
    private List<Individual> createTournamentPool(List<Individual> population) {
        List<Individual> tournamentPool = new ArrayList<Individual>();
        Random rand = new Random();
        for (int i = 0; i < Settings.TOURNAMENT_SIZE; i++) {
            tournamentPool.add(population.get(rand.nextInt(Settings.POPULATION_SIZE)));
        }
        return tournamentPool;
    }




    
    //Perform Roulette selection.
    //This is done by putting all the values on a "roulette wheel" after normalization.
    //And the generating a random number to select a parent randomly from said "roulette wheel".
    //The higher the fitness, the higher the chance to be selected.
    public List<Individual> rouletteSelector(List<Individual> population) {
        List<Individual> parents = new ArrayList<Individual>();

        double normalizer = getNormalizationValue(population);
        double sum = getNormalizedFitnessSum(population, normalizer);
        parents.add(rouletteWinner(population, normalizer, randomDouble(sum)));
        parents.add(rouletteWinner(population, normalizer, randomDouble(sum)));
        return parents;
    }

    //Pick a winner, based on a randomly generated value, from a "roulette wheel".
    public Individual rouletteWinner(List<Individual> population, double normalizer, double random) {
        double partialSum = 0d;
        for (Individual ind : population) {
            partialSum += ind.getFitness() + normalizer;
            if (partialSum >= random) {
                return ind;
            }
        }
        return null;
    }

    //Generate a random double.
    private double randomDouble(double upperBound) {
        Random rng = new Random();
        return rng.nextDouble() * upperBound;
    }

    //Find the lowest fitness in the dataset.
    private double getNormalizationValue(List<Individual> population) {
        double lowestValue = Double.MAX_VALUE;

        for (Individual ind : population) {
            if (ind.getFitness() < lowestValue) {
                lowestValue = ind.getFitness();
            }
        }

        return Math.abs(lowestValue);
    }

    //Return the total of all normalized fitness.
    private double getNormalizedFitnessSum(List<Individual> population, double normalizer) {
        double sum = 0d;
        for (Individual ind : population) {
            sum += ind.getFitness() + normalizer;
        }
        return sum;
    }
}
