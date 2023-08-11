package flappyBird;

import java.awt.event.KeyEvent;

public class Bird extends Sprite {
	
	private int dx;
	private int dy;
	protected int upForce;
	protected boolean jump;
	protected final int GRAVITY = 8;

	public Bird(int x, int y) {
		super(x, y);

		initBird();
	}

	private void initBird() {
		
		loadImage("src/images/resizedBird.png");
		getImageDimensions();
	}
	
	public void move() {
		
		x += dx;
		y += dy;
		
		if (x < 1) {
			x = 1;
		}
		
		if (y < 1) {
			y = 1;
		}
	}
	
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();

		
		if (key == KeyEvent.VK_SPACE) {

			dy = -15;
		}
	}
	
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_SPACE) {
			if (upForce <= GRAVITY) {
				dy = GRAVITY;
			}
		}
	}
	
	public int getUpForce() {
		return upForce;
	}
	
	public void deductUpForce() {
		if (upForce > GRAVITY) {
			upForce--;
		}
	}
	
	public void setUpForce(int upForce) {
		this.upForce = upForce;
	}
	
	public void startJump() {
		if (upForce > GRAVITY) {
			this.jump = true;
		}
	}
}
