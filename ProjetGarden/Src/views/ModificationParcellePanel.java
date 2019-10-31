package views;
import listeners.*;
import javax.swing.*;
import java.awt.*;
import my_api.*;

/** Vue de la modification de parcelles */
public class ModificationParcellePanel extends JPanel{

	/** @param am Gérant des actions faites les boutons de modification de parcelles
	@param ag Gérant des actions faites sur le gestionnaire
	@param ap Gérant des actions faites sur une parcelle
	@param root Parcelle racine */
  	public ModificationParcellePanel(ActionModif am, ActionGestionnaire ag, ActionParcelle ap, Parcelle root){
	  	super();

	  	//Innitialisation de la vue
	  	this.setLayout(new GridLayout(1,1));

	  	JPanel panel = new JPanel();
	  	panel.setLayout(new GridBagLayout());

	  	GridBagConstraints gbc = new GridBagConstraints();

	  	//Ajout des boutons de modification
	  	gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		panel.add(am.getPanel(),gbc);

		//Ajout de l'élément graphique représentant le jardin
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 5;
		gbc.weighty = 5;
		panel.add(ap.getPanel(root,0),gbc);
		
		//Ajout du gestionnaire de parcelles
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 5;
		panel.add(ag.getPanel(),gbc);
		//Ajout de l'arborescence au gestionnaire de parcelles
		ag.creerDepuisParcelleRacine(root);

		//Ajout du tout à la vue
		this.add(panel,BorderLayout.CENTER);
	  }
}