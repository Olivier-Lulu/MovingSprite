package modele;

import java.awt.Point;

public class StrategieTireur extends StrategiePatrouille {

	// Le sens ou direction du mouvement du tireur (en terme d'abscisse)
	private int sens = 1;
	private int frequenceTire;
	private int type;
	private int mouvXTir;
	private int mouvYTir;
	private int pas = 1;

	public StrategieTireur(int frequenceTire, int type, Point deplacement,
			int taillePatrouille, int frequenceSaut, int hauteurSaut,
			int mouvX, int mouvY) {
		super(deplacement, taillePatrouille, frequenceSaut, hauteurSaut);
		this.type = type;
		this.frequenceTire = frequenceTire;
		mouvXTir = mouvX;
		mouvYTir = mouvY;
	}

	@Override
	public int eval(Entite e, Niveau n) {
		// s'il va à gauche c'est pair
		int etat = super.eval(e, n);
		if (pas % frequenceTire == 0) {
			if (etat % 2 == 0) {
				if (etat != 0)
					sens = -1;
			} else
				sens = 1;
			tirer(e, n);
			etat = 6;
		}
		pas++;
		if (etat == 0)
			if (mouvXTir < 0)
				return 2;
			else
				return 1;
		else
			return etat;
	}

	private void tirer(Entite e, Niveau n) {
		n.boulettes.add(new EntiteBoulette(e.getPosX(), e.getPosY(), n, type,
				sens * mouvXTir, mouvYTir));
	}
}
