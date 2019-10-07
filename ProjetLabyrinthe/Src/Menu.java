/**
 * La classe Menu gère la partie graphique du menu de la simulation et implémente des controleurs
 * aux composants qui l'a compose.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class Menu extends Graphisme{

	/**
	  * Constructeur pour créer et afficher le menu de départ.
 	  * @param fenetre
  	  */
	public Menu(JFrame fenetre){
		
		//initialisation de l'objet
		super(fenetre);
		//Préparation de la fenetre
		fenetre.setSize(850, 650);
 		fenetre.setLocationRelativeTo(null);
    	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//mise en page du panneau
		JButton bouton = new JButton("Simulation");
		JButton bouton2 = new JButton("Jouer");
		JPanel panneau = new JPanel();

		Lancer observateur = new Lancer(this,fenetre);
		bouton.addActionListener(observateur);
		bouton2.addActionListener(observateur);

		bouton.setPreferredSize(new Dimension(200,50));
		bouton.setFont(new Font("Arial",Font.BOLD,25));

		bouton2.setPreferredSize(new Dimension(150,50));
		bouton2.setFont(new Font("Arial",Font.BOLD,25));

		panneau.add(bouton);
		panneau.add(bouton2);
		panneau.setBackground(new Color(188, 196, 193));
		this.setPanneau(panneau);

		JLabel titre = new JLabel("Labyrinthe Simulator",(int)JComponent.CENTER_ALIGNMENT);
		
		titre.setOpaque(true);
		titre.setBackground(new Color( 44, 62, 80 ));
		titre.setFont(new Font("Arial",Font.BOLD,55));
		titre.setForeground(new Color(208, 211, 212));

		this.setFond(titre);
		this.fenetre.add(titre,BorderLayout.CENTER);
		this.fenetre.add(panneau,BorderLayout.SOUTH);
		this.fenetre.setMinimumSize(new Dimension(800,600));
		this.fenetre.setVisible(true);
	}

	/**
  	  * Permet de changer la fenetre pour faire place au choix du type de labyrinthe.
  	  */
	public void choixTypeLabyrinthe(){
		this.nettoyageFenetre();

		JButton[] bouton = new JButton[3];
		String[] intitule = {"Créer Labyrinthe","Charger Labyrinthe","Retour"};

		ChoixLabyrinthe observateur = new ChoixLabyrinthe(this);

		JPanel panneau = new JPanel();

		panneau.add(new JLabel());

		for (int i=0;i<3 ;i++ ) {
			bouton[i]= new JButton(intitule[i]);
			bouton[i].addActionListener(observateur);
			bouton[i].setPreferredSize(new Dimension(150,50));
			bouton[i].setFont(new Font("Arial",Font.BOLD,15));
			bouton[i].setMargin(new Insets(0,0,0,0));
			panneau.add(bouton[i]);
		}

		JLabel titre = new JLabel("Choix de Labyrinthe",(int)JComponent.CENTER_ALIGNMENT);
		
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

/**
  * Permet de changer la fenêtre pour que l'utilisateur puisse choisir
  * la taille du labyrinthe qu'il va créer.
  */
	public void choixTaille(){
		this.nettoyageFenetre();

		JLabel etiquette = new JLabel("Taille du Labyrinthe ?");
		Font font = new Font("Arial",Font.BOLD,25);
		etiquette.setFont(font);

		JPanel panneau = new JPanel();
		GridLayout gestionnaire = new GridLayout(2,1);
		JTextField lignetext = new JTextField();
		ChoisirTaille observateur = new ChoisirTaille(this);

		etiquette.setHorizontalAlignment(JLabel.CENTER);
		lignetext.addActionListener(observateur);
		lignetext.setHorizontalAlignment(JTextField.CENTER);
		lignetext.setBackground(new Color(208, 211, 212));
		
		panneau.setLayout(gestionnaire);
		panneau.add(etiquette);
		panneau.add(lignetext);
		panneau.setPreferredSize(new Dimension(150,100));

		JLabel titre = new JLabel("Editeur de Labyrinthe !",(int)JComponent.CENTER_ALIGNMENT);
		
		titre.setOpaque(true);
		titre.setBackground(new Color( 44, 62, 80 ));
		titre.setFont(new Font("Arial",Font.BOLD,55));
		titre.setForeground(new Color(208, 211, 212));

		this.fenetre.add(titre,BorderLayout.CENTER);
		this.setFond(titre);
		this.setPanneau(panneau);
		this.fenetre.add(panneau,BorderLayout.SOUTH);
		this.fenetre.setVisible(true);
	}
}
