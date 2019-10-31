package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;

/**
  * La barre de naviguation lors de l'affichage du jardin 
  */
public class JardinBar extends JPanel{
	
	public JardinBar(String nomJardin,Fenetre frame){
		JLabel menu = new JLabel(nomJardin);

		menu.setFont(new Font("Arial",Font.BOLD,25));
		
		JLabel message = new JLabel("Cliquez sur une parcelle pour voir l'historique");
		message.setFont(new Font("Arial",Font.BOLD,22));

		JButton bouton = new JButton("Retour au Menu");
		bouton.addActionListener(new ReturnMenuListener(frame));


		bouton.setFont(new Font("Arial",Font.BOLD,22));

		this.setLayout(new BorderLayout());
		this.add(menu,BorderLayout.CENTER);
		menu.setHorizontalAlignment(JLabel.CENTER);
		this.add(bouton,BorderLayout.WEST);	
		this.add(message,BorderLayout.SOUTH);		
	}
}