/**
 * La classe ChoixDeroulement gère les évènement des boutons du déroulement.
 * 	Les objets de cette classe vont permettre de retenir le choix de l'utilisateur.
 */
import java.awt.event.*;

public class ChoixDeroulement extends ControleDeroulement{

	/**
	  * Constructeur. 
	  * @param deroulement
	  */
	public ChoixDeroulement(Deroulement deroulement){
		super(deroulement);
	}
	
	/**
	  * Cette méthode sera invoquer lorsqu'un évènement est réaliser.
	  * Elle fait appel à une méthode différente en fonction du bouton appuyer.
	  */
	@Override
	public void actionPerformed(ActionEvent evenement){
		String tmp = evenement.getActionCommand();

		if (tmp.equals("Déroulement automatique")) {
			this.deroulement.automatique();
		} else if(tmp.equals("Déroulement manuel")){ // Sinon Déroulement manuel
			this.deroulement.manuel();
		} else {
			Grille tmpg = this.deroulement.getRecherche().getGrille();
			this.deroulement.nettoyageFenetre2();
			this.deroulement=new Deroulement(this.deroulement.getFenetre(),tmpg);
		}
	}
} 