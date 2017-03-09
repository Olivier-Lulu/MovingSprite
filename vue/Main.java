package vue;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import modele.Parametres;

import controleur.Createur;


public class Main{

	
	public static void main (String[] args){
		JFrame frame = new JFrame();
		Niveau n = null;
		try {
			n = Createur.creerNiveau("/data/Level1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Menu m = new Menu (true,false);
		Parametres p = new Parametres();
		frame.setSize(600,600);
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		frame.setVisible(true);
		(new gamethread(n,m,frame,p)).start();
		frame.revalidate();
		System.out.println("pppp");
		
		(new SoundThread(n)).play();
	}
}
