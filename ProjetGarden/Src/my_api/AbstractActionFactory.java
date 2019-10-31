package my_api;
import java.time.LocalDate;
import my_api.enums.*;

/**
  * Interface de la factory d'action
  *
  */
public interface AbstractActionFactory{

	/**
	  * Ajoute une action sol
	  */
	public Action addAction(LocalDate date, ActionSolType type);

	/**
	  * Ajoute une action Legume
	  */
	public Action addAction(LocalDate date,Legume legume, ActionLegumeType type);
}
