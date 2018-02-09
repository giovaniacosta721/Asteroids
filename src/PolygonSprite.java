import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

/**
* An instance of this class initializes an instance of the
* AffineTransform property first and foremost, allowing for
* rotation of sprites as a funtion of the angle variable. It
* sets the guidelines by which to rotate asteroids. It also 
* creates getters and setters for other classes to use.
* 
* 
* @author gacosta13
*
*/

public abstract class PolygonSprite extends Sprite {

	public static AffineTransform IDENTITY = new AffineTransform();
	protected Polygon myPolygon;
	protected Color myColor;
	protected double angle;
	
	/**
	 * The constructor for this class recalls the constructor of the previous
	 * class to achieve the same function.
	 */
	
	public PolygonSprite(int left, int right, int top, int bottom) {
		super(left, right, top, bottom);
		this.angle = 0;
	}
	
	/**
	 * This method provided the basis with which to utilize the transform identity.
	 * It provides a translation algorithm and a rotation one as well, to change 
	 * direction and position of the sprites.
	 */

	public void drawOn(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		g2.setColor(myColor);
		
		g2.setTransform(IDENTITY);
		g2.translate(x, y);
		g2.rotate(angle);
		g2.fill(myPolygon);
	}
	
	/**
	 * This method adds an angle theta to the current sprite's angle of rotation.
	 * Used when calculating the points of the ship through the identity transform.
	 */
	
	public void rotate(double theta) {
		angle += theta;
	}

		//Setters & Getters
	public static AffineTransform getIDENTITY() {
		return IDENTITY;
	}
	public static void setIDENTITY(AffineTransform iDENTITY) {
		IDENTITY = iDENTITY;
	}
	public Polygon getMyPolygon() {
		return myPolygon;
	}
	public void setMyPolygon(Polygon myPolygon) {
		this.myPolygon = myPolygon;
	}
	public Color getMyColor() {
		return myColor;
	}
	public void setMyColor(Color myColor) {
		this.myColor = myColor;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
