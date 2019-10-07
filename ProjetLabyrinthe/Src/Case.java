/**
 * La classe case va permettre de faire le lien entre la grille et le tableau
 * de panneau
 */

import javax.swing.*;
import java.awt.*;

public class Case extends JButton{
	/**
	  * La ligne où sera la case.
	  */
	private int ligne;
	/**
	  * La colonne où sera la case.
	  */ 
	private int colonne;

/**
  * Constructeur pour créer graphiquement les cases de la grille.
  *@param i,j
  */
	public Case(int i,int j){
		super();
		this.ligne=i;
		this.colonne=j;
		this.setBorderPainted(false); //Enlève les animations aux boutons
	}
	
/**
  *
  *@return la ligne.
  */
	public int getLigne(){
		return this.ligne;
	}

/**
  *
  *@return la colonne.
  */	
	public int getColonne(){
		return this.colonne;
	}
}	
