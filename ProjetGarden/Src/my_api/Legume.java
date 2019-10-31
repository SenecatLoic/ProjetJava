package my_api;
import my_api.enums.*;
import java.util.*;
import java.time.Month;
import java.time.format.TextStyle;

/**
  * Un légume dispose de plusieurs attribut descrit dans cette interface
  */
public interface Legume{
    
    /** 
     * autorise le semis pour le mois donné en argument
     * @param m un mois (voir Month.java)
     * @return void
     */
    public void semisOK(Month m);

    /** 
     * autorise le semis pour le mois donné en argument
     * @param m un mois (voir Month.java)
     * @return void
     */
    public void recolteOK(Month m);

    /** 
     * Calcul si c'est possible de semer le mois donner en paramètre
     * @param m un mois (voir Month.java)
     * @return boolean
     */
    public Boolean getSemis(Month m);

    /** 
     * Calcul si c'est possible de récolter le mois donner en paramètre
     * @param m un mois (voir Month.java)
     * @return void
     */
    public Boolean getRecolte(Month m);

    /** 
     * Retourne le nom du légume
     * @return nom Légume
     */
    public String getNom();

    /** 
     * Retourne la famille du légume
     * @return famille Légume
     */
    public FamilleLegume getFamille();
    
    public default String toString(TextStyle t){
        // Stringbuilder is the most efficient method of building a String like datastructure incrementally. 
        StringBuilder sb = new StringBuilder( this.getNom() + " (" + this.getFamille() + "), semis ");
        for (Month m : Month.values()){
            if (this.getSemis(m))
                sb.append(String.format("%s ",m.getDisplayName(t, Locale.FRANCE)));
        }
        sb.append(", récolte ");
        for (Month m : Month.values()){
            if (this.getRecolte(m))
                sb.append(String.format("%s ",m.getDisplayName(t, Locale.FRANCE)));
        }
        sb.append("%n");
        return sb.toString();
    }
}