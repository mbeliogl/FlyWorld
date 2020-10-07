package com.maxim;
import java.util.ArrayList;
import java.util.Random;

// abstract class is a subclass of Creature
public abstract class Predator extends Creature
{
    public Predator(GridLocation loc, FlyWorld fw, String imgF)
    {
	super(loc, fw, imgF); //constructor from Creature
    }
    public abstract ArrayList<GridLocation> generateLegalMoves();
    public abstract boolean eatsFly();
    public boolean isPredator()
    {
	return true;
    }

    // method uses random and legal(list) to choose a random location to where the predator moves

	/**
	 * Uses the list of legal moves to randomly choose a location where the predator moves
	 */
    public void update() {
    	ArrayList<GridLocation> legal = generateLegalMoves();
        Random rand = new Random();

		if(legal.size() != 0) {
			int index = rand.nextInt(legal.size());
			// removes creature from current location and puts into the new location
			GridLocation crTo = legal.get(index);
			location.removeCreature();
			crTo.setCreature(this);
			location = crTo;
	    }
	else { System.out.println("empty"); }
    }



}
