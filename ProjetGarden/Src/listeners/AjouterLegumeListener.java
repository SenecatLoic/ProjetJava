package listeners;
import views.*;
import my_api.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import my_api.enums.*;

/**
  * Cette classe va récupérer les données taper par l'utilisateur puis
  * les envoyer au modèle pour créer un légume.
  */
public class AjouterLegumeListener extends FormulaireListener{

	private FamilleLegume famille;

	private int semis;

	private int recolte;

	public AjouterLegumeListener(JTextField chps,Parcelle p,Fenetre f){
		this.champs=chps;
		this.parcelle=p;
		this.fenetre=f;	
		this.famille=FamilleLegume.AUTRE;	
	}

	public void actionPerformed(ActionEvent e){

		AbstractLegumeFactory factory = this.fenetre.getControlleur().getLegumeFactory();

		String nom = this.champs.getText();

		if (nom.equals("")) {
			JOptionPane.showMessageDialog(null, "Veuillez Entrez un nom ! ","Erreur", JOptionPane.ERROR_MESSAGE);
		} else{
			factory.AddLegume(nom,this.famille, this.semis, this.recolte);
			
			this.fenetre.removeContenu();
			this.fenetre.removeBar();

			this.fenetre.setContenu(new ActionPanel(this.parcelle,this.fenetre));
			this.fenetre.setBar(new BarParcelle(this.fenetre,this.parcelle));			
		}	
	}

	public void itemStateChanged(ItemEvent e){
		Object tmp = e.getItem();
		this.famille=(FamilleLegume)tmp;		
	}

	public void setSemis(int s){
		this.semis=s;
	}

	public void setRecolte(int r){
		this.recolte=r;
	}
}