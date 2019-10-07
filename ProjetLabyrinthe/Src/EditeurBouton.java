/**
 * La classe EditeurBouton permet de gérer les evènements des boutons
 *  qu'il y a dans l'éditeur.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;

public class EditeurBouton implements ActionListener{
  /**
    * L'éditeur subira des changements graphique en fonction des boutons appuyer.
    */
  private Editeur editeur;

  /**
    * Constructeur.
    * @param grille,editeur
    */
  public EditeurBouton(Editeur editeur){
    this.editeur=editeur;
  }

  /**
    *Cette méthode sera réalisée lorsque l'utilisateur déclanchera des évènements.
    * Elle test lequelles des boutons est cliqué en fonction du nom du bouton.
    */
  @Override
  public void actionPerformed(ActionEvent evenement){
    String tmp=evenement.getActionCommand();

      if (tmp.equals("Sauvegarde")) {
        if (this.editeur.verification()) {
          //pour charger un labyrinthe
          JFileChooser chooser = new JFileChooser(); 
          //l'utilisateur sera dans les fichiers des labyrinthes
          chooser.setCurrentDirectory(new File("labyrinthe") ); 
          File file;
          //permet de savoir si l'utilisateur a choisi son option
          int returnVal = chooser.showSaveDialog(this.editeur.getPanneau()); 

          if (returnVal == JFileChooser.APPROVE_OPTION) {
            file=chooser.getSelectedFile(); //On prends le fichier sélectionner
            tmp = file.getName();
            this.editeur.getGrille().sauvegarderGrille(file);
          }
        }
    
    } else if (tmp.equals("Labyrinthe aléatoire")){
      this.editeur.getGrille().labyrintheAleatoire();
      this.changerEditeur();
    } else if(tmp.equals("Vider le Labyrinthe")){
      this.editeur.setGrille(new Grille(this.editeur.getGrille().getTaille()));
      this.changerEditeur();
    } else {
      Grille g=this.editeur.getGrille(); //Uniquement pour rendre le code plus lisible

      if (this.editeur.verification()) {
        //on enlève les composants du panneau
        this.editeur.getAffichageGrille().getAffichage().removeAll(); 
        //Puis on enlève le panneau de la fenêtre
        this.editeur.fenetre.remove(this.editeur.getAffichageGrille().getAffichage()); 
        this.editeur.getPanneau().removeAll();
        this.editeur.fenetre.remove(this.editeur.getPanneau());
        Deroulement deroulement = new Deroulement(this.editeur.getFenetre(),g);         
      }
    }
  }

  /**
    * Cette méthode permet de réinitialiser graphiquement l'éditeur.
    *
    */
  public void changerEditeur(){
    this.editeur.fenetre.remove(this.editeur.getAffichageGrille().getAffichage());
    this.editeur.fenetre.remove(this.editeur.getPanneau());
    this.editeur = new Editeur(this.editeur.getFenetre(),this.editeur.getGrille());
  }
}