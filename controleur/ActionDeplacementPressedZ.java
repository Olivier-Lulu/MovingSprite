package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import modele.StrategieJoueur;

public class ActionDeplacementPressedZ extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	private StrategieJoueur joueurStrat;
	
	public ActionDeplacementPressedZ (StrategieJoueur joueurStrat){
		super ("pressed Z");
		this.joueurStrat = joueurStrat;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		joueurStrat.saut();
	}

}
