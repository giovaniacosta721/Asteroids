import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

/**
* Tests general functions of the methods in the bullet class to
* ensure reliability
*
* @author gacosta13
*
*/

public class BulletTest {
	/**
	 * Ensures the constructor is properly setting things
	 */
	@Test
	public void testCreation() {
		Bullet b = new Bullet(0, 800, 0, 800);
		
		Assert.assertEquals(0, b.getMinx());
		Assert.assertEquals(0, b.getMiny());
		Assert.assertEquals(800, b.getMaxx());
		Assert.assertEquals(800, b.getMaxy());
		Assert.assertEquals(Bullet.MAX_AGE, b.getAge());
		
		Assert.assertFalse(b.isTooOld());
		
		assertEquals(b.x, 400, 1);
		assertEquals(b.y, 400, 1);
	}
	
	/**
	 * tests movement with altered velocity
	 */
	@Test
	public void testMove() {
		Bullet b = new Bullet(0, 800, 0, 800);
		
		assertEquals(b.x, 400, 0);
		assertEquals(b.y, 400, 0);
		
		assertEquals(b.dx, 0, 0);
		assertEquals(b.dy, 0, 0);
		
		b.move();
		
		assertEquals(b.dx, 0, 0);
		assertEquals(b.dy, 0, 0);
	
		assertEquals(b.x, 400, 0);
		assertEquals(b.y, 400, 0);
		
		b.setVelocity(1, 1);
		b.move();
		
		assertEquals(b.dx, 1, 0);
		assertEquals(b.dy, 1, 0);
	
		assertEquals(b.x, 401, 0);
		assertEquals(b.y, 401, 0);
		
		assertEquals(b.getAge(), 18);
		
		b.setVelocity(-1, 0);
		b.move();
		b.move();
		
		assertEquals(b.dx, -1, 0);
		assertEquals(b.dy, 0, 0);
	
		assertEquals(b.x, 399, 0);
		assertEquals(b.y, 401, 0);
	}
	
	/**
	 * Makes sure there are no x/y errors when it moves to the bottom of
	 * the screen
	 */
	@Test
	public void testMoveDown() {
		Bullet b = new Bullet(0, 400, 0, 400);
		
		b.setVelocity(1, 0);
		b.move();
		assertEquals(b.x, 201, 0);
		assertEquals(b.y, 200, 0);
		
		for (int i = 0; i < 199; i++){
			b.move();
			assertEquals(b.x, 201+i+1, 0);
			assertEquals(b.y, 200, 0);
		}
		
		b.move();
	}

}
