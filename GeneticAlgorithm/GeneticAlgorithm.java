package ChadCollinsAsmt2;

public class GeneticAlgorithm {
    private static double uniformChance = 0.5;
    private static double mutationChance = 0.1;
    private static int tournamentSize = 5;

    public static Population GAPopulation(Population pop, int n) {
        Population newPopulation = new Population(pop.size(), n, false);
        newPopulation.setIndividual(0, pop.getFittest());
        for(int i = 1; i < pop.size(); i++) {
            Individual ind1 = tournamentSelection(pop, n);
            Individual ind2 = tournamentSelection(pop, n);
            Individual child = reproduce(ind1, ind2);
            newPopulation.setIndividual(i, child);
        }
        return newPopulation;
    }

    private static Individual reproduce(Individual parent1, Individual parent2) {
        Individual child = new Individual();
        for(int i = 0; i < parent1.size(); i++) {
            if(Math.random() <= uniformChance) {
                child.setMove(i, 0, parent1.getMove(i, 0));
                child.setMove(i, 1, parent1.getMove(i, 1));
            } else {
                child.setMove(i, 0, parent2.getMove(i, 0));
                child.setMove(i, 1, parent2.getMove(i, 1));
            }
        }
        mutate(child);
        return child;
    }

    private static void mutate(Individual ind) {
        for (int i = 0; i < ind.size(); i++) {
            if(Math.random() < mutationChance)
                ind.setNewMove(i);
        }
    }

    private static Individual tournamentSelection(Population pop, int n) {
        Population tournament = new Population(tournamentSize, n, false);
        for(int i = 0; i < tournamentSize; i++) {
            int randomID = (int)(Math.random() * pop.size());
            tournament.setIndividual(i, pop.getIndividual(randomID));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
