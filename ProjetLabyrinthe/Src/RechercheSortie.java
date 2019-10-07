/**
 * La classe RechercheSortie a les méthodes ou sont écrit les algorithmes pour sortir thésée du
 *  labyrinthe.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RechercheSortie extends Point {
  /**
    * Grille qui représente le labyrinthe.
    */
  private Grille grille;
  /**
    * Position de l'algorithme.
    */
  private Thesee thesee;
  /**
    * Tableau qui code les deplacements de l'algorithme
    */
  public final int[][] deplacements={{1,0},{0,1},{-1,0},{0,-1}};
  /**
    *Variable pour intéragir avec le tableau de déplacement
    */
  private int valeurdeplacement; 

  private ArrayDeque<Point> pile;


/**
  * @param g,t
  */
  public RechercheSortie(Grille g,Thesee t){
    this.grille=g;
    this.thesee=t;
    this.valeurdeplacement=0;   
    this.pile= new ArrayDeque<>();
  }

/** Cette méthode applique l'algorithme aléatoire en faisant appel à la méthode
  * @see RechercheSortie#avancementAleatoire()
  * avancementAleatoire. 
  * @return le nombre de tour pour parvenir à la sortie.
  */
  public int aleatoire() {
    int nbfois=0;
    int y=(int)this.thesee.getY(); //On garde en mémoire le départ de thésee si on veut lancer 
    int x=(int)this.thesee.getX(); //plusieurs fois la recherche aléatoire

    while(this.thesee.getX()!=grille.getSortieX() || this.thesee.getY()!=grille.getSortieY()){
      this.avancementAleatoire();
      nbfois++;

    //Si l'algo est infini ou trop long à cause d'un chemin inexistant
      if (nbfois==1000000) { 
        return -1;
      }
    }
    this.thesee.setLocation(x,y); //On remet la valeur du départ

    return nbfois;
  }

  /**
    * Cette méthode va appeler 100 fois une méthode qui applique l'algorithme aléatoire.
    * @see RechercheSortie#aleatoire()
    * @return La moyenne
    */
  public double moyenne(){
    double moyenne=0; //Moyenne des 100 passages de l'algo aléatoire
    
    for (int i=0;i<100 ;i++ ) {
      moyenne+=(double)this.aleatoire();
    }
    return moyenne/100;
  }

  /**
    * Cette méthode applique l'algorithme déterministe en faisant appel à la méthode
    * @see RechercheSortie#avancementDeterministe()
    * @return La moyenne
    */
  public int deterministe(){
    int nbfois=0;

    while(this.thesee.getX()!=grille.getSortieX() || this.thesee.getY()!=grille.getSortieY()){
      nbfois++;
      this.avancementDeterministe();

      //Si l'algo est infini ou trop long à cause d'un chemin inexistant
      if (nbfois==1000000) { 
        return -1;
      }
    }    
    //On réinitialise tout pour que l'utilisateur puisse relancer l'algo
    this.thesee=new Thesee(grille.getDepart(),grille.getTaille()); 
    return nbfois;
  }

  /**
    * Cette méthode sert à changer la valeur du déplacement.
    * @param valeur valeur actuelle
    * @return valeur changer.
    */
  public static int plusDeplacement(int valeur){
    if (valeur!=3) {
      return valeur+1;

    } else {
      return valeur=0;
    }
  }

  /**
    * Méthode qui avance d'un tour l'algorithme déterministe.
    */
  public void avancementDeterministe(){
    int tmpd=this.valeurdeplacement;
    byte flag=0;
    byte nbcasevide=0;
    int nbfoisp=0; //nombre de fois ou on es passer sur la case

    int posy=(int)this.thesee.getY();
    int posx=(int)this.thesee.getX();  

      //On consulte la mémoire de thésée
    for (int i=0;i<4 ;i++ ) { //la variable tmp permet de prendre en priorité un chemin pas connus
      posy+=deplacements[tmpd][1];
      posx+=deplacements[tmpd][0];

      if (this.thesee.getValeurAt(posy+1,posx+1)==0 || 
        this.thesee.getValeurAt(posy+1,posx+1)>1) {
        
        nbcasevide++;
        this.valeurdeplacement=tmpd; //On place la nouvelle valeur pour le déplacement
      } else if (this.thesee.getValeurAt(posy+1,posx+1)==-1) {
        this.valeurdeplacement=tmpd; //On place la nouvelle valeur pour le déplacement
        flag=-1; //Une case non explorer est prioritaire
        i=4;
        nbfoisp=-1;     
      }

      posy=(int)this.thesee.getY(); //on réinititalise les valeurs 
      posx=(int)this.thesee.getX();         
      tmpd=RechercheSortie.plusDeplacement(tmpd); //On change de déplacement
    }


    //Il y a que des chemins que l'on connait a force d'y passer
    //Pour éviter que l'algo reste bloquer 
    if (flag!=-1) {

      //L'algo est dans un voie sans issue donc on la referme.
      if (nbcasevide==1) {
        this.thesee.setValeurAt((int)this.thesee.getY()+1,(int)this.thesee.getX()+1,1);  
      } else{

      //On regarde combien de fois chaque case on été visité
        nbfoisp=this.thesee.getValeurAt(posy+1,posx+1);
      
      for (int i=0,tmp;i<4 ;i++) {
        posy+=deplacements[tmpd][1];
        posx+=deplacements[tmpd][0];
 

        tmp=this.thesee.getValeurAt(posy+1,posx+1);  

        //On test si la valeur est plus petite
        if (tmp!=1 && tmp <= nbfoisp) {
          this.valeurdeplacement=tmpd;
          nbfoisp=tmp;
        }

        posy=(int)this.thesee.getY(); //on réinititalise les valeurs 
        posx=(int)this.thesee.getX(); 
        tmpd=RechercheSortie.plusDeplacement(tmpd); //On change de déplacement
      }
      }
    }

    posy+=deplacements[this.valeurdeplacement][1];
    posx+=deplacements[this.valeurdeplacement][0];
        
      //On essaye de faire bouger thésée
      //Il y a un risque que cela depasse la taille du tableau
    try{
      int test = grille.getValeurIndice(posy,posx);

      if (test==0) {
        //Pour ne pas confondre les cases visité avec des murs
        if(nbfoisp+1==1){
          nbfoisp=1;
        }

        this.thesee.setLocation(posx,posy);
        this.thesee.setValeurAt(posy+1,posx+1,nbfoisp+1); //On écrit dans la mémoire de thésée
      } else{
        this.thesee.setValeurAt(posy+1,posx+1,1); //On écrit dans la mémoire de thésée
        posy=(int)this.thesee.getY(); //On remet dans les variables la position de thesee
        posx=(int)this.thesee.getX();
        this.valeurdeplacement=RechercheSortie.plusDeplacement(this.valeurdeplacement);  
      }          
      } catch(ArrayIndexOutOfBoundsException e){
        //L'intérêt d'avoir 2 lignes et 2 colonnes en plus pour réprésenter les bordures
        this.thesee.setValeurAt(posy+1,posx+1,1); 
        posy=(int)this.thesee.getY(); //On remet dans les variables la position de thesee
        posx=(int)this.thesee.getX();
        this.valeurdeplacement=RechercheSortie.plusDeplacement(this.valeurdeplacement);
      }    
  }

  /**
    * Méthode qui avance d'un tour l'algorithme aléatoire.
    */
  public void avancementAleatoire(){
    Random r = new Random();    
    int alea=r.nextInt(4); // on veut une valeur entre 0 et 3 inclus car il y a 4 possibilite de deplacement
    int posy=(int)this.thesee.getY();
    int posx=(int)this.thesee.getX();

    posy+=deplacements[alea][1];
    posx+=deplacements[alea][0];

        //Il y a un risque que cela depasse la taille du tableau
      try{
        int test = grille.getValeurIndice(posy,posx);

        if (test==0) {
          this.thesee.setLocation(posx,posy);
        } else{
          posy=(int)this.thesee.getY(); //On remet dans les variables la position de theser
          posx=(int)this.thesee.getX();          
        }
          
        } catch(ArrayIndexOutOfBoundsException e){
          posy=(int)this.thesee.getY(); //On remet dans les variables la position de theser
          posx=(int)this.thesee.getX();
        }
      }

