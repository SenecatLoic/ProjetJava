package my_api;
import java.time.LocalDate;
/**
 Une action est quelque chose qu'on fait à un moment sur une parcelle.
*/
public abstract class Action {
  /**
    * Date de l'action
    */
  protected LocalDate date;

  /**
    * Parcelle sur laquelle l'action se déroule
    */
  protected Parcelle parcelle;  	
  /**
	   Renvoie la date
	   @return date de l'action
	  */      
  public abstract LocalDate getDate();
	/**
	   Renvoie la parcelle
	   @return parcelle
	  */      
  public abstract Parcelle getParcelle();
}