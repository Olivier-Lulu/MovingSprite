package controleur;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import modele.Entite;
import modele.EntiteAnime;
import modele.HitBox;
import modele.Niveau;
import modele.StrategieJoueur;
import modele.Strategie;
import modele.StrategiePatrouille;
import modele.StrategieScorable;
import modele.StrategieTireur;
import vue.SpriteStocker;

public class Createur {

	/*
	 * 
	 * 
	 * Permet de créer une entiter a partire d'un fichier
	 * \param niveau le niveau dans lequel evoluera l'entite
	 * \param posX position horizontal initial de l'entite
	 * \param posY position vertical initial de l'entite
	 * \param strategie strategie de deplacement utilisé par l'entite
	 * \param path le chemin d'acces vers le fichier decrivant l'entiter
	 * 
	 * Le fichier doit etre formaté de la maniere suivante:
	 * 
	 * Entite:
	 * sprite: <x du sprite de depart> <y du sprite de depart> <nombre de frame>
	 * score: <point gagner en tuant l'entité>
	 * box: <taille globale x>,<taille globale y>
	 * subBox: <position x par rapport au point haut Gauche de l'entité>,<position y par rapport au point haut Gauche de l'entité> <taille x de la sous boite>,<taille en y de la sous boite>
	 * repeter autant de subBox que voulus
	 * --- 
	 * repeter autemp de box que de frame dans l'animation
	 */
	public static Entite creerEntiter(String path, int posX, int posY, Strategie strategie, SpriteStocker stock) throws IOException{
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		int nbSprite = 0;
		int score = 0;
	    Strategie strat = strategie;
    	BufferedReader fichier=new BufferedReader(new FileReader(new File(Createur.class.getResource(path).getFile())));
    	String ligne;
    	StringTokenizer st =null;
    	HitBox[] hitBox = null;
    	try{
    		ligne = fichier.readLine();
    		if(!ligne.equals("Entite:")){
    			fichier.close();
    			throw new IllegalArgumentException("Entite:\nfichier au mauvais format");
    		}
    		//lecture des info sur le sprite
    		ligne = fichier.readLine();
    		if(ligne != null)
    			st = new StringTokenizer(ligne);
    		if(!st.nextToken().equals("sprite:")){
    			fichier.close();
    			throw new IllegalArgumentException("Entite:\nsprite: manquant");
    		}
    		if(st.hasMoreTokens())
    			try{
    				x = Integer.parseInt(st.nextToken());
    			}catch(NumberFormatException e){
    				System.out.println("Entite:\nx du sprite mal formaté");
    			}
    		else{
    			fichier.close();
    			throw new IllegalArgumentException("Entite:\ndescription sprite manquant");
    		}
    		if(st.hasMoreTokens())
    			try{
    				y = Integer.parseInt(st.nextToken());
    			}catch(NumberFormatException e){
    				System.out.println("Entite:\ny du sprite mal formaté");
    			}
    		else{
    			fichier.close();
    			throw new IllegalArgumentException("Entite:\ndescription sprite manquant");
    		}
    		if(st.hasMoreTokens())
    			try{
    				nbSprite = Integer.parseInt(st.nextToken());
    			}catch(NumberFormatException e){
    				System.out.println("Entite:\nnombre de sprite mal formaté");
    			}
    		else{
    			fichier.close();
    			throw new IllegalArgumentException("Entite:\ndescription sprite manquant");
    		}
    		
    		//lecture des infos de score
    		ligne = fichier.readLine();
    		if(ligne != null)
    			st = new StringTokenizer(ligne);
    		else{
    			fichier.close();
    			throw new IllegalArgumentException("Entite:\nfichier terminer apres solide:");
    		}
    		if(!st.nextToken().equals("score:")){
    			fichier.close();
    			throw new IllegalArgumentException("Entite:\nscore: manquant");
    		}
    		if(st.hasMoreTokens())
    			try{
    			score = Integer.parseInt(st.nextToken());
    			}catch(NumberFormatException e){
    				System.out.println("Entite:\nscore mal formaté");
    				fichier.close();
    				throw e;
    			}
    		else{
    			fichier.close();
    			throw new IllegalArgumentException("Entite:\nsolidité non renseigné");
    		}

    		//lecture des infos de hitBox
    		hitBox = new HitBox[nbSprite];
    		HitBox hit = null;
    		for(int i = 0; i < nbSprite;i++){// pour chaque frame de l'animation
    			ligne = fichier.readLine();
    			if(ligne != null)
    				st = new StringTokenizer(ligne," ,");
    			else{
        			fichier.close();
    				throw new IllegalArgumentException("SpriteBox:\n fichier vide apres sprite");
    			}
    			if(!st.nextToken().equals("box:")){
        			fichier.close();
    				throw new IllegalArgumentException("SpriteBox:\n box: manquant");
    			}
    			if(st.hasMoreTokens())
    				try{
    					width = Integer.parseInt(st.nextToken());
    				}catch(NumberFormatException e){
    					System.out.println("SpriteBox:\n largeur de box erroner");
    				}
    			else{
        			fichier.close();
    				throw new IllegalArgumentException("SpriteBox:\n descrption taille global manquant");
    			}
    			if(st.hasMoreTokens())
    				try{
    					height = Integer.parseInt(st.nextToken());
    				}catch(NumberFormatException e){
    					System.out.println("SpriteBox:\n longeur de box erroner");
    					fichier.close();
    					throw e;
    				}
    			else{
        			fichier.close();
    				throw new IllegalArgumentException("SpriteBox:\n descrption taille global manquant");
    			}
    			hit = new HitBox(new Rectangle(posX,posY,width,height));
    			ligne = fichier.readLine();

    			try{//lecture de toute les sous boites
    				while(!ligne.equals("---")){
    					st = new StringTokenizer(ligne," ,");
    					if(!st.nextToken().equals("subBox:"))
    						throw new IllegalArgumentException("Box:\n subBox mal formater");
    					if(st.hasMoreTokens())
    						posX = Integer.parseInt(st.nextToken());
    					else
    						throw new IllegalArgumentException("Box:\n subBox mal formater");
    					if(st.hasMoreTokens())
    						posY = Integer.parseInt(st.nextToken());
    					else
    						throw new IllegalArgumentException("Box:\n subBox mal formater");
    					if(st.hasMoreTokens())
    						width = Integer.parseInt(st.nextToken());
    					else
    						throw new IllegalArgumentException("Box:\n subBox mal formater");
    					if(st.hasMoreTokens())
    						height = Integer.parseInt(st.nextToken());
    					else
    						throw new IllegalArgumentException("Box:\n subBox mal formater");
    					hit.add(new Rectangle(posX,posY,width,height));		
    					ligne = fichier.readLine();
    				}
    				hitBox[i] = hit;
    			}catch(NumberFormatException e){
    				System.out.println("SpriteBox:\n taille subBox erroner");
    				fichier.close();
    				throw e;
    			}
    		}
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	fichier.close();
    	
    	if(nbSprite < 2)
    		return new Entite(strat,score, hitBox[0],x,y);
    	else
    		return new EntiteAnime(posX, posY,
    				null, true, strat, null ,nbSprite, score, hitBox,x,y);
	}

	/*
	 * permet de créer un niveau a partire d'un fichier
	 * \param path chemin d'acces au fichier décrivant le niveau
	 * 
	 * Le fichier doit etre formaté de la maniere suivante:
	 * 
	 * Niveau:
	 * Taille: <taille en X>,<taille en Y>
	 * <chemin d'acces au tileSet contenant une ligne d'image de 25*25> <nombre d'image a charger>
	 * solidité sprite
	 * <1 pour solide 0 sinon> <numero de sprite a charger>
	 * ...
	 * Monde:
	 * <numero de la ligne dans "solidité sprite" décrivant le bloc> ...
	 * ...
	 * Ennemis:
	 * <nom du fichier decrivant l'enemis> <position en x>,<position en Y> <nom de la strategie> <parrametre de la stratégie, voire chaque strategie individuellement>
	 * Joueur: 
	 * <position en X du joueur>,<position en y de joueur>
	 *
	 */
	public static Niveau creerNiveau(String path) throws IOException{
		SpriteStocker stock = null;
		Entite[][] entite = null;
		LinkedList<Entite> mob = null;
		Entite joueur = null;
		BufferedReader fichier = new BufferedReader(new FileReader(new File(Createur.class.getResource(path).getFile())));
		String ligne;
		try{
			ligne = fichier.readLine();
			if(!ligne.equals("Niveau:")){
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n fichier au mauvais format");
			}
			//recupération de la taille du monde
			ligne = fichier.readLine();
			StringTokenizer st;
			if(ligne != null)
				st = new StringTokenizer(ligne," ,");
			else{
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n description taille monde manquant");
			}
			ligne = st.nextToken();
			if(!ligne.equals("Taille:")){
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n description Taille : monde manquant");
			}
			int x = 0;
			int y = 0;
			if(st.hasMoreTokens())
				try{
					x = Integer.parseInt(st.nextToken());
				}catch(NumberFormatException e){
					System.out.println("Niveau:\n erreur de taille x");
					fichier.close();
					throw e;
				}
			else{
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n taille du monde manquant");
			}
			if(st.hasMoreTokens())
				try{
					y = Integer.parseInt(st.nextToken());
				}catch(NumberFormatException e){
					System.out.println("Niveau:\n erreur de taille y");
					fichier.close();
					throw e;
				}
			else{
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n taille du monde manquant");
			}
			entite = new Entite[x][y];
			
			//recuperation du tileSet
			ligne = fichier.readLine();
			if(ligne == null){
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n tileSet manquant");
			}
			st = new StringTokenizer(ligne);
			ligne = st.nextToken();// on stock le chemin d'acces du tileset
			if(!st.hasMoreTokens()){
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n taille tileSet manquant");
			}
			try{
				x = Integer.parseInt(st.nextToken());// on stock le nombre d'image a charger
			}catch(NumberFormatException e){
				System.out.println("Niveau:\n erreur de taille tileSet");
				fichier.close();
				throw e;
			}
			try{
				stock = new SpriteStocker(ligne,x);
			}catch(Exception e){
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n tileSet manquant ou eroner");
			}
			
			// creation de la liste des different blocs du decors
			List<Point> l = new ArrayList<Point>();//arrayList car il faut un acces facile au ième element de la liste
			ligne = fichier.readLine();
			if(!ligne.equals("solidité sprite")){
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n legende code solidité sprite manquant ");
			}
			while(!ligne.equals("Monde:")){// on decris un nouveau bloc tant que on a pas atteint la fin de la liste marquer par le debut du monde
				ligne = fichier.readLine();
				if(ligne == null){
					fichier.close();
					throw new IllegalArgumentException("Niveau:\n fin du fichier dans la descrition du decors ");
				}else{
					if(!ligne.equals("Monde:")){
						st = new StringTokenizer(ligne);
						try{
							l.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
						}catch(NumberFormatException e){
							System.out.println("Niveau:\n erreur dans le code du decors");
							fichier.close();
							throw e;
						}
					}
				}
			}
			
			//recuperation de la structure du niveau
			for(int j = 0; j < entite[0].length;j++){
				ligne = fichier.readLine();
				if(ligne != null)
					st = new StringTokenizer(ligne);
				else{
					fichier.close();
					throw new IllegalArgumentException("Niveau:\n ligne du monde manquant");
				}
				for(int i = 0;i <entite.length;i++){
					if(!st.hasMoreTokens()){
						fichier.close();
						throw new IllegalArgumentException("Niveau:\n ligne du monde manquant");
					}
					ligne = st.nextToken();
					int code = 0;
					try{
						code = Integer.parseInt(ligne);
					}catch(NumberFormatException e){
						System.out.println("Niveau:\n erreur dans le code du monde");
						fichier.close();
						throw e;
					}
					entite[i][j] = new Entite(i*25,j*25,25,25,null,l.get(code).x == 1,null,stock.get(l.get(code).y),0);//le fait d'utiliser un string pourra peut etre changer
				}
			}
			
			//recuperation des donnée sur les ennemis
			mob = new LinkedList<Entite>();
			ligne = fichier.readLine();
			if(ligne.equals("Ennemis:")){
				ligne = fichier.readLine();
				while(!ligne.equals("Joueur:")){
					st = new StringTokenizer(ligne," ,");
					try{
						mob.add(Createur.creerEntiter("/data/"+st.nextToken(),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),creerStrategie(st),stock));
					}catch(NoSuchElementException e){
						System.out.println(ligne);
						System.out.println("Niveau:\n position d'un ennemis manquante ou erroner");
						fichier.close();
						throw e;
					}catch(NumberFormatException e){
						System.out.println("Niveau:\n erreur dans la position d'un ennemis");
						fichier.close();
						throw e;
					}
					ligne = fichier.readLine();
				}
			}else
				ligne = fichier.readLine();

			//recupération des donné du joueur
			if(!ligne.equals("Joueur:")){
				System.out.println(ligne);
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n Joueur: manquant");
			}
			ligne = fichier.readLine();
			if(ligne == null){
				fichier.close();
				throw new IllegalArgumentException("Niveau:\n Joueur manquant");
			}
			try{
				st = new StringTokenizer(ligne,",");

				joueur = Createur.creerEntiter("/data/humain",Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),new StrategieJoueur(),stock);
			}catch(NoSuchElementException e){
				System.out.println("Niveau:\n position du joueur manquante ou erroner");
				fichier.close();
				throw e;
			}catch(NumberFormatException e){
				System.out.println("Niveau:\n erreur dans la position du joueur");
				fichier.close();
				throw e;
			}
		}catch(IOException e){
			e.printStackTrace();
			fichier.close();
		}
		fichier.close();
		Niveau niveau = new Niveau(stock, entite, mob, joueur);

