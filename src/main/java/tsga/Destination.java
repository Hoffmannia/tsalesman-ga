package tsga;

import java.util.Random;


/**
 * This class represents a "City" (in the more traditional TSP sense) with an xaxis and yaxis location.
 */
public class Destination {

    private int xCoord;
    private int yCoord;
    public static final int CITY_LIMITS = 400; // Enforces assumption that city is 400x400
    private Random randGen = new Random();

    /**
     * Constructor that initializes the position of the destination based
     * on specified coordinates.
     * @param xIn x-coordinate of destination
     * @param yIn y-coordinate of destination
     */
    public Destination(int xIn, int yIn){
        xCoord = xIn;
        yCoord = yIn;
    }

    /**
     * Constructor that randomly assigns a location to the destination.
     */
    public Destination(){
        xCoord = randGen.nextInt(CITY_LIMITS);
        yCoord = randGen.nextInt(CITY_LIMITS);
    }

    /**
     * Retrieves x-coordinate of destination
     * @return this destination's x-coordinate
     */
    public int getX(){
        return xCoord;
    }

    /**
     * Retrieves y-coordinate of destination
     * @return this destination's y-coordinate
     */
    public int getY(){
        return yCoord;
    }       

    /**
     * Calculates the distance between this destination and another destination
     * @param otherDest the destination distance is to be measured to
     * @return the distance between this and the other destination
     */
    public double distanceTo(Destination otherDest){
        int xDiff = otherDest.getX() - xCoord;
        int yDiff = otherDest.getY() - yCoord;
        return Math.sqrt((xDiff*xDiff) + (yDiff*yDiff)); 
    }

    @Override
    public String toString(){
        return "X: " + xCoord + ", " + "Y: " + yCoord;
    }

}
