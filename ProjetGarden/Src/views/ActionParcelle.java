package views;
import listeners.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.*;
import my_api.*;
import my_api.enums.*;

/** Gérant des actions sur les parcelles */
public class ActionParcelle{

	private ParcellePanel selection1;	//Sélection pour la réunification de deux parcelles
	private ParcellePanel selection2;	//Sélection pour la réunification de deux parcelles
	private boolean index;				//Est-ce que la parcelle cliquée doit être retenue dans selection1 ou selection2 ? 
	private ClicParcelle clicParcelle;	//Gérant des clics sur une parcelle
	private ActionGestionnaire gestionnaire;	//Gérant du gestionnaire de parcelles
	private static Map<Parcelle,ParcellePanel> parcellePanelListe;	//Conserve les ParcellePanel pour les retrouver depuis un objet Parcelle
	private JOptionPane optionPane;	//Afficher des popups

	/** @param am Gérant des actions sur les boutons d'action
	@param ag Gérant du gestionnaire de parcelles */
	public ActionParcelle(ActionModif am, ActionGestionnaire ag){

		//Innitialisation
		this.selection1 = null;
		this.selection2 = null;
		this.index = true;
		this.clicParcelle = new ClicParcelle(am,this);
		this.gestionnaire = ag;
		this.optionPane = new JOptionPane();
		this.gestionnaire.addActionParcelle(this); //Ajout de l'élement actel au gérant du gestionnaire de parcelles
		am.addActionParcelle(this);	 //Ajout de l'élement actel au gérant des actions sur les boutons d'action
		this.parcellePanelListe = new HashMap<Parcelle,ParcellePanel>();
	}

	/** Obtenir un jardin sous forme graphique
	@return Retourne un ParcellePanel contenant d'autres ParcellePanel pour constituer le jardin
	@param parcelle Parcelle depuis laquelle on souhaite créer le jardin
	@param niveau niveau de la parcelle depuis laquelle on souhaite créer le jardin */
	public ParcellePanel getPanel(Parcelle parcelle, int niveau){

		//Innitialisation
		int colone = 1;
		int ligne = 1;
		ParcellePanel jardin = new ParcellePanel(parcelle,niveau);
		jardin.setBackground(ParcellePanel.COULEUR_BASE);

		this.parcellePanelListe.put(parcelle,jardin);	//Ajout ç la liste des ParcellePanels

		//Mise en place du GridLayout en fonction de l'oriantation du découpage
		if (parcelle.getSplit() == Orientation.HORIZONTAL){

			colone = 1;
			ligne = 2;
		}else if(parcelle.getSplit() == Orientation.VERTICAL){

			colone = 2;
			ligne = 1;
		}

		GridLayout gd  =  new GridLayout(ligne,colone);
		jardin.setLayout(gd);

		//On essaye de créer ses enfants de la même façon
		try{

			jardin.add(this.getPanel(parcelle.getFirst(),niveau + 1));	//Premier enfant
			jardin.add(this.getPanel(parcelle.getSecond(),niveau + 1));	//Second enfant
		}catch(IllegalStateException e){

			//Si la parcelle n'a pas de sous-parcelles, ajout de bordure noires
			jardin.setBorder(BorderFactory.createLineBorder(Color.black));
		}

		jardin.addMouseListener(this.clicParcelle);	//Ajout du gérant des clics sur parcelle

		return jardin;	//On retourne le ParcellePanel de la parcelle
	}

  	/** Obtenir un ParcellePanel depuis une parcelle
  	@return Retourne le ParcellePanel correpondant sinon null
  	@param p Parcelle */
	public static ParcellePanel getParcellePanelAvecParcelle(Parcelle p){

  		return parcellePanelListe.get(p);
	}

	/** Obtenir la sélection 1
	@return Retourne null si il n'y pas de première sélection */
	public ParcellePanel getSelection1(){

		return this.selection1;
	}

	/** Obtenir la sélection2
	@return Retourne null si il n'y pas de deuxième sélection */
	public ParcellePanel getSelection2(){

		return this.selection2;
	}

	/** Modifier un ParcellePanel lorsque le curseur en sort
	@param pp ParcellePanel en question */
	public void sort(ParcellePanel pp){

		if(this.selection1 != pp && this.selection2 != pp){

			pp.setBackground(ParcellePanel.COULEUR_BASE);
		}else{

			pp.setBackground(ParcellePanel.COULEUR_ACTIF);
		}

		this.gestionnaire.sort(pp.getGestionnaireBouton());
	}

	/** Modifier un ParcellePanel lorsque le curseur entre dedans
	@param pp ParcellePanel en question */
	public void entre(ParcellePanel pp){

		pp.setBackground(ParcellePanel.COULEUR_SURVOL);
		this.gestionnaire.entre(pp.getGestionnaireBouton());
	}

