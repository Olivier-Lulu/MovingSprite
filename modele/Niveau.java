package modele;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;
import vue.SpriteStocker;

public class Niveau{


	public SpriteStocker stock;

	public Entite[][] entite;
	public LinkedList<Entite> mob;
	public Entite joueur;
	public LinkedList<Bouclier> boucliers = new LinkedList<Bouclier>();
	public LinkedList<EntiteBoulette> boulettes = new LinkedList<EntiteBoulette>();
	
	public Niveau(SpriteStocker stock, Entite[][] entite, LinkedList<Entite> mob, Entite joueur) {
		super();
		this.stock = stock;
		this.entite = entite;
		this.mob = mob;
		this.joueur = joueur;		
		
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
		ListIterator<Bouclier> itBouclier = boucliers.listIterator();
		while (itBouclier.hasNext()){
			Bouclier pointeur = itBouclier.next();
			pointeur.disparitionNaturelle();
			if (pointeur.doitDeceder())
				boucliers.remove(pointeur);
		}
		
		//évalutation des boulettes
		ListIterator<EntiteBoulette> itBoulette = boulettes.listIterator();
		while(itBoulette.hasNext()){
			EntiteBoulette boulette = itBoulette.next();
			boulette.eval();
			if (boulette.doitDeceder()){
				boulettes.remove(boulette);
			}
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
		
		//la liste qui va servir à enregistrer un tracé pour en faire un bouclier
		LinkedList<Entite> trace = new LinkedList<Entite>();

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
		
		int typeTrace = (positionYCLickSouris - positionYLacheSouris < 0) ? 1 : 2;
		
		int curseurX =(positionXCLickSouris*600)/width - (300 - joueur.getWidth() - joueur.getPosX());
		int curseurY =(positionYCLickSouris*600)/height - (300 - joueur.getHeight() - joueur.getPosY());
		
		//si l'ecart d'ordonnee est plus grand, les blocks s'empilent
		//verticalement
		if(Mana.aMana()){
			if (deltaX <= deltaY){
				if (deltaY < StrategieTrace.tailleBlockTrace)
					return;
				int decalageHorizontal = deltaX / (deltaY / StrategieTrace.tailleBlockTrace);

				for (int i = 0; i < deltaY; i += StrategieTrace.tailleBlockTrace){
					if(Mana.aMana()){
						Mana.manaBaisse();
						trace.add(new EntiteTrace(
								curseurX, curseurY, 
								this, new StrategieTrace(), typeTrace));
					}

					//il faut toujours tracer du click vers le lache
					if (positionXCLickSouris <= positionXLacheSouris){
						curseurX += decalageHorizontal;
					}else{
						curseurX -= decalageHorizontal;
					}

					if (positionYCLickSouris <= positionYLacheSouris){
						curseurY += StrategieTrace.tailleBlockTrace;
					}else{
						curseurY -= StrategieTrace.tailleBlockTrace;
					}
				}
				
				
				boucliers.add(new Bouclier(trace, typeTrace));

				//si l'ecart d'abscisse est plus grand, les blocks s'empilent
				//horizontalement
			}else{

				if (deltaX < StrategieTrace.tailleBlockTrace)
					return;
				int decalageVertical = deltaY / (deltaX / StrategieTrace.tailleBlockTrace);

				for (int i = 0; i < deltaX; i += StrategieTrace.tailleBlockTrace){
					if(Mana.aMana()){
						Mana.manaBaisse();
						trace.add(new EntiteTrace(
								curseurX, curseurY, 
								this, new StrategieTrace(), typeTrace));
					}

					//il faut toujours tracer du click vers le lache
					if (positionXCLickSouris <= positionXLacheSouris){
						curseurX += StrategieTrace.tailleBlockTrace;
					}else{
						curseurX -= StrategieTrace.tailleBlockTrace;
					}

					if (positionYCLickSouris <= positionYLacheSouris){
						curseurY += decalageVertical;
					}else{
						curseurY -= decalageVertical;
					}
				}
				boucliers.add(new Bouclier(trace, typeTrace));
			}
		}
	}
	
}
