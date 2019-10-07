/**
  * La classe re tour permet de retourner au menu lorsqu'un évènement ce passe
  */
import java.awt.event.*;

public class Retour implements ActionListener{

	private Graphisme graphisme;

	private Affichagegrille affichage;

	public Retour(Graphisme g){
		this.graphisme=g;
		this.affichage=null;
	}

	public Retour(Graphisme g,Affichagegrille af){
		this.graphisme=g;	
		this.affichage=af;
	}

	@Override
	public void actionPerformed(ActionEvent evenement){
		this.graphisme.nettoyageFenetre();
		
		if (this.affichage!=null) {
			this.graphisme.fenetre.remove(this.affichage.getAffichage());
		}

		Menu m = new Menu(this.graphisme.fenetre);
	}
}