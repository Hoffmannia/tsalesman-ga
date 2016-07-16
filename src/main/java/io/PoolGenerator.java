package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tsga.DestPool;
import tsga.Destination;

/**
 * This class populates the pool of locations that characterizes a Traveling
 * Salesman Problem.
 */


public class PoolGenerator{

    private BufferedReader input;

    /**
     * Constructor that initializes the reader or exits with an error
     * @param inputFileName name of file containing location information
     */
    public PoolGenerator(String inputFileName){
        try{
            input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        } catch(FileNotFoundException fne){
            System.err.println("Specified input file does not exist. Please restart with valid file");
            System.exit(1);
        }
    }

    /**
     * Reads/parses the information from the input file and populates the pool of destinations
     * for the TSP.
     */
    public void populatePool(){
        try{
            int numDest = Integer.parseInt(input.readLine());
            
            for(int i = 0; i < numDest; i++){
                String line = input.readLine();
                String[] coords = line.split(" ");
                Destination newDest = new Destination(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                DestPool.add(newDest);
            }
        } catch(IOException ioe){
            System.err.println("Error in reading file.");
            System.exit(1);
        }
    }
}

