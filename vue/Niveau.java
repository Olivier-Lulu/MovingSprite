package vue;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controleur.ActionDeplacementPressedD;
import controleur.ActionDeplacementPressedQ;
import controleur.ActionDeplacementPressedZ;
import controleur.ActionDeplacementReleasedD;
import controleur.ActionDeplacementReleasedQ;
import controleur.ActionTrace;
import modele.Bouclier;
import modele.Entite;
import modele.EntiteBoulette;
import modele.EntiteTrace;
import modele.StrategieJoueur;
import modele.StrategieTireur;
import modele.StrategieTrace;

public class Niveau extends JPanel{
	
	private static final long serialVersionUID = 1L;

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
		
		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed D"), "pressed D");
		this.getActionMap().put("pressed D",new ActionDeplacementPressedD((StrategieJoueur)joueur.getStrat())  );

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "released D");
		this.getActionMap().put("released D",new ActionDeplacementReleasedD((StrategieJoueur)joueur.getStrat())  );

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed Q"), "pressed Q");
		this.getActionMap().put("pressed Q",new ActionDeplacementPressedQ((StrategieJoueur)joueur.getStrat())  );

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released Q"), "released Q");
		this.getActionMap().put("released Q",new ActionDeplacementReleasedQ((StrategieJoueur)joueur.getStrat())  );

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed Z"), "pressed Z");
		this.getActionMap().put("pressed Z",new ActionDeplacementPressedZ((StrategieJoueur)joueur.getStrat())  );

		this.addMouseListener(new ActionTrace(this));
		
		mob.add(new Entite(200, 1000, 25, 25, this, true, new StrategieTireur(2), new Sprite(stock.getSprite(2, 4))));
		
	}

	@Override
	public void paint(Graphics g){
		
		// affichage du décors
		for (int i =0;i < entite.length;i++){
			for(int j = 0 ; j < entite[0].length;j++){	
				Entite e = entite[i][j];
				if(Math.sqrt(Math.pow(e.getPosX()-joueur.getPosX(),2.0) +  Math.pow(e.getPosY()- joueur.getPosY(),2.0))<=500)
					e.rendu(g,(300 -joueur.getWidth())-joueur.getPosX(),300 -joueur.getHeight()-joueur.getPosY(),getWidth(),getHeight());
			}
		}
		
		// affichage des entité du niveau
		Iterator<Entite> it = mob.iterator();
		while(it.hasNext()){
			Entite e = it.next();
			if(Math.sqrt(Math.pow(e.getPosX()-joueur.getPosX(),2.0) +  Math.pow(e.getPosY()- joueur.getPosY(),2.0))<=500)
				e.rendu(g,(300 -joueur.getWidth())-joueur.getPosX(),300 -joueur.getHeight()-joueur.getPosY(),getWidth(),getHeight());
		}
		
		//affichage du joueur
		joueur.rendu(g,(300 -joueur.getWidth())-joueur.getPosX(),
			300 -joueur.getHeight()-joueur.getPosY(),getWidth(),getHeight());
		//joueur.drawDebug(g,(300 -joueur.getWidth())-joueur.getPosX(),300 -joueur.getHeight()-joueur.getPosY(),getWidth(),getHeight());
		
		//affichage des boucliers tracés  joueur
		for (Bouclier boubou : boucliers){
			boubou.rendu(g, (300 -joueur.getWidth())-joueur.getPosX(),
					300 -joueur.getHeight()-joueur.getPosY(), 
					getWidth(), 
					getHeight());
		}
		
		//affichage des boulettes
		ListIterator<EntiteBoulette> itBoulette = boulettes.listIterator();
		while(itBoulette.hasNext()){
			itBoulette.next().rendu(g,
					(300 -joueur.getWidth())-joueur.getPosX(),
					300 -joueur.getHeight()-joueur.getPosY(),
					getWidth(),
					getHeight());
		}

		//affichage du hud
		Hud.peindre(g);
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
	public void tracerLigne (int positionXCLickSouris ,int positionYCLickSouris ,int positionXLacheSouris ,int positionYLacheSouris ){
		
		//la liste qui va servir à enregistrer un tracé pour en faire un bouclier
		LinkedList<EntiteTrace> trace = new LinkedList<EntiteTrace>();
		
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
		
		int curseurX =(positionXCLickSouris*600)/getWidth() - (300 - joueur.getWidth() - joueur.getPosX());
		int curseurY =(positionYCLickSouris*600)/getHeight() - (300 - joueur.getHeight() - joueur.getPosY());
		
		//si l'ecart d'ordonnee est plus grand, les blocks s'empilent
		//verticalement
		if(Hud.aMana()){
			if (deltaX <= deltaY){
				if (deltaY < EntiteTrace.tailleBlockTrace)
					return;
				int decalageHorizontal = deltaX / (deltaY / EntiteTrace.tailleBlockTrace);

				for (int i = 0; i < deltaY; i += EntiteTrace.tailleBlockTrace){
					if(Hud.aMana()){
						Hud.manaBaisse();
						trace.add(new EntiteTrace(curseurX, curseurY, 
								this, new StrategieTrace(),
								stock.get("4"), typeTrace));
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
				
				
				boucliers.add(new Bouclier(trace, typeTrace));

				//si l'ecart d'abscisse est plus grand, les blocks s'empilent
				//horizontalement
			}else{

				if (deltaX < EntiteTrace.tailleBlockTrace)
					return;
				int decalageVertical = deltaY / (deltaX / EntiteTrace.tailleBlockTrace);

				for (int i = 0; i < deltaX; i += EntiteTrace.tailleBlockTrace){
					if(Hud.aMana()){
						Hud.manaBaisse();
						trace.add(new EntiteTrace(curseurX, curseurY,
								this, new StrategieTrace(), 
								stock.get("4"), typeTrace));
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
				boucliers.add(new Bouclier(trace, typeTrace));
			}
		}
	}
	
}
