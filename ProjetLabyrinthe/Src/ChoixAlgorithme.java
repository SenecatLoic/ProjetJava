/**
 * La classe ChoixAlgorithme permet de gérer les evènements des bouttons 
 * du choix de l'algorithme.
 */
import java.awt.event.*;

public class ChoixAlgorithme extends ControleDeroulement{

	/**
	  * Constructeur. 
	  * @param deroulement
	  */
	public ChoixAlgorithme(Deroulement deroulement){
		super(deroulement);
	}

	/**
	  * Cette méthode sera invoquer lorsqu'un évènement est réaliser. 
	  */
	@Override
	public void actionPerformed(ActionEvent evenement){
		String tmp = evenement.getActionCommand();

		if (tmp.equals("Algorithme Aléatoire")) {
			this.deroulement.setChoix(false);	
			this.deroulement.choixDeroulement();		
		} else if(tmp.equals("Algorithme Déterministe")){
			this.deroulement.setChoix(true);
			this.deroulement.choixDeroulement();
  		} else {
  			this.deroulement.nettoyageFenetre2();
  			Menu menu = new Menu(this.deroulement.getFenetre());
  			menu.choixTypeLabyrinthe();
  		}
  		
	}

}