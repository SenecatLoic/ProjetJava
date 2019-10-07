/**
 * La classe EditeurRadioBouton permet de gérer les options pour créer
 * le labyrinthe.
 */

import java.awt.event.*;

public class EditeurRadioBouton implements ActionListener{
  /**
    * correspond a la position dans le tableau de RadioBouton.
    */
  private int options;

/**
  * Constructeur qui initialise l'option à zéro.
  */
  public EditeurRadioBouton(){
    this.options=0; //De base l'option est sur la construction de mur
  }

  /**
    *Cette méthode sera réaliser lorsque l'utilisateur déclanchera des évènements
    * en utilisant les boutons radio de l'éditeur.
    */
  @Override
  public void actionPerformed(ActionEvent evenement){
    String tmp=evenement.getActionCommand();

    if(tmp.equals("Départ")){
      this.options=1;
    } else if(tmp.equals("Sortie")){
      this.options=2;
    } else if(tmp.equals("Chemin")){
      this.options=3;
    } else {
      this.options=0;
    }
  }

  /**
    * Renvoi l'option sous forme d'entier.
    *@return int
    */
  public int getOption(){
    return this.options;
  }

}
