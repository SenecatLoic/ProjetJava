/**
 * La classe Choixmap gère les évènements lors du choix de la map
 * 	Cette classe va permettre de savoir quel grille a choisi l'utilisateur
 */
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Choixmap implements ActionListener{
	
	private JComboBox<String> liste;

	private Mapjeu mapj;

	public Choixmap(JComboBox<String> liste,Mapjeu map){
		this.liste=liste;
		this.mapj=map;
	}

		@Override
	public void actionPerformed(ActionEvent evenement){
		String tmp=(String)liste.getSelectedItem();

		File f1 = new File("labyrinthe/map/"+tmp);
		File f2 = new File("labyrinthe/map/"+tmp+"2");

		Grille g1 = new Grille(f1);
		Grille g2 = new Grille(f2);

		this.mapj.nettoyageFenetre();
		JeuDeroulement jd = new JeuDeroulement(this.mapj.fenetre,g1,g2);
	}
}