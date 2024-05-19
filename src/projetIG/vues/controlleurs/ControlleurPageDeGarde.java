package vues.controlleurs;

import cahierIG.Cahier;
import cahierIG.DateCahier;
import exceptions.CahierException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vues.Observateur;
import vues.PanneauDeControle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControlleurPageDeGarde implements Observateur{

    @FXML
    private Button pageAleatoireButton;

    @FXML
    private Button pdjButton;

    @FXML
    private Label plageDate;

    Cahier c;
    PanneauDeControle p;

    public ControlleurPageDeGarde(Cahier c, PanneauDeControle p)
    {
        this.c = c;
        this.p = p;
        c.ajouterObservateur(this);
    }

    @FXML
    void afficherPageDuJour(ActionEvent event) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);

        DateCahier date = new DateCahier(formattedDate);

        if(c.estDejaDansCahier(date))
        {
            System.out.println("Est déjà dans le cahier");
            p.choisirPage(date);
        }else
        {
            System.out.println("N'était pas encore dans le cahier");
            try {
                p.ajouterPage(date,"Nouvelle Page");
            } catch (CahierException e) {
                throw new RuntimeException(e);
            }
        }

        c.notifierObservateurs();
    }

    @FXML
    void afficherUnePageAleatoire(ActionEvent event) {

    }

    @Override
    public void reagir() {
        plageDate.setText(c.getMinimum().toString() + "-" + c.getMaximum().toString());
    }
}
