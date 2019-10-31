package views;
import listeners.*;
import my_api.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
  * Fenetre de l'application
  */
public class Fenetre extends JFrame{

    private JComponent menuBar;

    private JComponent contenu;

    /**
      * Un controlleur qui va faire la passerelle entre la vue et le modèle
      * @see ControleFenetre
      */
    private ControleFenetre controle;

    /**
      * ScrollBar de la fenetre
      */
    private JScrollPane barre;

    /**
      * Constructeur 
      */
	public Fenetre(ControleFenetre controle){
		super();
		this.setSize(900,600);
	    this.setLocation(600, 200);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.menuBar = null;
        this.contenu = null;
        this.controle = controle;
        this.barre = null;
    }

    /**
      * enlève le panneau de la fenetre
      */
    public void removeContenu(){
        this.remove(this.barre);
    }

    /**
      * enlève la barre de naviguation de la fenetre
      */
    public void removeBar(){
        this.remove(this.menuBar);
    }

    /**
      * Place un composant au milieu de la fenetre
      * @param c
      */
    public void setContenu(JComponent c){
        this.barre = new JScrollPane(c);
        this.barre.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.barre.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 

        this.contenu=c;
        this.add(this.barre,BorderLayout.CENTER);
        this.revalidate();
    }

    /**
      * Place un composant en haut de la fenetre
      * @param b
      */
    public void setBar(JComponent b){
        this.menuBar=b;
        this.add(b,BorderLayout.NORTH);
        this.revalidate();
    }    

    /**
      * @return ControleFenetre
      */
    public ControleFenetre getControlleur(){
        return this.controle;
    }
}