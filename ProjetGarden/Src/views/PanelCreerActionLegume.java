package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import my_api.enums.*;

/**
  * Cette classe affiche le formulaire pour ajouter une action legume.
  * @see ActionLegumeListener
  */

public class PanelCreerActionLegume extends JPanel{

	public PanelCreerActionLegume(Parcelle parcelle,Fenetre fenetre,Iterator<Legume> iterateur){
		//initialisation des listes déroulantes
		JComboBox<ActionLegumeType> listeAction = new JComboBox<ActionLegumeType>();

		JComboBox<String> listeLegume = new JComboBox<String>();

		ActionLegumeType[] tmpAction = ActionLegumeType.values();

		ArrayList<Legume> tmplegume = new ArrayList<Legume>();

		Color couleur = new Color( 207, 202, 194 );

		for (ActionLegumeType a : tmpAction) {
			listeAction.addItem(a);
		}

		while(iterateur.hasNext()){
			Legume l = iterateur.next();

			tmplegume.add(l);
			listeLegume.addItem(l.getNom());
		}


		Font font = new Font("Arial",Font.BOLD,22);

		listeLegume.setFont(font);
		listeAction.setFont(font);

		//initialisation des différents composants
		JLabel alerte = new JLabel ("Une alerte");
		alerte.setFont(font);
		alerte.setOpaque(true);
		alerte.setBackground(couleur);

		JLabel date = new JLabel("Date au format dd-mm-yyyy");
		date.setFont(font);
		date.setOpaque(true);
		date.setBackground(couleur);

		JButton valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(300,50));
		valider.setFont(font);

		JTextField champs = new JTextField(25);
		champs.setFont(font);

		//gestion des listeners

		ActionLegumeListener observateur = new ActionLegumeListener(champs,parcelle,fenetre,tmplegume,listeLegume);

		valider.addActionListener(observateur);
		listeAction.addItemListener(observateur);
		listeLegume.addItemListener(observateur);

		//positionnement des composants
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.NONE;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.CENTER;

		this.add(alerte,gbc);
		gbc.weightx=1;
		gbc.weighty=1;	
		gbc.gridx=1;
		gbc.gridwidth=2;
		this.add(listeAction,gbc);
		gbc.gridy=1;
		this.add(listeLegume,gbc);
		gbc.gridy=2;
		this.add(date,gbc);
		gbc.gridy=3;
		this.add(champs,gbc);
		gbc.gridy=4;
		this.add(valider,gbc);

		this.setBackground(couleur);
	}
}