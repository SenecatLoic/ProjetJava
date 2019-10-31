JFLAGS = -encoding utf-8 -implicit:none
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java


CLASSES = \
    api/Legume.java \
    api/AbstractLegumeFactory.java \
    api/AbstractJardinFactory.java \
    api/Action.java \
    api/ActionLegume.java \
    api/ActionLegumeType.java \
    api/ActionSolType.java \
    api/ActionSol.java \
    api/FamilleLegume.java \
    api/LegumeFactoryBD.java \
    api/JardinFactoryJ.java\
    api/LegumeFactoryJ.java\
    api/LegumeJ.java \
    api/Orientation.java \
    api/Parcelle.java\
    api/ParcelleJ.java\
        views/ActionLegumePanel.java \
        views/ActionPanel.java \
        views/ActionSolPanel.java \
        listeners/FormulaireListener.java \
        listeners/ActionLegumeListener.java \
        listeners/ActionSolListener.java\
        listeners/AjouterLegumeListener.java \
        views/BarAction.java\
        views/BarParcelle.java \
        listeners/ControleFenetre.java \
        views/CreerBar.java\
        views/Fenetre.java \
        views/JardinBar.java \
        views/JardinCreer.java\
        listeners/JardinCreerListener.java\
        listeners/JardinListener.java\
        views/JardinPanel.java \
        listeners/ListenerAction.java\
        listeners/ListenerMenuBar.java \
        listeners/MenuListener.java\
        views/Menu.java\
        views/MenuBar.java\
        listeners/MoisListener.java\
        views/PanelAjouterLegume.java\
        views/PanelCreerActionLegume.java\
        views/PanelCreerActionSol.java\
        listeners/ReturnActionListener.java\
        listeners/ReturnMenuListener.java\
        listeners/ReturnParcelleListener.java\
        views/ActionGestionnaire.java\
        listeners/ActionModif.java\
        views/ActionModifPanel.java\
        views/ActionParcelle.java\
        listeners/ClicGestionnaire.java\
        listeners/ClicModif.java\
        listeners/ClicParcelle.java\
        views/GestionnaireBouton.java\
        views/GestionnairePanel.java\
        listeners/ModifBouton.java\
        listeners/ModificationParcelle.java\
        views/ModificationParcellePanel.java\
        views/ParcellePanel.java\
        views/BarModification.java\
        views/PanelModification.java\
        Main.java\

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm views/*.class || rm api/*.class || rm listeners/*.class

run:
	java Main
