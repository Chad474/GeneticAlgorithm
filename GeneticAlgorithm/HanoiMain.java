package ChadCollinsAsmt2;

import java.io.*;

public class HanoiMain {
    static int POPULATION_SIZE = 50;

    public static void main(String[] args) {
        int n = 3;
        Fitness.setSolution(solutionSet(n, 1), n);
        
        Population pop = new Population(POPULATION_SIZE, n, true);
        
        int generation = 0;
        while(pop.getFittest().getFitness() < Fitness.getMaxFitness()) {
            generation++;
            System.out.println("Generation: " + generation + " Fittest: " + pop.getFittest().getFitness());
            pop = GeneticAlgorithm.GAPopulation(pop, n);
        }
        System.out.println("Final solution found.");
        System.out.println("Generation: " + generation);
        System.out.println("Movement sequence: " + pop.getFittest());
    }

    private static int[][] solutionSet(int n, int set) {
        if(n == 3) {
            int[][] s1 = new int[][]{{1,3},{1,2},{3,2},{1,3},{2,1},{2,3},{1,3}};
            int[][] s2 = new int[][]{{1,2},{1,3},{2,1},{3,2},{1,2},{1,3},{2,1},{2,3},{1,3}};
            int[][] s3 = new int[][]{{1,2},{1,3},{2,3},{1,2},{3,2},{3,1},{2,1},{2,3},{1,2},{1,3},{2,3}};
            
            if(set == 1)
                return s1;
            if(set == 2)
                return s2;
            else
                return s3;
        }
        if(n == 4) {
            //TBD
        }
        return new int[][]{{0,0}};
    }
}
