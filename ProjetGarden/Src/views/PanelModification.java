package views;
import listeners.*;
import javax.swing.*;
import java.awt.*;
import my_api.*;

public class PanelModification extends JPanel{

	public PanelModification(String nomJardin,Fenetre fenetre){
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		//on récupère le jardin factory pour avoir tout les jardins
    	AbstractJardinFactory factory = fenetre.getControlleur().getJardinFactory();

    	Parcelle parcelle = factory.getJardin(nomJardin);

		ModificationParcelle modificationParcelle = new ModificationParcelle(parcelle);

		JPanel contenu = modificationParcelle.getPanel();

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 4;
		this.add(contenu,gbc);		
	}
}