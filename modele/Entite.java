package modele;

import java.awt.Graphics;
import java.awt.Rectangle;
import vue.Sprite;

public class Entite {

	private Sprite sprite;
	protected HitBox hitBox;
	private Niveau niveau;
	//seulement utilisé pour les collisions avec le décor
	private boolean solid;
	private Strategie strat;
	//score gagné par le joueur à la mort de l'entité
	private int score;
	//utilisés pour les animations
	private int etat = 0;
	private int dernierEtat = 0;

	// définit les coordonnées du sprite dans le tilset
	protected int x;
	protected int y;

	public Entite(Sprite sprite, Niveau niveau, boolean solid, Strategie strat,
			int score, HitBox hitBox) {
		this.sprite = sprite;
		this.niveau = niveau;
		this.solid = solid;
		this.strat = strat;
		this.score = score;
		this.hitBox = hitBox;
	}

	public Entite(int posX, int posY, int width, int height, Niveau niveau,
			boolean solid, Strategie strat, Sprite sprite, int score) {
		this.sprite = sprite;
		this.strat = strat;
		this.niveau = niveau;
		this.solid = solid;
		this.score = score;
		this.hitBox = new HitBox(new Rectangle(posX, posY, width, height));
	}

	public Entite(Strategie strat, int score, HitBox hitBox, int x, int y) {
		this.sprite = null;
		this.niveau = null;
		this.solid = true;
		this.strat = strat;
		this.score = score;
		this.hitBox = hitBox;
		this.x = x;
		this.y = y;
	}

	/*
	 * Les entites créées via Createur n'ont pas de niveau, cette méthode permet
	 * de leur définir un niveau.
	 */
	public void setNiveau(Niveau niveau) {
		if (this.niveau == null && niveau != null)
			this.niveau = niveau;
	}

	/*
	 * Détecte si l'entité est en collision avec une autre.
	 */
	public boolean intersects(Entite e) {
		return hitBox.intersects(e.hitBox);
	}

	public int getPosX() {
		return hitBox.getPosX();
	}

	public int getPosY() {
		return hitBox.getPosY();
	}

	public void setPosition(int posX, int posY) {
		if (posX >= 0 && posY >= 0)
			hitBox.setPosition(posX, posY);
		else
			throw new IllegalArgumentException("coordonne negative");
	}

	public int getWidth() {
		return hitBox.getWidth();
	}

	public int getHeight() {
		return hitBox.getHeight();
	}

	public boolean isSolid() {
		return solid;
	}

	/*
	 * permet de dessiner l'entité
	 */
	public void rendu(Graphics g, int deltaX, int deltaY, int screenWidth,
			int screenHeight) {
		g.drawImage(sprite.getBufferedImage(),
				((getPosX() + deltaX) * screenWidth) / 600,
				((getPosY() + deltaY) * screenHeight) / 600,
				(getWidth() * screenWidth) / 600 + 1,
				(getHeight() * screenHeight) / 600 + 1, null);
	}

	/*
	 * permet de dessiner les hitBoxs de l'entité
	 */
	public void drawDebug(Graphics g, int deltaX, int deltaY, int screenWidth,
			int screenHeight) {
		hitBox.drawDebug(g, deltaX, deltaY, screenWidth, screenHeight);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public Strategie getStrat() {
		return strat;
	}

	/*
	 * permet à l'entité de mettre à jour sa position 
	 */
	public int eval() {
		etat = strat.eval(this, niveau);
		return etat;
	}

	public int getEtat() {
		return etat;
	}

	public int setEtat(int etat) {
		this.etat = etat;
		return etat;
	}

	public int getDernierEtat() {
		return dernierEtat;
	}

	public void setDernierEtat(int dernierEtat) {
		this.dernierEtat = dernierEtat;
	}

	/*
	 * permet de supprimer l'entité du niveau auquel elle est rattachée
	 */
	public void deces() {
		Score.augmenterScore(score);
		niveau.supprimerEntite(this);
	}

	/*
	 * permet d'avoir accès au sprite après la définition du niveau
	 */
	public void ajouterSprite() {
		if (niveau != null)
			sprite = new Sprite(niveau.stock.getSprite(x, y));
	};

}