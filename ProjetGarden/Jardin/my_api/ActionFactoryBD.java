import java.util.*;
import java.time.Month;
import java.time.LocalDate;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import my_api.enums.*;
import java.awt.*;
import javax.swing.*;

/**
  * Factory qui va uniquement crée des objets action
  *
  */
public class ActionFactoryBD implements AbstractActionFactory{

	/**
	  * Une seule instance de action factory
	  */
    private static ActionFactoryBD single_instance = null; 	

    private ActionFactoryBD(){
        // rien à faire, permet juste de rendre privé le constructeur par défaut.
    } 

    /**
      * Retourne la factory et si elle n'existe pas création de la factory
      * @return ActionFactoryBD, la factory
      */
    public static ActionFactoryBD getActionFactoryBD(){ 
        if (single_instance == null) 
            single_instance = new ActionFactoryBD(); 
        return single_instance; 
    }    

    /**
      * Ajoute à la base de données une action sur le sol.
      * @param date,type
      * Cette méthode peut échouer en cas de mauvaise connexion
      * @return Action qui vient d'être créer
      */
	public Action addAction(LocalDate date, ActionSolType type){
		Connection con = null;
        PreparedStatement requete = null;
        int id=0;

        //requête mise en forme de string
        String stringReq = "insert into Action(date,typeAction) values (?,?)";
        String stringReq2 = "select idAction from Action where date=? AND typeAction=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                //on envoie les données 
                requete = con.prepareStatement(stringReq);
                requete.setDate(1,java.sql.Date.valueOf(date));
                requete.setInt(2,ActionSolType.getId(type));

                requete.executeUpdate();

                //on récupère id sur la base de donnée
                requete = con.prepareStatement(stringReq2);
                requete.setDate(1,java.sql.Date.valueOf(date));
                requete.setInt(2,ActionSolType.getId(type));

                ResultSet rs = requete.executeQuery();
                rs.next();
                id = rs.getInt(1);

                try{
                    rs.close();
                }catch(SQLException e){
                    //on peut pas faire grand chose  
                }

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Problème de connexion",
                 "Impossible de se connecter, vérifiez votre connexion internet ou contactez le support.",
                  JOptionPane.ERROR_MESSAGE);
            } 

        }catch(ClassNotFoundException e){
            System.out.println("mauvais classpath");
        }finally {
                try {
                    if (requete!= null) {
                       requete.close(); 
                    }

                    if (con!=null) {
                       con.close();  
                    }
            } catch(SQLException e) {}
        }    
        
        return new ActionSolBD(id);		
	}

    /**
      * Ajoute à la base de données une action en rapport avec les légumes.
      * @param date,idLegume,type
      * Cette méthode peut échouer en cas de mauvaise connexion
      * @return Action qui vient d'être créer
      */
	public Action addAction(LocalDate date, Legume idLegume, ActionLegumeType type){
		Connection con = null;
        PreparedStatement requete = null;
        int id=0;
        LegumeBD legume=null;

        //si ce n'est pas un legumeBD cela ne peut fonctionner 
        if (idLegume instanceof LegumeBD) {
        	legume =(LegumeBD) idLegume;
        } else{
        	return null;
        }
        

        //requête mise en forme de string
        String stringReq = "insert into Action(date,typeAction,idLegume) values (?,?,?)";
        String stringReq2 = "select idAction from Action where date=? AND typeAction=? AND idLegume=?";

        //ce try est utile unqiuement dans la phase de développement
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setDate(1,java.sql.Date.valueOf(date));
                requete.setInt(2,ActionLegumeType.getId(type));
                requete.setInt(3,legume.getId());

                requete.executeUpdate();

                // on sélecetionne l'id de l'action qu'on vient de créer
                requete = con.prepareStatement(stringReq2);

                requete.setDate(1,java.sql.Date.valueOf(date));
                requete.setInt(2,ActionLegumeType.getId(type));
                requete.setInt(3,legume.getId());

                ResultSet rs = requete.executeQuery();
                rs.next();
                id = rs.getInt(1);
                try{
                    rs.close();
                }catch(SQLException e){
                    //on peut pas faire grand chose  
                } 

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Problème de connexion",
                 "Impossible de se connecter, vérifiez votre connexion internet ou contactez le support.",
                  JOptionPane.ERROR_MESSAGE);
            } 

        }catch(ClassNotFoundException e){
            System.out.println("mauvais classpath");
        }finally {
                try {
                    if (requete!= null) {
                       requete.close(); 
                    }

                    if (con!=null) {
                       con.close();  
                    }
            } catch(SQLException e) {
            //on peut pas faire grand chose 
            }       
        return new ActionLegumeBD(id);	
	   }
    }
}