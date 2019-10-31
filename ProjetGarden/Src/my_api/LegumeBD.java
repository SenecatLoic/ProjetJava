package my_api;
import my_api.enums.*;
import java.util.*;
import java.time.Month;
import java.time.format.TextStyle;
import java.sql.*;
import java.awt.*;
import javax.swing.*;

/**
 * Cette classe permet la persistance des Legumes
 */
public class LegumeBD implements Legume {

    /**
      * id de la base de données
      */
    private int id;

    /**
      * @param id du legume dans la BD
      */ 
    public LegumeBD(int id){
        this.id=id;
    }

    /**
      * On cache le constructeur par défault car l'objet ne fonctionnerai pas
      */
    private LegumeBD(){}
    
    /** 
     * autorise le semis pour le mois donné en argument
     * @param m un mois (voir Month.java)
     * @return void
     */
    public void semisOK(Month m){
        Connection con = null;
        PreparedStatement requete = null;
        int semis;
        //variable pour le décalage
        int tmp = 0b100000000000;

        //requête mise en forme de string
        String stringReq = "select semis from Legumes where idLegume=?";
        String stringReq2 = "update Legumes set semis=? where idLegume=? ";

        try{
            Class.forName("org.mariadb.jdbc.Driver");    
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();
                rs.next();
                semis = rs.getInt(1);
                try{
                    //on ferme tout de suite
                    rs.close();                    
                }catch(SQLException e){}


                for (int i=1; i < m.getValue();i++ ) {
                // décalage d'un bit vers la gauche
                    tmp = tmp >> 1;
                }
        
                semis = semis + tmp;

                requete = con.prepareStatement(stringReq2);
                requete.setInt(1,semis);
                requete.setInt(2,this.id);
                requete.executeUpdate();

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
    }

    /** 
     * autorise le semis pour le mois donné en argument
     * @param m un mois (voir Month.java)
     * @return void
     */
    public void recolteOK(Month m){
        Connection con = null;
        PreparedStatement requete = null;
        int recolte;
        //variable pour le décalage
        int tmp = 0b100000000000;

        //requête mise en forme de string
        String stringReq = "select recolte from Legumes where idLegume=?";
        String stringReq2 = "update Legumes set recolte=? where idLegume=? ";

        try{
            Class.forName("org.mariadb.jdbc.Driver");    
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();
                rs.next();
                recolte = rs.getInt(1);
                
                try{
                    //on ferme tout de suite
                    rs.close();                    
                }catch(SQLException e){}

                for (int i=1; i < m.getValue();i++ ) {
                // décalage d'un bit vers la gauche
                    tmp = tmp >> 1;
                }
        
                recolte = recolte + tmp;

                requete = con.prepareStatement(stringReq2);
                requete.setInt(1,recolte);
                requete.setInt(2,this.id);
                requete.executeUpdate();

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
    }

    /** 
     * retourne si c'est possible de semer ce mois là
     * @param m un mois (voir Month.java)
     * @return Boolean 
     */
    public Boolean getSemis(Month m){
        Connection con = null;
        PreparedStatement requete = null;
        int semis;

        boolean result=false;
        //variable pour le décalage
        int tmp = 0b100000000000;

        //requête mise en forme de string
        String stringReq = "select semis from Legumes where idLegume=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();
                rs.next();                
                semis = rs.getInt(1);
                
                try{
                    //on ferme tout de suite
                    rs.close();                    
                }catch(SQLException e){}

                for (int i=1; i < m.getValue();i++ ) {
                // décalage d'un bit vers la gauche
                    tmp = tmp >> 1;
                }

                //et logique 
                semis = tmp & semis;
                //si semis est null alors tmp qui est le mois souahaité n'est pas représenter dans semis
                if (semis==0) {

                    result=false;
                }else{
                    result=true;
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
        return result;       
    }

    /** 
     * retourne si c'est possible de récolter ce mois là
     * @param m un mois (voir Month.java)
     * @return Boolean 
     */
    public Boolean getRecolte(Month m){
        Connection con = null;
        PreparedStatement requete = null;
        int recolte;

        boolean result=false;
        //variable pour le décalage
        int tmp = 0b100000000000;

        //requête mise en forme de string
        String stringReq = "select recolte from Legumes where idLegume=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);

                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();
                rs.next();                
                recolte = rs.getInt(1);

                try{
                    //on ferme tout de suite
                    rs.close();                    
                }catch(SQLException e){}

                for (int i=1; i < m.getValue() ; i++ ) {
                    // décalage d'un bit vers la gauche
                    tmp = tmp >> 1;
                }
                
                //et logique 
                recolte = tmp & recolte;
                //si recolte est null alors tmp qui est le mois souahaité n'est pas représenter dans recolte
                if (recolte==0) {
                    System.out.println(recolte);
                    result=false;
                }else{
                    result=true;
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
        return result;         
    }
    /** 
      * Retourne le nom du légume
      * @return String nom du légume
      */
    public String getNom(){ 
        Connection con = null;
        PreparedStatement requete = null;
        String nom=null;

        //requête mise en forme de string
        String stringReq = "select nomLegume from Legumes where idLegume=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);

                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();
                rs.next();

                nom = rs.getString(1);

                try{
                    //on ferme tout de suite
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
        return nom;   
    }
    /** 
     * Retourne la famille du legume
     * @return FamilleLegume
     */
    public FamilleLegume getFamille(){ 
        Connection con = null;
        PreparedStatement requete = null;
        FamilleLegume famille=null;

        //requête mise en forme de string
        String stringReq = "select familleLegume from Legumes where idLegume=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);

                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();
                rs.next();
                int tmp = rs.getInt(1);
                
                try{
                    //on ferme tout de suite
                    rs.close();                    
                }catch(SQLException e){}

                //on cherche l'enum qui vaut tmp
                famille = FamilleLegume.getFamille(tmp);
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
        return famille;   
    }
    
    public String toString(){
        return ""+ getNom() +", " + getFamille();
    }

    /**
      * Retourne l'id du legume dans la BD
      * @return int idLegume
      */
    public int getId(){
        return this.id;
    }
}