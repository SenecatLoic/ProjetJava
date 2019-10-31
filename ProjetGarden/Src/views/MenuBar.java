package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;

/**
  * La barre de navigation du menu
  * @see ListenerMenuBar
  */
public class MenuBar extends JPanel{
	
	public MenuBar(Fenetre f){
		JLabel menu = new JLabel("Menu");

		menu.setFont(new Font("Arial",Font.BOLD,25));

		JButton bouton = new JButton("Créer Jardin");

		bouton.addActionListener(new ListenerMenuBar(f));

		bouton.setFont(new Font("Arial",Font.BOLD,22));

		this.setLayout(new BorderLayout());
		this.add(menu,BorderLayout.CENTER);
		menu.setHorizontalAlignment(JLabel.CENTER);
		this.add(bouton,BorderLayout.WEST);	
	}
}