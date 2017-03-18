package vue;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Mana;
import modele.Score;

public class GameThread extends Thread {

	FenetreNiveau fenetreNiveau = null;
	Menu menu;
	JFrame frame;
	Parametres param;
	SelectNiveau selectNiveau;
	/*
	 * Le papaPanel est le JPanel principal dans lequel le cardLayout sera mit en place.
	 * Le cardLayout comprendra tous les autres JPanel.
	 */
	final JPanel papaPanel = new JPanel();
	final CardLayout card = new CardLayout();

	public GameThread(Menu m, JFrame frame, Parametres p, SelectNiveau sn) {
		this.menu = m;
		this.frame = frame;
		this.param = p;
		this.selectNiveau = sn;
	}

	public void run() {
		SoundThread stMenu = new SoundThread("/data/Sprites/Musique3.wav");
		stMenu.start();
		papaPanel.setLayout(card);
		papaPanel.add(menu, "un");
		papaPanel.add(param, "deux");
		papaPanel.add(selectNiveau, "trois");
		this.frame.setContentPane(papaPanel);
		this.frame.validate();
		card.show(papaPanel, "un");
		/*
		 * Cette boucle permet de repaint le JPanel Menu tant qu'il est actif.
		 */
		while (menu.isRunning() && !this.isInterrupted()) {
			menu.repaint();
			/*
			 * Cette boucle permet de repaint le JPanel Parametres tant qu'il est actif.
			 */
			while (menu.isParam() && !this.isInterrupted()) {
				card.show(papaPanel, "deux");
				param.repaint();
				try {
					Thread.sleep(15);
				} catch (Exception e) {
					this.interrupt();
				}
			}
			if (!menu.isParam() && this.frame.getContentPane().equals(papaPanel)) {
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
		 * au sélecteur de niveau.
		 */
		SoundThread stgame = null;
		while (true) {
			selectNiveau.repaint();
			if (selectNiveau.getNiveauRunning() != null) {
				fenetreNiveau = new FenetreNiveau(selectNiveau.getNiveauRunning());
				papaPanel.add(fenetreNiveau, "quatre");
				stMenu.interrupt();
				stgame = new SoundThread("/data/Sprites/Musique2.wav");
				stgame.start();
			}
			/*
			 * Cette boucle permet de repaint le niveau attribué à la 
			 * fenêtre de niveau tant qu'il est actif.
			 */
			while (fenetreNiveau != null && fenetreNiveau.getNiveau() != null) {
				card.show(papaPanel, "quatre");
				fenetreNiveau.repaint();
				if (fenetreNiveau.getNiveau().peutBouger())
					fenetreNiveau.getNiveau().bouger();
				try {
					Thread.sleep(15);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (fenetreNiveau.getNiveau().aFini){
					fenetreNiveau =null;
					Mana.manaReinit();
					Score.scoreReinit();
					selectNiveau.setNiveauRunning(null);
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
