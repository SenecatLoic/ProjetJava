package my_api;
import my_api.enums.*;
import java.util.*;
import java.time.Month;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.awt.*;
import javax.swing.*;

/**
  * Cette classe permet de créer des legumeBD, et d'ajouter des légumes
  * dans la BD.
  */
public class LegumeFactoryBD implements AbstractLegumeFactory {

    // static variable single_instance of type Singleton 
    private static LegumeFactoryBD single_instance = null; 
    
    private LegumeFactoryBD(){
        // rien à faire, permet juste de rendre privé le constructeur par défaut.
    } 
  
    /**
      * static methode pour créer une unique instance
      */
    public static LegumeFactoryBD getLegumeFactoryBD(){ 
        if (single_instance == null) 
            single_instance = new LegumeFactoryBD(); 
        return single_instance; 
    }

    /**
      * Cette classe rajoute un légume à la BD.
      * @param nomLegume,famille,semis,recolte
      *@return Legume
      */
    public Legume AddLegume(String nomLegume, FamilleLegume famille, int semis, int recolte){

        if (semis < 0 || semis > 0b111111111111){
            throw new IllegalStateException("Erreur : semis doit être la valeur entière dont l'écriture binaire est sur 12 bits, un bit par mois de janvier à décembre de droite à gauche");
        }
        if (recolte < 0 || recolte > 0b111111111111){
            throw new IllegalStateException("Erreur : recolte doit être la valeur entière dont l'écriture binaire est sur 12 bits, un bit par mois de janvier à décembre de droite à gauche");
        }

        Connection con = null;
        Statement stmt = null;
        PreparedStatement requete = null;
        int id=0;
        String sql = "INSERT INTO Legumes(nomLegume, familleLegume, semis, Recolte) " +
            "VALUES (\""+
            nomLegume + "\", " +
            famille.ordinal() + ", " +
            "b\'" + String.format("%12s", Integer.toBinaryString(semis)).replace(" ", "0") + "\', " +
            "b\'" + String.format("%12s", Integer.toBinaryString(recolte)).replace(" ", "0") +
            "');";
        String req2 = "select idLegume from Legumes where nomLegume=?";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            try {
                con = MyConnection.getMyConnection().getConnectionBD();
                
                if(!con.isClosed()) {

                    try {
                        stmt = con.createStatement();

                        try{
                            stmt.executeUpdate(sql);
                            requete = con.prepareStatement(req2);
                            requete.setString(1,nomLegume);

                            ResultSet rs = requete.executeQuery();
                            rs.next();
                            id = rs.getInt(1);
                        } catch(SQLException e) {
                        } finally {
                            try {
                                requete.close();
                                stmt.close();
                            } catch(SQLException e) {
                            }
                            stmt = null;
                        }
                    } catch(SQLException e) {
                    }
                } else {  
                }
                try {
                    con.close();   
                } catch(SQLException e) { 
                }
            } catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "Problème de connexion",
                 "Impossible de se connecter, vérifiez votre connexion internet ou contactez le support.",
                  JOptionPane.ERROR_MESSAGE);   
            }
        }
        catch(ClassNotFoundException e) {
            System.out.println("classpath");
        }

        // ici retourner un LegumeJ
        LegumeBD l = new LegumeBD(id);
        return l;
    }
    
    // supprime si existant le légume.
    // remarque : legume ne connaît pas parcelle, actionlegumes etc.
    // Il faudra faire attention si vous enlevez un légume
    // Si votre base modélise les autres concepts en lien avec légumes et dispose de clés étrangères adaptées alors un on delete cascade devrait marcher.
    // 
    public void DeleteLegume(String nomLegume){
        throw new UnsupportedOperationException("Désolé, fonction non supporté.");
    }
    
    /**
      * Renvoi tout les légumes connus
      * @return iterator de legume
      */
    public Iterator<Legume> getLegumes(){
        Connection con = null;
        PreparedStatement requete = null;
        //pour généré un itérateur de legumes
        ArrayList<Legume> legumes = new ArrayList<Legume>();
        int id;

        //requête mise en forme de string
        String stringReq = "select idLegume from Legumes";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);

                ResultSet rs = requete.executeQuery();

                while(rs.next()){
                    id = rs.getInt(1);

                    legumes.add(new LegumeBD(id));
                }

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Problème de connexion",
                 "Impossible de se connecter, vérifiez votre connexion internet ou contactez le support.",
                  JOptionPane.ERROR_MESSAGE);
            } 

        }catch(ClassNotFoundException e){
            //mauvais classpath
        }finally {
                try {
                    if (requete!=null) {
                       requete.close(); 
                    }

                    if (con!=null) {
                        con.close();  
                    }   
            } catch(SQLException e) {}
        } 
        return legumes.iterator();       
    }

    // cherche légumes connus par famille.
    public Iterator<Legume> getLegumes(FamilleLegume famille){
        throw new UnsupportedOperationException("Désolé, fonction non supporté.");
    }

    // cherche légumes connus.
    // retourne un légume connu si la chaîne de caractère mot apparaît dans le nom de ce dernier.
    public Iterator<Legume> getLegumes(String mot){
        throw new UnsupportedOperationException("Désolé, fonction non supporté.");
    }

    /**
      * Retourne le legume correcpondant au nom
      * @param nomLegume
      * @return Legume
      */
    public Legume getLegume(String nomLegume){
        Connection con = null;
        PreparedStatement requete = null;
        int id = 0 ;

        //requête mise en forme de string
        String stringReq = "select idLegume from Legumes where nomLegume=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setString(1,nomLegume);

                ResultSet rs = requete.executeQuery();
                rs.next();
                id = rs.getInt(1);

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Problème de connexion",
                 "Impossible de se connecter, vérifiez votre connexion internet ou contactez le support.",
                  JOptionPane.ERROR_MESSAGE);
            } 

        }catch(ClassNotFoundException e){
            //mauvais classpath
        }finally {
                try {
                    if (requete!=null) {
                       requete.close(); 
                    }

                    if (con!=null) {
                        con.close();  
                    }   
            } catch(SQLException e) {}
        } 
        return new LegumeBD(id);            
    }
}