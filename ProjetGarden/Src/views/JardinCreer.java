package views;
import listeners.*;
import my_api.*;
import java.awt.*;
import javax.swing.*;

/**
  * Affichage du Formulaire de création de jardin
  * @see JardinCreerListener
  */
public class JardinCreer extends JPanel{
	public JardinCreer(Fenetre f){
		Color couleur = new Color( 207, 202, 194 );
		Font font = new Font("Arial",Font.BOLD,22);

		JLabel jardin= new JLabel("Nom :");
		jardin.setFont(font);
		jardin.setOpaque(true);
		jardin.setBackground(couleur);		

		JLabel dimx = new JLabel("Dimension X :");
		dimx.setFont(font);
		dimx.setOpaque(true);
		dimx.setBackground(couleur);

		JLabel dimy = new JLabel("Dimension Y : ");
		dimy.setFont(font);
		dimy.setOpaque(true);
		dimy.setBackground(couleur);

		JButton valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(300,50));
		valider.setFont(font);

		JTextField[] champs = new JTextField[3];

		for (int i= 0; i <3 ;i++ ) {
			champs[i]= new JTextField(25);
			champs[i].setFont(font);
		}

		//listener
		valider.addActionListener(new JardinCreerListener(f,champs));

		//positionnement des composants
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.NONE;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.CENTER;

		this.add(jardin,gbc);
		gbc.weightx=1;
		gbc.weighty=1;	
		gbc.gridx=1;
		gbc.gridwidth=2;
		this.add(champs[0],gbc);
		gbc.gridy=1;
		this.add(dimx,gbc);
		gbc.gridy=2;
		this.add(champs[1],gbc);
		gbc.gridy=3;
		this.add(dimy,gbc);
		gbc.gridy=4;
		this.add(champs[2],gbc);
		gbc.gridy=5;
		this.add(valider,gbc);

		this.setBackground(couleur);		
	}
}