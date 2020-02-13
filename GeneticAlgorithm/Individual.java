package ChadCollinsAsmt2;

import java.util.*;

public class Individual {
    private int[][] moves;  // Sequence of random moves.
    private int fitness;    // Used for fitness function.

    // Base constructor.
    public Individual(int n) {
    moves = new int[n][2];
    fitness = 0;
    }

    // Generate movement set. Creates an individual with randomly generated moves of length n, size 2.
    public void generateMoveset() {
        for(int i = 0; i < this.size(); i++) {
            moves[i] = generateMove();
        }
    }

    // Generates one random move with numbers ranging from 1 to 3. One move is two non-repeating numbers.
    public int[] generateMove() {
        int[] move = new int[2];
        move[0] = randomMove();
        for(int temp = randomMove(); temp != move[0]; temp = randomMove())
            move[1] = temp;
        return move;
    }

    // Randomization function. Helper function for generateMove.
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

    public void mutateMove(int i) {
        moves[i] = generateMove();
        fitness = 0;
    }

    public void partialInversion(int i) {
        setMove(i, 0, getMove(i, 1));
        setMove(i, 1, getMove(i, 0));
    }

    public int size() {
        return moves.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = Fitness.getFitness(this);
        }
        return fitness;
    }
    
    // toString override to display a sequence of moves.
    public String toString() {
        return Arrays.deepToString(moves);
    }
}
