/**
 * La classe Fond premet de charger une image de fond
 */

import java.awt.*;
import javax.swing.*;

public class Fond extends JComponent{
  /**
    * Image de fond.
    */
  private Image fond;

  private JFrame fenetre;

  /**
    * Constructeur du fond de l'écran.
    * @param image
    */
  public Fond(String image,JFrame fenetre) {
    super();
    this.fenetre=fenetre;
    fond = Toolkit.getDefaultToolkit().getImage(image);
  }

  /**
    * Méthode qui dessine automatique sur le composant lorsqu'il est créer.
    */
  @Override
  public void paintComponent(Graphics pinceau) {
    Graphics secondPinceau = pinceau.create();
    if (this.isOpaque()) {
      secondPinceau.setColor(this.getBackground());
      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    secondPinceau.drawImage(fond,0,0,this.fenetre.getWidth(),this.fenetre.getHeight(),this.fenetre);
  }
}
