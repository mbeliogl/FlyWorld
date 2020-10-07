package com.maxim;
// Spider.java
import java.util.ArrayList;

public class Spider extends Predator 
{
    private static final String spiderImg = "C:\\Users\\MAX\\IdeaProjects\\FlyWorld\\src\\com\\maxim\\images\\spider.jpg";

    // invoking Predator constructor
    public Spider(GridLocation loc, FlyWorld fw) {super(loc, fw, spiderImg);}

	/**
	 * Generates a list of ALL possible legal moves for a Spider.
	 * Spiders can move to any non predator-occupied space:
	 * 1. Can not move off the grid
	 * 2. Can not move onto a square that already has frog/bird on it
	 * 3. Always moves towards the fly
	 * @return ArrayList<GridLocation>, a list of legal grid locations from the world that the spider can move to
	 */
    @Override
    public ArrayList<GridLocation> generateLegalMoves() {
		ArrayList<GridLocation> legal = new ArrayList<GridLocation>();
	
		GridLocation flyLoc = world.getFlyLocation();
		int flyRow = flyLoc.getRow();
		int flyCol = flyLoc.getColumn();
		int sRow = location.getRow();
		int sCol = location.getColumn();

		/**
		 * Ex: if Spider's row is greater than Fly's row, the spider moves one row down
		 * Thus, the spider always moves TOWARDS the Fly
		 */
		if(sRow > flyRow) {
			if(world.isValidLoc(sRow - 1, sCol)  && !world.getLocation(sRow - 1, sCol).hasPredator()) {
				GridLocation newLoc = world.getLocation(sRow - 1, sCol);
				legal.add(newLoc);
		    }
	    }

		else if(sRow < flyRow) {
			if(world.isValidLoc(sRow + 1, sCol) && !world.getLocation(sRow + 1, sCol).hasPredator()) {
				GridLocation newLoc = world.getLocation(sRow + 1, sCol);
				legal.add(newLoc);
		    }
	    }

		if(sCol > flyCol) {
			if(world.isValidLoc(sRow, sCol - 1) && !world.getLocation(sRow, sCol - 1).hasPredator()) {
				GridLocation newLoc = world.getLocation(sRow, sCol - 1);
				legal.add(newLoc);
		    }
	    }

		else if(sCol < flyCol) {
			if(world.isValidLoc(sRow, sCol + 1) && !world.getLocation(sRow, sCol + 1).hasPredator()) {
				GridLocation newLoc = world.getLocation(sRow, sCol + 1);
				legal.add(newLoc);
		    }
	    }

		return legal;
    }

    // same as bird
    @Override
    public boolean eatsFly() {
		return world.getFlyLocation().equals(location);
	}

}
