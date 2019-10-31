import my_api.*;
import java.util.*;
import java.time.Month;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import views.*;
import listeners.*;

public class Main{
	public static void main(String[] args) {

        JardinFactoryBD jardin = new JardinFactoryBD();              

        Map<String,Parcelle> map = jardin.getAllJardin();

        LegumeFactoryBD legume = LegumeFactoryBD.getLegumeFactoryBD();
        
        AbstractActionFactory action = ActionFactoryBD.getActionFactoryBD();
        
        ControleFenetre controle = new ControleFenetre(jardin,legume,action);

        Fenetre fenetre = new Fenetre(controle);

        Menu m = new Menu(map,fenetre);

        MenuBar mb = new MenuBar(fenetre);
       
        fenetre.setContenu(m);

        fenetre.setBar(mb);

	fenetre.setVisible(true);
	}
}