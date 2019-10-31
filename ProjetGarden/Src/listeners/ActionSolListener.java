package listeners;
import views.*;
import my_api.*;
import my_api.enums.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
  * Cette classe va récupérer les données taper par l'utilisateur puis
  * les envoyer au modèle pour créer une action sur le sol.
  */
public class ActionSolListener extends FormulaireListener{

	/**
	  * Cet attribut permet de savoir quel type d'action choisi
	  * l'utilisateur
	  */
	private ActionSolType typeAction;

	public ActionSolListener(JTextField chps,Parcelle p,Fenetre f){
		this.champs=chps;
		this.parcelle=p;
		this.fenetre=f;
		//on initialise la variable
		this.typeAction=ActionSolType.AMENDER;
	}

	public void actionPerformed(ActionEvent e){
		//permet de savoir si il y a eu une levée d'exception
		boolean echec=false;
		
		LocalDate ldate;

		String date = this.champs.getText();

		try{
			ldate=parseDate(date);

			//ActionSol action = new ActionSol(ldate,this.parcelle,this.typeAction);
			AbstractActionFactory factory = this.fenetre.getControlleur().getActionFactory();
			Action action = factory.addAction(ldate,this.typeAction);
			
			this.parcelle.addAction(action);

			this.fenetre.removeContenu();
			this.fenetre.removeBar();

			this.fenetre.setContenu(new ActionPanel(this.parcelle,this.fenetre));
			this.fenetre.setBar(new BarParcelle(this.fenetre,this.parcelle));

		} catch(java.lang.NumberFormatException exception){
			echec=true;
		} catch(java.lang.StringIndexOutOfBoundsException exception){
			echec=true;
		}

		if (echec) {
			JOptionPane.showMessageDialog(null, "Ce n'est pas le bon format de date! ","Erreur", JOptionPane.ERROR_MESSAGE); 
		}
	}

	/**
	  * Chaque fois qu'on change d'item cette méthode va refresh
	  * la valeur du type d'action 
	  */
	public void itemStateChanged(ItemEvent e){
		this.typeAction=(ActionSolType)e.getItem();
	}
}