package modele;

public class Score {

	private static int score = 0;
	
	/*
	 * permet d'augmenter le score du joueur
	 */
	public static void augmenterScore(int valeurScore){
		score += valeurScore;
	}
	
	public static int getScore(){
		return score;
	}
}
