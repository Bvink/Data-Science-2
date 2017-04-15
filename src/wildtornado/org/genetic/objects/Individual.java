package wildtornado.org.genetic.objects;


import wildtornado.org.genetic.settings.Settings;

import java.util.Random;

public class Individual implements Comparable {

    private int[] DNA = new int[Settings.DNA_SIZE];                                //Binary DNA sample of the individual.
    private int realValue;                                                          //Decimal value of the individual.
    private double fitness;                                                         //Fitness of the individual, depends on the equation given in settings.

    public Individual(int... DNA) {
        if (confirmDNA(DNA)) {
            this.DNA = DNA;
        } else {
            setDNA();
        }
        calcRealValue();
        calcFitness();
    }

    private boolean confirmDNA(int[] DNA) {
        if (DNA.length == Settings.DNA_SIZE) {
            for (int i : DNA) {
                if (i > 1 || i < 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void setDNA() {
        Random rand = new Random();
        for (int i = 0; i < DNA.length; i++) {
            DNA[i] = rand.nextInt(2);
        }
    }

    private void calcRealValue() {
        int[] DNA = getDNA();
        int realValue = 0;
        for (int i = 0; i < DNA.length; i++) {
            realValue += (DNA[i] * Math.pow(2, DNA.length - i - 1));
        }
        this.realValue = realValue;
    }

    private void calcFitness() {
        this.fitness = Settings.FITNESS(getRealValue());
    }

    public int[] getDNA() {
        return DNA;
    }

    public int getRealValue() {
        return realValue;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Object o) {
        Individual that = (Individual) o;
        if (this.getFitness() > that.getFitness()) return -1;
        if (this.getFitness() < that.getFitness()) return 1;
        return 0;
    }

}
