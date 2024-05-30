package vues.controlleurs;

import cahierIG.Cahier;
import cahierIG.DateCahier;
import cahierIG.NodeTexteIG;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.CahierException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import vues.Observateur;
import vues.PanneauDeControle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class ControlleurPageDeGarde implements Observateur{

    @FXML
    private MenuItem ajouterParticipantButton;

    @FXML
    private MenuItem chargerButton;

    @FXML
    private ListView<String> listeParticipants;

    ObservableList<String> items;


    @FXML
    private Button pageAleatoireButton;

    @FXML
    private Button pdjButton;

    @FXML
    private Text plageDate;

    @FXML
    private MenuItem sauvegarderButton;
    @FXML
    private MenuItem supprimerParticipants;


    Cahier c;
    PanneauDeControle p;

    public ControlleurPageDeGarde(){

    }

    public ControlleurPageDeGarde(Cahier c, PanneauDeControle p)
    {

        this.c = c;
        this.p = p;
        c.ajouterObservateur(this);
    }

    public void initData(Cahier c, PanneauDeControle p) {
        this.c = c;
        this.p = p;
        c.ajouterObservateur(this);
        items = FXCollections.observableArrayList();
    }

    @FXML
    void initialize() {
    }

    @FXML
    void afficherPageDuJour(ActionEvent event) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);

        DateCahier date = new DateCahier(formattedDate);

        if(c.estDejaDansCahier(date))
        {
            //System.out.println("Est déjà dans le cahier");
            p.choisirPage(date);
        }else
        {
            //System.out.println("N'était pas encore dans le cahier");
            try {
                p.ajouterPage(date,"Nouvelle Page");
            } catch (CahierException e) {
                throw new RuntimeException(e);
            }
        }
        p.chargerPageActuelle();
        c.notifierObservateurs();
    }

    @FXML
    void afficherUnePageAleatoire(ActionEvent event){

    }

    @FXML
    void ajouterParticipant(ActionEvent event) {
        TextInputDialog text = new TextInputDialog();
        text.setTitle("Nouveau participant");
        text.setHeaderText("Qui vas-tu ajouter parmi les participants? :)");
        text.setContentText("");
        Optional<String> result = text.showAndWait();
        result.ifPresent(e -> {
            c.ajouterParticipant(e);
        });

        c.notifierObservateurs();
    }

    @FXML
    void chargerCarnet(ActionEvent event) {
        p.chargerMonde();
    }

    @FXML
    void fermerCarnet(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void sauvegargerCarnet(ActionEvent event) {
        try {
            p.sauvegarderMonde();
        } catch (CahierException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void supprimerParticipants(ActionEvent event) {
        ObservableList<String> selected = listeParticipants.getSelectionModel().getSelectedItems();

        for(String g : selected)
        {
            try {
                p.supprimerParticipant(g);
            } catch (CahierException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la suppression d'un participant!");
                alert.setContentText(e.toString());
                alert.show();
                PauseTransition p = new PauseTransition(Duration.seconds(5));
                p.setOnFinished(ev-> alert.close());

                p.play();

                throw new RuntimeException(e);
            }
        }

        c.notifierObservateurs();

    }

    @Override
    public void reagir() {

        if(c.getMinimum() != null && c.getMaximum() != null)
            plageDate.setText(c.getMinimum().toString() + " - " + c.getMaximum().toString());

        listeParticipants.getItems().clear();
        c.getParticipants().
                forEach(c->listeParticipants.getItems().add(c));




    }
}
