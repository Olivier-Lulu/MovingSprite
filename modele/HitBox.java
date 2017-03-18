package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class HitBox {

	private Rectangle global;// hitBox globale
	private List<Rectangle> list;// liste de toutes les sous hitBoxs

	public HitBox() {
		list = new LinkedList<Rectangle>();
		global = new Rectangle();
	}

	public HitBox(Rectangle r) {
		list = new LinkedList<Rectangle>();
		global = r;
	}

	/*
	 * permet d'ajouter un rectangle à la liste des sous hitBoxs
	 */
	public void add(Rectangle r) {
		list.add(r);
	}

	public int getPosX() {
		return (int) global.getX();
	}

	public int getPosY() {
		return (int) global.getY();
	}

	public int getWidth() {
		return (int) global.getWidth();
	}

	public int getHeight() {
		return (int) global.getHeight();
	}

	public void setPosition(int posX, int posY) {
		global.setLocation(posX, posY);
	}

	/*
	 * permet de savoir si deux HitBoxs s'intersectent
	 */
	public boolean intersects(HitBox hitBox) {
		if (global.intersects(hitBox.global)) {
			Iterator<Rectangle> it = list.iterator();
			while (it.hasNext())
				if (hitBox.intersects(it.next(), getPosX(), getPosY()))
					return true;
			return false;
		} else
			return false;
	}

	/*
	 * cette fonction teste si une hitBox intersecte un sous rectangle d'une autre
	 * hitBox
	 * on suppose que le rectangle global de la HitBox de r intersecte this.global
	 */
	private boolean intersects(Rectangle r, int posX, int posY) {
		if (list.isEmpty())
			return true;
		Iterator<Rectangle> it = list.iterator();
		while (it.hasNext()) {
			Rectangle rect = it.next();
			Rectangle r1 = new Rectangle(r.x + posX, r.y + posY, r.width,
					r.height);
			Rectangle r2 = new Rectangle(rect.x + getPosX(), rect.y + posY,
					rect.width, rect.height);
			if (r1.intersects(r2)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * fonction dessinant en rouge le rectangle global et en bleu les sous
	 * rectangles
	 */
	public void drawDebug(Graphics g, int deltaX, int deltaY, int screenWidth,
			int screenHeight) {
		g.setColor(Color.RED);
		g.fillRect(((getPosX() + deltaX) * screenWidth) / 600,
				((getPosY() + deltaY) * screenHeight) / 600,
				(getWidth() * screenWidth) / 600 + 1,
				(getHeight() * screenHeight) / 600 + 1);
		Iterator<Rectangle> it = list.iterator();
		while (it.hasNext()) {
			Rectangle rect = it.next();
			g.setColor(Color.BLUE);
			g.drawRect(((rect.x + getPosX() + deltaX) * screenWidth) / 600,
					((rect.y + getPosY() + deltaY) * screenHeight) / 600,
					(rect.width * screenWidth) / 600 + 1,
					(rect.height * screenHeight) / 600 + 1);
		}
	}
	
}
