package listeners;
import views.*;
import my_api.*;
import java.awt.event.*;
import javax.swing.*;

/**
  * Permet de retourner à la page des parcelles.
  */
public class ReturnParcelleListener implements ActionListener{
	
	private Fenetre fenetre;

	private Parcelle parcelle;

	public ReturnParcelleListener(Fenetre f,Parcelle p){
		this.fenetre=f;
		this.parcelle=p;
	}

	public void actionPerformed(ActionEvent e){
	    this.fenetre.removeContenu();
    	this.fenetre.removeBar();

    	this.fenetre.setBar(new JardinBar(this.parcelle.getNomJardin(),this.fenetre));
    	this.fenetre.setContenu(new JardinPanel(this.parcelle.getRoot(),this.fenetre));
	}
}