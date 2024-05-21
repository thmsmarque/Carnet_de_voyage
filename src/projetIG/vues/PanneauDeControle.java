package vues;

import cahierIG.Cahier;
import cahierIG.DateCahier;
import exceptions.CahierException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vues.controlleurs.ControlleurPageDeGarde;
import vues.controlleurs.ControlleurPageJour;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class PanneauDeControle {

    Cahier cahier;
    Stage stage;
    public ControlleurPageJour controlleurPageJour;
    public PanneauDeControle(Cahier c, Stage stage)
    {
        cahier = c;
        this.stage = stage;

    }

    /**
     * Passe la page courante à la page suivante
     */
    public void jourSuivant()
    {
        cahier.jourSuivant();
    }

    /**
     * Passe la page courante du cahier à la page précédente
     */
    public void jourPrecedent()
    {
        cahier.jourPrecedent();
    }

    /**
     * Passe la page courante à celle passée en paramètre
     * @param date page à regarder
     */
    public void choisirPage(DateCahier date)
    {
        try {
            cahier.changerPage(date);
        } catch (CahierException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Change le style du cahier
     */
    public void changerStyle()
    {

    }

    /**
     * Ajoute la node à l'emplacement séléctionné
     * @param type type de la node à ajouter
     */
    public void ajouterNode(String type)
    {

    }

    /**
     * Supprime la node séléctionnée
     */
    public void suppprimerNode()
    {

    }

    public void retourPageDeGarde()
    {
        cahier.setCourante(null);
        final URL url = getClass().getResource("/fxml/PDG_Carnet.fxml");
        // Création du loader.
        final FXMLLoader fxmlLoader = new FXMLLoader(url);


        ControlleurPageDeGarde controlleurPageDeGarde = new ControlleurPageDeGarde(cahier, this);
        ControlleurPageJour controlleurPageJour = new ControlleurPageJour(cahier, this);


        fxmlLoader.setControllerFactory(ic-> {
            if(ic.equals(vues.controlleurs.ControlleurPageDeGarde.class)) return controlleurPageDeGarde;
            else if(ic.equals(vues.controlleurs.ControlleurPageJour.class)) return controlleurPageJour;
            else return null;
        });

        final BorderPane root;
        try {
            root = fxmlLoader.load();
            final Scene scene = new Scene(root, 600, 800);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        cahier.notifierObservateurs();
    }

    /**
     * Si elle n'existe pas, ajoute une nouvelle date
     */
    public void ajouterPage(DateCahier date, String titre) throws CahierException {
        cahier.ajouterPage(date,titre);
        chargerPageActuelle();
    }

    public void chargerPageActuelle()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/PageJour.fxml"));
        loader.setControllerFactory(ic -> {
            if (ic.equals(vues.controlleurs.ControlleurPageJour.class)) return controlleurPageJour;
            return null;
        });

        final BorderPane root;
        try {
            root = loader.load();
            final Scene scene = new Scene(root, 600, 800);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Ajoute un nouveau participant
     * @param nom nom du participant à ajouter
     */
    public void ajouterParticipant(String nom)
    {
        cahier.ajouterParticipant(nom);
    }

}
