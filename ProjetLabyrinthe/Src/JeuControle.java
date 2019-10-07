/**
 * La classe JeuControle permet de gérer les touches taper de l'utilisateur
 * 
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class JeuControle implements KeyListener{

	private JeuDeroulement jeuDeroulement;
	
	public JeuControle(JeuDeroulement jd){
		this.jeuDeroulement=jd;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		Jeu tmpj = this.jeuDeroulement.getJeu();

		//si l'utilisateur rentre dans un mur il y aura une exception
		//Il faut donc protégé tout cela
		try{
			if (keycode==KeyEvent.VK_DOWN) {
				tmpj.getRecherche().avancementSud();
			} else if (keycode==KeyEvent.VK_UP) {
				tmpj.getRecherche().avancementNord();
			} else if (keycode==KeyEvent.VK_LEFT) {
				tmpj.getRecherche().avancementOuest();
			} else if (keycode==KeyEvent.VK_RIGHT) {
				tmpj.getRecherche().avancementEst();
			}
		}catch(ArrayIndexOutOfBoundsException e){}

			//Affiche graphique des 2 entités
		this.jeuDeroulement.getAfficheGrille().changeDepart(tmpj.getPersonnage());
		this.jeuDeroulement.getAfficheGrille().afficheMob(tmpj.getMonstre());

		if(tmpj.getRecherche().isFinnish()){
			this.jeuDeroulement.getAfficheGrille().afficheMob(tmpj.getMonstre());
			//on supprimer le listener de la fenetre
			this.jeuDeroulement.getFenetre().removeKeyListener(this);
			this.jeuDeroulement.finJeu(true);
		}else{
			//on appele la méthode qui va gérer le tour du monstre
			if( tmpj.jouer( this.jeuDeroulement.getDifficult() ) ){
				this.jeuDeroulement.getAfficheGrille().afficheMob(tmpj.getMonstre());
				//on supprimer le listener de la fenetre
				this.jeuDeroulement.getFenetre().removeKeyListener(this);
				this.jeuDeroulement.finJeu(false);
			}		
		}
		this.jeuDeroulement.getAfficheGrille().afficheMob(tmpj.getMonstre());
	}

	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
}