	/** Modifier un ParcellePanel lorsqu'on le sélectionne
	@param pp ParcellePanel en question */
	public void selection(ParcellePanel pp){

		//Si deux parcelles ont été sélectionnées
		if(this.selection1 != null && this.selection2 != null){

			//Si index vaut true
			if(this.index){

				this.selection1.setBackground(ParcellePanel.COULEUR_BASE);	//La couleur de l'ancienne sélection est remis à celle de base
				this.selection1 = pp;	//On enregistre la nouvelle parcelle dans selection1
				this.selection1.setBackground(ParcellePanel.COULEUR_ACTIF);	//La couleur de la nouvelle sélection est mise à celle active
			}else{

				this.selection2.setBackground(ParcellePanel.COULEUR_BASE);	//La couleur de l'ancienne sélection est remis à celle de base
				this.selection2 = pp;	//On enregistre la nouvelle parcelle dans selection2
				this.selection2.setBackground(ParcellePanel.COULEUR_ACTIF);	//La couleur de la nouvelle sélection est mise à celle active
			}

			this.index = !this.index;	//Inversion de l'index
		}

		//Si la première sélection n'est pas faite
		if(this.selection1 == null){

			this.selection1 = pp;	//On enregistre la nouvelle parcelle dans selection1
			this.selection1.setBackground(ParcellePanel.COULEUR_ACTIF);	//La couleur de la nouvelle sélection est mise à celle active
		//Si c'est la seconde qui n'est pas faite
		}else if(this.selection2 == null && !this.selection1.equals(pp)){

			this.selection2 = pp;	//On enregistre la nouvelle parcelle dans selection2
			this.selection2.setBackground(ParcellePanel.COULEUR_ACTIF);	//La couleur de la nouvelle sélection est mise à celle active
		}
	}

	/** Désélectionne les parcelles sélectionnées */
	public void deselection(){

		//Si la sélection 1 n'est pas nulle
		if(this.selection1 != null){

			this.selection1.setBackground(ParcellePanel.COULEUR_BASE);	//La couleur est remis à celle de base
			this.selection1 = null;	//La sélection 1 désélectionnée
		}

		//Si la sélection 2 n'est pas nulle
		if(this.selection2 != null){

			this.selection2.setBackground(ParcellePanel.COULEUR_BASE);	//La couleur est remis à celle de base
			this.selection2 = null;	//La sélection 2 désélectionnée
		}
	}

	/** Découper une parcelle verticalement
	@param pp ParcellePanel de la parcelle à découper */
	public void coupeVerticale(ParcellePanel pp){

		//Tentative de découpage de la parcelle
		try{

			pp.getParcelle().SplitParcelle(Orientation.VERTICAL);
		}catch(IllegalStateException e){

			//Affichage d'un message indiquant que la parcelle est déjà découpée puis sortie de la méthode
			this.optionPane.showMessageDialog(null,"La parcelle contient des sous-parcelles, elle ne peut être découpée de nouveau.");
			return;
		}

		pp.setLayout(new GridLayout(1,2));	//Ajout d'un nouveau Layout 
		pp.add(this.getPanel(pp.getParcelle().getFirst(),pp.getNiveau() + 1));	//Ajout de la première parcelle à la mère
		pp.add(this.getPanel(pp.getParcelle().getSecond(),pp.getNiveau() + 1));	//Ajout de la seconde parcelle à la mère
		pp.setBorder(BorderFactory.createEmptyBorder());	//Retrait des bordures de la mère
		pp.revalidate();	//Rafraichissement de la mère
		this.gestionnaire.refresh();	//Rafraichissement du gestionnaire de parcelle
	}

	/** Découper une parcelle horizontalement
	@param pp ParcellePanel de la parcelle à découper */
	public void coupeHorizontale(ParcellePanel pp){

		//Si la parcelle est déja découpée
		try{

			pp.getParcelle().SplitParcelle(Orientation.HORIZONTAL);
		}catch(IllegalStateException e){

			//Affichage d'un message indiquant que la parcelle est déjà découpé puis sortie de la méthode
			this.optionPane.showMessageDialog(null,"La parcelle contient des sous-parcelles, elle ne peut être découpée de nouveau.");
			return;
		}

		pp.setLayout(new GridLayout(2,1));	//Ajout d'un nouveau Layout 
		pp.add(this.getPanel(pp.getParcelle().getFirst(),pp.getNiveau() + 1));	//Ajout de la première parcelle à la mère
		pp.add(this.getPanel(pp.getParcelle().getSecond(),pp.getNiveau() + 1));	//Ajout de la seconde parcelle à la mère
		pp.setBorder(BorderFactory.createEmptyBorder());	//Retrait des boordures de la mère
		pp.revalidate();	//Rafraichissement de la mère
		this.gestionnaire.refresh();	//Rafraichissement du gestionnaire de parcelle
	}

