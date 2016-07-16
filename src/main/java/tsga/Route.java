package tsga;

import java.util.ArrayList;
import java.util.Collections;

import tsga.Destination;

/**
 * This class represents the "Tour" (in more traditional TSP terms) made up of Destinations
 */
public class Route{

    private ArrayList route = new ArrayList<Destination>();

    /**
     * Constructor that initializes Route to all null locations
     */
    public Route(){
        for(int i = 0; i < DestPool.size(); i++){
            route.add(null);
        }
    }

    /**
     * Retrieves a destination at desired location in route
     * @param pos position in route
     * @return the destination at position
     */
    public Destination getDest(int pos){
        return (Destination)route.get(pos);
    }

    /**
     * Inserts the specified destination at specified postion in route
     * @param dest destination to be inserted into route
     * @param pos position in route that destination will be inserted
     */
    public void setDest(Destination dest, int pos){
        route.set(pos, dest);
    }

    /**
     * Generates a random route by populating the route in order with the
     * entire pool of destinations and then shuffling it.
     */
    public void generateRoute(){
        for(int i = 0; i < DestPool.size(); i++){
            setDest(DestPool.get(i), i) ;
        }
        Collections.shuffle(route);
    }

    /**
     * Calculates the distance the salesman would travel if he were to
     * take this route.
     * @return total distance of route
     */
    public int getDistance(){
        Destination currentDest;
        Destination nextDest;
        int totalDist = 0;

        if(routeSize() <= 1){
            return totalDist;
        }

        for(int i = 0; i < routeSize() - 1; i++){
            currentDest = getDest(i);
            nextDest = getDest(i+1);
            if(currentDest == null){
                System.out.println("Current: " + currentDest + ", Next: " + nextDest);
                System.out.println(toString());

            }
            totalDist += currentDest.distanceTo(nextDest); 
        }
        return totalDist;
    }

    /**
     * Calculates the fitness of this route (in this case, shorter routes are rewarded with a higher
     * fitness value so the fitness function is simply the reciprocal of the route's distance).
     * @return fitness of this route
     */
    public double getFitness(){
        int distance = getDistance();
        if(distance == 0){
            // TODO Handle strange occurence of dividing by 0
        }
        return 1 / (double)distance; 
    }

    /**
     * Retrieves the number of destinations in this route
     * @return number of destinations in this route
     */
    public int routeSize(){
        return route.size();
    }

    /**
     * Returns a boolean value representing whether or not the input
     * Destination is in the current route
     * @param dest Destination to be searched for
     * @return boolean result of search
     */
    public boolean contains(Destination dest){
        return route.contains(dest);
    }

    /**
     * Returns the position in this route of the input Destination
     * @param dest Destination to be searched for
     * @return position index of Destination
     */
    public int indexOf(Destination dest){
        return route.indexOf(dest);
    }

    @Override
    public String toString(){
        String retString = "";

        retString += ("("+getDest(0)+") ");
        for(int i = 1; i < routeSize(); i++){
            retString += ("-> (" + getDest(i) + ") ");
        }
        return retString;
    }
}
