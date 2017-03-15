package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import modele.StrategieJoueur;

/*
 * Décrit la réaction du joueur lorsqu'on appuie sur la touche q
 */
public class ActionDeplacementPressedQ extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	private StrategieJoueur joueurStrat;
	
	public ActionDeplacementPressedQ (StrategieJoueur joueurStrat){
		super ("pressed Q");
		this.joueurStrat = joueurStrat;
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		joueurStrat.deplacementGaucheDebut();
	}

}
