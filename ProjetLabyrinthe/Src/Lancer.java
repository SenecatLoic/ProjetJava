/**
 * La classe Lancer premet de faire appel a la méthode 
 *  @see Menu lorsqu'un composant est actionné.
 */

import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class Lancer extends Menucontroleur{

  private JFrame fenetre;
  /**
    *Constructeur
    * @param menu
    */
	public Lancer(Menu menu,JFrame fenetre){
		super(menu);
    this.fenetre=fenetre;
	}

  /**
    * Cette méthode appele une méthode de menu.
    *@see Menu#choixTypeLabyrinthe()
    * choixTypeLabyrinthe 
    */	
	@Override
	public void actionPerformed(ActionEvent evenement){
    String tmp = evenement.getActionCommand();
		
    if(tmp.equals("Simulation")){
      this.menu.choixTypeLabyrinthe();
    } else {
      this.menu.nettoyageFenetre();
      Mapjeu mapjeu = new Mapjeu(this.fenetre);
    }
  }
}