package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.JPanel;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
  * Va afficher la globalité des actions
  */

public class ActionPanel extends JPanel{
	
	private Parcelle parcelle;

	public ActionPanel(Parcelle p,Fenetre fenetre){
		this.parcelle=p;

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		Iterator<Action> iterateur = p.getAllActions();

		JButton bouton = new JButton("Ajouter");

		JButton bouton2 =new JButton("Ajouter Légume");

		bouton.addActionListener(new ListenerAction(fenetre,p));
		bouton2.addActionListener(new ListenerAction(fenetre,p));

		gbc.fill=GridBagConstraints.NONE;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.CENTER;	
		gbc.weightx=0;
		gbc.weighty=0;	

		this.add(bouton,gbc);
		gbc.gridy=1;
		gbc.gridheight=2;
		this.add(new JLabel(" "),gbc);
		gbc.gridheight=1;		
		gbc.gridy=3;
		this.add(bouton2,gbc);
		gbc.weightx=1;
		gbc.weighty=1;	

		int j=4;
		gbc.gridx=0;

		while (iterateur.hasNext()) {
			Action tmpAction = iterateur.next();
			
			gbc.fill=GridBagConstraints.BOTH;
			gbc.gridy=j;
			gbc.gridwidth=1;
			gbc.gridheight=4;
			gbc.anchor=GridBagConstraints.CENTER;
			gbc.weightx=2;
			gbc.weighty=2;			
			j+=4;
		if (tmpAction instanceof ActionLegume) {
			ActionLegumePanel alp = new ActionLegumePanel(tmpAction);
			this.add(alp,gbc);
			} else{
				ActionSolPanel asp = new ActionSolPanel(tmpAction);
				this.add(asp,gbc);
			}
		}
	}
}