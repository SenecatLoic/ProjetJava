/**
 * La classe abstraite ControleDeroulement est hérité par tout les controleurs
 * du deroulement.
 */
import java.awt.event.*;

public abstract class ControleDeroulement implements ActionListener{
	/**
	  * Déroulement qui va subir des changements lorsqu'un evènement sera réaliser.
	  */
	protected Deroulement deroulement;

	public ControleDeroulement(Deroulement deroulement){
		this.deroulement=deroulement;
	}
	
	/**
	  * Méthode nécésaire lorsqu'un objet héritera de cette classe.
	  */
		@Override
	public void actionPerformed(ActionEvent evenement){}

}