package listeners;
import views.*;
import my_api.*;
import java.util.*;

/**
  * Cette classe à pour but de garder certaines informations sur l'API.
  *	Par exemple elle retient la factory de jardin qui est utilisée.
  */
public class ControleFenetre{

	private AbstractJardinFactory jardin;

	private AbstractLegumeFactory legume;

	private AbstractActionFactory action;
	
	public ControleFenetre(AbstractJardinFactory jardin,AbstractLegumeFactory legume,AbstractActionFactory action){
		this.jardin = jardin;
		this.legume = legume;
		this.action = action;
	}

	public AbstractJardinFactory getJardinFactory(){
		return this.jardin;
	}

	public AbstractLegumeFactory getLegumeFactory(){
		return this.legume;
	}

	public AbstractActionFactory getActionFactory(){
		return this.action;
	}
}