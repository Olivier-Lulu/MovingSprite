package modele;

import vue.Sprite;

public class EntiteTrace extends Entite{

	private int ttl = 150;
	public static int tailleBlockTrace = 5;
	
	public EntiteTrace(int posX, int posY, Niveau niveau,
			Strategie strat, Sprite sprite, int typeTrace) {
		super(posX, posY, tailleBlockTrace, tailleBlockTrace, niveau, false, strat, sprite, 0);
	}

	public boolean doitDeceder (){
		if (ttl < 1){
			return true;
		}
		ttl--;
		return false;
	}
	
}
