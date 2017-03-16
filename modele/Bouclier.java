package modele;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;

public class Bouclier {

	public LinkedList<Entite> ligne;
	private int type;
	private double pente;
	private double penteAxeSymetrie;
	private double abscisseOrigineAxeSymetrie;
	private double abscisseOrigineAxeProjection;
	
	public Bouclier (LinkedList<Entite> ligne, int type){
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
			pente = 0;		
	}
	
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
	
	public Point calculerDirectionRebond (Point mouvementIncident, int xContact, int yContact){
		if ( pente!= 0 ){
			calculerEquationAxeSymetrie(xContact, yContact);

			int xProjete = xContact - mouvementIncident.x;
			int yProjete = yContact - mouvementIncident.y;

			calculerAbscisseOrigineAxeProjection(xProjete, yProjete);

			Point centreSymetrie = calculerCentreSymetrie();

			int xImage = 2*centreSymetrie.x - xProjete;
			int yImage = 2*centreSymetrie.y - yProjete;

			int xImageMouvement = xImage - xContact;
			int yImageMouvement = yImage - yContact;

			return new Point(xImageMouvement, yImageMouvement);
		}else
			return new Point(-mouvementIncident.x, -mouvementIncident.y);
	}
	
	public void disparitionNaturelle (){
		ListIterator<Entite> itTrace = ligne.listIterator();
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
		for (Entite block : ligne){
			block.rendu(g, deltaX, deltaY, screenWidth, screenHeight);
		}
	}
	
}
