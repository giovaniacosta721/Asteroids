import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * This class controls the bullet object shot at asteroids by the ship. It
 * sets the "age" of the bullet, which determines how many timer ticks the
 * bullet may travel before expiring
 *
 * @author gacosta13
 *
 */

public class Bullet extends Sprite {

	public static final int MAX_AGE = 20;
	private int age;

	/**
	 * The constructor for this class sets the maximum age for which the
	 * bullet may "live". The bullet disappears after this threshold is 
	 * passed.
	 */

	public Bullet(int left, int right, int top, int bottom) {
		super(left, right, top, bottom);
		size = 2;
		age = MAX_AGE;
	}

	/**
	 * This method draws the bullet based off an x and y.
	 */

	public void drawOn(Graphics gfx) {

		int w = this.size*2;

		Graphics2D g2 = (Graphics2D)gfx;
		g2.setTransform(PolygonSprite.IDENTITY);

		gfx.setColor(Color.blue);
		gfx.fillOval((int) this.x, (int) this.y, w, w);
	}

	/**
	 * This recalls the move() method of the superclass, 
	 * achieving the same function. Decrements age each 
	 * time it is called as well, which is every timer tick
	 */
	@Override
	public void move() {
		super.move();
		age--;
	}

	/**
	 * Looks at the age of the bullet and returns true should
	 * the bullet surpass this limit. Used to remove expired
	 * bullets from the field.
	 */

	public boolean isTooOld() {
		return age < 0;
	}

	//Getters & Setters
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


}
