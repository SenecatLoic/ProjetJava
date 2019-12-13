package my_api;
import java.sql.*;
import java.time.LocalDate;
/**
  * Cette classe à pour but de gérer la connexion avec la BD
  *
  */
public class MyConnection {

	String url  = "url";
    String user = "user";
    String passwd  = "passwd"; // pas sûr que ce soit mon mot de passe...

	/**
	  * Mécanisme du singleton car on veut qu'une seule connection à la BD
	  */
	private static MyConnection single_instance = new MyConnection();

	private Connection connexion;

  /**
    instancie la connexion avec la base de données
    
    */
	private MyConnection(){
		try{
			this.connexion = DriverManager.getConnection(url, user, passwd);
		}catch(SQLException e){
			System.out.println("probleme connexion");
		}
	}

	public static MyConnection getMyConnection(){ 
        return single_instance; 
    }

    /**
      * Renvoi la connexion, il faudra la fermer à chaque utilisation
      * pour plus de sécurité
      */
    public Connection getConnectionBD() throws SQLException{
    	//indique si il faut recréer une connexion à cause d'une deconnection
    	boolean recreate=false;

    	if (connexion == null) {
    		recreate=true;
    	} else if (connexion.isClosed()) {
            recreate=true;
        }       

    	//on essaye de se connecter à nouveau
    	if (recreate) {
    		connexion = DriverManager.getConnection(url, user, passwd); 		
    	}
    	return connexion;
    }
}
