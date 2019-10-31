package my_api.enums;

/**
  * Les différentes familles de légume existante sont stocké dans cette enum
  * Chaque famille est lier à un entier pour que ce soit plus optimisé lorsqu'il y a usage
  * d'une Base de Données.
  */
public enum FamilleLegume {
    AUTRE(0), ALLIACEES(1), CHENOPODES(2), CUCURBITACEES(3), CRUCIFERES(4), LEGUMINEUSES(5), OMBELLIFERES(6), SOLANACEES(7);

    //valeur des enums pour les différencier
    private int id;

    private FamilleLegume(int id){
    	this.id=id;
    }
    
    /**
      * @param ac ActionLegumeType
      * @return Sa valeur 
      */
    public static int getId(FamilleLegume famille){
    	return famille.id;
    }

    /**
      * @param valeur D'une famille
      * @return FamilleLegume
      * @throws IllegalStateException si la valeur n'est pas comprise dans l'enum
      */
	public static FamilleLegume getFamille(int valeur){
 		
 		for(FamilleLegume famille : FamilleLegume.values()){
 			
 			if( FamilleLegume.getId(famille) == valeur){
 				return famille;
 			}
 		}
    //l'entier eest trop grand
 		throw new IllegalStateException();
	}
}
