package listeners;
import views.*;
import my_api.*;
import java.awt.event.*;
import java.time.*;
import java.awt.*;
import javax.swing.*;

/**
  * Cette classe montre un standard à appliqué lorsqu'on doit écouter
  * la création d'un formulaire
  */
public abstract class FormulaireListener implements ActionListener,ItemListener{

	protected JTextField champs;

	protected Parcelle parcelle;

	protected Fenetre fenetre;

	public void actionPerformed(ActionEvent e){}

	public LocalDate parseDate(String date) throws java.lang.NumberFormatException,java.lang.StringIndexOutOfBoundsException{
		char jour[] = new char[2];
		date.getChars(0,2,jour,0);

		char mois[] = new char[2];
		date.getChars(3,5,mois,0);


		char annee[] = new char[4];
		date.getChars(6,10,annee,0);

		String j = new String(jour);
		String m = new String(mois);
		String a = new String(annee);

		return LocalDate.of(Integer.parseInt(a),Integer.parseInt(m),Integer.parseInt(j));
	}

	public void itemStateChanged(ItemEvent e){}
}