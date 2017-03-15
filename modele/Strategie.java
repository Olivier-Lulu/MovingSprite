package modele;

import java.awt.Point;

public class Strategie implements Eval{
	
	public Point deplacement;
	public boolean enVols;
	public boolean estSensibleGravite;
	
	public 	Strategie(int deplacementX, int deplacementY, boolean enVols, boolean estSensibleGravite){
		deplacement = new Point(deplacementX, deplacementY);
		this.enVols = enVols;
		this.estSensibleGravite = estSensibleGravite;
	}
	
	public 	Strategie(Point deplacement, boolean enVols, boolean estSensibleGravite){
		this.deplacement = deplacement;
		this.enVols = enVols;
		this.estSensibleGravite = estSensibleGravite;
	}
	
	@Override
	public int eval(Entite e, Niveau n){
		return Physique.move(e, n);
	}

}
