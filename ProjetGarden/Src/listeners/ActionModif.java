package listeners;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import my_api.*;

/** Controller des actions sur les boutons d'action sur les parcelles */
public class ActionModif{

	private ModifBouton selection;	//Bouton sélectionné
	private ActionParcelle actionParcelle;	//Gérant des actions sur les parcelles
	private ActionModifPanel actionPanel;	//Vue des boutons d'action sur les parcelles

	public ActionModif(){

		this.selection = null;
		this.actionPanel = new ActionModifPanel(new ClicModif(this));
	}

	/** Ajouter un gérant des actions sur les parcelles
	@param ap Gérant des actions sur les parcelles */
	public void addActionParcelle(ActionParcelle ap){

		this.actionParcelle = ap;
	}

	/** Obtenir la vue des boutons d'action sur les parcelles */
	public ActionModifPanel getPanel(){

		return this.actionPanel;
	}

	/** Obtenir l'action du bouton sélectionné
	@return Retourne -1 si aucun bouton n'est sélectionné */
	public int getAction(){

		if(this.selection != null){

			return this.selection.getAction();
		}

		return -1;
	}

	/** En cas de clic sur un bouton
	@param mb Bouton cliqué */
	public void clicBouton(ModifBouton mb){

		//Si le gérant des actions sur les parcelles n'est pas vide et si le nouveau bouton cliqué n'est pas "confirmer réunification" (Car deselection() efface les parcelles sélectionnée, or elles sont nécessaires pour réunir les parcelles)
		if(this.actionParcelle != null && mb.getAction() != ModifBouton.CONFIRMER){

			this.actionParcelle.deselection();	//Désélection
		}

		//Si un bouton est déjà sélectionné et que c'est le même que celui cliqué
		if(this.selection != null && this.selection == mb){

			//On désélectionne le bouton
			this.selection.setBackground(ModifBouton.COULEUR_BASE);
			this.selection = null;
		}else{

			//Sinon, si un bouton est déjà sélectionné et que ce n'est pas le même que celui cliqué
			if(this.selection != null && this.selection != mb){

				this.selection.setBackground(ModifBouton.COULEUR_BASE);	//On désélectionne l'ancien
				this.selection = mb;	//On sélectionne le nouveau

				//S'il doit rester enfoncé
				if(this.selection.getSiPersistant()){

					this.selection.setBackground(ModifBouton.COULEUR_ACTIF);	//On idique par une autre couleur qu'il est toujours enfoncé
				}
			//Si aucun bouton n'était déjà sélectionné
			}else{
				
				this.selection = mb;	//On sélectionne le nouveau

				//S'il doit rester enfoncé
				if(this.selection.getSiPersistant()){

					this.selection.setBackground(ModifBouton.COULEUR_ACTIF);	//On idique par une autre couleur qu'il est toujours enfoncé
				}
			}
		}

		//Si un bouton est déjà sélectionné et que l'action est "confirmer réunification"
		if(this.selection != null && this.selection.getAction() == ModifBouton.CONFIRMER){

			this.actionParcelle.reunir();	//On réuni les parcelles
		}
	}

	/** Modifier un bouton de modification de parcelle lorsque le curseur en sort
	@param mb Bouton de modification de parcelle en question */
	public void sort(ModifBouton mb){

		//Si un bouton est sélectionné
		if(this.selection != null){

			//Et que c'est le même que celui dont le cuseur sort et qu'il doit rester enfoncé
			if(this.selection.equals(mb) && this.selection.getSiPersistant()){

				this.selection.setBackground(ModifBouton.COULEUR_ACTIF);	//On idique par une autre couleur qu'il est toujours enfoncé
			}else{

				mb.setBackground(ModifBouton.COULEUR_BASE);	//Sinon on met la couleur de base
			}
		}else{

			mb.setBackground(ModifBouton.COULEUR_BASE);	//Sinon on met la couleur de base
		}
	}

	/** Modifier un bouton de modification de parcelle lorsque le curseur entre dedans
	@param mb Bouton de modification de parcelle en question */
	public void entre(ModifBouton mb){

		mb.setBackground(ModifBouton.COULEUR_SURVOL);	//Couleur de survol
	}
}