package modele;

import java.io.IOException;
import java.util.List;

import vue.Niveau;

public interface Eval {

	/*
	 * permet de definir le comportement d'une entite en fonction de son environnement
	 * \param e entite dont on defini le comportement
	 * \param tiles tableau representant le decors du niveau
	 * \param l liste des ennemis du niveau
	 * \param lTrace liste des boucliers du niveau
	 */
	public int eval(Entite e, Niveau n);
}
