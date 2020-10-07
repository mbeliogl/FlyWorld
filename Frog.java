package com.maxim;
// Frog.java
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

/**
 * Handles display, movement, and fly hunting for Frogs
 */

public class Frog extends Predator {
    private static final String frogImg = "C:\\Users\\MAX\\IdeaProjects\\FlyWorld\\src\\com\\maxim\\images\\frog.jpg";

    /**
     * Creates a new Frog object
     * The image file for a frog is frog.jpg
     * @param loc a GridLocation
     * @param fw the FlyWorld the frog is in
     */
	// invoking predator constructor
    public Frog(GridLocation loc, FlyWorld fw) { super(loc,fw,frogImg); }

	/**
     * @return boolean, always true
     */
    @Override
    public boolean isPredator()
    {
	return true;
    }

    /**
     * Generates a list of ALL possible legal moves for a frog
     * Frogs can move one space in any of the four cardinal directions, but:
     * 1. Can not move off the grid
     * 2. Can not move onto a square that already has frog on it
     * @return ArrayList<GridLocation>, a list of legal grid locations from the world that the frog can move to
     */
    public ArrayList<GridLocation> generateLegalMoves() {
		ArrayList<GridLocation> legal = new ArrayList<GridLocation>();

		int r = location.getRow();
		int c = location.getColumn();

		// check every condition (direction) for its legality and add to the list if legal
		if(world.isValidLoc(r+1,c)) {
			if(! world.getLocation(r+1,c).hasPredator()) {
				legal.add(world.getLocation(r+1,c));
		    }
	    }
		if(world.isValidLoc(r-1,c)) {
			if(! world.getLocation(r-1,c).hasPredator()) {
			legal.add(world.getLocation(r-1,c));
		    }
	    }
		if(world.isValidLoc(r,c+1)) {
			if(! world.getLocation(r,c+1).hasPredator()) {
				legal.add(world.getLocation(r,c+1));
		    }
	    }
		if(world.isValidLoc(r,c-1)) {
			if(! world.getLocation(r,c-1).hasPredator()) {
			legal.add(world.getLocation(r,c-1));
		    }
	    }

		return legal;
    }

    /**
     * This method helps determine if a frog is in a location where it can eat a fly
	 * A frog can eat the fly if it is on the same square as the fly or 1 spaces away in one of the cardinal directions
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly() {
		// for every condition, check whether the frog could eat a fly(when next to it)
		int r = location.getRow();
		int c = location.getColumn();

		if(world.getFlyLocation().equals(location)) {
			return true;
	    }
		if(world.getFlyLocation().getRow()+1 == location.getRow() && world.getFlyLocation().getColumn() == location.getColumn()) {
			return true;
	    }
		if(world.getFlyLocation().getRow()-1 == location.getRow() && world.getFlyLocation().getColumn() == location.getColumn()) {
			return true;
	    }
		if(world.getFlyLocation().getColumn()+1 == location.getColumn() && world.getFlyLocation().getRow() == location.getRow()) {
			return true;
	    }
		if(world.getFlyLocation().getColumn()-1 == location.getColumn() && world.getFlyLocation().getRow() == location.getRow()) {
			return true;
	    }
		return false; // THIS LINE IS JUST SO CODE COMPILES
    }
}
