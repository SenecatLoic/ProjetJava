package listeners;
import views.*;
import javax.swing.*;
import java.awt.*;
import my_api.*;

/** Bouton de modification des parcelles */
public class ModifBouton extends JLabel{

	private boolean persiste;  //Détermine si le bouton doit rester enfoncé
	private int action;  //A quoi sert le bouton

	public final static int COUPE_HORIZONTALE = 0;
	public final static int COUPE_VERTICALE = 1;
	public final static int REUNIR = 2;
	public final static int CONFIRMER = 3;

	public final static Color COULEUR_SURVOL = new Color(200,50,50);
	public final static Color COULEUR_ACTIF = new Color(255,150,0);
	public final static Color COULEUR_BASE = new Color(50,50,50);

    /** @param titre Titre du bouton
    @param action Utilité du bouton
    @param persiste Détermine si le bouton doit rester enfoncé */
  	public ModifBouton(String titre, int action, boolean persiste){

      //Innitialisation
  		super(titre);
  		this.action = action;
  		this.persiste = persiste;
  		this.setBackground(ModifBouton.COULEUR_BASE);
  		this.setForeground(Color.WHITE);
  		this.setHorizontalAlignment(JLabel.CENTER);
  		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  		this.setOpaque(true);
  	}

    /** Obtenir si le bouton doit rester enfoncé */
  	public boolean getSiPersistant(){

  		return this.persiste;
  	}

    /** Obtenir à  quoi sert le bouton */
  	public int getAction(){

  		return this.action;
  	}
 }