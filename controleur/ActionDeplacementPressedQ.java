package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import modele.StrategieJoueur;

public class ActionDeplacementPressedQ extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	private StrategieJoueur joueurStrat;
	
	public ActionDeplacementPressedQ (StrategieJoueur joueurStrat){
		super ("pressed Q");
		this.joueurStrat = joueurStrat;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		joueurStrat.deplacementGaucheDebut();
	}

}