/** Cette méthode sert a appeler 
  * @see RechercheSortie#avancementAleatoire()
  * avancementAleatoire. Puis vérifier si l'algorithme est fini
  * @return Point
  */
  public Point avancementAleatoireManuel(){
    this.avancementAleatoire();

      //Si thésée trouve la sortie on renvoi null
    if (this.thesee.getX()==grille.getSortieX() && this.thesee.getY()==grille.getSortieY()) {
      this.thesee=new Thesee(grille.getDepart(),grille.getTaille());
      return null;
    }
    return this.thesee;
  }

/** Cette méthode sert a appeler 
  * @see RechercheSortie#avancementDeterministe()
  * avancementAleatoire. Puis vérifier si l'algorithme est fini
  * @return Point
  */
  public Point avancementDeterministeManuel(){
    this.avancementDeterministe();

      //Si thésée trouve la sortie on renvoi null
    if (this.thesee.getX()==grille.getSortieX() && this.thesee.getY()==grille.getSortieY()) {
      this.thesee=new Thesee(grille.getDepart(),grille.getTaille());
      return null;      
    }
    return this.thesee;
  }

  /**
    * @throws ArrayIndexOutOfBoundsException
    */
  public void avancementNord(){
    int posy=(int)this.thesee.getY()-1;
    int posx=(int)this.thesee.getX(); 
  
    int test = grille.getValeurIndice(posy,posx);

    if (test!=1) {
      this.thesee.setLocation(posx,posy);
    }
  }

  /**
    * @throws ArrayIndexOutOfBoundsException
    */
    public void avancementSud(){
      int posy=(int)this.thesee.getY()+1;
      int posx=(int)this.thesee.getX(); 
  
      int test = grille.getValeurIndice(posy,posx);

      if (test!=1) {
       this.thesee.setLocation(posx,posy);
      }
  }

  /**
    * @throws ArrayIndexOutOfBoundsException
    */
    public void avancementOuest(){
      int posy=(int)this.thesee.getY();
      int posx=(int)this.thesee.getX()-1; 
  
      int test = grille.getValeurIndice(posy,posx);

      if (test!=1) {
        this.thesee.setLocation(posx,posy);
      }
  }

  /**
    * @throws ArrayIndexOutOfBoundsException
    */
    public void avancementEst(){
      int posy=(int)this.thesee.getY();
      int posx=(int)this.thesee.getX()+1; 
  
      int test = grille.getValeurIndice(posy,posx);

      if (test!=1) {
        this.thesee.setLocation(posx,posy);
      }
  }

  public boolean isFinnish(){

    if (this.thesee.getX()==grille.getSortieX() && this.thesee.getY()==grille.getSortieY()) {
      return true;
    } else{
      return false;
    }
  }

  /**
    * Renvoi la grille.
    * @return La grille du labyrinthe.
    */
  public Grille getGrille(){
    return this.grille;
  }

  public Thesee getThesee(){
    return this.thesee;
  }

  public int parcourslargeur(Point p){
    grille.setValeurIndice((int)p.getX(),(int)p.getY(),2);


    for (int i=0;i<4 ;i++ ) {
      int tmpy = (int)p.getY()+deplacements[i][0];
      int tmpx = (int)p.getX()+deplacements[i][1];

      //si on trouve la sortie on renvoie 0
      if (tmpy==grille.getSortieY() && tmpx==grille.getSortieX()) {
        this.pile.push(new Point(tmpx,tmpy));
        return 0;
      }
      try{
        if (grille.getValeurIndice(tmpy,tmpx)==0) {

          if (parcourslargeur(new Point(tmpx,tmpy))==0) {
            this.pile.push(new Point(tmpx,tmpy));
            return 0;
          }
        }
      }catch(ArrayIndexOutOfBoundsException e){}
      }
      return 1;
  }

  /**
    * Méthode qui change la sortie du personnage lorsqu'onréalise un parcours en largeur
    */
  public void changeSortie(Thesee t){
    Point tmp;
    ArrayDeque<Point> piletmp= new ArrayDeque<>();    

    while(true){
      tmp=this.pile.pollLast();
      //on remet tout dans la pile
      if (tmp==null) {
        tmp=piletmp.pollFirst();
        while(tmp!=null){
          this.pile.offerLast(tmp);
          tmp=piletmp.pollFirst();
        }
        this.pile.offerLast(new Point((int)t.getX(),(int)t.getY()));
      }

      try{
        if (tmp.getX()==t.getX() && tmp.getY()==t.getY()) {
          break;
        }
      }catch(NullPointerException e){
        break;
      }

      piletmp.push(tmp);
    }
  }

  public ArrayDeque<Point> getPile(){
    return this.pile;
  }

}

