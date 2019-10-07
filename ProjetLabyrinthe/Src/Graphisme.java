/**
 * La classe Graphisme sevira aux classes qui s'occupent de la partie graphique du programme.
 * Ces classes pourront hérité de celle-ci.
 */

import java.awt.*;
import javax.swing.*;

public class Graphisme{
	/**
	  * Fenetre lié à cette objet.
	  */
	protected JFrame fenetre;
	/**
	  * Panneau lié à cette objet.
	  */
	protected JPanel panneau;

	protected JLabel fond;

	protected JTextArea text;

	/**
	  * Constructeur pour initialiser l' attributs de l'objet. 
	  * @param fenetre
	  */
	public Graphisme(JFrame fenetre){
		this.fenetre=fenetre;
	}

	/**
	  * Retourne la valeur de la fenetre.
  	  * @return La fenetre associé à l'objet.
      */
	public JFrame getFenetre(){
		return this.fenetre;
	}

	/**
	  * Retourne la valeur du panneau.
	  * @return Le panneau de l'objet.
	  */
	public JPanel getPanneau(){
		return this.panneau;
	}

	/**
      * Permet de nettoyer la fenetre de certain de ses composants.
      */
	public void nettoyageFenetre(){
		this.panneau.removeAll();
		this.fenetre.remove(this.panneau);
		this.fenetre.remove(this.fond);
	}

	/**
      * Permet de nettoyer la fenetre de certain de ses composants.
      */
	public void nettoyageFenetre2(){
		this.panneau.removeAll();
		this.fenetre.remove(this.panneau);
		this.fenetre.remove(this.text);
	}	

	/**
	  * Change le panneau.
	  * @param p 
	  */
	public void setPanneau(JPanel p){
		this.panneau=p;
	}


	public void setFond(JLabel lb){
		this.fond=lb;
	}

	public void setArea(JTextArea t){
		this.text=t;
	}

	/**
	  * change la fenetre
	  * @param fenetre
	  */
	public void setFenetre(JFrame fenetre){
		this.fenetre=fenetre;
	}
}