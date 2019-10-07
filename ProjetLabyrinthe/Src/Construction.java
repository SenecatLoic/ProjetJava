/**
 * La classe Construction permet de gérer les évènements sur le labyrinthe de l'éditeur.
 * Cette classe va apporté les changements fais par l'utilisateur sur le labyrinthe.
 *
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Construction implements ActionListener{
  /**
    * L'option choisi par l'utilisateur.
    */
  private EditeurRadioBouton options;
  /**
    * La grille sur laquelle l'objet va se baser.
    */
  private Grille labyrinthe;
  /**
    * La case qui représente la sortie.
    */ 
  private Case sortie;
  /**
    * La case qui représente le départ.
    */
  private Case depart;

  /**
    * Constructeur.
    * @param observateur,grille
    */
  public Construction(EditeurRadioBouton observateur,Grille grille){
    this.options=observateur;
    this.labyrinthe=grille;
    this.sortie= new Case(-1,-1); 
    this.depart= new Case(-1,-1); 
  }

  /**
    * Cette méthode sera invoquer lorsqu'un évènement est réaliser dans l'éditeur.
    * Elle colorie les cases en fonction de leur nature.
    * @param evenement 
    */  
  @Override
  public void actionPerformed(ActionEvent evenement){
    Case partie = (Case) evenement.getSource();

    if(this.options.getOption()==0 ){

      partie.setBackground(Color.BLACK);
      this.labyrinthe.setValeurIndice(partie.getColonne(),partie.getLigne(),1);

        //Si le mur est positionner sur le départ celui-ci est supprimé
      if (this.isDepart(partie.getColonne(),partie.getLigne()) ) {
        this.labyrinthe.setDepart(null);
        this.depart=new Case(-1,-1);
      }

        //Si le mur est positionner sur la sortie celle-ci est supprimé
      if (this.isSortie(partie.getColonne(),partie.getLigne()) ) {
        this.labyrinthe.setSortie(null);
        this.sortie=new Case(-1,-1);
      }

    } else if (this.options.getOption()==1) {
      partie.setBackground(Color.RED); //On change de couleur
      this.labyrinthe.setDepart(partie.getColonne(),partie.getLigne());
      this.labyrinthe.setValeurIndice(partie.getColonne(),partie.getLigne(),0); 

      this.depart.setBackground(Color.WHITE);
      this.depart=partie;
 
    //On enlève la valeur de sortie pour éviter que le départ soit au meme endroit que la sortie
      if (this.isDepart(this.sortie.getColonne(),this.sortie.getLigne()) ) { 

        this.labyrinthe.setSortie(null);
        this.sortie=new Case(-1,-1);
      }


    } else if (this.options.getOption()==2) {
      partie.setBackground(Color.GREEN); //On change de couleur
      this.labyrinthe.setSortie(partie.getColonne(),partie.getLigne());
      this.labyrinthe.setValeurIndice(partie.getColonne(),partie.getLigne(),0);
      
      this.sortie.setBackground(Color.WHITE);
      this.sortie=partie;

      //On enlève la valeur du départ pour éviter que le départ soit au meme endroit que la sortie
      if (this.isSortie(this.depart.getColonne(),this.depart.getLigne()) ) { 

        this.labyrinthe.setDepart(null);
        this.depart=new Case(-1,-1);
      }

        //On enlève la valeur de sortie pour éviter que le départ soit au meme endroit que la sortie
      if (this.isDepart(this.sortie.getColonne(),this.sortie.getLigne()) ) { 
        this.labyrinthe.setSortie(null);
        this.sortie=new Case(-1,-1);
      }

    } else if (this.options.getOption()==3) { 
      //Si le chemin est positionner sur le départ celui-ci est supprimé
      if (this.isDepart(partie.getColonne(),partie.getLigne()) ) {
        this.labyrinthe.setDepart(null);
        this.depart=new Case(-1,-1);
      }

        //Si le chemin est positionner sur la sortie celle-ci est supprimé
      if (this.isSortie(partie.getColonne(),partie.getLigne()) ) {
        this.labyrinthe.setSortie(null);
        this.sortie=new Case(-1,-1);
      }

      partie.setBackground(Color.WHITE);
      this.labyrinthe.setValeurIndice(partie.getColonne(),partie.getLigne(),0);
    }
  }

  /**
    * Change la valeur de la sortie.
    * @see case 
    *@param sortie
    */
  public void setSortie(Case sortie){
    this.sortie=sortie;
  }

  /**
    * Change la valeur du départ.
    * @see case
    * @param depart
    */
  public void setDepart(Case depart){
    this.depart=depart;
  }

  /**
    * Vérifie si c'est les coordonnées corresponde au départ
    * @param vcolonne,vligne valeur de la ligne et de la colonne
    */
  public boolean isDepart(int vcolonne,int vligne){
      if (this.depart.getLigne()==vligne && 
        this.depart.getColonne()==vcolonne ) {
        return true;
      } else{
        return false;
      }
  }

  /**
    * Vérifie si c'est les coordonnées corresponde à la sortie.
    * @param vcolonne,vligne valeur de la ligne et de la colonne
    */
  public boolean isSortie(int vcolonne,int vligne){
      if (this.sortie.getLigne()==vligne && 
        this.sortie.getColonne()==vcolonne ) {
        return true;
      } else{
        return false;
      }
  }
}
