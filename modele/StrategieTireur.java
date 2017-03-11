package modele;

import vue.Niveau;
import vue.Sprite;

public class StrategieTireur extends Strategie {

	private int pas = 0;
	private int sens = 1;
	
	public StrategieTireur() {
		super(1, 0, true, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int eval(Entite e, Niveau n){
		//s'il va à gauche c'est pair
		int etat = Physique.move(e, n.entite, n.mob, n.boucliers);
		if (pas < 50){
			pas++;
		}else{
			pas = 0;
			this.deplacement.x = -this.deplacement.x;
			if ( etat % 2 == 0 )
				sens = -1;
			else
				sens = 1;
			tirer(e, n);
		}
		return etat;
	}
	
	private void tirer (Entite e, Niveau n){
		n.boulettes.add(new Entite(e.getPosX(), e.getPosY(),
					10, 10, n, true,
					new Strategie(sens * 10, 0, false, false), new Sprite(n.stock.getSprite(6, 1))));
	}
	
}
