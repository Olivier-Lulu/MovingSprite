package modele;

public class Score {

	private static int score = 0;
	
	public static void augmenterScore(int valeurScore){
		score += valeurScore;
	}
	
	public static int getScore(){
		return score;
	}
}
