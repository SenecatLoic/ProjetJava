package listeners;
import views.*;
import my_api.*;
import java.awt.event.*;

/**
  * Permet de retourner au menu de l'application
  */
public class ReturnMenuListener implements ActionListener{
		
	private Fenetre fenetre;

	public ReturnMenuListener(Fenetre f){
		this.fenetre=f;
	}

	public void actionPerformed(ActionEvent e){
		this.fenetre.removeContenu();
    	this.fenetre.removeBar();

    	this.fenetre.setBar(new MenuBar(this.fenetre));

    	//on récupère le jardin factory pour avoir tout les jardins
    	AbstractJardinFactory factory = this.fenetre.getControlleur().getJardinFactory();
    	
    	this.fenetre.setContenu(new Menu(factory.getAllJardin(),this.fenetre));
	}
}