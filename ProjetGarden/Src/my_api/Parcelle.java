package my_api;
import my_api.enums.*;
import java.util.*;
import java.time.*;

/**
 * Interface principale de l'API
 * Explicite les méthodes permettant de gérer les parcelles (taille, structure, navigation) et les actions sur ces dernières.
 */
public interface Parcelle{


    /**     
     * retourne un itérateur sur les actions réalisées sur la parcelle mais pas sur une ancêtre de celle-ci.
     */

    public Iterator<Action> getActions();

    /**     
     * retourne un itérateur sur les actions vraiment réalisées sur la parcelle (y compris sur une ancêtre de celle-ci).
     */
    @SuppressWarnings("unchecked")
    public Iterator<Action> getAllActions();
    
    /**     
     * ajoute une action à la parcelle.
     */
    public void addAction(Action a);
    
    /**
     * retourne le type de split de la parcelle (voir Orientation.java) null si pas de sous-parcelle
     */
    public Orientation getSplit();

    /**
     * retourne la sous parcelle gauche (si split vertical) ou haute (si split horizontal).
     * IllegalStateException si la parcelle n'a pas de sous-parcelle
     */
    public Parcelle getFirst();

    /**
     * retourne la sous parcelle droite (si split vertical) ou basse (si split horizontal).
     * IllegalStateException si la parcelle n'a pas de sous-parcelle
     */
    public Parcelle getSecond();

    /**
     * réunit les sous-parcelles (on oublie les sous-parcelles)
     */
    public void reset();
    
    /**
     *  retourne la parcelle mère de la parcelle (self si parcelle racine du jardin)
     */
    public Parcelle getMother();

    /**
     *  retourne la parcelle racine de la parcelle (self si racine du jardin)
     */
    public Parcelle getRoot();

    /**
     *  retourne l'abscisse du coin en haut à gauche de la parcelle
     */
    public int getx0();
    
    /**
     *  retourne l'ordonnée du coin en haut à gauche de la parcelle
     */
    public int gety0();
    
    /**
     *  retourne l'abscisse du coin en bas à droite de la parcelle
     */
    public int getx1();
    
    /**
     *  retourne l'ordonnée du coin en bas à droite de la parcelle
     */
    public int gety1();
    
    /**
     *  retourne le nom du jardin de la parcelle
     */
    public String getNomJardin();

    public int getID();

    /**
     * découpe la parcelle actuelle en crééant deux sous-parcelles selon l'orientation passée en argument.
     * Les parcelles sont de taille quasi-identique. La dimension qui est divisé par deux sera arrondi à l'entier supérieur pour la première parcelle. 
     */
    public void SplitParcelle(Orientation o);
}
