package views;
import listeners.*;
import my_api.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
  * Menu de l'application
  *@see MenuListener
  */
public class Menu extends JPanel{
	
	public Menu(Map<String,Parcelle> map,Fenetre fenetre){

        
        Iterator it = map.keySet().iterator();
		this.setLayout(new GridLayout(map.size(),1));

		//gestionnaire des panneaux qui seront contenu dans le menu
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.NONE;
		gbc.gridwidth=2;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.insets=new Insets(10,10,10,10);

        while(it.hasNext()){

        	JPanel panneau = new JPanel();
            
            String key = it.next().toString();
            
            JButton[] bouton = new JButton[3];

            JLabel nom = new JLabel(key);

            panneau.setBackground(new Color(160, 152, 150));
        	panneau.setLayout(new GridBagLayout());

        	nom.setFont(new Font("Arial",Font.BOLD,22));

        	bouton[0] = new JButton("Historique");
        	bouton[1] = new JButton("Modifier");
            bouton[2] = new JButton("Supprimer");

    		gbc.weightx=1;
			gbc.weighty=1;	    	
   			gbc.gridy=0;
			gbc.gridx=0;
        	panneau.add(nom,gbc);
        	gbc.gridx=3;
        	gbc.weightx=0;
			gbc.weighty=0;

        	for (int i=0;i<3 ; i++) {
        		bouton[i].setFont(new Font("Arial",Font.BOLD,18));

                bouton[i].addActionListener(new MenuListener(fenetre,map.get(key),key));
                panneau.add(bouton[i],gbc);
        		gbc.gridy+=2;
        	}

        	panneau.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220),15));
        	this.add(panneau);
        }
	}
}