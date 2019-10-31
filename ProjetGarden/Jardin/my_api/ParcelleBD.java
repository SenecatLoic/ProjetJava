import java.util.*;
import java.time.*;
import java.sql.*;
import api.*;

/**
 * ParcelleBD ne garantie pas la persistance des données.
 * Les données sont des objets java.
 * À utiliser pour des tests mais pas en production.
 */
public class ParcelleBD implements Parcelle{

    private int idParcelle;

    /**
     * Nom du jardin à laquelle la parcelle appartient
     */
    private String nomJardin;

    /**
     * abscisse coin en haut à gauche.
     */
    private int x0;
    /**
     * ordonnée coin en haut à gauche.
     */
    private int y0;
    /**
     * abscisse coin en bas à droite.
     */
    private int x1;
    /**
     * ordonnée coin en bas à droite.
     */
    private int y1;

    /**
     * En cas de découpage de la parcelle, indique si le découpage est horizontal ou vertical
     */
    private Orientation split;

    /**
     * sous parcelle gauche (si split vertical) ou haute (si split horizontal).
     */
    private ParcelleBD sousParcelle0;

    /**
     * sous parcelle droite (si split vertical) ou basse (si split horizontal).
     */
    private ParcelleBD sousParcelle1;


    /**
     * parcelle racine du jardin
     */
    private ParcelleBD tilde;
    
     /**     
      * parcelle mère
      */
    private ParcelleBD pointPoint;

    /**     
     * stocke les actions réalisées sur la parcelle.
     */
    private ArrayList<Action> actions = new ArrayList<Action>();

