package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import modele.Parametres;

public class ParamClick implements MouseListener {

	Parametres p;

	public ParamClick(Parametres p) {
		// TODO Auto-generated constructor stub
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getX() > p.getWidth() / 2.28
				&& arg0.getX() < p.getWidth() / 1.87
				&& arg0.getY() > p.getHeight() / 3.35
				&& arg0.getY() < p.getHeight() / 3.15) {
			p.getFrame().setExtendedState(JFrame.NORMAL);
			p.getFrame().setSize(600, 600);
		}
		if (arg0.getX() > p.getWidth() / 2.28
				&& arg0.getX() < p.getWidth() / 1.85
				&& arg0.getY() > p.getHeight() / 2.59
				&& arg0.getY() < p.getHeight() / 2.46) {
			p.getFrame().setExtendedState(JFrame.NORMAL);
			p.getFrame().setSize(800, 800);
		}
		if (arg0.getX() > p.getWidth() / 2.25
				&& arg0.getX() < p.getWidth() / 1.75
				&& arg0.getY() > p.getHeight() / 2.11
				&& arg0.getY() < p.getHeight() / 2.03) {
			p.getFrame().setExtendedState(JFrame.NORMAL);
			p.getFrame().setSize(1000, 1000);
		}
		if (arg0.getX() > p.getWidth() / 2.23
				&& arg0.getX() < p.getWidth() / 1.75
				&& arg0.getY() > p.getHeight() / 1.78
				&& arg0.getY() < p.getHeight() / 1.72) {
			p.getFrame().setExtendedState(JFrame.NORMAL);
			p.getFrame().setSize(1200, 1200);
		}
		if (arg0.getX() > p.getWidth() / 2.23
				&& arg0.getX() < p.getWidth() / 1.76
				&& arg0.getY() > p.getHeight() / 1.55
				&& arg0.getY() < p.getHeight() / 1.48) {
			p.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
			p.getFrame().setResizable(true);
		}
		if (arg0.getX() > p.getWidth() / 2.31
				&& arg0.getX() < p.getWidth() / 1.67
				&& arg0.getY() > p.getHeight() / 1.28
				&& arg0.getY() < p.getHeight() / 1.22) {
			p.getM().setParam(false);
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
