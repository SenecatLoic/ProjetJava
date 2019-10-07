/**
 * La classe ChoisirTaille gère les évènements liés au choix
 *  de la taille du labyrinthe.
 */

import java.awt.event.*;
import javax.swing.*;

public class ChoisirTaille extends Menucontroleur{

	/**
	  * Constructeur.
	  * @param menu
	  */
	public ChoisirTaille(Menu menu){
		super(menu);
	}

	/**
	  * Cette méthode sera invoquer lorsqu'un évènement est réaliser.
	  */
	@Override
	public void actionPerformed(ActionEvent evenement){
		int taille=0;
		boolean isint=true;

		try{
			taille = Integer.parseInt(evenement.getActionCommand());
		} catch (NumberFormatException e){
			isint=false;
		} catch (NullPointerException e){
			isint=false;
		}

		if (isint) {
		//On met une limite de taille
			if (taille<71) {
				Grille grille = new Grille(taille);
				this.menu.nettoyageFenetre();
	   			Editeur d = new Editeur(this.menu.getFenetre(),grille);
			} else {
			JOptionPane popup = new JOptionPane();
			String tmps = "La taille est trop grande! Donner une taille inférieur à 71";
			popup.showMessageDialog(null, tmps,"Attention!",JOptionPane.WARNING_MESSAGE);
		}

		} else{
		//Un objet qui sert de PopUp lors de la mauvaise saisie de la taille
			JOptionPane popup = new JOptionPane();
			popup.showMessageDialog(null, "Ce n'est pas un entier! Veuillez en taper un.",
				"Attention!",JOptionPane.WARNING_MESSAGE);
		}
  }
}
