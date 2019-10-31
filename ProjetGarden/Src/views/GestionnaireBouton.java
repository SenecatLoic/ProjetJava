package views;
import listeners.*;
import javax.swing.*;
import java.awt.*;
import my_api.*;

/** Bouton correspondant à une parcelle et contenu dans l'arborescence du gestionnaire de parcelle */
public class GestionnaireBouton extends JLabel{

  private ParcellePanel parcellePanel;  //Parcelle sous forme graphique correspondant au bouton
  private boolean selectionne;  //Est-ce que le bouton est sélectionné

	final static Color COULEUR_SURVOL = new Color(200,50,50);
	final static Color COULEUR_ACTIF = new Color(200,200,200);
	final static Color COULEUR_BASE = new Color(80,80,80);
  final static Color COULEUR_TEXTE_BASE = new Color(255,255,255);
  final static Color COULEUR_TEXTE_ACTIF = new Color(80,80,80);

  /** @param titre Titre donné au bouton
  @param pp Parcelle sous forme graphique correspondant au bouton
  @param ag Gérant des actions faites sur le gestionnaire */
  public GestionnaireBouton(String titre, ParcellePanel pp, ActionGestionnaire ag){
      super();

      //Innitialisation
      this.parcellePanel = pp;
      this.parcellePanel.addGestionnaireBouton(this);
      this.selectionne = false;

      //Indentation
      titre = "| _ _" + titre;
      String indentation = " ";

      //Pour n >= 0 niveaux, on indente n fois
      for(int i = 0, niveau = this.parcellePanel.getNiveau(); i < niveau; i++){

        //Si on à atteint le niveau de la parcelle
        if(i == niveau - 1){

          //on fait une indentation simple
          indentation = "     " + indentation;
        }else{

          //Sinon on indente avec une barre pour la lisibilité
          indentation = " |    " + indentation;
        }
      }

      //Ajout de l'indentation au titre
      titre = indentation + titre;

      //Fin de l'innitialisation
      this.setText(titre);
      this.setBackground(GestionnaireBouton.COULEUR_BASE);
      this.setForeground(GestionnaireBouton.COULEUR_TEXTE_BASE);
      this.setOpaque(true);
      this.addMouseListener(new ClicGestionnaire(ag));
    }

    /** Indiquer si l'objet est sélectionné
    @param b Indique si l'objet est sélectionné */
    public void setSiSelectionne(boolean b){

      this.selectionne = b;
    }

    /** Obtenir si l'objet est sélectionné */
    public boolean getSiSelectionne(){

      return this.selectionne;
    }

    /** Obtenir la parcelle sous forme graphique */
    public ParcellePanel getParcellePanel(){

      return this.parcellePanel;
    }
 }