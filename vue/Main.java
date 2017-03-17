package vue;

import java.awt.Color;

import javax.swing.JFrame;

import modele.Parametres;

public class Main {

	static final int WIDTH = 600;
	static final int HEIGHT = 600;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Menu m = new Menu(true, false);
		Parametres p = new Parametres(frame, m);
		SelectNiveau sn = new SelectNiveau();
		frame.setSize(WIDTH, HEIGHT);
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		gamethread gt = new gamethread(m, frame, p, sn);
		gt.start();
		frame.revalidate();
		SoundThread st = new SoundThread();
		st.start();
	}
}
