package modele;

public class StrategieScorable extends Strategie {
	/*
	 * cette classe est utilisée comme strategie pour les objets ne servant qu'a
	 * augmenter le score
	 */

	public StrategieScorable() {
		super(0, 0, false, false);
	}

	@Override
	public int eval(Entite e, Niveau n) {
		return 1;
	}
}
