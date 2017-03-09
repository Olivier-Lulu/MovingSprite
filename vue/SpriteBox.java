package vue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modele.HitBox;

/*
 * cette classe est voué a disparetre pour etre remplacer par un truc qui gere les animation ou autre
 * elle permet surtout de tester la class hitBox et de lui creer son constructeur
 */

public class SpriteBox extends Sprite {

	HitBox hitBox;
	
	public SpriteBox(BufferedImage image, HitBox hitBox){
		super(image);
		this.hitBox = hitBox;
		
	}

	// a changer plus tard
	public SpriteBox(Rectangle rectangle, Sprite sprite) {
		image = sprite.image;
		hitBox = new HitBox(rectangle);
	}

	public int getPosX(){
    	return hitBox.getPosX();
    }
	
    public int getPosY(){
    	return hitBox.getPosY();
    }
    
    public int getWidth(){
    	return (int) hitBox.getWidth();
    }
    
    public int getHeight(){
    	return (int) hitBox.getHeight();
    }
    
    public void setPosition(int posX, int posY){
    	hitBox.setPosition(posX,posY);
    }
    
    public boolean intersects(SpriteBox spriteBox){
    	return hitBox.intersects(spriteBox.hitBox);
    }

	public void drawDebug(Graphics g,int deltaX,int deltaY,int screenWidth,int screenHeight) {
		hitBox.drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
		
	}
	
}