    /**     
     * retourne un itérateur sur les actions réalisées sur la parcelle mais pas sur une ancêtre de celle-ci.
     */
    @SuppressWarnings("unchecked")
    public Iterator<Action> getActions(){
        ArrayList<Action> actions = new ArrayList<Action>();
        Connection con = null;
        PreparedStatement requete = null;

        //requête mise en forme de string
        String stringReq = "select idAction,idLegume from Action where idParcelle=?";

        try{
            Class.forName("org.mariadb.jdbc.Driver");    
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setInt(1,this.id);

                ResultSet rs = requete.executeQuery();

                while(rs.next()){
                    int idaction = rs.getInt(1);
                    int idLegume = rs.getInt(2);

                    if (idLegume==null) {
                        actions.add(new ActionSolBD(idaction));
                    }else{
                       actions.add(new ActionLegumeBD(idaction)); 
                    } 
                }

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

        return actions.iterator();    
    }

    /**     
     * retourne un itérateur sur les actions vraiment réalisées sur la parcelle (y compris sur une ancêtre de celle-ci).
     */
    @SuppressWarnings("unchecked")
    public Iterator<Action> getAllActions(){
        ParcelleBD walkingUp = this;
        ArrayList<Action> allActions = (ArrayList<Action>) actions.clone();
        while (walkingUp.pointPoint != walkingUp) {
            walkingUp = walkingUp.pointPoint; // moveUp
            ArrayList<Action> copiesActions = (ArrayList<Action>) walkingUp.actions.clone();
            allActions.addAll(copiesActions);// un peu moche ça retourne un booléen
        }
        return allActions.iterator();
    }

    /**     
     * ajoute une action à la parcelle.
     */
    public void addAction(Action action){
        Connection con = null;
        PreparedStatement requete = null;
        int idAction = 0;

        if (action instanceof ActionLegumeBD) {
            action = (ActionLegumeBD) action;
        } else{
            action = (ActionSolBD) action;
        }

        idAction = action.getId();

        //requête mise en forme de string
        String stringReq = "update Action set idParcelle=? where idAction=? ";

        try{
            Class.forName("org.mariadb.jdbc.Driver");    
            try {
                con = MyConnection.getMyConnection().getConnectionBD();

                requete = con.prepareStatement(stringReq);
                requete.setInt(1,this.idParcelle);
                requete.setInt(2,idAction);

                requete.updateQuery();

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
    }
    


    /**
     * retourne le type de split de la parcelle (voir Orientation.java) null si pas de sous-parcelle
     */
    public Orientation getSplit(){
        return split;
    }

    /**
     * retourne la sous parcelle gauche (si split vertical) ou haute (si split horizontal).
     */
    public Parcelle getFirst(){
        if (this.split == null || this.sousParcelle0 == null) throw new IllegalStateException("La parcelle n\'a pas de sous-parcelle");
        return sousParcelle0;
    }

    /**
     * retourne la sous parcelle droite (si split vertical) ou basse (si split horizontal).
     */
    public Parcelle getSecond(){
        if (this.split == null || this.sousParcelle1 == null) throw new IllegalStateException("La parcelle n\'a pas de sous-parcelle");
        return sousParcelle1;
    }

    /**
     * réunit les sous-parcelles (un quasi destructeur. on oublie les sous-parcelles, le garbage collector se chargera de les détruire)
     */
    public void reset(){
        if (this.split == null || this.sousParcelle0 == null || this.sousParcelle1 == null) throw new IllegalStateException("Pas possible de détruire le contenu de la parcelle car elle ne contient pas deux sous-parcelles.");

        try{

            MyConnection myConnection = MyConnection.getMyConnection();
            Connection connection = myConnection.getConnectionBD();
            PreparedStatement update = connection.prepareStatement("UPDATE Parcelle SET idSousParcelle0 = ?, idSousParcelle1 = ?, orientation = ? WHERE idParcelle = ? AND NomJardin = ?");
            PreparedStatement delete1 = connection.prepareStatement("DELETE FROM Parcelle WHERE idParcelle = ? AND NomJardin = ?");
            PreparedStatement delete2 = connection.prepareStatement("DELETE FROM Parcelle WHERE idParcelle = ? AND NomJardin = ?");

            update.setNull(1,Types.NULL);
            update.setNull(2,Types.NULL);
            update.setNull(3,Types.NULL);
            update.setInt(4,this.idParcelle);
            update.setString(5,this.nomJardin);
            update.executeUpdate();

            delete1.setInt(1,this.sousParcelle0.getID());
            delete1.setString(2,this.nomJardin);
            delete1.executeUpdate();

            delete2.setInt(1,this.sousParcelle1.getID());
            delete2.setString(2,this.nomJardin);
            delete2.executeUpdate();
            
            delete1.close();
            delete2.close();
            update.close();
            connection.close();
        }catch(SQLException e){

            System.err.println("Erreur BDD: "+e.getMessage());
        }

        this.split=null;
        this.sousParcelle0=null;
        this.sousParcelle1=null;
    }

    /**
     *  retourne la parcelle mère de la parcelle (self si parcelle racine du jardin)
     */
    public Parcelle getMother(){
        return this.pointPoint;
    }

    /**
     *  retourne la parcelle racine de la parcelle (self si racine du jardin)
     */
    public Parcelle getRoot(){
        return this.tilde;
    }

    public int getID(){
        return this.idParcelle;
    }

    public void setID(int id){
        this.idParcelle = id;
    }

    /**
     *  retourne l'abscisse du coin en haut à gauche de la parcelle
     */
    public int getx0(){
        return this.x0;
    }
    /**
     *  retourne l'ordonnée du coin en haut à gauche de la parcelle
     */
    public int gety0(){
        return this.y0;
    }
    /**
     *  retourne l'abscisse du coin en bas à droite de la parcelle
     */
    public int getx1(){
        return this.x1;
    }
    /**
     *  retourne l'ordonnée du coin en bas à droite de la parcelle
     */
    public int gety1(){
        return this.y1;
    }
    /**
     *  retourne le nom du jardin de la parcelle
     */
    public String getNomJardin(){
        return this.nomJardin;
    }
    
    /**
     * Constructeur pour un nouveau jardin
     */
    public ParcelleBD(String nomJardin, int id, int dimx, int dimy){
        if (dimx <= 0 || dimy <=0) throw new IllegalArgumentException("Les dimensions du nouveau jardin doivent être strictement positives");
        if (nomJardin.length()==0) throw new IllegalArgumentException("Le nom du nouveau jardin ne peut pas être vide");
        this.nomJardin = nomJardin;
        this.x0=0;
        this.y0=0;
        this.x1=dimx;
        this.y1=dimy;
        this.split=null;
        this.sousParcelle0=null;
        this.sousParcelle1=null;
        this.idParcelle = id;
        this.tilde = this;
        this.pointPoint = this;
    }

    /**
     * Constructeur pour une nouvelle parcelle qui n'a pas de descendant (attention doit rester privé, usage interne seulement)
     */
    private ParcelleBD(String nomJardin, int x0, int y0, int x1, int y1, ParcelleBD tilde, ParcelleBD pointPoint){
        this.nomJardin = nomJardin;
        this.x0=x0;
        this.y0=y0;
        this.x1=x1;
        this.y1=y1;
        this.sousParcelle0=null;
        this.sousParcelle1=null;
        this.tilde = tilde;
        this.pointPoint = pointPoint;

        try{

            MyConnection myConnection = MyConnection.getMyConnection();
            Connection connection = myConnection.getConnectionBD();
            PreparedStatement requete = connection.prepareStatement("INSERT INTO Parcelle(idParcelle,idSousParcelle0,idSousParcelle1,x0,y0,x1,y1,orientation,NomJardin,idParcelleMere) VALUES(?,?,?,?,?,?,?,?,?,?)");
            requete.setNull(1,Types.NULL);
            requete.setNull(2,Types.NULL);
            requete.setNull(3,Types.NULL);
            requete.setInt(4,this.x0);
            requete.setInt(5,this.y0);
            requete.setInt(6,this.x1);
            requete.setInt(7,this.y1);
            requete.setNull(8,Types.NULL);
            requete.setString(9,this.nomJardin);
            requete.setInt(10,this.pointPoint.getID());
            requete.executeUpdate();
            
            requete.close();
            connection.close();
        }catch(SQLException e){

            System.err.println("Erreur BDD private constructor: "+e.getMessage());
        }
    }

    /**
     * découpe la parcelle actuelle en crééant deux sous-parcelles selon l'orientation passée en argument.
     */
    public void SplitParcelle(Orientation o){
        Objects.requireNonNull(o, "Orientation o ne doit pas être null");
        if (Objects.nonNull(this.split) || Objects.nonNull(this.sousParcelle0) || Objects.nonNull(this.sousParcelle1)) throw new IllegalStateException("La parcelle ne peut pas être découpée car elle contient des sous-parcelles.");
        this.split = o;
        ParcelleBD p0, p1;
        if (o == Orientation.HORIZONTAL){
            int xsplit = (x1-x0) /2 + (x1-x0) %2; // arrondi supérieur histoire de réviser le modulo.
            p0 = new ParcelleBD(this.nomJardin, this.x0, this.y0,  xsplit, this.y1, this.tilde, this);
            p1 = new ParcelleBD(this.nomJardin,  xsplit, this.y0, this.x1, this.y1, this.tilde, this);
            this.sousParcelle0 = p0;
            this.sousParcelle1 = p1;
        }
        if (o == Orientation.VERTICAL){
            int ysplit = (y1-y0) /2 + (y1-y0) %2; // arrondi supérieur histoire de réviser le modulo.
            p0 = new ParcelleBD(this.nomJardin, this.x0, this.y0, this.x1,  ysplit, this.tilde, this);
            p1 = new ParcelleBD(this.nomJardin, this.x0,  ysplit, this.x1, this.y1, this.tilde, this);
            this.sousParcelle0 = p0;
            this.sousParcelle1 = p1;
        }

        try{

            MyConnection myConnection = MyConnection.getMyConnection();
            Connection connection = myConnection.getConnectionBD();
            PreparedStatement select = connection.prepareStatement("SELECT idParcelle FROM Parcelle WHERE idParcelleMere = ?");
            PreparedStatement update1 = connection.prepareStatement("UPDATE Parcelle SET idSousParcelle0 = ? WHERE idParcelle = ? AND NomJardin = ?");
            PreparedStatement update2 = connection.prepareStatement("UPDATE Parcelle SET idSousParcelle1 = ? WHERE idParcelle = ? AND NomJardin = ?");
            PreparedStatement update3 = connection.prepareStatement("UPDATE Parcelle SET orientation = ? WHERE idParcelle = ? AND NomJardin = ?");

            select.setInt(1,this.idParcelle);
            ResultSet resultat = select.executeQuery();

            int i;
            for(i = 0;i < 2 && resultat.next();i++){

              if(i == 0){

                update1.setInt(1,resultat.getInt(1));
                this.sousParcelle0.setID(resultat.getInt(1));
                update1.setInt(2,this.idParcelle);
                update1.setString(3,this.nomJardin);
              }

              if(i == 1){

                update2.setInt(1,resultat.getInt(1));
                this.sousParcelle1.setID(resultat.getInt(1));
                update2.setInt(2,this.idParcelle);
                update2.setString(3,this.nomJardin);

              }
            }

            update3.setString(1,""+this.split);
            update3.setInt(2,this.idParcelle);
            update3.setString(3,this.nomJardin);

            update1.executeUpdate();
            update2.executeUpdate();
            update3.executeUpdate();
            
            resultat.close();
            select.close();
            update2.close();
            update1.close();
            connection.close();
        }catch(SQLException e){

            System.err.println("Erreur BDD split: "+e.getMessage());
        }
    }
}
