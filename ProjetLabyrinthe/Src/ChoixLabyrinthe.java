/**
 * La classe ChoixLabyrinthe gère les boutons de choix de labyrinthe.
 */

import java.awt.event.*;
import javax.swing.filechooser.*;
import javax.swing.*;
import java.io.*;

public class ChoixLabyrinthe extends Menucontroleur{

	/**
	  * Constructeur. 
	  * @param menu
	  */
	public ChoixLabyrinthe(Menu menu){
		super(menu);
	}

	/**
	  * Cette méthode sera invoquer lorsqu'un évènement est réaliser.
  	  */	
	@Override
	public void actionPerformed(ActionEvent evenement){
		String tmp = evenement.getActionCommand();
		Grille g = new Grille(1);

		if (tmp.equals("Créer Labyrinthe")) {
			this.menu.choixTaille();
		} else if(tmp.equals("Charger Labyrinthe")) {
		try{	
			JFileChooser chooser = new JFileChooser(); //pour charger un labyrinthe
    		chooser.setCurrentDirectory(new File("labyrinthe") );
			File file;	
			int returnVal = chooser.showOpenDialog(this.menu.getFenetre());
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        	
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
            	file = chooser.getSelectedFile();
            	g=new Grille(file);
			
			}
			this.menu.nettoyageFenetre(); //On nettoie la fenetre
			Deroulement deroulement= new Deroulement(this.menu.getFenetre(),g);
  		} catch(NullPointerException e){
  			this.menu.choixTypeLabyrinthe();
  		} //Si jamais l'utilisateur ne choisi aucun fichier
  		} else{
  			this.menu.nettoyageFenetre(); //On nettoie la fenetre
  			this.menu= new Menu(this.menu.fenetre);
  		}
	}
}