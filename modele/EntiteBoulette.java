package modele;

import java.util.LinkedList;
import vue.Sprite;

public class EntiteBoulette extends Entite {

	private int ttl = 75;
	private boolean aRebondit = false;
	public LinkedList<Bouclier> boucliersInterdits = new LinkedList<Bouclier>();
	private int type;
	
	public EntiteBoulette(int posX, int posY, Niveau niveau, int sens, int type) {
		super(posX, posY, 15, 15, niveau, true, 
				new Strategie(sens * 10, 0, false, false), 
				new Sprite(niveau.stock.getSprite(6, type+2)), 0);
		// TODO Auto-generated constructor stub
		this.type = type;
	}

	public boolean doitDeceder (){
		if (ttl < 1){
			return true;
		}
		ttl--;
		return false;
	}
	
	public void aRebondit() {
		aRebondit = true;
	}
	
	public boolean tueLesMobs (){
		return aRebondit;
	}
	
	public int getType (){
		return type;
	}
			
}
