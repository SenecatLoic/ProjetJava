package listeners;
import views.*;
import my_api.*;
import java.awt.event.*;
import javax.swing.*;

/**
  * Cette classe écoute le menu et redirige vers d'autres pages de l'application.
  */
public class MenuListener implements ActionListener{
	
	private Fenetre fenetre;

	private Parcelle racine;

	private String nomjardin;

	public MenuListener(Fenetre f,Parcelle racine,String nom){
		this.fenetre=f;
		this.racine=racine;
		this.nomjardin=nom;
	}

	public void actionPerformed(ActionEvent e){

		String tmp = e.getActionCommand();

		if (tmp.equals("Historique")) {
			fenetre.removeContenu();		
			fenetre.removeBar();
			
			fenetre.setBar(new JardinBar(this.nomjardin,this.fenetre));
			fenetre.setContenu(new JardinPanel(this.racine,this.fenetre));
		}else if (tmp.equals("Modifier")){
			fenetre.removeContenu();
			fenetre.removeBar();
			fenetre.setBar(new BarModification(this.fenetre));
			fenetre.setContenu(new PanelModification(this.nomjardin,this.fenetre));			

		} else{
			int choix = JOptionPane.showConfirmDialog(null, 
                "Voulez-vous vraiment supprimer ce jardin ?","Attention!", JOptionPane.YES_NO_OPTION);

			if (choix == JOptionPane.YES_OPTION) {
				//on supprime le jardin 
				this.fenetre.getControlleur().getJardinFactory().DeleteJardin(this.nomjardin);
				fenetre.removeContenu();
				fenetre.setContenu(new Menu(this.fenetre.getControlleur().getJardinFactory().getAllJardin(),this.fenetre));	
			}
		}
	}
}