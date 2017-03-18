package modele;

import java.awt.Point;

public class StrategiePatrouille extends Strategie {

	private int taillePatrouille;// nombre de pas a faire dans une direction avant de faire demi-tour
	private int pas = 1;// nombre de pas effectués depuis le dernier demi-tour
	private int frequenceSaut;// fréquence à laquelle il faut faire un saut
	private int hauteurSaut;// vitesse verticale à donner lors des sauts

	public StrategiePatrouille(Point deplacement, int taillePatrouille,
			int frequenceSaut, int hauteurSaut) {
		super(deplacement, true, true);
		this.taillePatrouille = taillePatrouille;
		this.frequenceSaut = frequenceSaut;
		this.hauteurSaut = hauteurSaut;
	}

	@Override
	public int eval(Entite e, Niveau n) {
		if (frequenceSaut != 0 && pas % frequenceSaut == 0 && !enVols)
			deplacement.y = hauteurSaut;
		if (pas == taillePatrouille) {
			deplacement.x = deplacement.x * -1;
			pas = 1;
		} else
			pas++;
		return super.eval(e, n);
	}

}
