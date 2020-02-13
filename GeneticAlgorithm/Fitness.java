package ChadCollinsAsmt2;

public class Fitness {

    static int movesLen;
    static int[][] solution;

    public static void setSolution(int[][] newSolution, int n) {
        movesLen = (n*n)+n+1;
        solution = new int[movesLen][2];
        solution = newSolution;
    }

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
        int maxFitness = solution.length;
        return maxFitness;
    }
}
