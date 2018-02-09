import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
* An instance of this class extends the Sprite class as well. Creates a sprite
* object using an array of x and y points determined to draw a triangle. Is 
* linked to controls the user may utilize to change the position and orientation of
* the object on-screen.
*
* @author gacosta13
*
*/

public class Ship extends PolygonSprite {
	
	private int left, right, top, bottom;
	
	/**
	 * The constructor for this class recalls the constructor of the previous
	 * class to achieve the same function. It adds the array of x and y points described earlier.
	 */
	
	public Ship(int left, int right, int top, int bottom) {
		super(left, right, top, bottom);

		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.setSize(15);
		
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];

		xPoints[0] = 0;
		xPoints[1] = 0;
		xPoints[2] = 10;
		yPoints[0] = 0;
		yPoints[1] = 10;
		yPoints[2] = 5;
		
		this.myPolygon = new Polygon(xPoints, yPoints, 3);
		this.myColor = Color.GREEN;
		
	}
	
	/**
	 * An instance of this class creates bullet objects, sets their positions,
	 *  velocities, and angles.
	 */

	public Bullet shoot() {
		Bullet s = new Bullet(left, right, top, bottom);
		s.setVelocity(this.getDx()+20*Math.cos(this.getAngle()), this.getDy()+20*Math.sin(this.getAngle()));
		s.setLocation((int)Math.round(this.getX()), (int)Math.round(this.getY()));
		return s;
		
	}
	
	/**
	 * This method adds to ship velocity, altering the x and y periodically.
	 */
	
	public void thrust() {
		double tempThrustDx = this.getDx() + (Math.cos(this.getAngle())*2);
		double tempThrustDy = this.getDy() + (Math.sin(this.getAngle())*2);
		this.setVelocity(tempThrustDx, tempThrustDy);
	}
	
	/**
	 * This method subtracts from ship velocity, altering the x and y periodically.
	 */
	
	public void brake() {
		double tempThrustDx = this.getDx() - (Math.cos(this.getAngle())*2);
		double tempThrustDy = this.getDy() - (Math.sin(this.getAngle())*2);
		this.setVelocity(tempThrustDx, tempThrustDy);
	}
	
	/**
	 * Draws instances of the ship polygon, keeping track of its position and 
	 * orientation.
	 */

	public void drawOn(Graphics gfx) {		
		super.drawOn(gfx);
	}
	
	@Override
	public void move(){
		super.move();
		rebound();
	}

	/**
	 * Resets the ship location to the center of the screen and
	 * sets dx and dy to 0.
	 */
	public void resetLocation() {
		this.x = right/2;
		this.y = bottom/2;
		this.dx = 0;
		this.dy = 0;
		
	}
}
