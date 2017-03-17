package vue;

import java.awt.Graphics;

import modele.Mana;
import modele.Score;

public class Hud {

	/*
	 * permet de dessiner la barre de mana et le score du joueur
	 */
	public static void peindre(Graphics g) {
		g.drawString("score :" + Score.getScore(), 0, 10);
		g.drawImage(Mana.getBarreManaVide().getBufferedImage(), 0, 25, 100, 25,
				null);
		g.drawImage(Mana.getBarreManaPlein().getBufferedImage(), 0, 25,
				Mana.getRatioMana(), 25, null);

	}

}
