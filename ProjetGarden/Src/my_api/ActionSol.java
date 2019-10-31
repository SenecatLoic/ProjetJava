package my_api;
import my_api.enums.*;
/**
  * Classe abstraite qui hérite de Action 
  @see Action
  * Cette classe a pour but de représenter les actions faites sur le sol.
  */
public abstract class ActionSol extends Action{
	/**
	  * Renvoi le type de l'action
	  * @return ActionSolType de l'action concerné
	  */
	public abstract ActionSolType getType();
}