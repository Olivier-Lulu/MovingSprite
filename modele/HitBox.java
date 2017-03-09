package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class HitBox{
	
	private Rectangle global;
	private List<Rectangle> list;
	
	public HitBox(){
		list = new LinkedList<Rectangle>();
		global = new Rectangle();
	}
	
	public HitBox(Rectangle r){
		list = new LinkedList<Rectangle>();
		global = r;
	}

	public void add(Rectangle r){
		list.add(r);
	}
	
	public int getPosX(){
    	return (int) global.getX();
    }
	
    public int getPosY(){
    	return (int) global.getY();
    }
    
    public int getWidth(){
    	return (int) global.getWidth();
    }
    
    public int getHeight(){
    	return (int) global.getHeight();
    }
    
    public void setPosition(int posX, int posY){
    	global.setLocation(posX, posY);
    }
    
    public boolean intersects(HitBox hitBox){
    	if(global.intersects(hitBox.global)){
    		Iterator<Rectangle> it = list.iterator();
    		while(it.hasNext())
    			if(hitBox.intersects(it.next(),getPosX(),getPosY()))
    				return true;
    		return false;
    	}else
    		return false;
    }

    /*
     * cette fonction test si une hitBox intersect un sous rectangle d'une autre hitBox
     * 
     * \pre le rectangle global de la HtiBaox de r intersect this.global
     */
    private boolean intersects(Rectangle r,int posX, int posY){
    	if(list.isEmpty())
    		return true;
    	Iterator<Rectangle> it = list.iterator();
    	while(it.hasNext()){
    		Rectangle rect = it.next();
    		Rectangle r1 = new Rectangle(r.x+posX,r.y+posY,r.width,r.height);
    		Rectangle r2 = new Rectangle(rect.x+getPosX(),rect.y+posY,rect.width,rect.height);
    		if(r1.intersects(r2)){
    			return true;
    		}
    	}
    	return false;
    }

	public void drawDebug(Graphics g,int deltaX,int deltaY,int screenWidth,int screenHeight) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillRect(((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1);
		Iterator<Rectangle> it = list.iterator();
		while(it.hasNext()){
			Rectangle rect = it.next();
			g.setColor(Color.BLUE);
			g.drawRect(((rect.x+getPosX()+deltaX)*screenWidth)/600, ((rect.y+getPosY()+deltaY)*screenHeight)/600, (rect.width*screenWidth)/600+1, (rect.height*screenHeight)/600+1);
		}
	}
}