	/** Réunir deux parcelles */
	public void reunir(){

		//Si les deux parcelles ne sont pas sélectionés
		if(this.selection1 == null || this.selection2 == null){

			//Affichage d'un message indiquant le problème puis sortie de la méthode
			this.optionPane.showMessageDialog(null,"Veuillez sélectionner 2 parcelles s'il vous plaît.");
			return;
		}

		//Si une des deux parcelles contient des sous parcelles puis sortie de la méthode
		if(this.selection1.getParcelle().getSplit() != null || this.selection2.getParcelle().getSplit() != null){

			this.deselection();	//Désélection
			//Affichage d'un message indiquant le problème puis sortie de la méthode
			this.optionPane.showMessageDialog(null,"Une des deux parcelles sélectionnée contient des sous-parcelles. Verifiez que vous ne selectionnez pas une parcelle dont vous avez occulté les détails.");
			return;
		}

		Parcelle mere = this.selection1.getParcelle().getMother();	// Récupération de la mère de la première sélection
		ParcellePanel merePanel = ActionParcelle.getParcellePanelAvecParcelle(mere);	//Récpération du ParcellePanel de la mère

		//Si la mmère de la première parcelle n'est pas la même que celle de la seconde
		if(!mere.equals(this.selection2.getParcelle().getMother())){

			this.deselection();	//Désélection
			//Affichage d'un message indiquant le problème puis sortie de la méthode
			this.optionPane.showMessageDialog(null,"Les deux sous-parcelles ne font pas parti de la même parcelle mère.");
			return;
		}

		merePanel.removeAll();	//Supression du contenu du ParcellePanel de la mère
		merePanel.setBorder(BorderFactory.createLineBorder(Color.black));	//Ajout d'une bordure noire sur le ParcellePanel de la mère
		mere.reset();	//Indiquation à la parcelle mère qu'elle n'est plus découpée
		merePanel.revalidate();	//Rafraichissement du ParcellePanel de la mère
		this.deselection();	//Désélection
		this.gestionnaire.refresh();	//Rafraichissement du gestionnaire de parcelle
	}

	/** Change la couleur de fond d'une parcelle et de ses sous-parcelles
	@param pp  ParcellePanel de la parcelle d'origine
	@param couleur Couleur à appliquer */
	public void setBackgroundRecursivement(ParcellePanel pp, Color couleur){

		//Si les sous-parcelles de la parcelle sont cachées
		if(pp.getGestionnaireBouton().getSiSelectionne()){

			//Et que la parcelle est sélectionnée
			if((this.selection1 != null && this.selection1.equals(pp)) || (this.selection2 != null && this.selection2.equals(pp))){

				pp.setBackground(ParcellePanel.COULEUR_ACTIF);	//La coulleur active est appliquée
			}else{

				pp.setBackground(couleur);	//Sinon la couleur transmise est appliquée
			}

		//Si elles ne sont pas cachées
		}else{

			//on essaye de changer le background des sous-parcelles
			try{

				this.setBackgroundRecursivement(this.getParcellePanelAvecParcelle(pp.getParcelle().getFirst()),couleur);
				this.setBackgroundRecursivement(this.getParcellePanelAvecParcelle(pp.getParcelle().getSecond()),couleur);	
			}catch(IllegalStateException e){	//S'il elle n'a pas de sous-parcelle

				//Et que la couleur transmise est celle de base
				if(couleur.equals(ParcellePanel.COULEUR_BASE)){

					//Et que la parcelle est sélectionnée
					if((this.selection1 != null && this.selection1.equals(pp)) || (this.selection2 != null && this.selection2.equals(pp))){

						pp.setBackground(ParcellePanel.COULEUR_ACTIF);	//La coulleur active est appliquée
					//Si elle n'est pas sélectionnée
					}else{

						pp.setBackground(couleur);	//Sinon la couleur transmise est appliquée
					}
				//Si la couleur de base n'est pas celle transmise
				}else{

					pp.setBackground(couleur);	//Sinon la couleur transmise est appliquée
				}
			}
		}
	}

	/** Cacher les sous-parcelles d'une parcelle
	@param pp ParcellePanel d'origine */
	public void cacherDetails(ParcellePanel pp){

		this.deselection();	//Désélection
		pp.removeAll();	//Supression du contenu du ParcellePanel de la parcelle d'origine
		pp.setBorder(BorderFactory.createLineBorder(Color.black));	//Ajout d'une bordure noire sur le ParcellePanel de la parcelle d'origine
		pp.revalidate();	//Rafraichissement du ParcellePanel de la parcelle d'origine
	}

	/** Montrer les sous-parcelles d'une parcelle
	@param pp ParcellePanel d'origine */
	public void montrerDetails(ParcellePanel pp){

		this.deselection();	//Désélection
		//On essaye de rajouter les sous-parcelles de la parcelle
		try{

			pp.add(this.getParcellePanelAvecParcelle(pp.getParcelle().getFirst()));	//Ajout de la première sous-parcelle
			pp.add(this.getParcellePanelAvecParcelle(pp.getParcelle().getSecond()));	//Ajout de la seconde sous-parcelle
			pp.setBorder(BorderFactory.createEmptyBorder());	//Retrait des bordures de la parcelle d'origine
			pp.revalidate();	//Rafraichissement du ParcellePanel de la parcelle d'origine
		}catch(IllegalStateException e){}		//Sinon on ne fait rien
	}
}