package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import my_api.enums.*;


/**
  * La classe JardinPanel sert à représenter graphiquement un jardin
  * @see JardinListener
  */
public class JardinPanel extends JPanel{

	private Parcelle parcelle;

	/**
	  * La parcelle racine du jardin
	  */
	public JardinPanel(Parcelle p,Fenetre fenetre){
		super();
		int col=1;
		int ligne=1;
		this.parcelle=p;
		this.setBackground(new Color(184, 150, 95));

		if (p.getSplit()==Orientation.VERTICAL) {
			col=2;
			ligne=1;
		} else if(p.getSplit()==Orientation.HORIZONTAL){
			col=1;
			ligne=2;
		}

		GridLayout gd = new GridLayout(col,ligne);
		this.setLayout(gd);

		try{
			this.add(new JardinPanel(p.getFirst(),fenetre));
			this.add(new JardinPanel(p.getSecond(),fenetre));			
		}catch(IllegalStateException e){
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			this.addMouseListener(new JardinListener(fenetre,this.parcelle));
		}
	}
}