		//toutes les entites on été créer sans reference au niveau (puisqu'il n'avais pas encors été créer) on leur donne donc leur reference maintenent
		//de meme les entite animé on besoin d'un acces a niveau pour cceder au spritstocker pour charger leur animation
		for(int i = 0; i< entite.length;i++)
			for(int j = 0; j < entite[0].length; j++)
				entite[i][j].setNiveau(niveau);
		Iterator<Entite> it = mob.iterator();
		while(it.hasNext()){
			Entite e = it.next();
			e.setNiveau(niveau);
			e.ajouterSprite();			
		}
		joueur.setNiveau(niveau);
		joueur.ajouterSprite();
		return niveau;
	}

	/*
	 * permet de gerer la creation des differente stratégie
	 * 
	 * le premier token de st contient le nom de la stratégie a utilisé
	 */
	private static Strategie creerStrategie(StringTokenizer st){
		Strategie strategie = null;
		String s;
		if(st.hasMoreTokens())
			s = st.nextToken();
		else
			throw new IllegalArgumentException("Niveau:/nNom de la strategie absent");
		switch(s){
		case "patrouille":
			strategie = creerStrategiePatrouille(st);
			break;
		case "scorable":
			strategie = new StrategieScorable();
			break;
		case "tireur":
			strategie = creerStrategieTireur(st);
			break;
		}
		return strategie;
	}
	
	/*
	 * permet de créer un strategie de patrouille 
	 * 
	 * les parrametre doivent etre formaté de la maniere suivante:
	 * <vitesse de deplacement horizontal> <vitesse vertical lors des sauts> <nombre de pas a effectuer avant demi-tours> <frequenc des saut>
	 * 
	 */
	private static Strategie creerStrategiePatrouille(StringTokenizer st){
		int vitesse = 0;
		int hauteurSaut = 0;
		int taillePatrouille = 0;
		int frequenceSaut = 0;
		
		//recuperation de la vitesse
		if(st.hasMoreTokens())
			try{
				vitesse = Integer.parseInt(st.nextToken());
			}catch(NumberFormatException e){
				System.out.println("Patrouille:\n vitesse mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Patrouille:\nIl manque 4 argument numerique");
		
		// recuperation de la Hauteur de saut
		if(st.hasMoreTokens())
			try{
				hauteurSaut = - Integer.parseInt(st.nextToken());
			}catch(NumberFormatException e){
				System.out.println("Patrouille:\n hauteur de saut mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Patrouille:\nIl manque 3 argument numerique");
		
		// recuperation de la distance de patrouille
		if(st.hasMoreTokens())
			try{
				taillePatrouille = Integer.parseInt(st.nextToken());
			}catch(NumberFormatException e){
				System.out.println("Patrouille:\n taille de la patrouille mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Patrouille:\nIl manque 2 argument numerique");
		
		// recuperation de la frequence de saut
		if(st.hasMoreTokens())
			try{
				frequenceSaut = Integer.parseInt(st.nextToken());
			}catch(NumberFormatException e){
				System.out.println("Patrouille:\n frequence de saut mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Patrouille:\nIl manque 1 argument numerique");
		
		if(st.hasMoreTokens())
			throw new IllegalArgumentException("Patrouille:\nTrop d'argument");
		return new StrategiePatrouille(new Point(vitesse,0), taillePatrouille, frequenceSaut, hauteurSaut);
	}

	/*
	 * permet de créer un strategie de tireur 
	 * 
	 * les parrametre doivent etre formaté de la maniere suivante:
	 * <vitesse de deplacement horizontal> <vitesse vertical lors des sauts> <nombre de pas a effectuer avant demi-tours> <frequenc des saut> <type de tire> <frequence de tire>
	 * 
	 */
	private static Strategie creerStrategieTireur(StringTokenizer st){
		int vitesse = 0;
		int hauteurSaut = 0;
		int taillePatrouille = 0;
		int frequenceSaut = 0;
		int type = 0;
		int frequenceTir = 0;
		int mouvTirX = 0;
		int mouvTirY = 0;
		
		int nombreArgumentRequis = 8;
		//recuperation de la vitesse
		if(st.hasMoreTokens())
			try{
				vitesse = Integer.parseInt(st.nextToken());
				nombreArgumentRequis --;
			}catch(NumberFormatException e){
				System.out.println("Tireur:\n vitesse mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Tireur:\nIl manque "+nombreArgumentRequis+" argument numerique");
		
		// recuperation de la Hauteur de saut
		if(st.hasMoreTokens())
			try{
				hauteurSaut = - Integer.parseInt(st.nextToken());
				nombreArgumentRequis --;
			}catch(NumberFormatException e){
				System.out.println("Tireur:\n hauteur de saut mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Tireur:\nIl manque "+nombreArgumentRequis+" argument numerique");
		
		// recuperation de la distance de patrouille
		if(st.hasMoreTokens())
			try{
				taillePatrouille = Integer.parseInt(st.nextToken());
				nombreArgumentRequis --;
			}catch(NumberFormatException e){
				System.out.println("Tireur:\n taille de la patrouille mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Tireur:\nIl manque "+nombreArgumentRequis+" argument numerique");
		
		// recuperation de la frequence de saut
		if(st.hasMoreTokens())
			try{
				frequenceSaut = Integer.parseInt(st.nextToken());
				nombreArgumentRequis --;
			}catch(NumberFormatException e){
				System.out.println("Tireur:\n frequence de saut mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Tireur:\nIl manque "+nombreArgumentRequis+" argument numerique");
		
		// recuperation du type de tire
		if(st.hasMoreTokens())
			try{
				type = Integer.parseInt(st.nextToken());
				nombreArgumentRequis --;
				if(type != 1 && type != 2 && type != 3)
					throw new IllegalArgumentException("Tireur:\nle type de tire ne peut etre que 1, 2 ou 3");
			}catch(NumberFormatException e){
				System.out.println("Tireur:\n type de tire mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Tireur:\nIl manque "+nombreArgumentRequis+" argument numerique");
		
		// recuperation de la frequence de tire
		if(st.hasMoreTokens())
			try{
				frequenceTir = Integer.parseInt(st.nextToken());
				nombreArgumentRequis --;
			}catch(NumberFormatException e){
				System.out.println("Tireur:\n frequence de tire mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Tireur:\nIl manque "+nombreArgumentRequis+" argument numerique");

		// recuperation du mouvementde tir
		if(st.hasMoreTokens())
			try{
				mouvTirX = Integer.parseInt(st.nextToken());
				nombreArgumentRequis --;
			}catch(NumberFormatException e){
				System.out.println("Tireur:\n mouvement en X de tir mal formaté");
				throw e;
			}
		else
			throw new IllegalArgumentException("Tireur:\nIl manque "+nombreArgumentRequis+" argument numerique");

		if(st.hasMoreTokens())
			try{
				mouvTirY = - Integer.parseInt(st.nextToken());
				nombreArgumentRequis --;
			}catch(NumberFormatException e){
				System.out.println("Tireur:\n mouvement en Y de tire mal formatée");
				throw e;
			}
		else
			throw new IllegalArgumentException("Tireur:\nIl manque "+nombreArgumentRequis+" argument numerique");
		
		
		return new StrategieTireur(frequenceTir, type,new Point(vitesse,0), taillePatrouille, frequenceSaut, hauteurSaut, mouvTirX, mouvTirY);
	}
}
