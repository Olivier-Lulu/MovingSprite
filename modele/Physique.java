package modele;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

public class Physique {

	/*
	 * permet à une entité de se déplacer 
	 */
	public static int move(Entite e, Niveau n) {
		if (e.getStrat().enVols)
			e.getStrat().deplacement.y += 1;
		return collision(e, n);
	}

	/*
	 * fait bouger l'entité e en prenant en compte les collisions 
	 */
	private static int collision(Entite e, Niveau n) {

		// mouve est une copie du déplacement de e
		// ce vecteur servira à effectuer des operations sur le mouvement de e
		// sans influer sur ce dernier
		Point mouve = new Point(e.getStrat().deplacement.x,
				e.getStrat().deplacement.y);
		int temp = 0;
		// si e est en l'air on augmente sa vitesse afin d'avoir un saut
		// réaliste
		if (e.getStrat().enVols)
			mouve.x = mouve.x * 2;

		// afin de ne pas tester la collision de e avec toutes les entités du
		// niveau on construit un rectangle
		// comprenant e et sa position finale théorique
		Rectangle r = new Rectangle(0, 0, 0, 0);
		if (mouve.x < 0) {
			r.x = e.getPosX() + mouve.x;
			r.width = mouve.x * -1 + e.getWidth();
		} else {
			r.x = e.getPosX();
			r.width = mouve.x + e.getWidth();
		}
		if (mouve.y < 0) {
			r.y = e.getPosY() + mouve.y;
			r.height = mouve.y * -1 + e.getHeight();
		} else {
			r.y = e.getPosY();
			r.height = mouve.y + e.getHeight();
		}

		// listeEntite servira à stocker les entités avec lesquelles il faudra
		// réagir à la collision avec e
		List<Entite> listeEntite = new LinkedList<Entite>();
		for (Entite entite : n.mob) {
			if (r.intersects(entite.getPosX(), entite.getPosY(),
					entite.getWidth(), entite.getHeight())
					&& !entite.equals(e)) {
				listeEntite.add(entite);
			}
		}

		// même explication que listeEntite 
		List<Bouclier> listeBouclier = new LinkedList<Bouclier>();
		for (Bouclier unBouclier : n.boucliers) {
			for (Entite entite : unBouclier.ligne) {
				if (r.intersects(entite.getPosX(), entite.getPosY(),
						entite.getWidth(), entite.getHeight())
						&& !entite.equals(e)) {
					listeBouclier.add(unBouclier);
					break;
				}
			}
		}

		// on bouge e unité par unité tant que cela est possible
		boolean possibleX = true;
		boolean possibleY = true;
		// ce i sert de compteur pour toutes les boucles
		int i = 0;
		while (possibleX || possibleY) {
			// s'il n'y a aucun deplacement en x inutile de faire les calculs
			// pour x
			if (mouve.x != 0)
				if (mouve.x > 0) {
					// calcule pour le cas ou le déplacement en x est positifs
					// donc vers la droite

					// teste s'il y a un risque que e sorte du niveau
					if ((e.getPosX() + e.getWidth() + 1) / 25 < n.entite.length) {
						boolean b = true;
						i = 0;
						// teste sur toute la taille de e, voir s'il peut
						// bouger de la tete au pied
						// pour autoriser le deplacement d'une unité
						while (i < e.getHeight() && b) {
							if (n.entite[(e.getPosX() + e.getWidth() + 1) / 25][(e
									.getPosY() + i) / 25].isSolid()) {
								b = false;
							}
							// les cases du niveau ayant une taille de 25 il
							// suffit de tester touts les 25 unités
							if (i < e.getHeight() - 26)
								i += 25;
							else if (i == e.getHeight() - 1)
								i++;
							else
								// si e n'a pas une taille en multiple de 25 ce
								// cas permet de forcer le test sur le reste
								i = e.getHeight() - 1;
						}
						if (b) {
							// si le déplacement est autorisé on actualise la
							// position de e
							e.setPosition(e.getPosX() + 1, e.getPosY());
							possibleX = true;
							temp = 1;
							mouve.x += -1;
						} else
							possibleX = false;
					} else {
						possibleX = false;
					}
				} else {
					// calcule pour le cas ou le déplacement en x est négatifs
					// donc vers la gauche
					// voir explication pour droite
					if ((e.getPosX() - 1) / 25 > -1) {
						boolean b = true;
						i = 0;
						while (i < e.getHeight() && b) {
							if (n.entite[(e.getPosX() - 1) / 25][(e.getPosY() + i) / 25]
									.isSolid()) {
								b = false;
							}
							if (i < e.getHeight() - 26)
								i += 25;
							else if (i == e.getHeight() - 1)
								i++;
							else
								i = e.getHeight() - 1;
						}
						if (b) {
							e.setPosition(e.getPosX() - 1, e.getPosY());
							possibleX = true;
							temp = 2;
							mouve.x += 1;
						} else
							possibleX = false;
					} else {
						possibleX = false;
					}
				}
			else
				possibleX = false;

			// s'il n'y a aucun déplacement en y, inutile de faire les calculs
			// pour y
			if (mouve.y != 0)
				if (mouve.y > 0)
					// calcule pour le cas ou le déplacement en y est positif
					// donc vers le bas
					// voir explication pour droite
					if ((e.getPosY() + e.getHeight() + 1) / 25 < n.entite[0].length) {
						boolean b = true;
						i = 0;
						while (i < e.getWidth() && b) {
							if (n.entite[(e.getPosX() + i) / 25][(e.getPosY()
									+ e.getHeight() + 1) / 25].isSolid()) {
								b = false;
							}
							if (i < e.getWidth() - 26)
								i += 25;
							else if (i == e.getWidth() - 1)
								i++;
							else
								i = e.getWidth() - 1;
						}
						if (b) {
							e.setPosition(e.getPosX(), e.getPosY() + 1);
							possibleY = true;
							mouve.y -= 1;
						} else
							possibleY = false;
					} else {
						e.getStrat().deplacement.y = 0;
						possibleY = false;
					}
				else
				// calcule pour le cas ou le déplacement en y est négatif donc
				// vers le haut
				// voir explication pour droite
				if ((e.getPosY() - 1) / 25 > -1) {
					boolean b = true;
					i = 0;
					while (i < e.getWidth() && b) {
						if (n.entite[(e.getPosX() + i) / 25][(e.getPosY() - 1) / 25]
								.isSolid()) {
							e.getStrat().deplacement.y = 0;
							b = false;
						}
						if (i < e.getWidth() - 26)
							i += 25;
						else if (i == e.getWidth() - 1)
							i++;
						else
							i = e.getWidth() - 1;
					}
					if (b) {
						e.setPosition(e.getPosX(), e.getPosY() - 1);
						possibleY = true;
						mouve.y += 1;
					} else
						possibleY = false;
				} else {
					e.getStrat().deplacement.y = 0;
					possibleY = false;
				}
			else
				possibleY = false;

			if (!e.equals(n.joueur)
					&& !(StrategieScorable.class.isAssignableFrom(e.getStrat()
							.getClass())) && n.joueur.intersects(e))
				n.joueur.deces();

			// après avoirs fais un déplacement de e on teste s'il n'est pas en
			// collision avec une autre entité
			if (e.equals(n.joueur))
				for (Entite mob : listeEntite) {
					if (StrategieScorable.class.isAssignableFrom(mob.getStrat()
							.getClass())) {
						mob.deces();
						listeEntite.remove(mob);
					}

					if (StrategieCheckPoint.class.isAssignableFrom(mob
							.getStrat().getClass())) {
						if (n.getFinDuNiveau().intersects(n.joueur.getPosX(),
								n.joueur.getPosY(), n.joueur.getWidth(),
								n.joueur.getHeight())) {
							n.aFini = true;
						} else {
							n.xPop = e.getPosX();
							n.yPop = e.getPosY();
						}
					}
				}

			if (EntiteBoulette.class.isAssignableFrom(e.getClass())) {
				for (Entite ent : listeEntite) {
					if (((EntiteBoulette) e).tueLesMobs()) {
						ent.deces();
						listeEntite.remove(ent);
					}
				}
			}

			for (Bouclier boubou : listeBouclier) {
				if (EntiteBoulette.class.isAssignableFrom(e.getClass())) {
					if (!((EntiteBoulette) e).boucliersInterdits
							.contains(boubou)
							&& (boubou.getType() == ((EntiteBoulette) e)
									.getType())) {
						e.getStrat().deplacement = boubou
								.calculerMouvmentApresRebond(
										e.getStrat().deplacement, e.getPosX(),
										e.getPosY());
						((EntiteBoulette) e).boucliersInterdits.add(boubou);
						((EntiteBoulette) e).rebondit();
					}
				}
			}
		}

		if (e.getStrat().estSensibleGravite)
			e.getStrat().enVols = true;
		i = 0;
		// on teste si e a du sol sous ses pieds
		while (i < e.getWidth()) {
			if (((e.getPosX() + i) / 25 < n.entite.length && (e.getPosY()
					+ e.getHeight() + 1) / 25 < n.entite[0].length)
					&& n.entite[(e.getPosX() + i) / 25][(e.getPosY()
							+ e.getHeight() + 1) / 25].isSolid()) {
				e.getStrat().enVols = false;
				break;
			}

			// voir explication dans déplacement vers la droite
			if (i < e.getHeight() - 26)
				i += 25;
			else if (i == e.getHeight() - 1)
				i++;
			else
				i = e.getHeight() - 1;
		}
		// Droite impair , gauche pair
		if (e.getStrat().enVols && temp == 2) {
			return 4;
		} else if (e.getStrat().enVols && temp == 1) {
			return 3;
		} else if (e.getStrat().enVols && temp == 0) {
			return 5;
		}
		return temp;
	}

}
