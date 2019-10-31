package my_api;
import my_api.enums.*;
import java.sql.*;
import java.util.*;
import java.time.Month;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.*;

/**
  * Action que l'on peut réalisé sur le sol d'une parcelle
  *
  */
public class ActionSolBD extends ActionSol {
    private int id;
    
    /**
      * constructeur avec l'id dans la BD de l'action voulu
      * @param id de l'action dans la BD
      */
    public ActionSolBD(int id){
        this.id=id;
    }
    
    /**
      * On cache le constructeur par défault car l'objet ne fonctionnerai pas
      */
    private ActionSolBD(){}

    /**
      * Retourne le type d'action sur le sol qui a été réalisée
      * @return ActionSoltype
      */
    public ActionSolType getType() { 
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

        return ActionSolType.getType(type);
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