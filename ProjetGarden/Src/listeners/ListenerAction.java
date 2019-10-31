package listeners;
import views.*;
import my_api.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
  * Ecoute le menu des actions et demande le choix de quels action créer
  *
  */
public class ListenerAction implements ActionListener{
	
	private Fenetre fenetre;

	private Parcelle parcelle;	

	public ListenerAction(Fenetre f,Parcelle p){
		this.fenetre=f;
		this.parcelle=p;
	}

	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("Ajouter")) {
			String dialogue = new String("Quel genre d'action vous voulez ajouter ? ");

			Object[] possibleValues = { "ActionSol", "ActionLegume"};

			Object selectedValue = JOptionPane.showInputDialog(null,dialogue,"",JOptionPane.INFORMATION_MESSAGE,null,
				possibleValues, possibleValues[0]);

			if (selectedValue != null) {
				this.fenetre.removeContenu();
				this.fenetre.removeBar();
				this.fenetre.setBar(new BarAction(this.fenetre,this.parcelle));	

				if (selectedValue.equals("ActionSol")) {
					this.fenetre.setContenu(new PanelCreerActionSol(this.parcelle,this.fenetre));
				} else {
					AbstractLegumeFactory factory = this.fenetre.getControlleur().getLegumeFactory();

					Iterator<Legume> legumes = factory.getLegumes();
					
					this.fenetre.setContenu(new PanelCreerActionLegume(this.parcelle,this.fenetre,legumes));
				}
			}
		} else{
			this.fenetre.removeContenu();
			this.fenetre.removeBar();	
			this.fenetre.setBar(new BarAction(this.fenetre,this.parcelle));	
			this.fenetre.setContenu(new PanelAjouterLegume(this.fenetre,this.parcelle));
		}
	}
}