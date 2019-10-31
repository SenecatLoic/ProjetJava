package listeners;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import my_api.*;

/** Contrôle les clics sur les boutons d'action */
public class ClicModif implements MouseListener{

	private ActionModif actionModif;	//Gérant des actions sur les boutons d'action

	/** @param am Gérant des actions sur les boutons d'action */
	public ClicModif(ActionModif am){

		this.actionModif = am;
	}

	public void mouseClicked(MouseEvent e){

		//Clic sur un bouton
		this.actionModif.clicBouton((ModifBouton)e.getComponent());
	}

	public void mouseExited(MouseEvent e) {

		//Le curseur sort d'un bouton
		this.actionModif.sort((ModifBouton)e.getComponent());
	}

	public void mouseEntered(MouseEvent e) {

		//Le curseur entre dans un bouton
		this.actionModif.entre((ModifBouton)e.getComponent());
	}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}
}