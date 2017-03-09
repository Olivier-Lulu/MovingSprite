package vue;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String fichier = "/data/Sprites/FondFini.png"; 
	
	private boolean running;
	
	private boolean param;
	
	BufferedImage image;
	java.net.URL url = this.getClass().getResource(fichier);
	
	
	public Menu(boolean running, boolean param) {
		addMouseListener(this);
		this.running= running;
		this.param = param;
		try{
			this.image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(image,0,0,getWidth(),getHeight(),0,0,1920,1080,null);	
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getX() > getWidth()/2.42 && arg0.getX() < getWidth()/1.97 && arg0.getY() > getHeight()/3.24 && arg0.getY() < getHeight()/2.71){
			this.running = false;
		}
		if (arg0.getX() > getWidth()/2.42 && arg0.getX() < getWidth()/1.63 && arg0.getY() > getHeight()/2.38 && arg0.getY() < getHeight()/2.12){
			this.param = true;
		}
	}

	public boolean isParam() {
		return param;
	}

	public void setParam(boolean param) {
		this.param = param;
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isRunning() {
		// TODO Auto-generated method stub
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
