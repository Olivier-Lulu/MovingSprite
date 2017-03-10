package modele;

import java.awt.Point;

public class StrategiePatrouille extends Strategie {
	
	private int taillePatrouille;//nombre de pas a faire dans une direction avant de faire demi-tours
	private int pas = 0;// nombre de pas effecuer depuis le dernier demi-tours
	private int frequenceSaut;//frequence a laquelle il faut faire un saut
	private int hauteurSaut;// vitesse vertical a donner lors des saut
	
	public StrategiePatrouille(Point deplacement, int taillePatrouille, int frequenceSaut, int hauteurSaut) {
		super(deplacement, true, true);
		this.taillePatrouille = taillePatrouille;
		this.frequenceSaut = frequenceSaut;
		this.hauteurSaut = hauteurSaut;
	}

	@Override
	public int eval(Entite e, Niveau n){
		if(frequenceSaut != 0 && pas%frequenceSaut == 0 && !enVols)
			deplacement.y = hauteurSaut;
		if(pas == taillePatrouille){
			deplacement.x = deplacement.x*-1;
			pas = 0;
		}else
			pas++;
		return super.eval(e, n);
	}
	
	

}
