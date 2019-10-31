package listeners;
import views.*;
import my_api.*;
import java.awt.event.*;

/**
  * En fonction du clic de l'utilisateur l'application va redirigé vers les actions
  * de la parcelle sélectionner.
  */
public class JardinListener implements MouseListener{

  private Parcelle parcelle;

  private Fenetre fenetre;

  public JardinListener(Fenetre frame,Parcelle p){
    this.fenetre=frame;
    this.parcelle=p;
  }

  public void mouseClicked(MouseEvent event) {
    this.fenetre.removeContenu();
    this.fenetre.removeBar();

    this.fenetre.setBar(new BarParcelle(this.fenetre,this.parcelle));
    this.fenetre.setContenu(new ActionPanel(this.parcelle,this.fenetre));

  }

  public void mouseEntered(MouseEvent event) {}

  public void mouseExited(MouseEvent event) {}

  public void mousePressed(MouseEvent event) {}

  public void mouseReleased(MouseEvent event) {}
        
}