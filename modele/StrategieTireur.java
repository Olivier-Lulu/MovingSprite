package modele;

import vue.Sprite;

public class StrategieTireur extends Strategie {

	private int pas = 0;
	private int sens = 1;
	private int type;
	
	public StrategieTireur(int type) {
		super(1, 0, true, true);
		// TODO Auto-generated constructor stub
		this.type = type;
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
		n.boulettes.add(new EntiteBoulette(e.getPosX(), e.getPosY(), n, sens, type));
	}
	
}
