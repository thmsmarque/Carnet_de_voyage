package vues;

import cahierIG.Cahier;
import cahierIG.DateCahier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.CahierException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vues.controlleurs.ControlleurPageDeGarde;
import vues.controlleurs.ControlleurPageJour;

import java.io.*;
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
        ControlleurPageJour controlleurPageJour = new ControlleurPageJour(cahier, this,stage);


        fxmlLoader.setControllerFactory(ic-> {
            if(ic.equals(vues.controlleurs.ControlleurPageDeGarde.class)) return controlleurPageDeGarde;
            else if(ic.equals(vues.controlleurs.ControlleurPageJour.class)) return controlleurPageJour;
            else return null;
        });


        final BorderPane root;
        try {
            root = fxmlLoader.load();
            final Scene scene = new Scene(root, 600, 700);
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
            final Scene scene = new Scene(root, 600, 700);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Méthode qui permet de retirer un participant de la liste
     * @param id participant à retirer
     * @throws CahierException vérifie que le participant est bien présent dans la liste
     */
    public void supprimerParticipant(String id) throws CahierException
    {
        if(!cahier.getParticipants().contains(id))
        {
            throw new CahierException("Vous tentez de supprimer un participant " + id + " non présent dans la liste");
        }else
        {
            cahier.getParticipants().remove(id);
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

    public void sauvegarderMonde()
    {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();;

        File file;

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showSaveDialog(stage);


        try
        {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter ecrire = new BufferedWriter(fileWriter);

                ecrire.write(gson.toJson(cahier));

            ecrire.newLine();


            ecrire.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void chargerMonde()
    {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();;

        File file;

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showSaveDialog(stage);

        try
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader lecture = new BufferedReader(fileReader);

            lecture.read(gson());



            lecture.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
