package drivers;

import tsga.Population;
import tsga.GA;
import tsga.DestPool;
import io.PoolGenerator;

/**
 * This class drives the creation and evolution of a population for
 * a genetic algorithm application to the traveling salesman problem.
 */
public class Driver{

    public static void main(String args[]){
        
        // Populate the generator with the desired locations for the TSP
        PoolGenerator poolGen = new PoolGenerator(args[0]);
        poolGen.populatePool();
        
        // Parse the command line for GA parameters
        double crossoverProb = Double.parseDouble(args[1]);
        double mutationProb = Double.parseDouble(args[2]);
        double elitismProb = Double.parseDouble(args[3]);
        int popSize = Integer.parseInt(args[4]);
        int tourneySize = Integer.parseInt(args[5]);
        int generations = Integer.parseInt(args[6]);
        int crossoverMode = Integer.parseInt(args[7]);
        
        // Create the original population from the pool of destinations
        Population pop = new Population(popSize, true);
        System.out.println("Initial Distance: " + pop.getFittest().getDistance());
        
        // Ready the GA for evolving
        GA algo = new GA(crossoverProb, mutationProb, elitismProb, popSize, tourneySize, crossoverMode);

        // Randomize the population once before moving into the formal evolutions
        pop = algo.evolve(pop);

        // Evolve the population the specified number of times
        for(int i = 0; i < generations; i++){
            pop = algo.evolve(pop);
        }
        
        // Output the stats of the fittest of the population
        System.out.println("Shortest Distance: " + pop.getFittest().getDistance());
        System.out.println("Best Route: " + pop.getFittest());

    }
}
