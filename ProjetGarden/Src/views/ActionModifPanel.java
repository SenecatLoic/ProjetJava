package views;
import listeners.*;
import javax.swing.*;
import java.awt.*;
import my_api.*;

/** vue des boutons de modification de parcelles */
public class ActionModifPanel extends JPanel{

	private ClicModif clicModif;	//Gérant des clics sur une parcelle

	/** @param cm Gérant des clics sur une parcelle */
  	public ActionModifPanel(ClicModif cm){
  		super();

  		//Innitialisation des différents éléments
  		this.clicModif = cm;
  		this.setLayout(new GridBagLayout());
  		GridBagConstraints gbc = new GridBagConstraints();
  		ModifBouton bouton;

  		//Bouton pour couper verticalement
  		//innitialisation
  		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		bouton = new ModifBouton("Couper verticalement",ModifBouton.COUPE_VERTICALE,true);
		bouton.addMouseListener(this.clicModif);
		//Ajout
		this.add(bouton,gbc);

		//Bouton pour couper horizontalement
  		//innitialisation
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		bouton = new ModifBouton("Couper horizontalement",ModifBouton.COUPE_HORIZONTALE,true);
		bouton.addMouseListener(this.clicModif);
		//Ajout
		this.add(bouton,gbc);

		//Bouton pour confirmer réunification
  		//innitialisation
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		bouton = new ModifBouton("Confirmer réunification",ModifBouton.CONFIRMER,false);
		bouton.addMouseListener(this.clicModif);
		//Ajout
		this.add(bouton,gbc);

		//Bouton pour réunir
  		//innitialisation
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		bouton = new ModifBouton("Réunir",ModifBouton.REUNIR,true);
		bouton.addMouseListener(this.clicModif);
		//Ajout
		this.add(bouton,gbc);
  	}
}