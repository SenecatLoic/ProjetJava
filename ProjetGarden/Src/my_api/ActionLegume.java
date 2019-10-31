package my_api;
import my_api.enums.*;
/**
  * Classe abstraite qui hérite de Action 
  @see Action
  * Cette classe a pour but de représenter les actions faites sur le sol.
  */
public abstract class ActionLegume extends Action{
	/**
	  * Renvoi le le légume concerné.
	  * @return Legume de l'action concerné
	  */
	public abstract Legume getLegume();
	/**
	  * Renvoi le type de l'action
	  * @return ActionLegumeType de l'action concerné
	  */
	public abstract ActionLegumeType getType();
}