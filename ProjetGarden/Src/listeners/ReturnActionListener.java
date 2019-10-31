package listeners;
import views.*;
import my_api.*;
import java.awt.event.*;

/**
  * Permet de retourner à la page des actions.
  */
public class ReturnActionListener implements ActionListener{
	private Fenetre fenetre;

	private Parcelle parcelle;
	
	public ReturnActionListener(Fenetre f,Parcelle p){
		this.fenetre=f;
		this.parcelle=p;
	}

	public void actionPerformed(ActionEvent e){
	    this.fenetre.removeContenu();
    	this.fenetre.removeBar();

    	this.fenetre.setBar(new BarParcelle(this.fenetre,this.parcelle));
    	this.fenetre.setContenu(new ActionPanel(this.parcelle,this.fenetre));
	}	
}