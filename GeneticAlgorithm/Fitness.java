package ChadCollinsAsmt2;

import java.util.*;

public class Fitness {

    static int movesLen;
    static int[][] solution;

    public static void setSolution(int[][] newSolution) {
        solution = newSolution;
    }

    // Fitness function. Gets the fitness of an individual by comparing the amount
    // of correct moves vs the length of the solution set.
    // Returns a fitness number.
    public static int getFitness(Individual ind) {
        int fitness = 0;
        for(int i = 0; i < ind.size() && i < solution.length; i++) {
            if(ind.getMove(i, 0) == solution[i][0] && 
               ind.getMove(i, 1) == solution[i][1])
                fitness++;
        }
        return fitness;
    }

    public static int getMaxFitness() {
        return solution.length;
    }
}
