package com.maxim;
// fly.java

/**
 * Contains several methods that aid in the display and movement of Mosca
 */
public class Fly extends Creature {
    private static final String flyImg = "C:\\Users\\MAX\\IdeaProjects\\FlyWorld\\src\\com\\maxim\\images\\fly.jpg";
      
    /**
     * Creates a new Fly object
     * The image file for a fly is fly.jpg
     * @param loc a GridLocation
     * @param fw the FlyWorld the fly is in
     */
    public Fly(GridLocation loc, FlyWorld fw)
    {
	super(loc, fw, flyImg);
    }
   

    /**
     * @return boolean, always false, Mosca is not a predator
     */
    @Override
    public boolean isPredator()
    {
	return false;
    }
    
    /**
     * Updates the fly's (Mosca's) location in the world
     * The fly can move in one of the four cardinal (N, S, E, W) directions
     * Ensures that the updated location is within the bounds of the world before moving the fly
     * @param direction one of the four cardinal directions
     *        Given as constants in FlyWorldGUI:
     *        	- FlyWorldGUI.NORTH<br>
     *        	- FlyWorldGUI.SOUTH<br>
     *        	- FlyWorldGUI.EAST<br>
     *        	- FlyWorldGUI.WEST<br>
     */
    public void update(int direction) {
		int newRow = location.getRow();
		int newCol = location.getColumn();

		if(direction == FlyWorldGUI.NORTH) {
			newRow = newRow - 1;
	    }
	
		else if(direction == FlyWorldGUI.SOUTH) {
			newRow = newRow + 1;
	    }

		else if(direction == FlyWorldGUI.EAST) {
			newCol = newCol + 1;
	    }

		else if(direction == FlyWorldGUI.WEST) {
			newCol = newCol - 1;
	    }

		if(world.isValidLoc(newRow, newCol)) {
	       location.removeCreature();
	       location = world.getLocation(newRow, newCol); // removes and adds
	       world.getLocation(newRow, newCol).setCreature(this);
	   }
    }
}
