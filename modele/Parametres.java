package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Parametres extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String fichier = "/data/Sprites/Parametres.jpg";
	
	java.net.URL url = this.getClass().getResource(fichier);
	
	BufferedImage image;
	
	public Parametres() {
		addMouseListener(this);
		try {
			this.image = ImageIO.read(url);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(image,0,0,getWidth(),getHeight(),0,0,800,800,null);
		g.setColor(Color.WHITE);
		g.drawString("Choisissez une résolution ", getWidth()/5, getHeight()/10);
		g.drawString("600 x 600 ", getWidth()/4, getHeight()/8);
		g.drawString("800 x 600 ", getWidth()/4, getHeight()/6);
		g.drawString("1000 x 800 ", getWidth()/4, getHeight()/4);
		g.drawString("1200 x 1000 ", getWidth()/4, getHeight()/2);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		/*if (arg0.getX() > getWidth()/2.42 && arg0.getX() < getWidth()/1.97 && arg0.getY() > getHeight()/3.24 && arg0.getY() < getHeight()/2.71){
			//this.running = false;
		}
		if (arg0.getX() > getWidth()/2.42 && arg0.getX() < getWidth()/1.97 && arg0.getY() > getHeight()/3.24 && arg0.getY() < getHeight()/2.71){
			//this.running = false;
		}
		if (arg0.getX() > getWidth()/2.42 && arg0.getX() < getWidth()/1.97 && arg0.getY() > getHeight()/3.24 && arg0.getY() < getHeight()/2.71){
			//this.running = false;
		}
		if (arg0.getX() > getWidth()/2.42 && arg0.getX() < getWidth()/1.97 && arg0.getY() > getHeight()/3.24 && arg0.getY() < getHeight()/2.71){
			//this.running = false;
		}*/
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
