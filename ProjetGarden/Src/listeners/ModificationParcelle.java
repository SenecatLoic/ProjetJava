package listeners;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import my_api.*;

/** Controller de la modification de parcelles */
public class ModificationParcelle{

	private Parcelle jardin;	//Parcelle racine
	private ModificationParcellePanel modifPanel;	//Vue de la modification de parcelles
	private ActionParcelle actionParcelle;

	/** Instancie et lance directement la modification d'une nouvelle parcelle
	@param jardin Parcelle racine */
  	public ModificationParcelle(Parcelle jardin){
	  	
	  	this.nouvelleModification(jardin);
	 }

	 /** Lance la modification d'une nouvelle parcelle
	 @param jardin Parcelle racine */
	 public void nouvelleModification(Parcelle jardin){

	 	this.jardin = jardin;
	  	//Innitialisation des objets nécessaires à la vue
	  	ActionModif actions = new ActionModif();
	  	ActionGestionnaire gestionnaire = new ActionGestionnaire(jardin);
	  	this.actionParcelle = new ActionParcelle(actions,gestionnaire);
	  	//Innitialisation de la vue
	  	this.modifPanel = new ModificationParcellePanel(actions,gestionnaire,this.actionParcelle,this.jardin);
	 }

	 /** Obtenir la vue de a modification de parcelles */
	 public ModificationParcellePanel getPanel(){

	 	return this.modifPanel;
	 }
}