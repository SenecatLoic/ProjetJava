package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;

/**
  * Barre de naviguation lors de la création de jardin
  */
public class CreerBar extends JPanel{
	
	public CreerBar(){
		JLabel menu = new JLabel("Parcelle");

		menu.setFont(new Font("Arial",Font.BOLD,25));

		JButton bouton = new JButton("Retour au Menu");
		bouton.setFont(new Font("Arial",Font.BOLD,22));

		JButton retour = new JButton("Retour");
		retour.setFont(new Font("Arial",Font.BOLD,22));

		this.setLayout(new BorderLayout());
		this.add(menu,BorderLayout.CENTER);
		menu.setHorizontalAlignment(JLabel.CENTER);
		this.add(bouton,BorderLayout.EAST);	
		this.add(retour,BorderLayout.WEST);	
	}
}