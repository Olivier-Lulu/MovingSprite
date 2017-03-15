package modele;

import java.util.List;
import modele.Entite;

public class StrategieTrace extends Strategie implements Eval {

	public StrategieTrace() {
		super(0, 0, false, false);
		// TODO Auto-generated constructor stub
	}

	public int eval(Entite e,Entite [][] tiles, List<Entite> l){
		if ( ((EntiteTrace)e).doitDeceder() )
			e.deces();
		return 1;
	}
}
