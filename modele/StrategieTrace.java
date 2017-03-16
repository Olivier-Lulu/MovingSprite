package modele;

import java.util.List;
import modele.Entite;

public class StrategieTrace extends Strategie implements Eval {
	
	private int ttl = 150;
	public static final int tailleBlockTrace = 5;

	public StrategieTrace() {
		super(0, 0, false, false);
		// TODO Auto-generated constructor stub
	}

	public int eval(Entite e,Entite [][] tiles, List<Entite> l){
		if (ttl < 1){
			e.deces(null);
		}
		ttl--;
		return 1;
	}
	
	public boolean doitDeceder(){
		if (ttl < 1){
			return true;
		}
		ttl--;
		return false;
	}
}
