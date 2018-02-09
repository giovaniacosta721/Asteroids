import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * An instance of this class renders an adjustable 800x800 window
 * in which the playing field is visible and the boundaries are set.
 * It creates the asteroid, ship, and sets the background along with
 *  the timer in which to base all movement off of. The sounds are
 *  set here as well.
 * 
 * @author gacosta13
 *
 */

public class GameApplet extends Applet {

	//Member Variables
	public static int MAX_WIDTH = 600;
	public static int MAX_HEIGHT = 600;
	public static Random rand;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Bullet> bullets;
	private Ship theShip;
	private int shipCount;
	private Timer myTimer;
	private static Image background;
	private AudioClip shootClip;
	private AudioClip explodeClip;
	private AudioClip thrustClip;
	private ImageIcon win;
	private ImageIcon lose;
	private BufferedImage buff;

	/**
	 * Sets up the applet, which calls the init() method to
	 * initialize it and the paint() method to draw things
	 * on it.
	 */

	public GameApplet() throws HeadlessException {

	}

	/**
	 * Initializes the size, sound clips, adds the keylistener,
	 * and timer of the applet. Creates the ship, and sets the
	 * timer to tick every second.
	 */

	@Override
	public void init() {
		shipCount = 3;

		this.setSize(MAX_WIDTH, MAX_HEIGHT);
		this.setFocusable(true);
		shootClip = this.getAudioClip(this.getDocumentBase(),"shoot.wav");
		explodeClip = this.getAudioClip(this.getDocumentBase(),"explode.wav");
		thrustClip = this.getAudioClip(this.getDocumentBase(),"thrusters.wav");

		background = this.getImage(this.getDocumentBase(), "Images/star_background.jpg");
		win = new ImageIcon("Images/win.png");
		lose = new ImageIcon("Images/lose.png");
		
		theShip = new Ship(0, MAX_WIDTH, 0, MAX_HEIGHT);

		bullets = new ArrayList<Bullet>();

		rand = new Random();
		int limit = rand.nextInt(10)+10;
		asteroids = new ArrayList<Asteroid>();
		for(int i = 0; i<limit; i++) {
			Asteroid a = new Asteroid(0, this.getWidth(), 0, this.getHeight(), 5 + rand.nextInt(10));
			a.setLocation(rand.nextInt(this.getWidth()), rand.nextInt(this.getHeight()));

			if (!theShip.isCollided(a)){
				asteroids.add(a);
			}
		}

		KeyHelper myKH = new KeyHelper();
		this.addKeyListener(myKH);		
		
		myTimer = new Timer(1000/5, new TimerHelper() );	//1 second between ticks for 1000. 1000/x gives x times per second the timer will update
		myTimer.start();
	}

	/**
	 * Determines whether the game should be ended or not based on
	 * the number of lives spent through the game.
	 */

	public boolean isGameLost() {
		if(shipCount == 0 ) {
			return true;		//Will end the game when shipCount is zero	
		}
		else return false;
	}

	public boolean isGameWon() {
		if (asteroids.size() == 0){
			return true;	//Will end the game when there are no more asteroids
		}
		else return false;
	}

	/**
	 * Paints the background, asteroids, and ship
	 */

	@Override
	public void paint(Graphics g) {

		if(buff == null) {
			buff = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		}
		Graphics bgfx = buff.getGraphics();
		
		bgfx.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

		bgfx.setColor(Color.red);

		theShip.drawOn(bgfx);

		for(int i = 0; i<bullets.size(); i++) {
			bullets.get(i).drawOn(bgfx);
		}

		for(int i = 0; i<asteroids.size(); i++) {
			asteroids.get(i).drawOn(bgfx);
		}

		/*
		 * Checks if player lost
		 */
		if(isGameLost()) {
			Graphics2D g2 = (Graphics2D)bgfx;
			g2.setTransform(PolygonSprite.IDENTITY);
			bgfx.drawImage(lose.getImage(), 50, 0, null);
			myTimer.stop();
		}
		/*
		 * Checks if player won
		 */
		if(isGameWon()) {
			Graphics2D g2 = (Graphics2D)bgfx;
			g2.setTransform(PolygonSprite.IDENTITY);
			bgfx.drawImage(win.getImage(),50, 0, null);
			myTimer.stop();
		}
		
		g.drawImage(buff, 0, 0, this.getWidth(), this.getHeight(), null);

	}

	private class KeyHelper implements KeyListener {

		/**
		 * This method checks which keys are pressed and activates
		 * the ship thrusters should the "up" arrow be pressed. It
		 * plays the clip associated with it and moves the ship 
		 * accordingly. Also accounts for the other movements such
		 * as braking and bullet shots when the spacebar is pressed.
		 */

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				theShip.thrust();						//this is adding the 2 vector to the ship each time
				thrustClip.play();
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				Bullet shot = theShip.shoot();
				bullets.add(shot);
				shootClip.play();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				theShip.brake();
				thrustClip.play();
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				theShip.rotate(Math.PI/18);
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				theShip.rotate(-Math.PI/18);
			}

		}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}
	}

	/**
	 * Repaints every tick. Necessary for movement and is the 
	 * basis for the majority of operations in the applet.
	 */

	private class TimerHelper implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			theShip.move();	
			for (int i =0; i < asteroids.size(); i++){
				asteroids.get(i).move();
			}
			for(int i = 0; i<bullets.size(); i++) {
				bullets.get(i).move();
			}

			checkBulletCollisions();
			checkShipCollisions();

			GameApplet.this.repaint();
		}

		/**
		 * This method checks if the ship has collided with an asteroid,
		 * if so resets the ship (if there are lives remaining) and 
		 * destroys the asteroid it collided with
		 */
		private void checkShipCollisions() {
			for (int i = 0; i < asteroids.size(); i++){
				if (theShip.isCollided(asteroids.get(i))){
					shipCount--;
					asteroids.remove(i);
					explodeClip.play();
					theShip.resetLocation();
				}
			}
		}

		/**
		 * This method checks the bullets age and if they have collided 
		 * with any asteroids
		 */
		private void checkBulletCollisions() {
			for (int i = 0; i < bullets.size(); i++){
				//Checks the age of the bullet, if too old removes it
				if (bullets.get(i).isTooOld()){
					bullets.remove(i);
				} else {
					//checks it hasn't collided with asteroids
					for (int j = 0; j < asteroids.size(); j++){
						if (asteroids.get(j).isCollided(bullets.get(i)) ){
							bullets.remove(i);
							asteroids.remove(j);
							explodeClip.play();
							break;
						}
					}
				}
			}
		}

	}
}
