package ChadCollinsAsmt2;

public class Population {
    Individual[] individuals;
    
    public Population(int popSize, int n, boolean init) {
        individuals = new Individual[popSize];
        if(init) {
            for(int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual();
                newIndividual.createIndividual(n);
                setIndividual(i, newIndividual);
            }
        }
    }

    public Individual getIndividual(int i) {
        return individuals[i];
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        for(int i = 0; i < size(); i++) {
            if(fittest.getFitness() <= getIndividual(i).getFitness())
                fittest = getIndividual(i);
        }
        return fittest;
    }

    public int size() {
        return individuals.length;
    }

    public void setIndividual(int i, Individual ind) {
        individuals[i] = ind;
    }
}
