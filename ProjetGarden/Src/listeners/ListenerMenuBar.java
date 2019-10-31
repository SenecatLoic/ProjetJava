package listeners;
import views.*;
import java.awt.event.*;

/**
  * Ecoute la bar de navigation du Menu
  */
public class ListenerMenuBar implements ActionListener{
	
	private Fenetre fenetre;

	public ListenerMenuBar(Fenetre f){
		this.fenetre=f;
	}

	public void actionPerformed(ActionEvent e){
		fenetre.removeContenu();	
		fenetre.removeBar();
		fenetre.setBar(new JardinBar("Créer Jardin",this.fenetre));		
		fenetre.setContenu(new JardinCreer(this.fenetre));		
	}
}