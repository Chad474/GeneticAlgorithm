package ChadCollinsAsmt2;

import java.util.*;

public class Individual {
    // moves is a sequence of random moves.
    private int[][] moves = new int[14][2];
    private int fitness = 0;
    private int movesLength;
    
    // Constructor to create an individual who randomly generates moves based on n.
    public void createIndividual(int n) {
        movesLength = (n*n)+n+1;
        moves = new int[movesLength][2];
        for(int i = 0; i < movesLength; i++) {
            moves[i] = generateMove();
        }
    }

    // Generates one random move from 1 to 3.
    public int[] generateMove() {
        int[] move = new int[2];
        move[0] = randomMove();
        for(int temp = randomMove(); temp == move[0]; temp = randomMove())
            move[1] = temp;
        return move;
    }

    public int randomMove() {
        Random r = new Random();
        return r.nextInt(3) + 1;
    }

    public int getMove(int i, int j) {
        return moves[i][j];
    }

    public void setMove(int i, int j, int move) {
        moves[i][j] = move;
        fitness = 0;
    }

    public void setNewMove(int i) {
        moves[i] = generateMove();
        fitness = 0;
    }

    public int size() {
        return movesLength;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = Fitness.getFitness(this);
        }
        return fitness;
    }

    public String toString() {
        return Arrays.deepToString(moves);
    }
}
