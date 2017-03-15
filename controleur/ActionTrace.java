package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vue.FenetreNiveau;

/*
 * Décrit la réaction du jeu aux contrôles de la souris.
 */
public class ActionTrace implements MouseListener {

	private boolean traceAutorise = true; 
	private int positionXCLickSouris = 0;
	private int positionYCLickSouris = 0;
	private FenetreNiveau niveau;
	
	public ActionTrace(FenetreNiveau niveau){
		super();
		this.niveau = niveau;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(MouseEvent click) {
		// TODO Auto-generated method stub
		if (traceAutorise) {
			positionXCLickSouris = click.getX();
			positionYCLickSouris = click.getY();
			traceAutorise = false;
		}
	}

	public void mouseReleased(MouseEvent click) {
		// TODO Auto-generated method stub
		if (!traceAutorise){
			niveau.tracerLigne( positionXCLickSouris , positionYCLickSouris ,click.getX() , click.getY() );
			traceAutorise = true;
		}
	}

}
