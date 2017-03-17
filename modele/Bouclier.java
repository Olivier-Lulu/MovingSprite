package modele;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;

/*!
 * La classe bouclier sert à stocker la liste qui représente ce bouclier et à ainsi centraliser tous les calculs nécessaires 
 * 	à celui du rebond des boulettes sur les boucliers du joueur.
 * 
 * Pour calculer le rebond d'une boulette, on procède de la sorte :
 * 		- on calcule la pente du tracé (le coefficient directeur de celui-ci, considéré comme une drotie pour ce calcul);
 * 		- on peut ensuite calculer la pente et l'abscisse à l'origine de l'axe de symétrie, c'est à dire la droite perpendiculaire 
 * 			au tracé passant par le point de collision entre le bouclier et la boulette concernée;
 * 		- on calcule l'abscisse à l'origine de "l'axe de projection";
 * 		- on détermine les coordonnées du point d'intersection entre l'axe de symétrie et l'axe de projection, qui sera notre centre
 * 			de symétrie;
 * 		- on calcule les coordonnées du point image par symétrie centrale.
 * 
 * L'axe de projection est la droite parallèle au tracé passant par le "point à projeter".
 * Pour comprendre ce qu'est le point à projeter (ou point projeté), il faut avoir en tête que les mouvements sont représentés par des vecteurs. 
 * Pour calculer le point projeté, on prend les coordonnées du point de collision entre le bouclier et la boulette, puis on leur 
 * 	soustrait une fois le déplacement de cette boulette. 
 * Le point ainsi obtenu est celui dont on calculera l'image par symétrie centrale.
 * 
 * The rest is maths.
 */
public class Bouclier {

	public LinkedList<EntiteTrace> ligne;
	//c'est le type du bouclier qui détermine le type de boulette qu'il peut renvoyer

	private int type;
	private double pente;
	private double penteAxeSymetrie;
	private double abscisseOrigineAxeSymetrie;
	private double abscisseOrigineAxeProjection;
	
	public Bouclier (LinkedList<EntiteTrace> ligne, int type){
		this.ligne = ligne;
		calculerEquationLigne();
		this.type = type;
	}
	
	public void calculerEquationLigne (){
		double x1 = ligne.getFirst().getPosX();
		double y1 = ligne.getFirst().getPosY();
		double x2 = ligne.getLast().getPosX();
		double y2 = ligne.getLast().getPosY();
		if ((x2-x1)!=0)
			pente = (y2 - y1)/(x2 - x1);
		else
			pente = -1;		
	}
	
	//xContact et yContact sont les coordonnés du point de contact entre le bouclier et la boulette
	public void calculerEquationAxeSymetrie (int xContact, int yContact){
		if (pente != 0){
			penteAxeSymetrie = - (1.0/pente);
			abscisseOrigineAxeSymetrie = yContact - penteAxeSymetrie * xContact;
		}else{
			penteAxeSymetrie = 0;
			abscisseOrigineAxeSymetrie = yContact;
		}
	}
	
	public void calculerAbscisseOrigineAxeProjection(int xProjete, int yProjete){
		abscisseOrigineAxeProjection = yProjete - pente * xProjete;
	}
	
	public Point calculerCentreSymetrie(){
		int xCentreSymetrie = (int) (((abscisseOrigineAxeSymetrie - abscisseOrigineAxeProjection) * pente) / (1 + Math.pow(pente, 2)));
		int yCentreSymetrie = (int) (pente * xCentreSymetrie + abscisseOrigineAxeProjection);
		
		
		return new Point(xCentreSymetrie,yCentreSymetrie);
	}
	
	public Point calculerMouvmentApresRebond (Point mouvementIncident, int xContact, int yContact){
		if ( pente!= 0 ){
			calculerEquationAxeSymetrie(xContact, yContact);

			int xProjete = xContact - mouvementIncident.x;
			int yProjete = yContact - mouvementIncident.y;

			calculerAbscisseOrigineAxeProjection(xProjete, yProjete);

			Point centreSymetrie = calculerCentreSymetrie();

			int xImage = 2*centreSymetrie.x - xProjete;
			int yImage = 2*centreSymetrie.y - yProjete;

			//le nouveau mouvement est donc le vecteur entre les coordonnées du point de contact et
			//ceux de l'image du point projeté
			int xImageMouvement = xImage - xContact;
			int yImageMouvement = yImage - yContact;

			return new Point(xImageMouvement, yImageMouvement);
		}else{
			if (pente == -1)
				return new Point(-mouvementIncident.x, mouvementIncident.y);
			if (pente == 0)
				return new Point(mouvementIncident.x, -mouvementIncident.y);
		}	
		return new Point(0,0);
	}
	
	/*
	 * Méthode faisant disparaître la ligne du bouclier à la fin de sa durée de vie
	 */
	public void disparitionNaturelle (){
		ListIterator<EntiteTrace> itTrace = ligne.listIterator();
		while (itTrace.hasNext()){
			if (((StrategieTrace) itTrace.next().getStrat()).doitDeceder()){
				itTrace.previous();
				itTrace.remove();
				Mana.manaHausse();
			}
		}
	}
	
	public boolean doitDeceder (){
		return (ligne.size() == 0);
	}
	
	public int getType (){
		return type;
	}

	public void rendu (Graphics g, int deltaX, int deltaY,
			int screenWidth, int screenHeight){
		for (EntiteTrace block : ligne){

			block.rendu(g, deltaX, deltaY, screenWidth, screenHeight);
		}
	}
	
}
