package tsga;

import tsga.Destination;
import tsga.Route;

/**
 * This class represents a group of routes to be evolved by various operators
 * of the genetic algorithm
 */
public class Population{

    private Route[] routes;

    /**
     * Constructor that only initializes Population with random routes if
     * the init param is true
     * @param size number of routes in population
     * @param init boolean signifying whether or not to fill population
     */
    public Population(int size, boolean init){
        routes = new Route[size];

        if(init){
            for(int i = 0; i < size; i++){
                Route newRoute = new Route();
                newRoute.generateRoute();
                addRoute(i, newRoute);
            }
        }
    }   

    /**
     * Adds a route to the population in specified position
     * @param pos position to insert to
     * @param routeIn the route to be inserted
     */
    public void addRoute(int pos, Route routeIn){
        routes[pos] = routeIn;
    }

    /**
     * Retrieves the route in the population at the specified position
     * @param pos position to insert to
     * @return route at the specified position
     */
    public Route getRoute(int pos){
        return routes[pos];
    }

    /**
     * Retrieves the route with the highest fitness value in this population
     * @return fittest route
     */
    public Route getFittest(){
        Route fittest = routes[0];
        
        for(int i = 1; i < routes.length; i++){
            if(fittest.getFitness() <= routes[i].getFitness()){
                fittest = routes[i];
            }
        }
        return fittest;
    }

    /**
     * Returns the number of routes in the population
     * @return number of routes in the population
     */ 
    public int size(){
        return routes.length;
    }
}
