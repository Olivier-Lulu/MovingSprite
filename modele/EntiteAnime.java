package modele;

import java.awt.Graphics;
import vue.Sprite;

public class EntiteAnime extends Entite {

	Sprite [] spritetab;
	int x;
	int y;
	HitBox[] hitBox;
	int timerJoueur = 0;
	private int timerTireur = 0;
	
	public EntiteAnime(int posX, int posY,
			Niveau niveau, boolean solid, Strategie strat, Sprite sprite, int taille, int score, HitBox[] hitBox, int x, int y) {
		super(sprite, niveau, solid, strat, score, hitBox[0]);
		spritetab  = new Sprite[taille];
		this.hitBox = hitBox;
		this.x = x;
		this.y = y;
	}
	
	public void ajouterSprite(){
		for (int i =0 ; i  < spritetab.length; i++){
			spritetab[i] = new Sprite(this.getNiveau().stock.getSprite(i+x, y));
		}
	}

	public void rendu(Graphics g,int deltaX,int deltaY,int screenWidth,int screenHeight){
		if (this.spritetab.length >2){
			if ( this.getEtat() == 0){
				this.timerJoueur = 0;
				if (this.getDernierEtat() == 2 || this.getDernierEtat() == 4){
					g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600+ (getWidth()*screenWidth)/600+1, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
				}else{
					g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
				}
			}
			if ( this.getEtat() == 5){
				this.timerJoueur = 0;
				if (this.getDernierEtat() == 2 || this.getDernierEtat() == 4){
					g.drawImage(spritetab[3].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600+ (getWidth()*screenWidth)/600+1, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
				}else{
					g.drawImage(spritetab[3].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
				}
			}
			if (this.getEtat() == 1 && this.timerJoueur < 6){
				this.setDernierEtat(1);
				g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
			if (this.getEtat() == 1 && this.timerJoueur >= 6 && this.timerJoueur < 12){
				this.setDernierEtat(1);
				g.drawImage(spritetab[1].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
			if (this.getEtat() == 1 && this.timerJoueur >= 12 && this.timerJoueur <18){
				this.setDernierEtat(1);
				g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
			if (this.getEtat() == 1 && this.timerJoueur >= 18){
				this.setDernierEtat(1);
				g.drawImage(spritetab[2].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
			if (this.getEtat() == 2 && this.timerJoueur < 6){
				this.setDernierEtat(2);
				g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600+ (getWidth()*screenWidth)/600+1, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
			if (this.getEtat() == 2 && this.timerJoueur >= 6 && this.timerJoueur < 12){
				this.setDernierEtat(2);
				g.drawImage(spritetab[1].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600+ (getWidth()*screenWidth)/600+1, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
			if (this.getEtat() == 2 && this.timerJoueur >= 12 && this.timerJoueur < 18){
				this.setDernierEtat(2);
				g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600+ (getWidth()*screenWidth)/600+1, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
			if (this.getEtat() == 2  && this.timerJoueur >= 18){
				this.setDernierEtat(2);
				g.drawImage(spritetab[2].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600+ (getWidth()*screenWidth)/600+1, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}

			if (this.getEtat() == 3){
				this.timerJoueur = 0;
				this.setDernierEtat(3);
				g.drawImage(spritetab[3].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
			if (this.getEtat() == 4){
				this.setDernierEtat(4);
				this.timerJoueur = 0;
				g.drawImage(spritetab[3].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600+ (getWidth()*screenWidth)/600+1, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
		}else{
			if ((this.getEtat() == 6 || this.getDernierEtat() == 7 || this.getDernierEtat() == 8) && this.timerTireur < 15){
				if (this.getDernierEtat() == 1 || this.getDernierEtat() == 7){
					g.drawImage(spritetab[1].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600+ (getWidth()*screenWidth)/600+1, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
					this.setDernierEtat(7);
				}else if(this.getDernierEtat() == 2 || this.getDernierEtat() == 8){
					g.drawImage(spritetab[1].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
					this.setDernierEtat(8);
				}
				this.timerTireur++;
			}else if (this.getEtat() == 1 ){
				this.setDernierEtat(1);
				this.timerTireur = 0;
				g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600+ (getWidth()*screenWidth)/600+1, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}else if (this.getEtat() == 2 ){
				this.setDernierEtat(2);
				this.timerTireur = 0;
				g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
			}
		}
		this.timerJoueur++;
		if (this.timerJoueur > 24){
			this.timerJoueur = 0;
		}
	}	
}
