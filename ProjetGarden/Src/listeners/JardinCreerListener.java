package listeners;
import views.*;
import java.awt.event.*;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import my_api.*;

/**
  * Cette classe va contrôler le formulaire de création de jardin
  *
  */
public class JardinCreerListener implements ActionListener{
	
	private Fenetre fenetre;

	private JTextField[] champs;

	public JardinCreerListener(Fenetre f,JTextField[] champs){
		this.fenetre=f;
		this.champs=champs;
	}

	public void actionPerformed(ActionEvent e){
		String nom;
		int dimx;
		int dimy;

		AbstractJardinFactory factory = this.fenetre.getControlleur().getJardinFactory();

		try{
			nom = champs[0].getText();

			dimx = Integer.parseInt(champs[1].getText());

			dimy = Integer.parseInt(champs[2].getText());

			factory.AddJardin(nom,dimx,dimy);

			fenetre.removeContenu();	
			fenetre.removeBar();
			fenetre.setBar(new MenuBar(this.fenetre));
			fenetre.setContenu(new Menu(factory.getAllJardin(),this.fenetre));	

		}catch(java.lang.NumberFormatException except){
			JOptionPane.showMessageDialog(null, "Veuillez bien remplir les dimensions avec un nombre.",
				"Erreur", JOptionPane.ERROR_MESSAGE);
		} catch(java.lang.IllegalArgumentException except){
			JOptionPane.showMessageDialog(null, "Il manque le nom du jardin. ",
				"Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}