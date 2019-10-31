package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;

/**
  * Barre de naviguation sur toute les actions des parcelles
  */
public class BarModification extends JPanel{
	
	public BarModification(Fenetre frame){
		JLabel menu = new JLabel("Parcelle");

		menu.setFont(new Font("Arial",Font.BOLD,25));

		JButton bouton = new JButton("Retour au Menu");
		bouton.setFont(new Font("Arial",Font.BOLD,22));
		bouton.addActionListener(new ReturnMenuListener(frame));

		this.setLayout(new BorderLayout());
		this.add(menu,BorderLayout.CENTER);
		menu.setHorizontalAlignment(JLabel.CENTER);
		this.add(bouton,BorderLayout.WEST);	
	}
}