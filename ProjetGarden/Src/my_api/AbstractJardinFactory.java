package my_api;
import java.util.*;

/**
  * Interface pour les factory de jardin
  *
  */
public interface AbstractJardinFactory{

    /**
      * ajoute si possible un nouveau jardin et retourne la parcelle mère de ce jardin.
      */ 
    public Parcelle AddJardin(String nomJardin, int dimx, int dimy);

    /** 
      Supprime (si existant) le jardin
      */
    public void DeleteJardin(String nomJardin);

    /**
      retourne le jardin associé au nom
      @param nomJardin
      @return Parcelle mère du jardin
      */
    public Parcelle getJardin(String nomJardin);

    /**
      retourne tout les jardins
      @return map avec nom et parcelle mère
      */
    public Map<String,Parcelle> getAllJardin();
}