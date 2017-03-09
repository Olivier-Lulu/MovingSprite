package modele;

import vue.Hud;
import vue.Niveau;
import vue.Sprite;

public class ScorableEntite extends Entite {
	
	private int valeurScore;

	public ScorableEntite(int posX, int posY, int width, int height, Niveau niveau,	Sprite sprite, int valeurScore) {
		super(posX, posY, width, height, niveau, false, new StrategieScorable(), sprite);
		this.valeurScore = valeurScore;
	}
	
	public void deces(Entite e){
		Hud.augmenterScore(valeurScore);
		super.deces(null);
	}
}
