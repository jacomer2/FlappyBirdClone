package flappyBird;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visible;
	protected Image image;
	
	public Sprite(int x, int y) {
		
		//assign x position on board
		this.x = x;
		
		//assign y position on board
		this.y = y;
		
		//default to visible
		visible = true;
	}
	
	
	/**
	 * Store image dimensions in class width and height vars
	 */
	protected void getImageDimensions() {
		
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	/**
	 * retrieve image from image source file
	 * @param imageName source file of image
	 */
	protected void loadImage(String imageName) {
		
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds() {
		
		//width and height to adjust for more forgiving hit boxes
		return new Rectangle(x, y, width-80, height-80);
	}
	
}
