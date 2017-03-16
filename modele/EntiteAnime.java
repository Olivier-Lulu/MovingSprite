package modele;

import java.awt.Graphics;
import vue.Sprite;

public class EntiteAnime extends Entite {

	Sprite [] spritetab;
	int x;
	int y;
	HitBox[] hitBox;
	int timer = 0;
	
	public EntiteAnime(int posX, int posY,
			Niveau niveau, boolean solid, Strategie strat, Sprite sprite, int taille, int score, HitBox[] hitBox, int x, int y) {
		super(sprite, niveau, solid, strat, score, hitBox[0]);
		spritetab  = new Sprite[taille];
		this.hitBox = hitBox;
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
	}
	
	public void ajouterSprite(){
		for (int i =0 ; i  < spritetab.length; i++){
			spritetab[i] = new Sprite(this.getNiveau().stock.getSprite(i+x, y));
		}
	}

	@Override
	public void rendu(Graphics g,int deltaX,int deltaY,int screenWidth,int screenHeight){
		if ( this.getEtat() == 0){
			this.timer = 0;
			g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		if (this.getEtat() == 1 && this.timer < 6){
			g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		if (this.getEtat() == 1 && this.timer >= 6 && this.timer < 12){
			g.drawImage(spritetab[1].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		if (this.getEtat() == 1 && this.timer >= 12 && this.timer <18){
			g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		if (this.getEtat() == 1 && this.timer >= 18){
			g.drawImage(spritetab[2].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		if (this.getEtat() == 2 && this.timer < 10){
			g.drawImage(spritetab[0].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		if (this.getEtat() == 2 && this.timer >= 10 && this.timer < 20){
			g.drawImage(spritetab[1].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		if (this.getEtat() == 2 && this.timer >= 20){
			g.drawImage(spritetab[2].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		if (this.getEtat() == 3){
			g.drawImage(spritetab[3].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, (getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		if (this.getEtat() == 4){
			g.drawImage(spritetab[3].getBufferedImage(), ((getPosX()+deltaX)*screenWidth)/600, ((getPosY()+deltaY)*screenHeight)/600, -(getWidth()*screenWidth)/600+1, (getHeight()*screenHeight)/600+1,null);
		}
		this.timer++;
		if (timer > 24){
			timer = 0;
		}
	}

	@Override
	public boolean intersects(Entite e){
		if ( this.getEtat() == 0){
			return hitBox[0].intersects(e.hitBox);
		}
		if (this.getEtat() == 1 && this.timer < 6){
			return hitBox[0].intersects(e.hitBox);

		}
		if (this.getEtat() == 1 && this.timer >= 6 && this.timer < 12){
			return hitBox[1].intersects(e.hitBox);
		}
		if (this.getEtat() == 1 && this.timer >= 12 && this.timer <18){
			return hitBox[1].intersects(e.hitBox);
		}
		if (this.getEtat() == 1 && this.timer >= 18){
			return hitBox[2].intersects(e.hitBox);
		}
		if (this.getEtat() == 2 && this.timer < 10){
			return hitBox[0].intersects(e.hitBox);
		}
		if (this.getEtat() == 2 && this.timer >= 10 && this.timer < 20){
			return hitBox[1].intersects(e.hitBox);
		}
		if (this.getEtat() == 2 && this.timer >= 20){
			return hitBox[2].intersects(e.hitBox);
		}
		if (this.getEtat() == 3){
			return hitBox[3].intersects(e.hitBox);
		}
		if (this.getEtat() == 4){
			return hitBox[3].intersects(e.hitBox);
		}
		return super.intersects(e);
    }
    
	@Override
    public void setPosition(int posX, int posY){
    	if(posX < 0 || posY < 0 )
    		throw new IllegalArgumentException("coordonne negative");
    	for(int i = 0; i < hitBox.length; i++)
    		hitBox[i].setPosition(posX, posY);
    }
	
	@Override
	 public void drawDebug(Graphics g,int deltaX,int deltaY,int screenWidth,int screenHeight){
			if ( this.getEtat() == 0){
				hitBox[0].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
			}
			if (this.getEtat() == 1 && this.timer < 6){
				hitBox[0].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);

			}
			if (this.getEtat() == 1 && this.timer >= 6 && this.timer < 12){
				hitBox[1].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
			}
			if (this.getEtat() == 1 && this.timer >= 12 && this.timer <18){
				hitBox[1].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
			}
			if (this.getEtat() == 1 && this.timer >= 18){
				hitBox[2].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
			}
			if (this.getEtat() == 2 && this.timer < 10){
				hitBox[0].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
			}
			if (this.getEtat() == 2 && this.timer >= 10 && this.timer < 20){
				hitBox[1].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
			}
			if (this.getEtat() == 2 && this.timer >= 20){
				hitBox[2].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
			}
			if (this.getEtat() == 3){
				hitBox[3].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
			}
			if (this.getEtat() == 4){
				hitBox[3].drawDebug(g,deltaX,deltaY,screenWidth,screenHeight);
			}
			this.timer++;
			if (timer > 24){
				timer = 0;
			}
	    }

}
