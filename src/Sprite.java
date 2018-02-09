import java.awt.Graphics;

/**
* An instance of this class configures boundaries, velocity, 
* size and position of sprites drawn by classes that inherit from
* this it. The position of the sprite is controlled by x, y.
*
* @author gacosta13
*
*/

public abstract class Sprite {

	protected double x, y;
	protected double dx, dy;
	protected int size;
	protected int minx, miny;
	protected int maxx, maxy;

	/**
	 * The constructor for this class simply sets the input variables
	 * for it equal to the member variables of the class. This functions
	 * primarily to set the boundaries of the applet and center the Ship sprite.
	 * It also sets the velocity vector equal to 0.
	 */
	
	public Sprite(int left,int right, int top, int bottom) {
		this.minx = left;
		this.maxx = right;
		this.maxy = bottom;
		this.miny = top;

		this.x = right/2;
		this.y = bottom/2;
		this.dx = 0;
		this.dy = 0;

	}
	
	/**
	 * This method, using a Pythagorean algorithm, detects collision between sprite
	 * objects, namely between bullets and asteroids, for which to eliminate asteroids
	 * from the field. It uses the center points of either object and uses their 
	 * combined radii as the minimum possible distance between objects before collision
	 * is achieved. the Pythagorean identity is used to calculate distance between the
	 * two center points regardless of position on the field.
	 */

	public boolean isCollided(Sprite other) {
		double d = Math.sqrt((this.getX() - other.getX())*(this.getX() - other.getX()) +
				(this.getY() - other.getY())*(this.getY() - other.getY()));
		return(d < this.getSize() + other.getSize());
	}
	
	/**
	 * This method is used to set the location of a moved sprite to new coordinates with 
	 * which a draw method can recreate it on the field
	 */

	public void setLocation(int nx, int ny) {
		this.x = nx;
		this.y = ny;
	}

	/**
	 * This method is used to set the velocity vector of a certain sprite when modified by 
	 * collision or intentional modification by the user. This will be used to continuously
	 * modify the speed of a sprite.
	 */
	
	public void setVelocity(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * This method sets the size of a sprite to be a certain value ns. Useful in collision
	 * detection and in drawing.
	 */

	public void setSize(int ns) {
		this.size = ns;
	}
	
	/**
	 * Abstract method for which inherited classes may call upon and elaborate in their own class.
	 */

	public abstract void drawOn(Graphics gfx);	//Notice the abstract algorithm ends in a semicolon, not a bracket

	/**
	 * This will modify the location of the sprite with respect to the magnitude and direction of
	 * the velocity vector.
	 */

	public void move() {
		x += dx;
		y += dy;
	}
	
	/**
	 * Tests for collision with the boundaries of the applet window, and return true to indicate it.
	 */

	public boolean isOutOfBounds() {
		if( x<minx || x>maxx ||y<miny ||y>maxy) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Should the object be out of bounds, this method changes the direction in which
	 * the object is traveling with respect to which wall was hit.
	 */
	
	public void rebound() {
		if( x<minx || x>maxx ) {
			dx *= -1;
		}
		if(y<miny ||y>maxy) {
			dy *= -1;
		}
	}

	//Getter&Setters

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getDx() {
		return dx;
	}
	public void setDx(double dx) {
		this.dx = dx;
	}
	public double getDy() {
		return dy;
	}
	public void setDy(double dy) {
		this.dy = dy;
	}
	public int getMinx() {
		return minx;
	}
	public void setMinx(int minx) {
		this.minx = minx;
	}
	public int getMiny() {
		return miny;
	}
	public void setMiny(int miny) {
		this.miny = miny;
	}
	public int getMaxx() {
		return maxx;
	}
	public void setMaxx(int maxx) {
		this.maxx = maxx;
	}
	public int getMaxy() {
		return maxy;
	}
	public void setMaxy(int maxy) {
		this.maxy = maxy;
	}
	public int getSize() {
		return size;
	}


}
