/**
 * La classe ChoixDifficulte permet de gérer les évènements lors du choix
 * de la difficulté.
 */

import java.awt.event.*;

public class ChoixDifficulte implements ActionListener{

  private JeuDeroulement deroulement;
  /**
    *Constructeur
    * @param menu
    */
	public ChoixDifficulte(JeuDeroulement d){
    	this.deroulement=d;
	}

  /**
    * Cette méthode réagit lorsqu'il y a un évènement
    */	
	@Override
	public void actionPerformed(ActionEvent evenement){
		String tmp = evenement.getActionCommand();

		if (tmp.equals("Facile")) {
			this.deroulement.setDifficult(0);
		} else if(tmp.equals("Moyen")){
			this.deroulement.setDifficult(1);
		} else{
			this.deroulement.setDifficult(2);
		}
		this.deroulement.nettoyageFenetre();
		this.deroulement.startGame();
    }
  }