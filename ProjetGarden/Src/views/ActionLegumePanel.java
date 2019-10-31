package views;
import listeners.*;
import my_api.*;
import my_api.enums.*;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
  * Permet d'afficher une action à l'écran
  */
public class ActionLegumePanel extends JPanel{

	private ActionLegume action;

	public ActionLegumePanel(Action a){
		super();
		this.action= (ActionLegume) a;
		Legume l=this.action.getLegume();
		String noml = l.getNom();
		FamilleLegume familleL = l.getFamille();

		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();		
		
		JLabel label = new JLabel();

		label.setFont(new Font("Arial",Font.BOLD,18));

		label.setText("Action "+a.getDate()+" : "+this.action.getType()+" "+noml);

		gbc.gridy=0;
		this.add(new JLabel(" "),gbc);
		gbc.gridy=1;
		this.add(label,gbc);
		gbc.gridy=2;		
		this.add(new JLabel(" "),gbc);
		this.setBackground(new Color(179,159,155));
	}
}