package controleur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import modele.StrategieJoueur;

/*
 * Décrit la réaction du joueur lorsqu'on appuie sur la touche d
 */
public class ActionDeplacementPressedD extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private StrategieJoueur joueurStrat;

	public ActionDeplacementPressedD(StrategieJoueur joueurStrat) {
		super("pressed D");
		this.joueurStrat = joueurStrat;
	}

	public void actionPerformed(ActionEvent arg0) {
		joueurStrat.deplacementDroitDebut();
	}

}
