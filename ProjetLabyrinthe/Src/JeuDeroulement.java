/**
 * La classe JeuDeroulement sert de vue lorsqu'on lance le jeu.
 * Cette classe va afficher le jeu.
 */

import javax.swing.*;
import java.awt.*;

public class JeuDeroulement extends Graphisme{

	/**
	  * Pour connaitre le choix de l'utilisateur en terme de difficulté
	  */
	private int diffcult;

	/**
	  * Objet qui va gérer le jeu
	  */
	private Jeu jeu;

	/**
	  * L'affichage de la grille lors du déroulement
	  */
	private Affichagegrille affiche;

	/**
      * Constructeur qui affiche le choix entre les 2 algorithmes.
      * @param fenetre,grille,grille2
      */
	public JeuDeroulement(JFrame fenetre,Grille grille,Grille grille2){
		super(fenetre);
		JPanel panneau = new JPanel();		
		ChoixDifficulte observateur = new ChoixDifficulte(this);
		JLabel titre = new JLabel("Choissisez la difficulté !",(int)JComponent.CENTER_ALIGNMENT);
		this.jeu = new Jeu(grille,grille2);

		JButton[] bouton = new JButton[4];
		String[] intitule = {"Facile","Moyen","Difficile","retour au menu"};

		for (int i=0;i<3 ;i++ ) {
			bouton[i]= new JButton(intitule[i]);
			bouton[i].addActionListener(observateur);
			bouton[i].setPreferredSize(new Dimension(100,50));
			bouton[i].setFont(new Font("Arial",Font.BOLD,18));
			bouton[i].setMargin(new Insets(0,0,0,0));
			panneau.add(bouton[i]);
		}

		bouton[3]= new JButton(intitule[3]);
		bouton[3].setPreferredSize(new Dimension(150,50));
		bouton[3].setFont(new Font("Arial",Font.BOLD,18));
		bouton[3].setMargin(new Insets(0,0,0,0));	
		bouton[3].addActionListener(new Retour(this));	
		panneau.add(bouton[3]);

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

	public void startGame(){
		JPanel panneau = new JPanel();
		Affichagegrille affichage = new Affichagegrille(this.jeu.getRecherche().getGrille());
		JLabel explication = new JLabel("Utilisez les flèches directionnel pour "+ 
			"vous déplacer");

		explication.setFont(new Font("Arial",Font.BOLD,18));		

		this.setAffichageGrille(affichage);
		this.getAfficheGrille().afficheMob(this.jeu.getMonstre());

		//Controleur des touches du clavier
		JeuControle controleur = new JeuControle(this); 

        this.getFenetre().requestFocusInWindow(); //Permet au Key listener de fonctionner
		this.getFenetre().addKeyListener(controleur);

		//Il faut le faire qu'une seule fois
		if (this.diffcult==2) {
			this.jeu.getrecherchemob().parcourslargeur(this.jeu.getMonstre());
		}

		this.setPanneau(panneau);
		this.panneau.add(explication);

		this.fenetre.add(affichage.getAffichage(),BorderLayout.CENTER);
		this.fenetre.add(this.getPanneau(),BorderLayout.SOUTH);
		this.fenetre.setVisible(true);		
	}

	public void finJeu(boolean win){
		JButton bouton = new JButton("Retour");
		bouton.addActionListener(new Retour(this,this.affiche));
		bouton.setFont(new Font("Arial",Font.BOLD,18));	

		if (win) {
			JOptionPane popup = new JOptionPane(); //PopUp de la défaite
			popup.showMessageDialog(null, "Thésée est sorti du labyrinthe!"
			, "Victoire!", JOptionPane.INFORMATION_MESSAGE);	
		}else{
			JOptionPane popup = new JOptionPane(); //PopUp de la défaite
			popup.showMessageDialog(null, "Thésée s'est fait grignoter par le minotaure..."
			, "Défaite!", JOptionPane.INFORMATION_MESSAGE);		
		}

		this.panneau.add(bouton);
		this.fenetre.setVisible(true);
	}

	/**
	  * Retourne l'affichage de la grille.
      *@return Affichagegrille
      */
	public Affichagegrille getAfficheGrille(){
		return this.affiche;
	}

	/**
	  * Initialise ou chage l'affichage de la grille.
      *@param affichage de la grille.
      */
	public void setAffichageGrille(Affichagegrille affichage){
		this.affiche=affichage;
	}

	/**
	  * @return le jeu en question
	  */
	public Jeu getJeu(){
		return this.jeu;
	}

	public int getDifficult(){
		return this.diffcult;
	}

	public void setDifficult(int b){
		this.diffcult=b;
	}

}