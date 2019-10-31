package my_api.enums;
/**
  * Les différent type d'action possible sur les légumes sont notés dans cette enum
  * Chaque action est lier à un entier pour que ce soit plus optimisé lorsqu'il y a usage
  * d'une Base de Données.
  */
public enum ActionLegumeType {
    ARRACHER(0), ECLAIRCIR(1), RECOLTER(2), SEMER(3), TRANSPLANTER(4);

    //valeur des enums pour les différencier
    private int id;

    private ActionLegumeType(int id){
    	this.id=id;
    }

    /**
      * @param ac ActionLegumeType
      * @return Sa valeur 
      */
    public static int getId(ActionLegumeType ac){
    	return ac.id;
    }

    /**
      * @param valeur D'un type d'action
      * @return ActionLegumeType
      * @throws IllegalStateException si la valeur n'est pas comprise dans l'enum
      */
	public static ActionLegumeType getType(int valeur){
 		
 		for(ActionLegumeType legume : ActionLegumeType.values()){
 			
 			if( ActionLegumeType.getId(legume) == valeur){
 				return legume;
 			}
 		}
 		throw new IllegalStateException();
	}    
}