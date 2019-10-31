package listeners;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import my_api.*;

/** Contrôle les clics sur les parcelles */
public class ClicParcelle implements MouseListener{

	private ActionParcelle actionParcelle;	//Gérant des actions sur les parcelles
	private ActionModif actionModif;	//Gérant des actions sur les boutons d'action

	/**
	@param am Gérant des actions sur les boutons d'action
	@param ap Gérant des actions sur les parcelles */
	public ClicParcelle(ActionModif am, ActionParcelle ap){

		this.actionModif = am;
		this.actionParcelle = ap;
	}

	public void mouseClicked(MouseEvent e){

		//Couper parcelle verticalement
		if(this.actionModif.getAction() == ModifBouton.COUPE_VERTICALE){

			this.actionParcelle.coupeVerticale((ParcellePanel)e.getComponent());
		}

		//Couper parcelle horizontalement
		if(this.actionModif.getAction() == ModifBouton.COUPE_HORIZONTALE){

			this.actionParcelle.coupeHorizontale((ParcellePanel)e.getComponent());		
		}

		//Réunir parcelles
		if(this.actionModif.getAction() == ModifBouton.REUNIR){

			this.actionParcelle.selection((ParcellePanel)e.getComponent());
		}
	}

	public void mouseExited(MouseEvent e) {

		//Le curseur sort de la parcelle
		this.actionParcelle.sort((ParcellePanel)e.getComponent());
	}

	public void mouseEntered(MouseEvent e) {

		//Le curseur entre dans la parcelle
		this.actionParcelle.entre((ParcellePanel)e.getComponent());
	}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}
}