/**
 * La classe Main va lancer le programme et afficher la fenetre modifier par
 * @see menu(JFrame)
 */

import java.awt.*;
import javax.swing.*;

public class Main{
	public static void main(String[] args) {
 	   JFrame fenetre = new JFrame("Labyrinthe");

	   Menu menu = new Menu(fenetre);
	   fenetre.setVisible(true);
	}
}
