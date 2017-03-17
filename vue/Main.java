package vue;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import modele.Niveau;
import modele.Parametres;

import controleur.Createur;


public class Main{

	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	
	public static void main (String[] args){
		JFrame frame = new JFrame();
		Niveau n = null;
		try {
			n = Createur.creerNiveau("/data/NiveauOlivier.niveau");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Menu m = new Menu (true, false);
		Parametres p = new Parametres(frame,m);
		frame.setSize(WIDTH,HEIGHT);
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		gamethread gt = new gamethread(n,m,frame,p);
		gt.start();
		frame.revalidate();
		SoundThread st = new SoundThread();
		st.start();
	}
}
