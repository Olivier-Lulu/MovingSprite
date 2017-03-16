package modele;

import java.awt.Point;

public class StrategieTireur extends StrategiePatrouille{

	//Le sens ou direction du mouvement du tireur (en terme d'abscisse)
	private int sens = 1;
	private int frequenceTire;
	private int type;
	
	public StrategieTireur(int frequenceTire,int type ,Point deplacement, int taillePatrouille, int frequenceSaut, int hauteurSaut) {
		super(deplacement, taillePatrouille, frequenceSaut, hauteurSaut);
		this.type = type;
		this.frequenceTire = frequenceTire;
	}

	@Override
	public int eval(Entite e, Niveau n){
		//s'il va à gauche c'est pair
		int etat = super.eval(e, n);
		if (pas % frequenceTire == 1){
			if ( etat % 2 == 0 )
				sens = -1;
			else
				sens = 1;
			tirer(e, n);
			etat = 6;
		}
		return etat;
	}
	
	private void tirer (Entite e, Niveau n){
		n.boulettes.add(new EntiteBoulette(e.getPosX(), e.getPosY(), n, sens, type));
	}
}
