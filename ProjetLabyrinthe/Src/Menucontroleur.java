/**
 * La classe abstraite Menucontroleur est hérité par tout les controleurs
 * du menu.
 */

import java.awt.event.*;

public abstract class Menucontroleur implements ActionListener{
	/**
	  * Menu qui va subir des changements lorsqu'un evènement sera réaliser.
	  */	
	protected Menu menu;

	/**
      *Constructeur de la classe abstraite.
      * @param menu
      */
	public Menucontroleur(Menu menu){
		this.menu=menu;
	}

	/**
	  * Méthode nécésaire lorsqu'un objet héritera de cette classe.
	  */
	@Override
	public abstract void actionPerformed(ActionEvent evenement);

}