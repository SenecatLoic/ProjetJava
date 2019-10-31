package listeners;
import views.*;
import java.awt.event.*;
import java.time.Month;
import java.awt.*;
import javax.swing.*;
import my_api.*;

/**
  * La classe va envoyer au listener de création de légume quelles mois sont sélectionner
  * par l'utilisateur.
  @see AjouterLegumeListener
  */
public class MoisListener implements ItemListener{

	private int mois;

	private AjouterLegumeListener legumeListener;
	
	/**
	  * spécifie si on envoie les donner dans récolte ou semis 
	  */
	private boolean recolte;

	//constante 
    public final static boolean RECOLTE = true;

	public final static boolean SEMIS = false;

	public MoisListener(AjouterLegumeListener listener,boolean isRecolte){
		this.mois=0;
		this.legumeListener=listener;
		this.recolte=isRecolte;
	}
	
	public void itemStateChanged(ItemEvent e){
		JCheckBox box = (JCheckBox) e.getItem();
		Month m =  Month.valueOf(box.getText());


		if (e.getStateChange()==ItemEvent.SELECTED) {
			this.addMonth(m.getValue());
			if (this.recolte) {
				this.legumeListener.setRecolte(this.mois);
			} else{
				this.legumeListener.setSemis(this.mois);
			}
			
		}else{
			this.delMonth(m.getValue());
			if (this.recolte) {
				this.legumeListener.setRecolte(this.mois);
			} else{
				this.legumeListener.setSemis(this.mois);
			}
		}
	}

	/**
	  * Cette méthode à pour but de décaler les bits pour sauvegarder les mois
	  */
	private void addMonth(int month){
		int m = 0b1;

		for (int i=1; i < month;i++ ) {
			// décalage d'un bit vers la gauche
			m = m << 1;
		}
		
		this.mois = this.mois + m;
	}

	/**
	  * Cette méthode à pour but de décaler les bits pour sauvegarder les mois
	  */
	private void delMonth(int month){
		int tmp = 0b111111111111;
		int m = 0b1;

		for (int i=1; i < month;i++ ) {
			// décalage d'un bit vers la gauche
			m = m << 1;
		}

		tmp-=m;
		
		this.mois = this.mois & tmp;	
	}
}