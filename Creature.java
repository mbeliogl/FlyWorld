// homework #2 part 2
// Creature.java 
package com.maxim;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// initialize the abstract class 
public abstract class Creature
{
    private static String imgFile;
    
    protected GridLocation location; // all variables shared by all creatures

    protected FlyWorld world;

    protected BufferedImage image;

    // constructor takes location, flyWorld and name of file
    public Creature(GridLocation loc, FlyWorld fw, String imgF)
    {
	location = loc;
	world = fw;
	imgFile = imgF;
	try
	    {
	    image = ImageIO.read(new File(imgFile));
	    }
	catch (IOException ioe)
	{
	    System.out.println("Unable to read image file: " + imgFile);
	    System.exit(0);
	}

	// Puts the fly on the GridLocation so image displays
	location.setCreature(this);
    }
    public BufferedImage getImage()
    {
	return image;
    }
    public GridLocation getLocation()
    {
	return location;
    }
    public boolean isPredator()
    {
	return false;
    }
   
    




  
}
