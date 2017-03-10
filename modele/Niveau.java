package modele;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;

import vue.Sprite;
import vue.SpriteStocker;

public class Niveau{

	public SpriteStocker stock;

	public Entite[][] entite;
	public LinkedList<Entite> mob;
	public Entite joueur;
	public LinkedList<EntiteTrace> trace = new LinkedList<EntiteTrace>();
	public LinkedList<Entite> boulettes = new LinkedList<Entite>();
	
	public Niveau(SpriteStocker stock, Entite[][] entite, LinkedList<Entite> mob, Entite joueur) {
		super();
		this.stock = stock;
		this.entite = entite;
		this.mob = mob;
		this.joueur = joueur;
		
		mob.add(new Entite(200, 1000, 25, 25, this, true, new StrategieTireur(), new Sprite("/data/Sprites/goblin.png"),0));
		
	}

	/*
	 * force les entités du niveau a éffectuer leur action
	 */
	public void bouger(){
		// évaluation des ennemis
		Iterator<Entite> it = mob.iterator();
		while(it.hasNext()){
			it.next().eval();
		}
		
		//évaluation du joueur
		joueur.eval();
		
		//évaluation du tracé
		ListIterator<EntiteTrace> itTrace = trace.listIterator();
		for (int i =0;i<trace.size();i++){
			if (itTrace.next().doitDeceder()){
				itTrace.previous();
				Mana.manaHausse();
				itTrace.remove();
			}
		}
		
		//évalutation des boulettes
		ListIterator<Entite> itBoulette = boulettes.listIterator();
		while(itBoulette.hasNext()){
			itBoulette.next().eval();
		}
		
	}
	
	public Entite ajouterEntite(Entite e){
		entite[e.getPosX()/25][e.getPosY()/25] = e;
		//System.out.println(" entite contenu dans la case "+ e.getPosX()/25+ ","+e.getPosY()/25 + " = " + e.getId());
		return e;
	}
	
	/*
	 * utiliser pour supprimer un ennemis ou repositionner le joueur
	 */
	public void supprimerEntite(Entite e){
		if(joueur.equals(e)){
			joueur.setPosition(100, 400);
		}else
			if(mob.contains(e)){
				mob.remove(e);
			}
	}

	/*
	 * créer le tracer protegeant des boulettes
	 */
	public void tracerLigne (int positionXCLickSouris ,int positionYCLickSouris ,
			int positionXLacheSouris ,int positionYLacheSouris,
			int width, int height){
		//determiner le plus grand des décalages de coordonnees
		int deltaX;
		if (positionXCLickSouris <= positionXLacheSouris){
			deltaX = positionXLacheSouris - positionXCLickSouris;
		}else{
			deltaX = positionXCLickSouris - positionXLacheSouris;
		}
		
		int deltaY;
		if (positionYCLickSouris < positionYLacheSouris){
			deltaY = positionYLacheSouris - positionYCLickSouris;
		}else{
			deltaY = positionYCLickSouris - positionYLacheSouris;
		}
		
		int curseurX =(positionXCLickSouris*600)/width - (300 - joueur.getWidth() - joueur.getPosX());
		int curseurY =(positionYCLickSouris*600)/height - (300 - joueur.getHeight() - joueur.getPosY());
		
		//si l'ecart d'ordonnee est plus grand, les blocks s'empilent
		//verticalement
		if (deltaX <= deltaY){
			if (deltaY < EntiteTrace.tailleBlockTrace)
				return;
			int decalageHorizontal = deltaX / (deltaY / EntiteTrace.tailleBlockTrace);
						
			for (int i = 0; i < deltaY; i += EntiteTrace.tailleBlockTrace){
				if(Mana.aMana()){
					Mana.manaBaisse();
					trace.add(new EntiteTrace(curseurX, curseurY, 
							this, new StrategieTrace(),
							stock.get("4")));
				}
				
				//il faut toujours tracer du click vers le lache
				if (positionXCLickSouris <= positionXLacheSouris){
					curseurX += decalageHorizontal;
				}else{
					curseurX -= decalageHorizontal;
				}
				
				if (positionYCLickSouris <= positionYLacheSouris){
					curseurY += EntiteTrace.tailleBlockTrace;
				}else{
					curseurY -= EntiteTrace.tailleBlockTrace;
				}
			}
		
			
		//si l'ecart d'abscisse est plus grand, les blocks s'empilent
		//horizontalement
		}else{
			if (deltaX < EntiteTrace.tailleBlockTrace)
				return;
			int decalageVertical = deltaY / (deltaX / EntiteTrace.tailleBlockTrace);

			for (int i = 0; i < deltaX; i += EntiteTrace.tailleBlockTrace){
				if(Mana.aMana()){
					Mana.manaBaisse();
					trace.add(new EntiteTrace(curseurX, curseurY,
							this, new StrategieTrace(), 
							stock.get("4")));
				}

				//il faut toujours tracer du click vers le lache
				if (positionXCLickSouris <= positionXLacheSouris){
					curseurX += EntiteTrace.tailleBlockTrace;
				}else{
					curseurX -= EntiteTrace.tailleBlockTrace;
				}

				if (positionYCLickSouris <= positionYLacheSouris){
					curseurY += decalageVertical;
				}else{
					curseurY -= decalageVertical;
				}
			}
		}

	}
	
}
