package modele;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;

import vue.Hud;

public class Bouclier {

	public LinkedList<EntiteTrace> ligne;
	private double pente;
	//private double abscisseOrigine;
	private double penteAxeSymetrie;
	private double abscisseOrigineAxeSymetrie;
	
	public Bouclier (LinkedList<EntiteTrace> ligne){
		this.ligne = ligne;
		calculerEquationLigne();
	}
	
	public void calculerEquationLigne (){
		int x1 = ligne.getFirst().getPosX();
		int y1 = ligne.getFirst().getPosY();
		int x2 = ligne.getLast().getPosX();
		int y2 = ligne.getLast().getPosY();
		if ((x2-x1)!=0)
			pente = (y2 - y1)/(x2 - x1);
		else
			pente = 1;
		//abscisseOrigine = y1 - x1 * pente;
	}
	
	public void calculerEquationAxeSymetrie (int xContact, int yContact){
		penteAxeSymetrie = - 1/pente;
		abscisseOrigineAxeSymetrie = yContact - penteAxeSymetrie * xContact;
	}
	
	public Point calculerDirectionRebond (Point mouvementIncident){
		//Point fausseOrigine = new Point(0,0);
		
		double n = Math.pow(penteAxeSymetrie, 2) + Math.pow(abscisseOrigineAxeSymetrie, 2);
		double c = 0; //penteAxeSymetrie*fausseOrigine.x - fausseOrigine.y 
		
		int xImageFausseOrigine = (int) (0/*fausseOrigine.x*/ + 2 * penteAxeSymetrie *
				(abscisseOrigineAxeSymetrie - c ) / n);
		
		int yImageFausseOrigine = (int) (0/*fausseOrigine.y*/ - 2 *
				(abscisseOrigineAxeSymetrie - c ) / n);
		
		int xImageMouvement = (int) (mouvementIncident.x + 2 * penteAxeSymetrie *
				(abscisseOrigineAxeSymetrie - c ) / n);
		
		int yImageMouvement = (int) (mouvementIncident.y - 2 *
				(abscisseOrigineAxeSymetrie - c ) / n);
		
		return new Point (xImageMouvement - xImageFausseOrigine,
						yImageMouvement - yImageFausseOrigine);
	}
	
	public void disparitionNaturelle (){
		ListIterator<EntiteTrace> itTrace = ligne.listIterator();
		while (itTrace.hasNext()){
			if (itTrace.next().doitDeceder()){
				itTrace.previous();
				itTrace.remove();
				Hud.manaHausse();
			}
		}
	}
	
	public boolean doitDeceder (){
		return (ligne.size() == 0);
	}
	
	public void rendu (Graphics g, int deltaX, int deltaY,
			int screenWidth, int screenHeight){
		for (EntiteTrace block : ligne){
			block.rendu(g, deltaX, deltaY, screenWidth, screenHeight);
		}
	}
	
}
