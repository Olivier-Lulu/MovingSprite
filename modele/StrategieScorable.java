package modele;

public class StrategieScorable extends Strategie {
	/*
	 * cette classe est utiliser comme strategie pour les objet
	 */
	
	public StrategieScorable() {
		super(0, 0, false, false);
	}
	
	@Override
	public int eval(Entite e,Niveau n){
		return 1;
	}
}
