/**
 * La classe ToucheControle permet de gérer le clavier lors du
 *  mode manuel de l'algorithme.
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ToucheControle extends ControleDeroulement implements KeyListener{
	/**
	  * Etiquette qui permet d'afficher la prochaine case de l'algorithme.
	  */
	private JLabel etiquette;
	/**
	  * Position de l'algorithme.
	  */
	private Point thesee;
	/**
	  * Pour retenir que l'algo est fini
	  */
	private boolean fini; 

	public ToucheControle(Deroulement deroulement,JLabel etiquette,Point thesee){
		super(deroulement);
		this.etiquette=etiquette;
		this.thesee=thesee;
	} 

	@Override
	public void keyPressed(KeyEvent event) {
		//On change graphiquement la situation
		this.deroulement.getAfficheGrille().changeDepart(this.thesee);

		//On avance d'un tour l'algo qui a été choisi sans changer graphiquement le labyrinthe
		if (this.deroulement.getChoix()) {
			this.thesee=this.deroulement.getRecherche().avancementDeterministeManuel();
		} else{
			this.thesee=this.deroulement.getRecherche().avancementAleatoireManuel(); 
		}

		/*Si l'algo est fini ce morceau de code lèvera une exception. ceci est sans inidence car
		 il n'y a pas de prochaine case à afficher */
		try{
		//Pour montrer la prochaine case ou thésée va aller
		this.deroulement.getPanneau().remove(this.etiquette);
		this.etiquette=new JLabel(this.thesee.toString());
		this.deroulement.getPanneau().add(this.etiquette);
		this.deroulement.getFenetre().setVisible(true);	
		} catch (NullPointerException e){
		}

		//On vérifie après chaque changement graphique si l'algo n'est pas fini
		if (this.fini) {
			JOptionPane popup = new JOptionPane(); //PopUp de la victoire
			popup.showMessageDialog(null, "Thésée à trouver la sortie! Bravo !"
			, "Victoire!", JOptionPane.INFORMATION_MESSAGE);

			JPanel tmpp = this.deroulement.getAfficheGrille().getAffichage();
			//On enlève l'affichage de la grille
			this.deroulement.getFenetre().remove(tmpp); 
			this.deroulement.nettoyageFenetre2();
			this.deroulement.getFenetre().removeKeyListener(this);

			Grille grille = this.deroulement.getRecherche().getGrille();
			this.deroulement= new Deroulement(this.deroulement.getFenetre(),grille);					
		}

		// Lorsque la méthode renvoie null l'algo a fini son travail
		//Cependant il faut encore l'afficher à l'écran 
		if (this.thesee==null) {
			this.fini=true;
		}
	}

	//On ne se sert pas de ces 2 méthodes de l'interface keyListener 
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void actionPerformed(ActionEvent evenement){}
}