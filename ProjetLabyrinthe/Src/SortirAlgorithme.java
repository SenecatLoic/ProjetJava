/**
 * La classe SortirAlgorithme permet de sortir de 
 * l'algorithme lorsqu'un évènement est réalisé.
 */

import java.awt.event.*;
import javax.swing.*;

public class SortirAlgorithme extends ControleDeroulement{
	/**
	  * Le controleur de la fenêtre qu'il faut enlever pour éviter les
	  * interférence entre les ToucheControle.
	  */
	private ToucheControle touchecontrole;

	/**
	  * Constructeur. 
	  * @param deroulement,touche
	  */
	public SortirAlgorithme(Deroulement deroulement,ToucheControle touche){
		super(deroulement);
		this.touchecontrole=touche;
	}

	/**
	  * Cette méthode sera invoquer lorsqu'un évènement est réaliser.
	  * Elle va permettre de retrouver le choix du déroulement.
	  */
	@Override
	public void actionPerformed(ActionEvent evenement){
		Grille tmpg =this.deroulement.getRecherche().getGrille();
		this.deroulement.nettoyageFenetre2();
		JPanel tmpp = this.deroulement.getAfficheGrille().getAffichage();
		this.deroulement.getFenetre().remove(tmpp);
		this.deroulement.getFenetre().removeKeyListener(touchecontrole);
		this.deroulement= new Deroulement(this.deroulement.getFenetre(),tmpg);
	}
}