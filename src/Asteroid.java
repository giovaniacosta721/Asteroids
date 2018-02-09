import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;

/**
 * An instance of this class sets up the asteroid sprite, inherited from the
 * Sprite class. Sets a size and deltaAngle with which the asteroid is able
 * to tumble continuously. The deltaAngle is not to be changed.
 *
 * @author gacosta13
 *
 */

public class Asteroid extends PolygonSprite {	
	
	protected double deltaAngle;

	/**
	 * The constructor for this class recalls the constructor of the previous
	 * class to achieve the same function. Also sets the size of the asteroid.
	 */

	public Asteroid(int left, int right, int top, int bottom, int size) {
		super(left, right, top, bottom);
		this.size = size;
		
		Random rand = new Random();
		this.angle = Math.PI/9*rand.nextInt(9);
		this.dx = rand.nextDouble() -.3;
		this.dy = rand.nextDouble() - .4;
		
		int n = rand.nextInt(5) + 5;	//random number of points to include
		int[] xArrays = new int[n];
		int[] yArrays = new int[n];

		/*adds indeterminate number to each coordinate point in the array to place
		 * the entire asteroid in a random place in the map
		 */		
		for(int i=0; i < n; i++) {
			double angle = i * (2 * Math.PI) / n;
			
			xArrays[i] = (int) (size*Math.cos(angle) + rand.nextInt(size/2));
			yArrays[i] = (int) (size*Math.sin(angle) + rand.nextInt(size/2));

		}
		this.myPolygon = new Polygon(xArrays, yArrays, n);
		this.myColor = Color.RED;
	}

	/**
	 * The constructor for this class recalls the constructor of the previous
	 * class to achieve the same function. Also uses the rebound and isoutofbounds
	 * methods to determine whether or not the velocity vector should be changed.
	 * Changes the angle of rotation incrementally.
	 */

	@Override
	public void move() {
		super.move();
		if(this.isOutOfBounds()) {
			super.rebound();
		}
		this.angle += deltaAngle;
	}

	//Getters & Setters
	public double getdAngle() {
		return deltaAngle;
	}
	public void setdAngle(double dAngle) {
		this.deltaAngle = dAngle;
	}

}
