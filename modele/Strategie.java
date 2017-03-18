package modele;

import java.awt.Point;

public class Strategie{

	public Point deplacement;// vecteur de deplacement a apliquer pour le
								// prochain appele de eval
	public boolean enVols;
	public boolean estSensibleGravite;

	public Strategie(int deplacementX, int deplacementY, boolean enVols,
			boolean estSensibleGravite) {
		deplacement = new Point(deplacementX, deplacementY);
		this.enVols = enVols;
		this.estSensibleGravite = estSensibleGravite;
	}

	public Strategie(Point deplacement, boolean enVols,
			boolean estSensibleGravite) {
		this.deplacement = deplacement;
		this.enVols = enVols;
		this.estSensibleGravite = estSensibleGravite;
	}

	/*
	 * permet de definir le comportement d'une entite en fonction de son
	 * environnement 
	 */
	public int eval(Entite e, Niveau n) {
		return Physique.move(e, n);
	}

}
