package modele;

import vue.Sprite;

public class EntiteCheckPoint extends Entite {

	public EntiteCheckPoint(int posX, int posY, Niveau niveau, Strategie strat, Sprite sprite) {
		super(posX, posY, 25, 50, niveau, false, new Strategie(0, 0, false, false), sprite, 0);
		// TODO Auto-generated constructor stub
	}

}
