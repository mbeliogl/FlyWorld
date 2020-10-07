package com.maxim;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Handles all the work for GUI setup and deals with keystrokes
 */
public class FlyWorldGUI extends JPanel {
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;

    public static final int ATHOME = 0;
    public static final int EATEN = 1;
    public static final int NOACTION =-1;

    private GridBagConstraints gbc;

    private FlyWorld world;

    private Action moveNorth = new AbstractAction("NORTH") {
		@Override
		public void actionPerformed(ActionEvent e)
	{
	    updateWorld(NORTH);
	}
    };

    private Action moveSouth = new AbstractAction("SOUTH") {
		@Override
		public void actionPerformed(ActionEvent e) { updateWorld(SOUTH); }
    };
    
    private Action moveEast = new AbstractAction("EAST") {
		@Override
		public void actionPerformed(ActionEvent e)
	{
	    updateWorld(EAST);
	}
    };

    private Action moveWest = new AbstractAction("WEST") {
		@Override
		public void actionPerformed(ActionEvent e) { updateWorld(WEST); }
    };

    /**
     * Initializes some GUI pieces like keystroke-handling and calls FlyWorld to create the world based on a given file
     * @param fileName the file containing the description of the grid
     */
    public FlyWorldGUI(String fileName) {
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "NORTH");
		getActionMap().put("NORTH", moveNorth);

		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "SOUTH");
		getActionMap().put("SOUTH", moveSouth);

		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "WEST");
		getActionMap().put("WEST", moveWest);

		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "EAST");
		getActionMap().put("EAST", moveEast);

		world = new FlyWorld(fileName);
		gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());

		for (int row = 0; row < world.getNumRows(); row++) {
	    	for (int col = 0; col <  world.getNumCols(); col++) {
				gbc.gridx = col;
				gbc.gridy = row;
				add(world.getLocation(row, col), gbc);
	    	}
		}
    }

    /**
     * Called when one of the arrow keys is pressed
     * Moves the fly and checks it's status
     * Then moves all predators and checks fly's status again
     * @param direction the direction to try to move the fly
     */
    public void updateWorld(int direction) {
		int result = world.moveFly(direction);

		if (result == ATHOME) {
	    	flyGotHome();
	    	System.exit(0);
		}

		if (result == EATEN) {
	    	flyGotEaten();
	    	System.exit(0);
		}

		if (world.movePredators()) {
	    	flyGotEaten();
	    	System.exit(0);
		}
    }
    
    private void flyGotHome() {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
	
		JOptionPane.showMessageDialog(frame,
				      "Mosca got home safely!",
				      "You win!",
				      JOptionPane.WARNING_MESSAGE);
    }

    private void flyGotEaten() {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
	
		JOptionPane.showMessageDialog(frame,
				      "Oh no, Mosca got eaten!",
				      "You lose!",
				      JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String [] args) {
		JFrame mainFrame = new JFrame("Fly World");
		FlyWorldGUI w = new FlyWorldGUI("C:\\Users\\MAX\\IdeaProjects\\FlyWorld\\src\\com\\maxim\\worlds\\world0.txt");

		mainFrame.getContentPane().add("Center", w);
		w.setFocusable(true);
		w.requestFocusInWindow();

		mainFrame.setSize(600, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
    }
}
