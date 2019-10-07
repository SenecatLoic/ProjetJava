/**
 * La classe Jeu sert de controleur lorsqu'on lance le jeu
 * Cette classe fait appel a plusieurs objet pour réaliser le jeu
 */

import java.util.*;
import java.awt.*;
import java.io.File;

public class Jeu{
	
	/**
	  * L'objet qui représente notre personnage dans le jeu
	  */
	private Thesee personnage;

	/**
	  * L'objet qui représente le monstre dans le jeu
	  */
	private Thesee monstre;

	/**
	  * L'objet qui gère le personnage
	  */
	private RechercheSortie recherche;

	/**
	  * L'objet qui va gérer les déplacements du monstre
	  */
	private RechercheSortie recherchemob;

	/**
      * Constructeur qui pose les bases du jeu
      * @param fenetre,grille
      */
	public Jeu(Grille grillep,Grille grillem){

		this.personnage = new Thesee(grillep.getDepart(),grillep.getTaille());

		grillem.setSortie(grillep.getDepart());
		this.monstre = new Thesee (grillem.getDepart(),grillem.getTaille());
	
		//on prépare l'objet pour chercher la sortie
		this.recherche= new RechercheSortie(grillep,this.personnage);

		//on prépare l'objet pour chercher la sortie
		this.recherchemob = new RechercheSortie(grillem,this.monstre);
	}

	/**
	  *
	  */
	public boolean mobfacile(){
		Point tmp;
		Point oldpos = this.recherchemob.getThesee();		

		if (oldpos.equals(this.personnage)) {
			return true;
		}

		this.recherchemob.getGrille().setSortie(this.personnage);

		tmp=this.recherchemob.avancementAleatoireManuel();

		if (tmp==null) {
			return true;
		} else{
			return false;
		}

	}

	/**
	  *@param b représente la difficulté désiré
	  *@return boolean pour savoir si le monstre à manger ou pas le joueur
	  */
	public boolean jouer(int b){
		boolean result;

		if (b==0) {
			result=this.mobfacile();
		} else if(b==1){
			result=this.mobdifficile();
		} else{
			result=this.mobparcours();
		}
		return result;
	}

	/**
	  *
	  */
	public boolean mobdifficile(){
		Point tmp;
		Point oldpos = this.recherchemob.getThesee();		

		if (oldpos.equals(this.personnage)) {
			return true;
		}

		this.recherchemob.getGrille().setSortie(this.personnage);

		tmp=this.recherchemob.avancementDeterministeManuel();

		if (tmp==null) {
			return true;
		} else{
			return false;
		}
	}

	public boolean mobparcours(){
		Point tmp;
		Point oldpos = this.recherchemob.getThesee();		

		if (oldpos.equals(this.personnage)) {
			return true;
		}

		this.recherchemob.changeSortie(this.personnage);
		this.recherchemob.getGrille().setSortie(this.personnage);

		ArrayDeque<Point> pile = this.recherchemob.getPile();
		pile.addLast(new Point((int)this.personnage.getX(),(int)this.personnage.getY()));
		tmp=pile.pollFirst();

		this.monstre.setLocation(tmp);
		
		if (this.recherchemob.isFinnish()) {
			return true;
		} else{
			return false;
		}

	}

	/**
	  * Méthode qui renvoi la recherche du personnage
	  * @return L'objet associé au personnage de l'utilisateur
	  */
	public RechercheSortie getRecherche(){
		return this.recherche;
	}

	/**
	  * Méthode qui renvoi le personnage
	  * @return L'objet associé au personnage de l'utilisateur
	  */
	public Thesee getPersonnage(){
		return this.personnage;
	}

	public Thesee getMonstre(){
		return this.monstre;
	}

	public RechercheSortie getrecherchemob(){
		return this.recherchemob;
	}
}