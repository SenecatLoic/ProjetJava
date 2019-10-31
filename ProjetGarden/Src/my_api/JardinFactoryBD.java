package my_api;
import java.util.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;

    /**
     * Factory concrète qui va crééer des objets ParcelleJ, ceci est la version sans BdD pour faire des tests.
     */
    public class JardinFactoryBD implements AbstractJardinFactory{

        // ajoute si possible un nouveau jardin et retourne la parcelle mère de ce jardin.
        public Parcelle AddJardin(String nomJardin, int dimx, int dimy){
            ParcelleBD p = (ParcelleBD)this.newJardin(nomJardin, dimx, dimy);

            Connection con = null;
            PreparedStatement requete = null;

            //requête mise en forme de string
            String stringReq = "insert into Jardin(NomJardin,dimx,dimy,parcelleRacine) values (?,?,?,?)";

            try{
                Class.forName("org.mariadb.jdbc.Driver");
            
                try {
                    con = MyConnection.getMyConnection().getConnectionBD();

                    requete = con.prepareStatement(stringReq);
                    requete.setString(1,nomJardin);
                    requete.setInt(2,dimx);
                    requete.setInt(3,dimy);
                    requete.setInt(4,p.getID());

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

            return p;
        }

        // supprime (si existant) le jardin.
        public void DeleteJardin(String nomJardin){
            Connection con = null;
            PreparedStatement requete = null;

            //requête mise en forme de string
            String stringReq = "delete from Jardin where NomJardin=?";

            try{
                Class.forName("org.mariadb.jdbc.Driver");
            
                try {
                    con = MyConnection.getMyConnection().getConnectionBD();

                    requete = con.prepareStatement(stringReq);
                    requete.setString(1,nomJardin);

                    requete.executeUpdate();

                }catch(SQLException e){
                    // échec de la connexion
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

            }catch(ClassNotFoundException e){
                //mauvais classpath
            }        
        }

        // retourne (si existant) le jardin.
        public Parcelle getJardin(String nomJardin){
            Connection con = null;
            PreparedStatement requete = null;
            int idParcelle=0;
            int dimx = 0;
            int dimy = 0;

            //requête mise en forme de string
            String stringReq = "Select parcelleRacine, dimx, dimy from Jardin where NomJardin =?";

            try{
                Class.forName("org.mariadb.jdbc.Driver");
            
                try {
                    con = MyConnection.getMyConnection().getConnectionBD();

                    requete = con.prepareStatement(stringReq);
                    requete.setString(1,nomJardin);

                    ResultSet result = requete.executeQuery();

                    result.next();
                    idParcelle=result.getInt(1);
                    dimx=result.getInt(2);
                    dimy=result.getInt(3);
                }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Problème de connexion",
                 "Impossible de se connecter, vérifiez votre connexion internet ou contactez le support.",
                  JOptionPane.ERROR_MESSAGE);
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

            }catch(ClassNotFoundException e){
                //mauvais classpath
            }  

            return new ParcelleBD(nomJardin,idParcelle,dimx,dimy);
        }

        /**
          *  retourne tout les jardins de la BD
          *@return Map<String,Parcelle>
          */
        public Map<String,Parcelle> getAllJardin(){
            Connection con = null;
            PreparedStatement requete = null;
            Map<String,Parcelle> jardin = new HashMap<String,Parcelle>();
            String tmpNom;

            //requête mise en forme de string
            String stringReq = "select NomJardin from Jardin";

            try{
                Class.forName("org.mariadb.jdbc.Driver");
            
                try {
                    con = MyConnection.getMyConnection().getConnectionBD();

                    requete = con.prepareStatement(stringReq);

                    ResultSet rs = requete.executeQuery();

                    while(rs.next()){
                        tmpNom = rs.getString(1);

                        jardin.put(tmpNom,this.getJardin(tmpNom));
                    }

                }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Problème de connexion",
                 "Impossible de se connecter, vérifiez votre connexion internet ou contactez le support.",
                  JOptionPane.ERROR_MESSAGE);
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

            }catch(ClassNotFoundException e){
                //mauvais classpath
            }         
            return jardin;      
        }

        /**
         * Pseudo Constructeur pour un nouveau jardin. Retourne la parcelle initiale du jardin
         */
        public Parcelle newJardin(String nomJardin, int dimx, int dimy){
            Connection con = null;
            PreparedStatement requete = null;
            int idParcelle=0;
            //requête mise en forme de string
            String stringReq = "insert into Parcelle(x0,y0,x1,y1,NomJardin) values (?,?,?,?,?)";     
            String stringReq2 = "select idParcelle from Parcelle where NomJardin=?";  

            try{
                Class.forName("org.mariadb.jdbc.Driver");
            
                try {
                    con = MyConnection.getMyConnection().getConnectionBD();

                    requete = con.prepareStatement(stringReq);
                    requete.setInt(1,0);
                    requete.setInt(2,0);
                    requete.setInt(3,dimx);
                    requete.setInt(4,dimy);
                    requete.setString(5,nomJardin);

                    requete.executeUpdate();

                    requete = con.prepareStatement(stringReq2);
                    requete.setString(1,nomJardin);

                    ResultSet rs = requete.executeQuery();
                    rs.next();

                    idParcelle = rs.getInt(1);

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
            return new ParcelleBD(nomJardin, idParcelle, dimx, dimy);
        }
    }