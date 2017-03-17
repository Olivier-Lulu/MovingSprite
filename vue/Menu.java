package vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controleur.MenuClick;

public class Menu extends JPanel{

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
		addMouseListener(new MenuClick(this));
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


	public boolean isParam() {
		return param;
	}

	public void setParam(boolean param) {
		this.param = param;
	}

	public boolean isRunning() {
		// TODO Auto-generated method stub
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
