package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;
import my_api.enums.*;

/**
  * Cette classe affiche le formulaire pour ajouter une action sol.
  * @see ActionSolListener
  */
public class PanelCreerActionSol extends JPanel{

	public PanelCreerActionSol(Parcelle parcelle,Fenetre fenetre){
		//initialisation de la liste déroulante
		JComboBox<ActionSolType> listeAction = new JComboBox<ActionSolType>();

		ActionSolType[] tmpAction = ActionSolType.values();

		for (ActionSolType a : tmpAction) {
			listeAction.addItem(a);
		}


		Font font = new Font("Arial",Font.BOLD,23);

		listeAction.setFont(font);

		//initialisation des différents composants
		JLabel alerte = new JLabel ("Une alerte");
		alerte.setFont(font);
		alerte.setOpaque(true);
		alerte.setBackground(new Color( 207, 202, 194 ));
		JLabel date = new JLabel("Date au format dd-mm-yyyy");
		date.setFont(font);
		date.setOpaque(true);
		date.setBackground(new Color( 207, 202, 194 ));

		JButton valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(300,50));
		valider.setFont(font);

		JTextField champs = new JTextField(25);
		champs.setFont(font);

		//gestion des listeners
		ActionSolListener observateur = new ActionSolListener(champs,parcelle,fenetre);

		valider.addActionListener(observateur);
		listeAction.addItemListener(observateur);

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
		this.add(date,gbc);
		gbc.gridy=2;
		this.add(champs,gbc);
		gbc.gridy=3;
		this.add(valider,gbc);

		this.setBackground(new Color( 207, 202, 194 ));
	}
}