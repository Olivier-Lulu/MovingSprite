package vue;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modele.Niveau;

import controleur.SelectNiveauClick;

public class SelectNiveau extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fichier = "/data/Sprites/SelectNiveau.jpg";
	
	BufferedImage image;
	
	java.net.URL url = this.getClass().getResource(fichier);
	
	private int largeurString;

	private Niveau NiveauRunning = null;
	
	Rectangle [] zc;
	
	ArrayList<String> NomNiveau = new ArrayList<String>();

	public SelectNiveau() {
		addMouseListener(new SelectNiveauClick(this));
		int temp = 0;
		URL url2 = this.getClass().getResource("/data");
		File dossier = new File(url2.getPath());
		File [] niveauTab = dossier.listFiles();
		for (int i = 0; i< niveauTab.length;i++){
			String name = niveauTab[i].getName();
			if (name.substring(name.lastIndexOf(".")+1).equals("niveau")){
				NomNiveau.add(temp, name);
				temp++;
			}
		}
		try{
			this.image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		int y =getHeight()/10;
		zc = new Rectangle [NomNiveau.size()];
		g.drawImage(image,0,0,getWidth(),getHeight(),0,0,1600,1200,null);
		g.setColor(Color.white);
		g.setFont(new Font("Sans",0,18));
		String s = " Selectionnez un niveau : ";
		setLargeurString(g.getFontMetrics().stringWidth(s));
		g.drawString("Selectionnez un niveau :", getWidth()/10, y);
		for (int i = 1 ; i <= NomNiveau.size(); i++){
			y += 25;
			String s2 = ("Niveau " + i );
			g.drawString(s2,getWidth()/10 + getLargeurString() + 5 , y );
			Rectangle2D r = g.getFontMetrics().getStringBounds(s2,g);
			zc[i-1] = new Rectangle((int)r.getMinX()+getWidth()/10 + getLargeurString() + 5, (int)r.getMinY()+y, (int)r.getWidth(), (int)r.getHeight());
		}
	}

	public int getLargeurString() {
		return largeurString;
	}

	public void setLargeurString(int largeurString) {
		this.largeurString = largeurString;
	}

	public Rectangle2D[] getZc() {
		return zc;
	}

	public ArrayList<String> getNomNiveau() {
		return NomNiveau;
	}

	public Niveau getNiveauRunning() {
		return NiveauRunning;
	}

	public void setNiveauRunning(Niveau niveauRunning) {
		NiveauRunning = niveauRunning;
	}
	
}
