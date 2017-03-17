package modele;

import java.awt.Point;

public class StrategieCheckPoint extends Strategie {
	/*
	 * cette classe est utiliser comme strategie pour les objets ne servant que de checkPoint
	 */
	
	public StrategieCheckPoint(){
		super(new Point(0,0),false,false);
	}
	
	@Override
	public int eval(Entite e,Niveau n){
		return 1;
	}
}
