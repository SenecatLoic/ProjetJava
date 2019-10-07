/**
 * La classe Affichagegrille va permettre d'afficher le labyrinthe
 */

import javax.swing.*;
import java.awt.*;

public class Affichagegrille{
  /**
    * Panneau qui va permettre de stocker toute les cases de la grille.
    */
  private JPanel affichage; 
  /**
    * Ce tableau de case permettra d'actionner des évènements.
    */
  private Case[][] grilleaffiche;
  /**
    * Case du départ, dans l'éditeur.
    */
  private Case depart;
  /**
    * Case de la sortie, dans l'éditeur.
    */  
  private Case sortie;
  /**
    * Les panneau qui seront afficher lors du déroulement de l'algorithme manuel.
    * Leur intérêt est que ce ne sont pas des boutons cliquables.
    */
  private JPanel[][] affichagealgo;
  /**
    * Panneau qui sera afficher lors du déroulement de l'algorithme manuel.
    */
  private JPanel departalgo;

  /**
    * Position du monstre
    */
  private JPanel monstre;

  /**
    * Constructeur pour afficher la grille dans l'éditeur.
    * @param grille,observateur
    */
  public Affichagegrille(Grille grille,Construction observateur){
    int taille=grille.getTaille();

    GridLayout page = new GridLayout(taille,taille,2,2);
    this.affichage = new JPanel();
    this.grilleaffiche= new Case[taille][taille];
    affichage.setLayout(page);

    //Boucle pour colorier les parties du labyrinthe

    for (int i=0;i<taille ;i++ ) {
      for (int j=0;j<taille ;j++ ) {

        this.grilleaffiche[i][j] = new Case(i,j);
        this.grilleaffiche[i][j].addActionListener(observateur);

        if (grille.getValeurIndice(i,j)==0) {
          this.grilleaffiche[i][j].setBackground(Color.WHITE);
          try{
            if (j==grille.getDepartX() && i==grille.getDepartY()) {
              this.grilleaffiche[i][j].setBackground(Color.RED);
              observateur.setDepart(this.grilleaffiche[i][j]); //On met dans l'observateur le depart
              this.depart=this.grilleaffiche[i][j];
            }

            if (j==grille.getSortieX() && i==grille.getSortieY()) {
              this.grilleaffiche[i][j].setBackground(Color.GREEN);
              observateur.setSortie(this.grilleaffiche[i][j]);
              this.sortie=this.grilleaffiche[i][j];
          }
          } catch (NullPointerException e){

          }
        } else {
          this.grilleaffiche[i][j].setBackground(Color.BLACK);
        }
        affichage.add(this.grilleaffiche[i][j]);
      }
    }
    affichage.setBackground(Color.BLACK);
  }

  /**
    * Constructeur pour afficher la grille en mode graphique.
    * @param g
    */
  public Affichagegrille(Grille g){
    int taille=g.getTaille();

    this.affichage = new JPanel();

    GridLayout page = new GridLayout(taille,taille,2,2);
    this.affichagealgo= new JPanel[taille][taille];
    affichage.setLayout(page);

    //Boucle pour colorier les parties du labyrinthe

    for (int i=0;i<taille ;i++ ) {
      for (int j=0;j<taille ;j++ ) {

        this.affichagealgo[i][j] = new JPanel();

        if (g.getValeurIndice(i,j)==0) {
          this.affichagealgo[i][j].setBackground(Color.WHITE);
          try{
            if (j==g.getDepartX() && i==g.getDepartY()) {
              this.affichagealgo[i][j].setBackground(Color.RED);
              this.departalgo=this.affichagealgo[i][j];              
            }

            if (j==g.getSortieX() && i==g.getSortieY()) {
              this.affichagealgo[i][j].setBackground(Color.GREEN);            
          }
          } catch (NullPointerException e){
          }
        } else {
          this.affichagealgo[i][j].setBackground(Color.BLACK);
        }
        affichage.add(this.affichagealgo[i][j]);
      }
    }
    affichage.setBackground(Color.BLACK);
  }

  /**
    * @return JPanel
    */
  public JPanel getAffichage(){
    return this.affichage;
  }

  /**
    * Cette méthode sert à afficher l'avancement de l'algorithme lors du déroulement
    * manuel.
    * @param position position qui va subir un changement de couleur.
    */
  public void changeDepart(Point position){
   try{
    this.departalgo.setBackground(Color.WHITE);
    this.affichagealgo[(int)position.getY()][(int)position.getX()].setBackground(Color.RED);
    this.departalgo=this.affichagealgo[(int)position.getY()][(int)position.getX()];
    }catch(NullPointerException e){} //Si cette exception est lévée cela veut dire que l'algo est fini
  }

  /**
    * Cette méthode permet d'afficher le monstre 
    * @param position position du monstre
    */
  public void afficheMob(Point position){

    if (this.monstre!=null) {
      this.monstre.setBackground(Color.WHITE);
    }

    this.affichagealgo[(int)position.getY()][(int)position.getX()].setBackground(Color.BLUE);
    this.monstre=this.affichagealgo[(int)position.getY()][(int)position.getX()];
  }

}
