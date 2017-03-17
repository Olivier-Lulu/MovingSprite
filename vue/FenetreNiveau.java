package vue;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controleur.ActionDeplacementPressedD;
import controleur.ActionDeplacementPressedQ;
import controleur.ActionDeplacementPressedZ;
import controleur.ActionDeplacementReleasedD;
import controleur.ActionDeplacementReleasedQ;
import controleur.ActionPressedP;
import controleur.ActionTrace;
import modele.Bouclier;
import modele.Entite;
import modele.EntiteBoulette;
import modele.Niveau;
import modele.StrategieJoueur;

public class FenetreNiveau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Niveau niveau;

	public FenetreNiveau(Niveau n) {

		this.niveau = n;

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("pressed D"), "pressed D");
		this.getActionMap().put(
				"pressed D",
				new ActionDeplacementPressedD((StrategieJoueur) niveau.joueur
						.getStrat()));

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("released D"), "released D");
		this.getActionMap().put(
				"released D",
				new ActionDeplacementReleasedD((StrategieJoueur) niveau.joueur
						.getStrat()));

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("pressed Q"), "pressed Q");
		this.getActionMap().put(
				"pressed Q",
				new ActionDeplacementPressedQ((StrategieJoueur) niveau.joueur
						.getStrat()));

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("released Q"), "released Q");
		this.getActionMap().put(
				"released Q",
				new ActionDeplacementReleasedQ((StrategieJoueur) niveau.joueur
						.getStrat()));

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("pressed Z"), "pressed Z");
		this.getActionMap().put(
				"pressed Z",
				new ActionDeplacementPressedZ((StrategieJoueur) niveau.joueur
						.getStrat()));
		
		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("pressed P"), "pressed P");
		this.getActionMap().put(
				"pressed P",
				new ActionPressedP(this.niveau));

		this.addMouseListener(new ActionTrace(this));
	}

	public void paint(Graphics g) {

		// affichage du décors
		for (int i = 0; i < niveau.entite.length; i++) {
			for (int j = 0; j < niveau.entite[0].length; j++) {
				Entite e = niveau.entite[i][j];
				if (Math.sqrt(Math.pow(e.getPosX() - niveau.joueur.getPosX(),
						2.0)
						+ Math.pow(e.getPosY() - niveau.joueur.getPosY(), 2.0)) <= 500)
					e.rendu(g,
							(300 - niveau.joueur.getWidth())
									- niveau.joueur.getPosX(),
							300 - niveau.joueur.getHeight()
									- niveau.joueur.getPosY(), getWidth(),
							getHeight());
			}
		}

		// affichage des entité du niveau
		Iterator<Entite> it = niveau.mob.iterator();
		while (it.hasNext()) {
			Entite e = it.next();
			if (Math.sqrt(Math.pow(e.getPosX() - niveau.joueur.getPosX(), 2.0)
					+ Math.pow(e.getPosY() - niveau.joueur.getPosY(), 2.0)) <= 500)
				e.rendu(g,
						(300 - niveau.joueur.getWidth())
								- niveau.joueur.getPosX(),
						300 - niveau.joueur.getHeight()
								- niveau.joueur.getPosY(), getWidth(),
						getHeight());
		}

		// affichage du joueur
		niveau.joueur.rendu(g,
				(300 - niveau.joueur.getWidth()) - niveau.joueur.getPosX(), 300
						- niveau.joueur.getHeight() - niveau.joueur.getPosY(),
				getWidth(), getHeight());
		// niveau.joueur.drawDebug(g,(300 - niveau.joueur.getWidth())-
		// niveau.joueur.getPosX(),300 - niveau.joueur.getHeight()-
		// niveau.joueur.getPosY(),getWidth(),getHeight());
		// affichage du tracé du joueur
		Iterator<Bouclier> itBouclier = niveau.boucliers.iterator();
		while (itBouclier.hasNext()) {
			itBouclier.next().rendu(g,
					(300 - niveau.joueur.getWidth()) - niveau.joueur.getPosX(),
					300 - niveau.joueur.getHeight() - niveau.joueur.getPosY(),
					getWidth(), getHeight());
		}

		// affichage des boulettes
		ListIterator<EntiteBoulette> itBoulette = niveau.boulettes
				.listIterator();
		while (itBoulette.hasNext()) {
			itBoulette.next().rendu(g,
					(300 - niveau.joueur.getWidth()) - niveau.joueur.getPosX(),
					300 - niveau.joueur.getHeight() - niveau.joueur.getPosY(),
					getWidth(), getHeight());
		}

		// affichage du hud
		Hud.peindre(g);
	}

	public void tracerLigne(int positionXCLickSouris, int positionYCLickSouris,
			int positionXLacheSouris, int positionYLacheSouris) {
		niveau.tracerLigne(positionXCLickSouris, positionYCLickSouris,
				positionXLacheSouris, positionYLacheSouris, getWidth(),
				getHeight());
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

}
