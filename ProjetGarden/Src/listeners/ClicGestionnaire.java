package listeners;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import my_api.*;

/** Contrôle les clics sur les boutons du gestionnaire de parcelles */
public class ClicGestionnaire implements MouseListener{

	private ActionGestionnaire gestionnaire;	//Gérant du gestionnaire de parcelles

	/** @param ag Gérant du gestionnaire de parcelles */
  	public ClicGestionnaire(ActionGestionnaire ag){

  		this.gestionnaire = ag;
  	}

  	public void mouseClicked(MouseEvent e){

  		//Clic sur un bouton du gestionnaire
  		gestionnaire.clicBouton((GestionnaireBouton)e.getComponent());
  	}

	public void mouseExited(MouseEvent e){

		//Le curseur sort d'un bouton du gestionnaire
		gestionnaire.sort((GestionnaireBouton)e.getComponent());
	}

	public void mouseEntered(MouseEvent e){

		//Le curseur entre dans un bouton du gestionnaire
		gestionnaire.entre((GestionnaireBouton)e.getComponent());
	}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}
}