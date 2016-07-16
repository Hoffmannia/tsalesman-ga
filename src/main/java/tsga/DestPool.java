package tsga;

import tsga.Destination;

import java.util.ArrayList;

/**
 * This class holds and gives access to the set of locations that will be used to characterize the 
 * Traveling Salesman Problem.
 */
public class DestPool{

    public static ArrayList pool = new ArrayList<Destination>();

    /**
     * Adds a Destination into the pool of locations.
     * @param destIn the Destination to be added
     */
    public static void add(Destination destIn){
       pool.add(destIn); 
    }

    /**
     * Returns a Destination at the specified index
     * @param index index of desired Destination
     * @return Destination at desited index
     */
    public static Destination get(int index){
        return (Destination)pool.get(index);
    }

    /**
     * Returns the number of Destinations in the pool
     * @return number of destinations
     */
    public static int size(){
        return pool.size();
    }

}
