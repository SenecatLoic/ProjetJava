package listeners;
import views.*;
import my_api.*;
import my_api.enums.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
  * Cette classe va récupérer les données taper par l'utilisateur puis
  * les envoyer au modèle pour créer une action légume.
  */
public class ActionLegumeListener extends FormulaireListener{

	private ActionLegumeType typeAction;

	private ArrayList<Legume> legumes;

	private JComboBox<String> listeLegume;

	public ActionLegumeListener(JTextField chps,Parcelle p,Fenetre f,ArrayList<Legume> l,JComboBox<String> legume){
		this.champs=chps;
		this.parcelle=p;
		this.fenetre=f;
		this.typeAction=ActionLegumeType.ARRACHER;
		this.legumes=l;
		this.listeLegume=legume;
	}

	public void actionPerformed(ActionEvent e){
		
		boolean echec=false;
		
		LocalDate ldate=null;

		String date = this.champs.getText();

		String tmp = (String) this.listeLegume.getSelectedItem();

		Legume choisi = null;

		for (Legume l : this.legumes) {
			if (l.getNom().equals(tmp)) {
				choisi=l;
			}
		}

		try{
			ldate=parseDate(date);

		} catch(java.lang.NumberFormatException exception){
			echec=true;
		} catch(java.lang.StringIndexOutOfBoundsException exception){
			echec=true;
		} catch(java.time.DateTimeException exception){
			echec=true;
		}

		if (echec) {
			JOptionPane.showMessageDialog(null, "Ce n'est pas le bon format de date! ","Erreur", JOptionPane.ERROR_MESSAGE); 
		}else{
			if(!choisi.getSemis(ldate.getMonth()) && this.typeAction.equals(ActionLegumeType.SEMER)){
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas planter ce mois-ci !","Erreur", JOptionPane.ERROR_MESSAGE); 
			}else if(!choisi.getRecolte(ldate.getMonth()) && this.typeAction.equals(ActionLegumeType.RECOLTER)){
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas Récolter ce mois-ci !","Erreur", JOptionPane.ERROR_MESSAGE); 
			}else{
				//ActionLegume action = new ActionLegume(ldate,this.parcelle,choisi,this.typeAction);
				AbstractActionFactory factory = this.fenetre.getControlleur().getActionFactory();
				Action action = factory.addAction(ldate,choisi,this.typeAction);

				this.parcelle.addAction(action);

				this.fenetre.removeContenu();
				this.fenetre.removeBar();

				this.fenetre.setContenu(new ActionPanel(this.parcelle,this.fenetre));
				this.fenetre.setBar(new BarParcelle(this.fenetre,this.parcelle));				
			}
		}
	}

	public void itemStateChanged(ItemEvent e){
		Object tmp = e.getItem();

		if (tmp instanceof ActionLegumeType) {
			this.typeAction = (ActionLegumeType) tmp;
		}
	}
}