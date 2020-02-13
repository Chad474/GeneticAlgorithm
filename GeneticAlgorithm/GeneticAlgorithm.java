package ChadCollinsAsmt2;

public class GeneticAlgorithm {
    private static double mutationChance = 0.005;    // Mutation rate, set to 0 for no mutations.
    private static double inversionChance = 0.0005;  // Inversion rate, set to 0 for no inversions.
    private static int tournamentSize = 5;          // Sets size for tournament selection.
    
    // Genetic Algorithm for the Population. Takes in a population and the selection method. 
    // Returns a new population with a higher chance of success based on the fitness function.
    public static Population GAPopulation(Population pop, boolean tournament) {
        Population newPopulation = new Population(pop.size());
        newPopulation.setIndividual(0, pop.getFittest());
        for(int i = 1; i < pop.size(); i++) {
            Individual ind1;
            Individual ind2;
            if(tournament) {
                ind1 = tournamentSelection(pop);
                ind2 = tournamentSelection(pop);
            } else {
                ind1 = rouletteSelection(pop);
                ind2 = rouletteSelection(pop);
            }
            Individual child = reproduce(ind1, ind2, newPopulation);
            newPopulation.setIndividual(i, child);
        }
        return newPopulation;
    }

    // Reproduction. Takes in two individuals and returns a child of them. The child is a
    // combination of either parents movements with a small chance to mutate a move.
    private static Individual reproduce(Individual parent1, Individual parent2, Population newPopulation) {
        Individual child = new Individual(parent1.size());
        for(int i = 0; i < parent1.size(); i++) {
            if(Math.random() <= 0.5) {
                child.setMove(i, 0, parent1.getMove(i, 0));
                child.setMove(i, 1, parent1.getMove(i, 1));
            } else {
                child.setMove(i, 0, parent2.getMove(i, 0));
                child.setMove(i, 1, parent2.getMove(i, 1));
            }
        }
        mutate(child, newPopulation);
        return child;
    }

    // Mutation. Takes in an individual and returns a mutation of one movement, an
    // inversion of one movement, or does nothing depending on the mutation and inversion
    // chances.
    private static void mutate(Individual child, Population newPopulation) {
        for(int i = 0; i < child.size(); i++) {
            if(Math.random() < mutationChance) {
                child.mutateMove(i);
                newPopulation.increaseMutationCounter();
                break;
            }
            if(Math.random() < inversionChance) {
                child.partialInversion(i);
                newPopulation.increaseInversionCounter();
                break;
            }
        }
    }

    // Tournament selection. Takes in a population, creates a new smaller population
    // from that original population based on the tournament size. Returns the individual
    // with the highest fitness out of the newer population.
    private static Individual tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize);
        for(int i = 0; i < tournamentSize; i++) {
            int randVal = (int)(Math.random() * pop.size());
            tournament.setIndividual(i, pop.getIndividual(randVal));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }

    // Roulette Wheel Selection. Takes in a population and returns a random individual
    // from that population, with individuals of higher fitness having a higher chance
    // to be returned.
    private static Individual rouletteSelection(Population pop) {
        int sum = 0;
        for(int i = 0; i < pop.size(); i++) {
            sum += pop.getIndividual(i).getFitness();
        }
        int randVal = (int)(Math.random() * sum);
        for(int i = 0; i < pop.size(); i++) {
            randVal -= pop.getIndividual(i).getFitness();
            if(randVal <= 0)
                return pop.getIndividual(i);
        }
        return null;
    }
}
