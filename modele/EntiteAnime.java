package modele;

import java.awt.Graphics;
import java.io.IOException;

import vue.Niveau;
import vue.Sprite;
import vue.SpriteStocker;

public class EntiteAnime extends Entite {
	
	Sprite [] sprites = new Sprite [4];

	public EntiteAnime(int posX, int posY, Niveau niveau, boolean solid,
			Strategie strat, String sprite, SpriteStocker stock, int score) throws IOException {
		super(posX, posY, niveau, solid, strat, sprite,score);
		for (int i =0 ; i<=3;i++){
			//sprites[i] = new Sprite(stock.getSprite(2, i+1));
		}
	}
	
	public void rendu(Graphics g,int deltaX,int deltaY,int screenWidth,int screenHeight, int i){
    	g.drawImage(sprites[i].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
    }
}
