package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import modele.StrategieJoueur;

/*
 * Décrit la réaction du joueur lorsqu'on appuie sur la touche z
 */
public class ActionDeplacementPressedZ extends AbstractAction {
	private static final long serialVersionUID = 1L;

	private StrategieJoueur joueurStrat;

	public ActionDeplacementPressedZ(StrategieJoueur joueurStrat) {
		super("pressed Z");
		this.joueurStrat = joueurStrat;
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		joueurStrat.saut();
	}

}
