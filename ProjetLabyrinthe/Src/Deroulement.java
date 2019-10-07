/**
 * La classe Deroulement permet d'afficher toute la phase avant et après le déroulement de l'algorithme.
 * Cette classe fait appel a plusieurs objet pour réaliser l'algorithme
 *
 */

import javax.swing.*;
import java.awt.*;

public class Deroulement extends Graphisme{

	/**
	  * L'objet qui permet de réaliser les algorithmes.
	  */
	private RechercheSortie recherche;
	/**
	  * Variable qui stock le choix de l'utilisateur.
	  * Si elle vaut false on fait l'algo aléatoire sinon on fait le déterministe
	  */
	private boolean choix; 
	/**
	  * La position sur laquelle vont se baser les algorithmes.
	  */
	private Thesee thesee;
	/**
	  * L'affichage de la grille lors du déroulement
	  */
	private Affichagegrille affiche;

  /**
    * Constructeur qui affiche le choix entre les 2 algorithmes.
    * @param fenetre,grille
    */
	public Deroulement(JFrame fenetre,Grille grille){
		super(fenetre);
		this.thesee = new Thesee(grille.getDepart(),grille.getTaille());

		//on prépare l'objet pour chercher la sortie
		this.recherche= new RechercheSortie(grille,this.thesee);

		JButton[] bouton = new JButton[3];
		String[] intitule = {"Algorithme Aléatoire","Algorithme Déterministe","Choisir un autre Labyrinthe"};

		ChoixAlgorithme observateur = new ChoixAlgorithme(this);

		JPanel panneau = new JPanel();

		for (int i=0;i<3 ;i++ ) {
			bouton[i]= new JButton(intitule[i]);
			bouton[i].addActionListener(observateur);
			bouton[i].setPreferredSize(new Dimension(200,50));
			bouton[i].setFont(new Font("Arial",Font.BOLD,15));
			bouton[i].setMargin(new Insets(0,0,0,0));
			panneau.add(bouton[i]);
		}

		JTextArea titre = new JTextArea("\n\n\n\n\n      Choisissez l'algorithme de votre choix.\n"+ 
		"      L'algorithme déterministe est intelligent.\n");
		
		titre.setEditable(false);
		
		titre.setOpaque(true);

		titre.setBackground(new Color( 44, 62, 80 ));
		titre.setFont(new Font("Arial",Font.BOLD,40));
		titre.setForeground(new Color(208, 211, 212));

		this.fenetre.add(titre,BorderLayout.CENTER);
		this.setArea(titre);
		this.setPanneau(panneau);
		this.fenetre.add(titre,BorderLayout.CENTER);
		this.fenetre.add(panneau,BorderLayout.SOUTH);
		this.fenetre.setVisible(true);
	}

	/**
	  * Affiche le choix du déroulement.
	  */
	public void choixDeroulement(){
		this.nettoyageFenetre2();

		JButton[] bouton = new JButton[3];
		String[] intitule = {"Déroulement automatique",
		"Déroulement manuel",
		"Retour"};

		ChoixDeroulement observateur = new ChoixDeroulement(this);

		JPanel panneau = new JPanel();

		for (int i=0;i<3 ;i++ ) {
			bouton[i]= new JButton(intitule[i]);
			bouton[i].addActionListener(observateur);
			bouton[i].setPreferredSize(new Dimension(200,50));
			bouton[i].setFont(new Font("Arial",Font.BOLD,14));
			bouton[i].setMargin(new Insets(0,0,0,0));
			panneau.add(bouton[i]);
		}

		JTextArea titre = new JTextArea("\n\n\n   Vous pouvez choisir de quel manière \n   va se dérouler l'algorithme. \n"+
			"   Le déroulement automatique renvoie le nombre \n   moyen de coup pour sortir sur 100 test.\n"+
			"   Le mode manuel c'est à vous de jouer ! \n");
		titre.setEditable(false);
		titre.setOpaque(true);
		titre.setBackground(new Color( 44, 62, 80 ));
		titre.setFont(new Font("Arial",Font.BOLD,35));
		titre.setForeground(new Color(208, 211, 212));

		this.fenetre.add(titre,BorderLayout.CENTER);
		this.setPanneau(panneau);
		this.fenetre.add(panneau,BorderLayout.SOUTH);
		this.fenetre.setVisible(true);
	}

	/**
	  * Si le déroulement est automatique on fait appel à cette méthode.
	  */
	public void automatique(){
		double nbfois;
		Grille tmp = this.recherche.getGrille();

		if (this.choix) { //Si la variable est vrai on fait l'algo déterministe sinon celui aléatoire
			nbfois=this.recherche.deterministe();
		} else{
			nbfois=this.recherche.moyenne();
		}

		//si le labyrinthe n'as pas de chemin de sortie ou est trop long
		if (nbfois==-1) { 
			JOptionPane popup = new JOptionPane();
			popup.showMessageDialog(null, "Thésée restera à jamais dans ce labyrinthe!!!"
			, "", JOptionPane.INFORMATION_MESSAGE);	
		} else {
			JOptionPane popup = new JOptionPane();
			popup.showMessageDialog(null, "Thésée à trouver la sortie en "+nbfois+" fois"
			, "", JOptionPane.INFORMATION_MESSAGE);	
		}

		//On réinitialise la position de départ.
		this.thesee= new Thesee(tmp.getDepart(),tmp.getTaille());
		this.recherche= new RechercheSortie(tmp,this.thesee);
	}
	
	/**
	  * Si le déroulement est manuel on fait appel à cette méthode.
	  */
	public void manuel(){
		this.nettoyageFenetre2();

		Affichagegrille affichage = new Affichagegrille(this.recherche.getGrille());
		JLabel explication = new JLabel("Appuyer sur une touche " 
				+"pour faire avancer Thésée. Prochaine case : ");

		this.setAffichageGrille(affichage);
		Point tmp = new Point();

		//On avance d'un tour notre algo sans afficher le changement
		if (this.choix) {
			tmp=this.recherche.avancementDeterministeManuel();
		} else {
			tmp=this.recherche.avancementAleatoireManuel();
		}	

		JLabel prochain = new JLabel(tmp.toString()); //Pour afficher la prochaine case

		prochain.setFont(new Font("Arial",Font.BOLD,14));		

		ToucheControle controleur = new ToucheControle(this,prochain,tmp); //Controleur des touches du clavier
        this.getFenetre().requestFocusInWindow(); //Permet au Key listener de fonctionner
		this.getFenetre().addKeyListener(controleur);

		//mise en page
		SortirAlgorithme observateur = new SortirAlgorithme(this,controleur);
		JButton bouton = new JButton("Sortir de l'algorithme");
		bouton.addActionListener(observateur);

		JPanel panneau = new JPanel();
		this.setPanneau(panneau);
		this.panneau.add(explication);
		this.panneau.add(prochain);
		this.panneau.add(bouton);

		this.fenetre.add(affichage.getAffichage(),BorderLayout.CENTER);
		this.fenetre.add(this.getPanneau(),BorderLayout.SOUTH);
		this.fenetre.setVisible(true);
	}

	/**
  	  * @param b
      */
	public void setChoix(boolean b){
		this.choix=b;
	}

	/**
	  * Retourne RechercheSortie.
      * @return RechercheSortie
      * @see RechercheSortie
      */
	public RechercheSortie getRecherche(){
		return this.recherche;
	}

	/**
	  * Retourne le choix de l'utilisateur.
      *@return boolean
      */
	public boolean getChoix(){
		return this.choix;
	}
	/**
	  * Retourne l'affichage de la grille.
      *@return boolean
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
}