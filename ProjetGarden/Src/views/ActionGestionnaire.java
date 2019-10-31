package views;
import listeners.*;
import javax.swing.*;
import java.awt.*;
import my_api.*;

/** Controller des action dans l'arborescence du gestionnaire de parcelle */
public class ActionGestionnaire{

	private int layoutCompte;  //Nombre d'élément utilisé aussi pour la taille du GridLayout
	private GestionnairePanel gestionnairePanel; //Vue du gestionnaire de parcelle
  private ActionParcelle actionParcelle;  //Gérant des actions sur les parcelles
  private Parcelle root;  //Parcelle racine

    /** @param root Parcelle racine */
  	public ActionGestionnaire(Parcelle root){

  		this.layoutCompte = 0;
      this.root = root;
  		this.gestionnairePanel = new GestionnairePanel();
  	}

    /** Ajouter un gérant des actions sur les parcelles
    @param ap Gérant des actions sur les parcelles */
    public void addActionParcelle(ActionParcelle ap){

      this.actionParcelle = ap;
    }

    /** Obtenir la vue du gestionnaire de parcelle */
    public GestionnairePanel getPanel(){

      return this.gestionnairePanel;
    }

    /** Rafraichir le gestionnaire de parcelle */
    public void refresh(){

      //Si la parcelle racine est renseignée
      if(this.root != null){

        this.gestionnairePanel.reset(); //Suppression de tout les éléments contenus dans la vue
        this.gestionnairePanel.setLayout(new GridLayout(25,1)); //Ajout d'un nouveau GridLayout de base pour 25 éléments
        this.layoutCompte = 0;

        this.creerDepuisParcelleRacine(this.root);  //Recréation de l'aborescence depuis la parcelle racine
      }
    }

    /** Permet de créer une arborescence depuis une parcelle d'origine
    @param parcelle Parcelle d'origine */
    public void creerDepuisParcelleRacine(Parcelle parcelle){

      this.root = parcelle;
      this.layoutCompte++;

      //S'il y a plus de 25 éléments
      if(this.layoutCompte > 25){

        this.gestionnairePanel.setLayout(new GridLayout(this.layoutCompte,1));  //Modification du GridLayout de la vue en fonction du nouveau nombre d'éléments
      }

      //Récupération du bouton du gestionnaire que correspond à la parcelle
      ParcellePanel pp = actionParcelle.getParcellePanelAvecParcelle(parcelle);
      GestionnaireBouton bouton = pp.getGestionnaireBouton();
      
      //Si cet élément n'existe pas
      if(bouton == null){

        bouton = new GestionnaireBouton(this.root.getNomJardin(),pp,this);  //Création de l'élément
      }

      this.gestionnairePanel.addBouton(bouton); //Ajout au gestionnaire

      //On essaye d'ajouter les enfants de la parcelle
      try{

        this.addDepuisParcelle(this.root.getFirst(),"Parcelle-" + 1); //Ajout du premier
        this.addDepuisParcelle(this.root.getSecond(),"Parcelle-" + 2);  //Ajout du second
      }catch(IllegalStateException e){}

      //Si la parcelle n'a pas d'enfants et que les détails sur les sous-parcelles sont occultés
      if(bouton.getSiSelectionne()){

        //Le bouton est affiché comme séletionné
        bouton.setBackground(GestionnaireBouton.COULEUR_ACTIF);
        bouton.setForeground(GestionnaireBouton.COULEUR_TEXTE_ACTIF);
        this.actionParcelle.cacherDetails(bouton.getParcellePanel()); //On cache donc les détails
      }
    }

