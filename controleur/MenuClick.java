package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vue.Menu;

public class MenuClick implements MouseListener {

	private Menu m;
	
	public MenuClick(Menu m) {
		// TODO Auto-generated constructor stub
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getX() > m.getWidth()/2.42 && arg0.getX() < m.getWidth()/1.97 && arg0.getY() > m.getHeight()/3.24 && arg0.getY() < m.getHeight()/2.71){
			m.setRunning(false);
		}
		if (arg0.getX() > m.getWidth()/2.42 && arg0.getX() < m.getWidth()/1.63 && arg0.getY() > m.getHeight()/2.38 && arg0.getY() < m.getHeight()/2.12){
			m.setParam(true);
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
