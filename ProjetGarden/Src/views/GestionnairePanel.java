package views;
import listeners.*;
import javax.swing.*;
import java.awt.*;
import my_api.*;

/** Vue du gestionnaire de parcelles */
public class GestionnairePanel extends JPanel{

	private JPanel conteneur;  //Conteneur du gestionnaire de parcelles (il est contenu dans l'objet GestionnairePanel)

  	public GestionnairePanel(){
  		super();
  		this.conteneur = new JPanel();

      //innitialisation de la vue
  		this.setLayout(new GridBagLayout());
  		this.setPreferredSize(new Dimension(100,200));

      //Innitialisation du conteneur
  		this.conteneur.setLayout(new GridLayout(25,1));
  		this.conteneur.setBackground(new Color(80,80,80));
      this.conteneur.setBounds(0,0,100,200);
  		
      //Innitialisation de la barre de scroll
  		JScrollPane sp = new JScrollPane(this.conteneur); //Ajout de la barre de scroll au conteneur
  		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
  		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      sp.setBorder(BorderFactory.createLineBorder(Color.black));
      
      //Ajout du conteneur à la vue
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.fill = GridBagConstraints.BOTH;
      gbc.weightx = 1;
      gbc.weighty = 1;
  		this.add(sp,gbc);
  	}

    /** Permet de modifier la disposition du gestionnaire de parcelles
    @param gl Nouveau GridLayout */
    public void setLayout(GridLayout gl){

      this.conteneur.setLayout(gl);
    }

    /** Vide le gestionnaire de parcelle de tous les objets qu'il contient */
    public void reset(){

      this.conteneur.removeAll();
    }

    /** Ajoute un GestionnaireBouton au gestionnaire de panel
    @param gb Nouveau GestionnaireBouton à ajouter */
    public void addBouton(GestionnaireBouton gb){

      this.conteneur.add(gb,BorderLayout.CENTER);
    }
}