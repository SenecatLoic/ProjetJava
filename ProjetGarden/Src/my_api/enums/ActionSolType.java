package my_api.enums;
/**
  * Les différent type d'action possible sur le sol sont notés dans cette enum
  * Chaque action est lier à un entier pour que ce soit plus optimisé lorsqu'il y a usage
  * d'une Base de Données.
  */
public enum ActionSolType {
    AMENDER(0), BECHER(1), BINER(2), BUTTER(3), FUMER(4), PAILLER(5);
   
   //valeur des enums pour les différencier
    private int id;

    private ActionSolType(int id){
    	this.id=id;
    }

    /**
      * @param ac ActionLegumeType
      * @return Sa valeur 
      */
    public static int getId(ActionSolType ac){
    	return ac.id;
    }

    /**
      * @param valeur D'un type d'action
      * @return ActionSolType
      * @throws IllegalStateException si la valeur n'est pas comprise dans l'enum
      */
	public static ActionSolType getType(int valeur){
 		
 		for(ActionSolType sol : ActionSolType.values()){
 			
 			if( ActionSolType.getId(sol) == valeur){
 				return sol;
 			}
 		}
 		throw new IllegalStateException();
	}    
}
