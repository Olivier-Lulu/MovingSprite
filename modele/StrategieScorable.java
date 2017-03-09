package modele;

import java.util.List;

public class StrategieScorable extends Strategie {

	public StrategieScorable() {
		super(0, 0, false, false);
		// TODO Auto-generated constructor stub
	}
	
	public int eval(Entite e,Entite [][] tiles, List<Entite> l){
		return 1;
	}
}
