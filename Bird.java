package com.maxim;
// Bird.java
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class Bird extends Predator {
    private static final String birdImg = "C:\\Users\\MAX\\IdeaProjects\\FlyWorld\\src\\com\\maxim\\images\\bird.jpg";

	// invoking Predator constructor
    public Bird(GridLocation loc, FlyWorld fw) { super(loc,fw, birdImg);}

	/**
	 * Generates a list of ALL possible legal moves for a bird.
	 * Birds can move to any non predator-occupied space:
	 * 1. Can not move off the grid
	 * 2. Can not move onto a square that already has frog/spider on it
	 * 3. Can move onto a square that already has a fly on it
	 * @return ArrayList<GridLocation>, a list of legal grid locations from the world that the bird can move to
	 */
	@Override
    public ArrayList<GridLocation> generateLegalMoves() {
		ArrayList<GridLocation> legal = new ArrayList<GridLocation>();
		// adding EVERY location that doesn't have a predator to the list
		for(int row = 0; row < world.getNumRows(); row++) {
			for(int col = 0; col < world.getNumCols(); col++) {
				if(world.isValidLoc(row, col)) {
					if(! world.getLocation(row, col).hasPredator()) {
					legal.add(world.getLocation(row,col));
				    }
			    }
		    }
	    }

	return legal;
    }

	/**
	 * A bird can eat the fly if it is on the same square as the fly
	 * @return boolean true if the fly can be eaten, false otherwise
	 */
	@Override
    public boolean eatsFly() {
		return world.getFlyLocation().equals(location);
	}
}
	    
