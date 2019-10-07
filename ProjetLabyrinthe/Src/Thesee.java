/**
 * La classe Thesee permet de gérer la position du personnage dans l'algorithme
 */

import java.util.*;
import java.awt.*;

public class Thesee extends Point{
  /**
    * Mémoire de thésée.
    */
  private int tab[][]; 

/**
  *Constructeur.
  *@param depart,taille
  */
  public Thesee(Point depart,int taille){
    super(depart); 
    this.tab= new int[taille+2][taille+2]; //+2 car on met des bordures au labyrinthe en mémoire 
   	for (int i=0;i<taille+2 ;i++ ) { //au départ l'algorithme considère que toute les cases sont des chemins
    	for (int j=0;j<taille+2 ;j++ ) {
        	this.tab[i][j]=-1; // -1 représente les cases non explorer par l'algorithme
      	}
    }
    this.tab[(int)depart.getY()+1][(int)depart.getX()+1]=0; //La case de départ est déja explorer
  }

/**
  * Cette méthode permet d'écrire dans la mémoire les actions réalisés.
  * @param y,x,valeur
  * @return int
  */
  public void setValeurAt(int y,int x,int valeur){
  	this.tab[y][x]=valeur;
  }

/**
  * Cette méthode permet de consulter la mémoire des actions réalisés.
  * @param y,x
  * @return int
  */
  public int getValeurAt(int y,int x){
  	return this.tab[y][x];
  }

  public int[][] getTab(){
    return this.tab;
  }
}