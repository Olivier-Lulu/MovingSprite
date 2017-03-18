package modele;

import java.awt.Point;

public class StrategieCheckPoint extends Strategie {
	/*
	 * cette classe est utilisée comme strategie pour les objets servant que
	 * de checkPoint ou de fin de niveau
	 */

	public StrategieCheckPoint() {
		super(new Point(0, 0), false, false);
	}

	@Override
	public int eval(Entite e, Niveau n) {
		return 1;
	}
}