    /** Permet de créer une arborescence depuis une parcelle mère
    @param parcelle Parcelle mère
    @param texte Titre transmis par la mère */
    private void addDepuisParcelle(Parcelle parcelle, String texte){

      this.layoutCompte++;

      //S'il y a plus de 25 éléments
      if(this.layoutCompte > 25){

        this.gestionnairePanel.setLayout(new GridLayout(this.layoutCompte,1));  //Modification du GridLayout de la vue en fonction du nouveau nombre d'éléments
      }

      //Récupération du bouton du gestionnaire que correspond à la parcelle
      ParcellePanel pp = actionParcelle.getParcellePanelAvecParcelle(parcelle);
      GestionnaireBouton bouton = pp.getGestionnaireBouton();

      //Si cet élément n'existe pas
      if(bouton == null){

        bouton = new GestionnaireBouton(texte,pp,this);  //Création de l'élément
      }

      this.gestionnairePanel.addBouton(bouton); //Ajout au gestionnaire

      //On essaye d'ajouter les enfants de la parcelle
      try{

        this.addDepuisParcelle(parcelle.getFirst(),texte + "-" + 1);  //Ajout du premier
        this.addDepuisParcelle(parcelle.getSecond(),texte + "-" + 2);  //Ajout du second
      }catch(IllegalStateException e){}

      //Si la parcelle n'a pas d'enfants et que les détails sur les sous-parcelles sont occultés
      if(bouton.getSiSelectionne()){

        //Le bouton est affiché comme séletionné
        bouton.setBackground(GestionnaireBouton.COULEUR_ACTIF);
        bouton.setForeground(GestionnaireBouton.COULEUR_TEXTE_ACTIF);
        this.actionParcelle.cacherDetails(bouton.getParcellePanel()); //On cache donc les détails
      }
    }

    /** En cas de clic sur un élément de l'arborescence
    @param gb Eléùent cliqué */
    public void clicBouton(GestionnaireBouton gb){

      //S'il était déjà sélectionné
      if(gb.getSiSelectionne()){

        //On le désélectionne
        gb.setBackground(GestionnaireBouton.COULEUR_BASE);
        gb.setForeground(GestionnaireBouton.COULEUR_TEXTE_BASE);
        gb.setSiSelectionne(false);
        this.actionParcelle.montrerDetails(gb.getParcellePanel());  //On réaffiche les détails de la parcelle correspondante
      }else{

        //On le sélectionne
        gb.setBackground(GestionnaireBouton.COULEUR_ACTIF);
        gb.setForeground(GestionnaireBouton.COULEUR_TEXTE_ACTIF);
        gb.setSiSelectionne(true);
        this.actionParcelle.cacherDetails(gb.getParcellePanel());  //On cache les détails de la parcelle correspondante
      }
    }

  /** Modifier un bouton de élément de l'arborescence lorsque le curseur en sort
  @param gb Elément de l'arborescence en question */
  public void sort(GestionnaireBouton gb){

    //Si le bouton est sélectionné
    if(gb.getSiSelectionne()){

      //On l'affiche comme sélectionné
      gb.setBackground(GestionnaireBouton.COULEUR_ACTIF);
      gb.setForeground(GestionnaireBouton.COULEUR_TEXTE_ACTIF);
    }else{

      //Sinon on le montre normalement
      gb.setBackground(GestionnaireBouton.COULEUR_BASE);
      gb.setForeground(GestionnaireBouton.COULEUR_TEXTE_BASE);
    }

    this.actionParcelle.setBackgroundRecursivement(gb.getParcellePanel(),ParcellePanel.COULEUR_BASE); //On ne montre plus toutes les sous-parcelles consernés par cette parcelle
  }

  /** Modifier un bouton de élément de l'arborescence lorsque le curseur entre dedans
  @param gb Elément de l'arborescence en question */
  public void entre(GestionnaireBouton gb){

    //On montre qu'on le survol
    gb.setBackground(GestionnaireBouton.COULEUR_SURVOL);
    gb.setForeground(GestionnaireBouton.COULEUR_TEXTE_BASE);
    this.actionParcelle.setBackgroundRecursivement(gb.getParcellePanel(),ParcellePanel.COULEUR_SURVOL); //On montre toutes les sous-parcelles consernés par cette parcelle
  }
}