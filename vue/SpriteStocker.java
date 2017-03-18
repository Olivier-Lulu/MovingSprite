package vue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteStocker {

	private HashMap<Integer, Sprite> stock;

	/*
	 * BufferedImage qui contient le tableau de Sprite comprenant tous les Sprites utilisés
	 * dans le jeu.
	 */
	public static BufferedImage table = null;

	public SpriteStocker(String path, int i) {
		stock = new HashMap<Integer, Sprite>();

		try {
			table = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int j = 0;
		while (j++ != i) {
			stock.put(j, new Sprite(getSprite(j, 1)));
		}
	}

	public void add(Sprite s, Integer nom) {
		if (!stock.containsKey(nom))
			stock.put(nom, s);
	}

	public Sprite get(Integer nom) {
		if (stock.containsKey(nom))
			return stock.get(nom);
		return null;
	}

	public int getTaille() {
		return stock.size();
	}

	/*
	 * Fonction permettant de récupérer une portion de 25 pixels par 25 dans le tableau de Sprite.
	 */
	public BufferedImage getSprite(int x, int y) {
		return table.getSubimage(x * 25 - 25, y * 25 - 25, 25, 25);
	}
}
