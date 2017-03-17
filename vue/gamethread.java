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
		papaPanel.setLayout(card);
		papaPanel.add(m, "un");
		papaPanel.add(p, "deux");
		papaPanel.add(sn, "trois");
		long dernier = System.currentTimeMillis();
		int images = 0;
		this.frame.setContentPane(papaPanel);
		this.frame.validate();
		card.show(papaPanel, "un");
		/*
		 * Cette boucle permet de repaint le JPanel menu tant qu'il est actif.
		 */
		while (m.isRunning() && !this.isInterrupted()) {
			long maintenant = System.currentTimeMillis();
			m.repaint();
			images++;
			if (maintenant - dernier > 1000) {
				dernier = maintenant;
				System.out.println(images + " images par seconde");
				images = 0;
			}
			/*
			 * Cette boucle permet de repaint le JPanel parametres tant qu'il est actif.
			 */
			while (m.isParam() && !this.isInterrupted()) {
				maintenant = System.currentTimeMillis();
				card.show(papaPanel, "deux");
				p.repaint();
				images++;
				if (maintenant - dernier > 1000) {
					dernier = maintenant;
					System.out.println(images + " images par seconde");
					images = 0;
				}
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
		while (true) {
			sn.repaint();
			if (sn.getNiveauRunning() != null) {
				fn = new FenetreNiveau(sn.getNiveauRunning());
				papaPanel.add(fn, "quatre");
			}
			/*
			 * Cette boucle permet de repaint le niveau attribué à la 
			 * fenêtre de niveau tant qu'il est actif.
			 */
			while (fn != null && fn.getNiveau() != null) {
				card.show(papaPanel, "quatre");
				long maintenant = System.currentTimeMillis();
				fn.repaint();
				if (fn.getNiveau().peutBouger())
					fn.getNiveau().bouger();
				images++;
				if (maintenant - dernier > 1000) {
					dernier = maintenant;
					// System.out.println(images + " images par seconde");
					images = 0;
				}
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
