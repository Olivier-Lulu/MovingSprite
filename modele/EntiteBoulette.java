package modele;

import java.util.LinkedList;
import vue.Sprite;


/*
 * Les EntiteBoulette représentent les boulettes envoyées par les monstres. Elles peuvent être renvoyées par les boucliers tracés par le joueur.
 */
public class EntiteBoulette extends Entite {

	private int ttl = 75;
	//Attribut à savoir si une boulette est mortelle pour les ennemis ou pas.
	private boolean aRebondit = false;
	public LinkedList<Bouclier> boucliersInterdits = new LinkedList<Bouclier>();
	//Le type d'une boulette sert à savoir quel type de bouclier peut la renvoyer
	private int type;
	
	public EntiteBoulette(int posX, int posY, Niveau niveau, int type, int mouvX, int mouvY) {
		super(posX, posY, 15, 15, niveau, true, 
				new Strategie(mouvX, mouvY, false, false), 
				new Sprite(niveau.stock.getSprite(3, type+2)), 0);
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
	
	public void rebondit() {
		aRebondit = true;
	}
	
	//Une boulette ne peut tuer les ennemis que lorsqu'elle a rebondit au moins une fois sur un bouclier.
	public boolean tueLesMobs (){
		return aRebondit;
	}
	
	public int getType (){
		return type;
	}
			
}
