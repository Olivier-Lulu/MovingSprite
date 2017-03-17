package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import modele.Niveau;

import vue.SelectNiveau;

public class SelectNiveauClick implements MouseListener {

	SelectNiveau sn;

	public SelectNiveauClick(SelectNiveau sn) {
		// TODO Auto-generated constructor stub
		this.sn = sn;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= sn.getNomNiveau().size(); i++) {
			if (sn.getZc()[i - 1].contains(arg0.getX(), arg0.getY())) {
				Niveau niveau = null;
				try {
					niveau = Createur.creerNiveau("/data/"
							+ sn.getNomNiveau().get(i - 1));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sn.setNiveauRunning(niveau);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
