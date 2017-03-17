package vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


import controleur.ParamClick;

public class Parametres extends JPanel {

	/*
	 * Cette classe est un Conponent qui permet l'affichage des paramètres.
	 */
	
	private static final long serialVersionUID = 1L;

	/*
	 * Permet de récuperer le chemin d'accès vers l'image de fond.
	 */
	private static String fichier = "/data/Sprites/Parametres.png";
	private java.net.URL url = this.getClass().getResource(fichier);

	BufferedImage image;
	
	private JFrame frame;

	private Menu m;

	public Parametres(JFrame frame, Menu m) {
		addMouseListener(new ParamClick(this));
		this.frame = frame;
		this.setM(m);
		try {
			this.image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, 800, 800, null);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Menu getM() {
		return m;
	}

	public void setM(Menu m) {
		this.m = m;
	}
}