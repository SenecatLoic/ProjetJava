package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;
import java.time.Month;
import my_api.enums.*;

/**
  * Cette classe affiche un formulaire pour que l'uilisateur puisse ajouter un
  * légume.
  * @see AjouterLegumeListener
  * @see MoisListener
  */
public class PanelAjouterLegume extends JPanel{

	public PanelAjouterLegume(Fenetre f,Parcelle p){
		//initialisation de la liste déroulante
		JComboBox<FamilleLegume> listeFamille = new JComboBox<FamilleLegume>();

		FamilleLegume[] tmpAction = FamilleLegume.values();

		for (FamilleLegume a : tmpAction) {
			listeFamille.addItem(a);
		}

		Font font = new Font("Arial",Font.BOLD,20);

		listeFamille.setFont(font);

		//initialisation des différents composants
		JLabel nomLegume = new JLabel("Nom du légume : ");
		nomLegume.setFont(font);
		nomLegume.setOpaque(true);
		nomLegume.setBackground(new Color( 207, 202, 194 ));

		JLabel semis = new JLabel("Choisissez les mois de semis : ");
		semis.setFont(font);
		semis.setOpaque(true);
		semis.setBackground(new Color( 207, 202, 194 ));

		JLabel recolte = new JLabel("Choisissez les mois de récolte :");
		recolte.setFont(font);
		recolte.setOpaque(true);
		recolte.setBackground(new Color( 207, 202, 194 ));				

		JButton valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(300,50));
		valider.setFont(font);

		JTextField champs = new JTextField(25);
		champs.setFont(font);

		JPanel moisRecolte = new JPanel();
		moisRecolte.setLayout(new GridLayout(3,4));

		JPanel moisSemis = new JPanel();
		moisSemis.setLayout(new GridLayout(3,4));

		//listeners
		AjouterLegumeListener observateur = new AjouterLegumeListener(champs,p,f);

		valider.addActionListener(observateur);
		listeFamille.addItemListener(observateur);

		//Mois qui vont être écouter
		MoisListener listenerMois1 = new MoisListener(observateur,MoisListener.RECOLTE);
		MoisListener listenerMois2 = new MoisListener(observateur,MoisListener.SEMIS);	

		for (Month m : Month.values()) {
			JCheckBox tmp = new JCheckBox(m.toString());
			JCheckBox tmp2 = new JCheckBox(m.toString());
			tmp.addItemListener(listenerMois1);
			moisRecolte.add(tmp);
			tmp2.addItemListener(listenerMois2);
			moisSemis.add(tmp2);
		}		

		//positionnement des composants
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.NONE;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.weightx=1;
		gbc.weighty=1;		
		gbc.anchor=GridBagConstraints.CENTER;

		gbc.gridwidth=2;
		this.add(listeFamille,gbc);
		
		gbc.gridy=1;
		this.add(nomLegume,gbc);
		
		gbc.gridy=2;
		this.add(champs,gbc);
		
		gbc.gridy=3;
		this.add(recolte,gbc);
		
		gbc.gridy=4;
		this.add(moisRecolte,gbc);

		gbc.gridy=5;
		this.add(semis,gbc);
		
		gbc.gridy=6;
		this.add(moisSemis,gbc);
		
		gbc.gridy=7;
		this.add(valider,gbc);
		
		this.setBackground(new Color( 207, 202, 194 ));
	}
}