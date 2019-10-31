package views;
import listeners.*;
import javax.swing.*;
import java.awt.*;
import my_api.*;

/** Element graphique représentant une parcelle */
public class ParcellePanel extends JPanel{

	private Parcelle parcelle;	//Parcelle correspondante
	private GestionnaireBouton gestionnaireBouton;	//Bouton correspondant du gestionnaire de parcelle correspondant à la parcelle
	private int niveau;	//Désigne sa profondeur (n + 1 est une sous-parcelle de n)
	private ActionParcelle actionParcelle;

	final static Color COULEUR_SURVOL = new Color(170,89,45);
    final static Color COULEUR_ACTIF = new Color(184,139,95);
   	final static Color COULEUR_BASE = new Color(134,89,45);

   	/** @param parcelle Parcelle correspondante
   	@param niveau Profondeur de la parcelle (n + 1 est une sous-parcelle de n) */
	public ParcellePanel(Parcelle parcelle, int niveau){
		super();

		this.parcelle = parcelle;
		this.niveau = niveau;
		this.gestionnaireBouton = null;
	}

	/** Ajouter le bouton correspondant à la parcelle
	@param gb Bouton correspondant */
	public void addGestionnaireBouton(GestionnaireBouton gb){

		this.gestionnaireBouton = gb;
	}

	/** Récupérer le bouton correspondant à la parcelle */
	public GestionnaireBouton getGestionnaireBouton(){

		return this.gestionnaireBouton;
	}

	/** Récupérer le niveau */
	public int getNiveau(){

		return this.niveau;
	}

	/** Récupérer la parcelle */
	public Parcelle getParcelle(){

		return this.parcelle;
	}
}
