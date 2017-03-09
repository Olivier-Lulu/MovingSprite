package vue;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Hud extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int MANA_MAXIMUM = 400;
	private static final Sprite MANA_VIDE = new Sprite("/data/Sprites/manaVide.png");
	private static final Sprite MANA_PLEIN = new Sprite("/data/Sprites/manaPlein.png");
	private static final Sprite COIN_SCORE = new Sprite("/data/Sprites/coin.gif");
	
	private static int score = 0;
	private static int mana = MANA_MAXIMUM ;
	
	/*
	 * permet de dessiner la barre de mana et le score du joueur
	 */
	public static void peindre(Graphics g){
		g.drawString("score :"+score,0,10);
		g.drawImage(COIN_SCORE.getBufferedImage(), 60,0, 20, 20,null);
		g.drawImage(MANA_VIDE.getBufferedImage(),0,25,100,25,null);
		g.drawImage(MANA_PLEIN.getBufferedImage(),0,25,mana*100/MANA_MAXIMUM,25,null);
		
	}
	
	public static void manaHausse(){
		mana +=10;
	}
	
	public static void manaBaisse(){
		mana -= 10;
	}
	
	public static boolean aMana(){
		return mana > 0;
	}
	
	public static void augmenterScore(int valeurScore){
		score += valeurScore;
	}

}
