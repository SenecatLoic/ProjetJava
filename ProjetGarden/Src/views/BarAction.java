package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;

/**
  * Barre de naviguation 
  */
public class BarAction extends JPanel{
	
	public BarAction(Fenetre frame,Parcelle parcelle){
		JLabel menu = new JLabel("Action");

		menu.setFont(new Font("Arial",Font.BOLD,25));

		JButton bouton = new JButton("Retour au Menu");
		bouton.setFont(new Font("Arial",Font.BOLD,22));
		bouton.addActionListener(new ReturnMenuListener(frame));

		JButton retour = new JButton("Retour");
		retour.setFont(new Font("Arial",Font.BOLD,22));
		retour.addActionListener(new ReturnActionListener(frame,parcelle));

		this.setLayout(new BorderLayout());
		this.add(menu,BorderLayout.CENTER);
		menu.setHorizontalAlignment(JLabel.CENTER);
		this.add(bouton,BorderLayout.EAST);	
		this.add(retour,BorderLayout.WEST);	
	}
}