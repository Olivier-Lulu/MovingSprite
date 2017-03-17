package modele;

public class StrategieJoueur extends Strategie {

	public StrategieJoueur() {
		super(0, 0, true, true);
	}

	@Override
	public int eval(Entite e, Niveau n) {
		return Physique.move(e, n);
	}

	/*
	 * les methodes deplacement permettent de faire varier la vitesse du joueur
	 */
	public void deplacementDroitDebut() {
		if (deplacement.x < 1) {
			deplacement.x = deplacement.x + 2;
		}
	}

	public void deplacementDroitFin() {
		if (deplacement.x > 0) {
			deplacement.x = deplacement.x - 2;
		}
	}

	public void deplacementGaucheDebut() {
		if (deplacement.x > -1) {
			deplacement.x = deplacement.x - 2;
		}
	}

	public void deplacementGaucheFin() {
		if (deplacement.x < -1) {
			deplacement.x = deplacement.x + 2;
		}
	}

	// Le joueur peut sauter environ 5 block et demi
	public void saut() {
		if (!enVols)
			deplacement.y = -18;
	}
}
