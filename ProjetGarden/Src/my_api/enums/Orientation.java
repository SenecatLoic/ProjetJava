package my_api.enums;

/**
  * Les différentes orientation de parcelle sont stocké dans cette enum.
  * Chaque Orientation est lier à un entier pour que ce soit plus optimisé lorsqu'il y a usage
  * d'une Base de Données.
  */
public enum Orientation {
    VERTICAL(0), HORIZONTAL(1);

    private int id;

    private Orientation(int id){
    	this.id=id;
    }

    public static int getId(Orientation ac){
    	return ac.id;
    }
}
