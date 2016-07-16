package tsga;

import java.util.Random;

import tsga.Route;

/**
 * This class implements a genetic algorithm to optimize the Traveling Salesman Problem
 * and can be customized with 6 parameters and 2 crossover implementations (my first naive approach and PMX)
 */
public class GA{

    private double crossoverProb; // probability that crossover will happen in an evolution
    private double mutationProb; // probability that mutation will happen in an evolution
    private double elitismProb; // probability that elitism will be applied
    private int popSize; // size of population
    private int tourneySize; // size of any given tournament
    private int crossoverMode; // choice of crossover implementation (1 for naive and 2 for PMX)
    private Random randGen = new Random();

    /**
     * Contructor that initializes parameters for the GA
     */ 
    public GA(double crossoverProbIn, double mutationProbIn, double elitismProbIn, int popSizeIn, int tourneySizeIn, int crossoverModeIn){
        crossoverProb = crossoverProbIn;
        mutationProb = mutationProbIn;
        elitismProb = elitismProbIn;
        popSize = popSizeIn;
        tourneySize = tourneySizeIn;
        crossoverMode = crossoverModeIn;
    }   

    /**
     * Evolves the population based on the algorithm parameters
     * @param pop population to be evolved
     * @return an evolved population
     */
    public Population evolve(Population pop){
        int evolveStart = 0;
        Population evolvedPop = new Population(popSize,false);

        if(Math.random() < elitismProb){
            Route fittest = pop.getFittest();
            evolvedPop.addRoute(0, fittest);
            evolveStart += 1;
        }
        
        for(int i = evolveStart; i < evolvedPop.size(); i++){
            if(Math.random() < crossoverProb){
                Route parent1 = tournament(pop);
                Route parent2 = tournament(pop);
                Route child = new Route();

                switch(crossoverMode){
                
                case 1:
                    child = originalCrossover(parent1, parent2);
                    break;
                case 2:
                    child = pmxCrossover(parent1, parent2);
                    break;
                }

                evolvedPop.addRoute(i, child);
            } else{
                evolvedPop.addRoute(i, pop.getRoute(i));
            }
        }

        for(int i = evolveStart; i < evolvedPop.size(); i++){
            if(Math.random() < mutationProb){
                mutate(evolvedPop.getRoute(i));
            }
        }

        return evolvedPop;
    }

    /**
     * Selects the fittest route of a tournament of routes which is 
     * a randomly selected subset of the input population
     * @param pop population that candidates are chosen from
     * @return winner of tournament
     */
    private Route tournament(Population pop){
        Population tourney = new Population(tourneySize, false);

        //Get tournament participants
        for(int i = 0; i < tourneySize; i++){
            int randInd = randGen.nextInt(pop.size() - 1);
            tourney.addRoute(i, pop.getRoute(randInd));
        }
        return tourney.getFittest();
    }

    /**
     * Mutates a route by swapping two randomly selected destinations
     * @param route route to be mutated
     */
    private void mutate(Route route){
        for(int i1 = 0; i1 < route.routeSize(); i1++){
            int i2 = randGen.nextInt(route.routeSize() - 1);
            Destination d1 = route.getDest(i1);
            Destination d2 = route.getDest(i2);
            route.setDest(d1, i2);
            route.setDest(d2, i1);
        }
    }

    /**
     * My first naiive approach to implementing a crossover implementation for
     * this problem (see README for higher-level explanation)
     * @param parent1 one parent route  
     * @param parent2 other parent route
     * @return child route
     */
    private Route originalCrossover(Route parent1, Route parent2){
        Route child = new Route();
        int splitIndex = randGen.nextInt(parent1.routeSize() - 1);

        for(int i = splitIndex; i < parent1.routeSize(); i++){
            child.setDest(parent1.getDest(i), i); 
        } 
        
        for(int j = 0; j < parent2.routeSize(); j++){
            if(!child.contains(parent2.getDest(j))){
                for(int k = 0; k < splitIndex; k++){
                    if(child.getDest(k) == null){
                        child.setDest(parent2.getDest(j), k);
                        break;
                    }
                }
            }
        }

        return child;
    }

    /**
     * PMX Crossover operator applied to the context of
     * this problem (see README for higher-level explanation)
     * @param parent1 one parent route  
     * @param parent2 other parent route
     * @return child route
     */
    private Route pmxCrossover(Route parent1, Route parent2){
        Route child = new Route();
        int startInd;
        int endInd;
        int i1 = randGen.nextInt(parent1.routeSize() - 1);
        int i2 = randGen.nextInt(parent1.routeSize() - 1);

        if(i1 < i2){
            startInd = i1;
            endInd = i2;
        } else{
            startInd = i2;
            endInd = i1;
        }

        // Select swathe of dests from parent1
        for(int i = startInd; i <= endInd; i++){
            child.setDest(parent1.getDest(i), i);
        }

        //Look through same positions in parent2
        for(int i = startInd; i <= endInd; i++){
            Destination stepADest = parent2.getDest(i);
            int stepAInd = i;
            boolean proceed = true;
            if(!child.contains(stepADest)){
                while(proceed){
                    //Locate parent1 value in same position
                    Destination V = parent1.getDest(stepAInd);
                    //locate same value in parent2 
                    int matchP2 = parent2.indexOf(V);

                    if(matchP2 < startInd || matchP2 > endInd){
                        //Insert stepA value into child at                         
                        child.setDest(stepADest, matchP2);
                        proceed = false;
                    } else{
                        stepAInd = matchP2;
                    }
                }
            }
        }

        for(int i = 0; i < parent2.routeSize(); i++){
            if(child.getDest(i) == null){
                child.setDest(parent2.getDest(i), i);
            }
        }
        return child;
    }
}
