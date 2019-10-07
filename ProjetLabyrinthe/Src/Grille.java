/**
 * La classe Grille stocke les labyrinthes et permet de les sauvegarder 
 * ou de les charger.
 */

import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Grille{
  /**
    * Tableau qui code les murs et les chemin du labyrinthe.
    */
	private int[][] tableau;

  /**
    * La taille du labyrinthe.
    */
	private int taille;

  /**
    * La sortie du labyrinthe.
    */
	private Point sortie;
  /**
    * Le départ du labyrinthe.
    */  
	private Point depart;

  /**
    * Constructeur qui initialise toute les cases du labyrinthe en tant que chemin.
    * @param taille taille d'un coté du labyrinthe
    */
  public Grille(int taille){
    this.taille=taille;
    this.tableau= new int[taille][taille];

      for (int i=0;i<this.taille ;i++ ) {
          for (int j=0;j<this.taille ;j++ ) {
            this.tableau[i][j]=0;
      }
    }

    //Ce sera à l'utilisateur d'initialiser ces valeurs
    this.sortie=null; 
    this.depart=null; 
  }

  /**
    * Ce constructeur charge un fichier de labyrinthe dans un certains format.
    * @param file le fichier en question.
    */
	public Grille(File file){
		int tmp;
		int tmp2;
    int test=0x80; //Nombre qui va permettre de comparer les bit

    try{
			FileInputStream f = new FileInputStream(file);
			BufferedInputStream buff = new BufferedInputStream(f);
			DataInputStream fichier = new DataInputStream(buff);

			try{
				this.taille=fichier.readByte();
        this.tableau = new int[this.taille][this.taille];

				tmp=(int)fichier.readByte(); //Lecture de la position de thesée
				tmp2=(int)fichier.readByte();
				this.depart = new Point(tmp2,tmp);

				tmp=(int)fichier.readByte();
				tmp2=(int)fichier.readByte();
				this.sortie = new Point(tmp2,tmp);

        tmp=fichier.read();

        for (int j=0;j<this.taille ;j++ ) {
          for (int i=0;i<this.taille;i++) {

            if (test==0) { //si test est égale à zero alors il faut relire un byte
              tmp=fichier.read();
              test=0x80; //Nombre qui va permettre de comparer les bit
            }

            tmp2=tmp&test; // et logique pour comparer que un seul bit
            test = test >> 1; //decalage d'un bit

            if (tmp2==0) { // si et logique donne 0 c'est que la case est vide
              this.tableau[i][j]=0;

            } else { //sinon c'est un mur
              this.tableau[i][j]=1;
            }
          }
        }
      }
			 catch (IOException e){
		    Grille.erreurfichier();

			}

			try{
				fichier.close();
			} catch (IOException e){
				Grille.erreurfichier();
			}

		} catch(FileNotFoundException e){
      Grille.erreurfichier();
		}
  }



	/**
    * Méthode qui initialise le labyrinthe de manière aléatoire
    */
    public void labyrintheAleatoire(){
    	Random r = new Random();
    //Il faut environ que 1/3 des cases soient des murs pour realiser un labyrinthe convenable
      for (int i=0;i<this.taille ;i++ ) {
      	for (int j=0;j<this.taille ;j++ ) {

        	if (r.nextInt(3)==1) {
        		this.tableau[i][j]=1;
        	} else{
        		this.tableau[i][j]=0;
        	}
      	}
    }
}

  /**
    * Méthode qui va sauvegarder une grille dans un fichier.
    * @param file le fichier en question
    */
  public void sauvegarderGrille(File file){
    int tmp=1; //on va decaler les bits pour ecrire dans le bon labyrinthe
    int tmp2=0;
    int count=0; //variable qui va compter le nombre de bit


    try{
      FileOutputStream f = new FileOutputStream(file);
      BufferedOutputStream buff = new BufferedOutputStream(f);
      DataOutputStream fichier = new DataOutputStream(buff);

      try{
        fichier.writeByte(this.taille);

        fichier.write((int)this.depart.getY()); //Lecture de la position de theser
        fichier.write((int)this.depart.getX());

        fichier.write((int)this.sortie.getY());
        fichier.write((int)this.sortie.getX());

        for (int j=0;j<this.taille ;j++ ) {
          for (int i=0;i<this.taille;i++,count++) {

            tmp2+=this.tableau[i][j];
            tmp=tmp&tmp2;

            if (count==7) { //si test est égale à zero alors il faut relire un byte
              fichier.writeByte(tmp);
              count=-1; //on va incrementer de 1 count et comme on veut count==0
              tmp=1;
              tmp2=0;
            } else {
              tmp2=tmp2 <<1; //On decale les bit
              tmp=tmp << 1;
              tmp++;
            }
          }
        }

        if (count!=0) {
          tmp= tmp>>1; //Pour eviter les pb lorsque les grilles ne st pas multiple de 8
          tmp = tmp << (7-(count-1));
          fichier.writeByte(tmp);
        }

      } catch (IOException e){
        System.err.println(e.getMessage());
      }

      try{
        fichier.close();
      } catch(IOException e){
        Grille.erreurfichier();
      }

    } catch (IOException e){
      Grille.erreurfichier();
    }
  }

  /**
    * Permet d'afficher à la console la grille, utile en cas de
    * recherche de bug.
   */ 
  /*public void afficherGrille(){
    for (int i=0;i<this.getTaille() ;i++ ) {
      for (int j=0;j<this.getTaille() ;j++ ) {
        System.out.print(this.tableau[i][j]);
      }
      System.out.println();
    }
  } */

  /**
    *Renvoi la valeur de la taille
    * @return la taille
    */
  public int getTaille(){
    return this.taille;
  }

  /**
    *Renvoi la valeur
    * @return le tableau de la grille
    */
  public int[][] getTab(){
    return this.tableau;
  }

  /**
    *Renvoi la valeur qui se positionne en i,j dans le tableau.
    *@return La valeur dans les position i,j
    *@param i,j les positions
    */
  public int getValeurIndice(int i,int j){
    return this.tableau[i][j];
  }

  /**
    *Renvoi la valeur X de la sortie.
    * @return X
    */
  public int getSortieX(){
    return (int) this.sortie.getX();
  }

  /**
    *Renvoi la valeur Y de la sortie.
    *  @return Y
    */
  public int getSortieY(){
    return (int) this.sortie.getY();
  }

  /**
    *Renvoi la valeur X du départ
    * @return X
    */
  public int getDepartX(){
    return (int) this.depart.getX();
  }

  /**
    *Renvoi la valeur Y du départ
    * @return Y
    */
  public int getDepartY(){
    return (int) this.depart.getY();
  }

  /**
    *Renvoi la valeur du départ sous forme de Point
    * @return le point de départ.
    */
  public Point getDepart(){
  	return this.depart;
  }

  /**
    *Renvoi la valeur de la sortie sous forme de Point
    * @return le point de sortie.
    */
  public Point getSortie(){
  	return this.sortie;
  }

  /**
    * Change de valeur de la taille.
    * @param taille taille du labyrinthe.
    */
  public void setTaille(int taille){
    this.taille=taille;
  }

  /**
    * Change de valeur la grille.
    * @param tab tableau du labyrinthe.
    */
	public void setGrille(int[][] tab){
		this.tableau=tab;
	}

  /**
    * Change de valeur
    * @param 
    */
	public void setValeurIndice(int x,int y,int valeur){
		this.tableau[y][x]=valeur;
	}

  /**
    * Change de valeur la sortie.
    * @param s un point.
    */
	public void setSortie(Point s){
		this.sortie=s;
	}

  /**
    * Change de valeur la sortie.
    * @param  x,y valeurs pour changer la sortie.
    */
	public void setSortie(int x,int y){
		this.sortie=new Point(x,y);
	}

  /**
    * Change de valeur le départ.
    * @param d un point.  
    */
	public void setDepart(Point d){
		this.depart=d;
	}

  /**
    * Change de valeur le départ.
    * @param x,y 2 valeurs pour changer de départ.
    */
	public void setDepart(int x,int y){
		this.depart= new Point(x,y);
	}

  public static void erreurfichier(){
    JOptionPane popup = new JOptionPane();
    popup.showMessageDialog(null, "Impossible d'accéder aux fichiers"
    , "", JOptionPane.INFORMATION_MESSAGE); 
  }
}