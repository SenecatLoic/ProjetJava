package my_api;
import my_api.enums.*;
import java.util.*;
import java.time.Month;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.awt.*;
import javax.swing.*;

/**
  * Les actionLegumes sont toutes les actions que l'on peut
  * réalisé avec un legume
  */
public class ActionLegumeBD extends ActionLegume {
    
    private int id;

    /**
      * constructeur avec l'id dans la BD de l'action voulu
      * @param id de l'action dans la BD
      */
    public ActionLegumeBD(int id){
        this.id=id;
    }
    
    private ActionLegumeBD(){
        //On cache le constructeur par défault car l'objet ne fonctionnerai pas
    }

    /**
      * Retourne le Legume à qui on réalise l'action
      * @return Legume de l'action
      */
    public Legume getLegume(){ 
        Connection con = null;
        PreparedStatement requete = null;
        int idLegume=0;

        //requête mise en forme de string
        String stringReq = "select idLegume from Action where idAction=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");    
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();
                rs.next();
                idLegume = rs.getInt(1);

                try{
                    rs.close();
                }catch(SQLException e){}

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

        return new LegumeBD(idLegume);        
    }
    /**
      * Retourne le type de l'action à qui on réalise l'action
      * @return ActionLegumeType de l'action
      */
    public ActionLegumeType getType(){ 
        Connection con = null;
        PreparedStatement requete = null;
        int type=0;

        //requête mise en forme de string
        String stringReq = "select typeAction from Action where idAction=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");    
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();
                rs.next();
                type = rs.getInt(1);
                try{
                    rs.close();
                }catch(SQLException e){}

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

        return ActionLegumeType.getType(type);  
    }

    /**
      * Retourne la date de l'action
      * @return la date de l'action
      */
    public LocalDate getDate() {
        Connection con = null;
        PreparedStatement requete = null;
        LocalDate localdate = null;

        //requête mise en forme de string
        String stringReq = "select date from Action where idAction=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");    
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();
                rs.next();
                localdate = rs.getDate(1).toLocalDate();

                try{
                    rs.close();
                }catch(SQLException e){}

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
        return localdate;
    }
    
    public Parcelle getParcelle() {
        throw new UnsupportedOperationException("Désolé, fonction non supporté.");        
    }

    public int getId(){
        return this.id;
    }
}