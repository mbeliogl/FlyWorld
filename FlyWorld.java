package com.maxim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Contains information about the world (i.e. grid) and handles most of the work that is NOT GUI specific
 */
public class FlyWorld {
	protected int numRows;
	protected int numCols;
	protected GridLocation[][] world;
	protected GridLocation start;
	protected GridLocation goal;
	protected ArrayList<Predator> predators;
	protected Fly mosca;

	/**
	 * Reads the file containing information about the grid
	 * Initializes the grid and other instance variables for use by FlyWorldGUI and other classes/methods
	 * @param fileName the file containing the world (grid) information
	 */
	public FlyWorld(String fileName) {
		predators = new ArrayList<Predator>();

		File inFile = new File(fileName);
		Scanner input = null;

		try {
			input = new Scanner(inFile);
		} catch (FileNotFoundException fnf) {
			System.err.println("Input file not found");
			System.exit(1);
		}

		numRows = input.nextInt();
		numCols = input.nextInt();
		input.nextLine();

		world = new GridLocation[numRows][numCols];
		int row = 0;

		while (input.hasNextLine()) {
			String line = input.nextLine();
			//Scanner lineScanner = new Scanner(line);
			for (int i = 0; i < numCols; i++) {
				String loc = line.substring(i, i + 1);
				world[row][i] = new GridLocation(row, i);
				if (loc.equals("s")) {
					world[row][i].setBackgroundColor(Color.GREEN);
					start = world[row][i];
					mosca = new Fly(start, this);
				}

				if (loc.equals("h")) {
					world[row][i].setBackgroundColor(Color.RED);
					goal = world[row][i];
				}

				if (loc.equals("f")) {
					Frog frog = new Frog(world[row][i], this);
					predators.add(frog);
				}
				if (loc.equals("b")) {
					Bird bird = new Bird(world[row][i], this);
					predators.add(bird);
				}

				if (loc.equals("a")) {
					Spider spider = new Spider(world[row][i], this);
					predators.add(spider);
				}
			}
			row++;
		}

	}

    /**
     * @return int, the number of rows in the world
     */
    public int getNumRows() { return numRows; }
    
    /**
     * @return int, the number of columns in the world
     */
    public int getNumCols() { return numCols; }
    
    /**
     * Determines if a specific (row, column) location is a valid location in the world
     * @param r a row
     * @param c a column
     * @return boolean
     */
    public boolean isValidLoc(int r, int c) {
		if(r < 0 || c < 0) {
			return false;
	    }
		else return r < numRows && c < numCols;
    }

    /**
     * Returns a specific location based on the given row and column
     * @param r the row
     * @param c the column
     * @return GridLocation
     */
    public GridLocation getLocation(int r, int c) { return world[r][c]; }

    /**
     * @return FlyWorldLocation, the location of the fly in the world
     */
    public GridLocation getFlyLocation() { return mosca.getLocation(); }

    
    /**
     * Moves the fly in the given direction (if possible)
	 * Checks if the fly got home or was eaten
     * @param direction the direction, N,S,E,W to move
     * @return int, determines the outcome of moving fly
     *            	Three possibilities:
     *             	1. fly is at home, return ATHOME (defined in FlyWorldGUI)
     *              2. fly is eaten, return EATEN (defined in FlyWorldGUI)
     *              3. fly not at home or eaten, return NOACTION (defined in FlyWorldGUI)
     */
    public int moveFly(int direction) {
		mosca.update(direction);

		if (mosca.getLocation().equals(goal)) { return FlyWorldGUI.ATHOME; }

		for (Predator f : predators) {
			if (f.eatsFly()) {
				return FlyWorldGUI.EATEN;
			} else {
				return FlyWorldGUI.NOACTION;
			}
		}
		return FlyWorldGUI.NOACTION; // THIS LINE IS JUST SO CODE COMPILES
	}

    
    /**
     * Moves all predator and checks if it eats the fly
     * @return boolean, return true if the predator eats fly, false otherwise
     */
    public boolean movePredators() {
    	for(Predator f : predators) {
	     	if(f.eatsFly()) {
		     	return true;
	     	}
	     	f.update();
	     	if(f.eatsFly()) {
		     	return true;
	     	}
    	}
	return false; // THIS LINE IS JUST SO CODE COMPILES
    }
}




		    
			
			      
