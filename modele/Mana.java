package modele;

import vue.Sprite;

public class Mana {

	private static final int MANA_MAXIMUM = 100;
	private static final Sprite MANA_VIDE = new Sprite(
			"/data/Sprites/manaVide.png");
	private static final Sprite MANA_PLEIN = new Sprite(
			"/data/Sprites/manaPlein.png");

	private static int mana = MANA_MAXIMUM;

	/*
	 * retourne le pourcentage de mana restant
	 */
	public static int getRatioMana() {
		return (mana * 100) / MANA_MAXIMUM;
	}

	public static void manaHausse() {
		if (mana + 10 <= MANA_MAXIMUM)
			mana += 10;
	}

	public static void manaBaisse() {
		if (mana - 10 >= 0)
			mana -= 10;
	}

	/*
	 * réinitialise le mana à son niveau de départ
	 */
	public static void manaReinit() {
		mana = MANA_MAXIMUM;
	}

	/*
	 * permet de savoir si il reste du mana
	 */
	public static boolean aMana() {
		return mana > 0;
	}

	public static Sprite getBarreManaPlein() {
		return MANA_PLEIN;
	}

	public static Sprite getBarreManaVide() {
		return MANA_VIDE;
	}

}
