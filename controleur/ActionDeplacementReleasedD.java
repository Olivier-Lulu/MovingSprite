package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import modele.StrategieJoueur;

public class ActionDeplacementReleasedD extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	private StrategieJoueur joueurStrat;
	
	public ActionDeplacementReleasedD (StrategieJoueur joueurStrat){
		super ("released D");
		this.joueurStrat = joueurStrat;
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		joueurStrat.deplacementDroitFin();
	}

}
