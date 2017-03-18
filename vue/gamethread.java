package vue;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Mana;
import modele.Score;

public class gamethread extends Thread {

	FenetreNiveau fn = null;
	Menu m;
	JFrame frame;
	Parametres p;
	SelectNiveau sn;
	/*
	 * Le papaPanel est le JPanel principal dans lequel le cardLayout sera mis en place.
	 * Le cardLayout comprendra tous les autres JPanel.
	 */
	final JPanel papaPanel = new JPanel();
	final CardLayout card = new CardLayout();

	public gamethread(Menu m, JFrame frame, Parametres p, SelectNiveau sn) {
		this.m = m;
		this.frame = frame;
		this.p = p;
		this.sn = sn;
	}

	public void run() {
		SoundThread stMenu = new SoundThread("/data/Sprites/Musique3.wav");
		stMenu.start();
		papaPanel.setLayout(card);
		papaPanel.add(m, "un");
		papaPanel.add(p, "deux");
		papaPanel.add(sn, "trois");
		this.frame.setContentPane(papaPanel);
		this.frame.validate();
		card.show(papaPanel, "un");
		/*
		 * Cette boucle permet de repaint le JPanel menu tant qu'il est actif.
		 */
		while (m.isRunning() && !this.isInterrupted()) {
			m.repaint();
			/*
			 * Cette boucle permet de repaint le JPanel parametres tant qu'il est actif.
			 */
			while (m.isParam() && !this.isInterrupted()) {
				card.show(papaPanel, "deux");
				p.repaint();
				try {
					Thread.sleep(15);
				} catch (Exception e) {
					this.interrupt();
				}
			}
			if (!m.isParam() && this.frame.getContentPane().equals(papaPanel)) {
				card.show(papaPanel, "un");
			}
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				this.interrupt();
			}
		}

		card.show(papaPanel, "trois");
		/*
		 * Cette boucle permet de repaint le sélecteur de niveau tant qu'il est actif.
		 * La boucle est infinie car lors d'une fin de niveau on retourne obligatoirement 
		 * au sélecteur de niveau
		 */
		SoundThread stgame = null;
		while (true) {
			sn.repaint();
			if (sn.getNiveauRunning() != null) {
				fn = new FenetreNiveau(sn.getNiveauRunning());
				papaPanel.add(fn, "quatre");
				stMenu.interrupt();
				stgame = new SoundThread("/data/Sprites/Musique2.wav");
				stgame.start();
			}
			/*
			 * Cette boucle permet de repaint le niveau attribué à la 
			 * fenêtre de niveau tant qu'il est actif.
			 */
			while (fn != null && fn.getNiveau() != null) {
				card.show(papaPanel, "quatre");
				fn.repaint();
				if (fn.getNiveau().peutBouger())
					fn.getNiveau().bouger();
				try {
					Thread.sleep(15);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (fn.getNiveau().aFini){
					fn =null;
					Mana.manaReinit();
					Score.scoreReinit();
					sn.setNiveauRunning(null);
					card.show(papaPanel, "trois");
					stgame.interrupt();
					stMenu = new SoundThread("/data/Sprites/Musique3.wav");
					stMenu.start();
				}
			}
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				this.interrupt();
			}
		}
	}
}
