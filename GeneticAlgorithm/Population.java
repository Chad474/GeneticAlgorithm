package ChadCollinsAsmt2;

import java.util.*;

public class Population {
    private Individual[] individuals;
    private int mutationCounter;
    private int inversionCounter;
    // Constructor. Creates a new population of individuals.   
    public Population(int popSize) {
       individuals = new Individual[popSize];
       mutationCounter = 0;
       inversionCounter = 0;
    }

    // Constructor. Creates a new population of individuals with randomly generated movements.
    public Population(int popSize, int n) {
        int individualLength = (int) Math.pow(2, n) + n + 1;
        individuals = new Individual[popSize];
        mutationCounter = 0;
        inversionCounter = 0;
        for(int i = 0; i < size(); i++) {
            Individual newIndividual = new Individual(individualLength);
            newIndividual.generateMoveset();
            setIndividual(i, newIndividual);
        }
    }

    public Individual getIndividual(int i) {
        return individuals[i];
    }

    public void setIndividual(int i, Individual ind) {
        individuals[i] = ind;
    }

    public int size() {
        return individuals.length;
    }

    // Fitness function helper to get the fittest individual of the population.
    // If a solution is found, a new individual containing only the necessary moves
    // is returned.
    public Individual getFittest() {
        Individual fittest = individuals[0];
        for(int i = 0; i < size(); i++) {
            if(fittest.getFitness() <= individuals[i].getFitness())
                fittest = individuals[i];
        }
        return fittest;
    }
    
    public Individual cleanupSolution() {
        Individual solution = new Individual(getFittest().size());
        for(int i = 0; i < getFittest().getFitness(); i++) {
            solution.setMove(i, 0, getFittest().getMove(i, 0));
            solution.setMove(i, 1, getFittest().getMove(i, 1));
        }
        return solution;
    }

    public int getFitnessPercent() {
        return (int) 100 * getFittest().getFitness()/Fitness.getMaxFitness();
    }
 
    public int getMutationCounter() {
        return mutationCounter;
    }
    public int getInversionCounter() {
        return inversionCounter;
    }
    public void increaseMutationCounter() {
        this.mutationCounter++;
    }
    public void increaseInversionCounter() {
        this.inversionCounter++;
    }

    // Print function used for testing. Prints an individual's movement set.
    public void printIndividual(int i) {
        System.out.println("Individual[" + i + "]: " + individuals[i].toString());
    }
}
