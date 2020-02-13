package ChadCollinsAsmt2;

import java.io.*;
import java.util.*;

public class HanoiMain {
    static int POPULATION_SIZE = 50;
    static final int terminate = 10000;  // Number of generations before termination.
    static boolean tournament = true;  // True for tournament selection. False for roulette.

    public static void main(String[] args) {
        if(tournament == true)
            System.out.println("Tournament selection method on.");
        else
            System.out.println("Roulette selection method on.");
        // Get user input for number of discs.
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter number of discs: ");
        int n = userInput.nextInt();  // Number of discs.
        
        Random r = new Random();
        int set = r.nextInt(3) + 1; // Randomly decide which solution set to use.
        Fitness.setSolution(Solutions.solutionSet(n, set)); // Get solution set.
        if(tournament == false) {
            System.out.printf("Solving using roulette selection, n = %d with solution set:\n %s\n", 
                 n, toString(Solutions.solutionSet(n, set)));
        } else {
            System.out.printf("Solving using tournament selection, n = %d with solution set:\n %s\n", 
                 n, toString(Solutions.solutionSet(n, set)));
        }

        Population pop = new Population(POPULATION_SIZE, n);
        int generation = 0; // Counter for the number of new populations created.

        // Main loop.
        // Terminates when the fitness of an individual is found to be the same as the solution set,
        // or when too many generations have passed.
        while((pop.getFittest().getFitness() < Fitness.getMaxFitness()) && generation < terminate) {
            generation++;
            System.out.printf("Generation: %d. Best fitness: %d%%. %d mutations, %d inversions.\n", 
                    generation, pop.getFitnessPercent(), pop.getMutationCounter(), 
                    pop.getInversionCounter());
            pop = GeneticAlgorithm.GAPopulation(pop, tournament);
        }

        if(generation >= terminate)
            System.out.printf("Timed out. Best solution found at %d with %d%% fitness.\n",
                    generation, pop.getFitnessPercent());
        else
            System.out.printf("Solution found at generation %d with %d%% fitness. ", 
                    generation, pop.getFitnessPercent());
        System.out.printf("Final movement sequence:\n %s\n", pop.cleanupSolution());
    }

    public static String toString(int[][] arr) {
        return Arrays.deepToString(arr);
    }
}
