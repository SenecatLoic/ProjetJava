package my_api;
import java.util.*;
import my_api.enums.*;

/**
  * Interface pour factory de Legume
  *
  */
public interface AbstractLegumeFactory {

    /** 
      * Ajoute un nouveau légume
      *@param semis, recolte : entiers sur 12 bits. De droite à gauche les mois (decembre bit 12 ... janvier bit 1).
      */ 
    public Legume AddLegume(String nomLegume, FamilleLegume famille, int semis, int recolte);

    /** 
      * supprime si existant le légume.
      */
    public void DeleteLegume(String nomLegume);
    
    /**
      *cherche légumes connus.
      * @return retourne tout les légumes
      */ 
    public Iterator<Legume> getLegumes();

    /**
      * @param famille des légumes voulu
      * @return les légumes de la famille indiquer
      */
    public Iterator<Legume> getLegumes(FamilleLegume famille);

    /** 
      * cherche légumes connus.
      * @return retourne un légume connu si la chaîne de caractère mot apparaît dans le nom de ce dernier.
      */ 
    public Iterator<Legume> getLegumes(String mot);

    /**
      * Retourne le legume correcpondant au nom
      */
    public Legume getLegume(String nomLegume);
}
