/**
 * La classe editeur permet d'afficher, à l'aide d'autre objet, l'éditeur de labyrinthe.
 *
 */


import javax.swing.*;
import java.awt.*;

public class Editeur{
  /**
    * Labyrinthe qui va être édité par l'utilisateur.
    */
  private Grille tableau;
  /**
    * Fenêtre qui va afficher les changements réalisés par l'utilisateur.
    */
  public JFrame fenetre;

  /**
    * L'affichage de la grille.
    */
  private  Affichagegrille afficher;

  /**
    * Panneau lié à la mise en page de l'éditeur.
    */
  private JPanel panneau;

  /**
    * Constructeur de la classe, qui va afficher l'éditeur.
    * @param fenetre,grille
    */
  public Editeur(JFrame fenetre,Grille grille){
    this.fenetre=fenetre;
    boolean b=true; //Pour selectionner un boutton au commencement
    EditeurRadioBouton controleuradio= new EditeurRadioBouton();
    this.tableau = grille;
    this.panneau = new JPanel();
    Construction observateur = new Construction(controleuradio,this.tableau);
    this.afficher = new Affichagegrille(this.tableau,observateur);
    EditeurBouton bouttoncontrol = new EditeurBouton(this);

    //Boutton pour construire le labyrinth
    JRadioButton[] option = new JRadioButton[4];
    ButtonGroup groupeoption = new ButtonGroup();
    String[] nomoption ={"Mur","Chemin","Départ","Sortie"};

    JButton[] boutton = new JButton[4];
    String[] nomboutton = {"Vider le Labyrinthe","Labyrinthe aléatoire","Sauvegarde","Continuer"};

    //Mise en page de tout les composants

    GridLayout misenpg = new GridLayout(3,1);
    this.panneau.setLayout(misenpg);

    //Mise en page des bouttons
    JPanel buton = new JPanel();
    GridLayout misenpgb = new GridLayout(1,4);
    buton.setLayout(misenpgb);

    //mise en page des bouttons radio
    JPanel choi = new JPanel();
    GridLayout misenpgc = new GridLayout(1,4);
    choi.setLayout(misenpgc);

    //Création des boutons de l'éditeur
    for (int i=0;i<4 ;i++ ) {
      option[i]=new JRadioButton(nomoption[i],b);
      groupeoption.add(option[i]);
      option[i].addActionListener(controleuradio);
      option[i].setBackground(Color.LIGHT_GRAY);
      choi.add(option[i]);
      b=false;
    }

    for (int i=0;i<4 ;i++ ) {
      boutton[i]=new JButton(nomboutton[i]);
      boutton[i].addActionListener(bouttoncontrol);
      buton.add(boutton[i]);
    }

    this.panneau.add(choi);
    this.panneau.add(new JLabel()); //Pour faire un espace entre les lignes
    this.panneau.add(buton);
    this.panneau.setBackground(Color.LIGHT_GRAY);

    this.fenetre.add(this.afficher.getAffichage(),BorderLayout.CENTER);
    this.fenetre.add(this.panneau,BorderLayout.SOUTH);
    this.fenetre.setVisible(true);

  }

  /**
    * Retourne la fenetre du menu.
    * @return Une fenetre.
    */
  public JFrame getFenetre(){
    return this.fenetre;
  }

  /**
    *
    * @return L'affichage de la grille.
    */ 
  public Affichagegrille getAffichageGrille(){
    return this.afficher;
  }

  /**
    *
    * @return Le panneau.
    */ 
  public JPanel getPanneau(){
    return this.panneau;
  }

  /**
    *
    * @return la grille de l'éditeur.
    */ 
  public Grille getGrille(){
    return this.tableau;
  }

  /**
    * @param g la nouvelle grille lié à l'éditeur.
    */
  public void setGrille(Grille g){
    this.tableau=g;
  }

  /**
    * Cette méthode permet de vérifier si le labyrinthe à été initialiser correctement.
    * @return boolean
    */
  public boolean verification(){
    
    //Si le départ ou l'arrivée n'as pas été créer on le signale
    if (this.tableau.getSortie()==null || this.tableau.getDepart()==null) {
          //On fait un appel à un objet qui fera office de PopUp pour le signaler
      JOptionPane popup = new JOptionPane(); 
      popup.showMessageDialog(null, "Le départ ou la sortie du labyrinthe a été oublier.",
        "Erreur!",JOptionPane.ERROR_MESSAGE);
      return false;            
      }     
    return true;
  }

}
