/**
  * La classe map jeu permet de proposer à l'utilisateur divers sorte de map.
  *
  */
import java.awt.*;
import javax.swing.*;

public class Mapjeu extends Graphisme{

	/**
      * Constructeur qui affiche le choix entre les 2 algorithmes.
      * @param fenetre
      */
	public Mapjeu(JFrame fenetre){
		super(fenetre);
		int i = 0;		
		
		JButton bouton = new JButton("Valider");
		String[] nomap= {"jeu","Monstro","Newton","Echec","China","Megalodon"};

		JComboBox<String> liste = new JComboBox<String>();
		liste.setFont(new Font("Arial",Font.BOLD,20));

		for (;i<6 ;i++ ) {
			liste.addItem(nomap[i]);
		}

		//ChoixDifficulte observateur = new ChoixDifficulte(this);

		JPanel panneau = new JPanel();
		GridLayout gestionnaire = new GridLayout(2, 1);
		panneau.setLayout(gestionnaire);

		bouton.addActionListener(new Choixmap(liste,this));
		bouton.setPreferredSize(new Dimension(100,50));
		bouton.setFont(new Font("Arial",Font.BOLD,20));
		bouton.setMargin(new Insets(0,0,0,0));

		panneau.add(liste);
		panneau.add(bouton);

		JLabel titre = new JLabel("Choissisez un labyrinthe !",(int)JComponent.CENTER_ALIGNMENT);
		
		titre.setOpaque(true);
		titre.setBackground(new Color( 44, 62, 80 ));
		titre.setFont(new Font("Arial",Font.BOLD,55));
		titre.setForeground(new Color(208, 211, 212));

		this.setFond(titre);
		this.fenetre.add(titre,BorderLayout.CENTER);
		this.setPanneau(panneau);
		this.fenetre.add(panneau,BorderLayout.SOUTH);
		this.fenetre.setVisible(true);
	}

	
}