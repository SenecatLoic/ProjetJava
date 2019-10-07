JFLAGS = -encoding utf-8 -implicit:none
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
 		Fond.java \
        Graphisme.java \
        Thesee.java \
        Case.java \
        Grille.java \
        Menu.java\
        Menucontroleur.java \
        ChoisirTaille.java\
        EditeurBouton.java \
        EditeurRadioBouton.java \
        Lancer.java\
       	RechercheSortie.java \
       	ChoixDeroulement.java \
       	ChoixAlgorithme.java\
       	Construction.java\
       	Affichagegrille.java\
       	Editeur.java \
       	Deroulement.java\
       	ControleDeroulement.java \
       	SortirAlgorithme.java\
       	ChoixLabyrinthe.java\
       	ToucheControle.java\
        Main.java\

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm *.class

run:
	java Main
