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
        for(int i = 0; i < Settings.TOURNAMENT_SIZE; i++) {
            tournamentPool.add(population.get(rand.nextInt(Settings.POPULATION_SIZE)));
        }
        return tournamentPool;
    }


}
