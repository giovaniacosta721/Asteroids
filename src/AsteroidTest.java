import static org.junit.Assert.*;

import org.junit.Test;


public class AsteroidTest {

	/**
	 * Tests the collide method using a ship and asteroid
	 */
	@Test
	public void testCollide() {
		Asteroid a = new Asteroid (0, 400, 0, 400, 10);
		assertEquals(a.getSize(), 10);
		Ship s = new Ship(0, 400, 0, 400);
		s.setX(a.getX());
		s.setY(a.getY());
		
		assertTrue(a.isCollided(s));
	}

}
