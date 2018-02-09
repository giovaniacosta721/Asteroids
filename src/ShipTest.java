import static org.junit.Assert.*;

import org.junit.Test;


public class ShipTest {

	/**
	 * tests the constructor properly seets values
	 * and that when it shoots it uses the right x, y and dx
	 */
	@Test
	public void test() {
		Ship s = new Ship(0, 400, 0, 400);
		assertEquals(s.x, 200, 0);
		assertEquals(s.y, 200, 0);
		
		assertEquals(s.getAngle(), 0, 0);
		
		s.rotate(Math.PI/9);
		assertEquals(s.getAngle(), .34, .01);
		
		Bullet b = s.shoot();
		
		assertEquals(b.getDx(), 18.79, .01);
		assertEquals(b.getX(), s.getX(), .001);
		assertEquals(b.getY(), s.getY(), .001);
	}

}
