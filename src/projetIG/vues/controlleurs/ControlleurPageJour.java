package vues.controlleurs;

import cahierIG.Cahier;
import cahierIG.NodeIG;
import cahierIG.PageJourIG;
import exceptions.CahierException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import vues.Observateur;
import vues.PanneauDeControle;

public class ControlleurPageJour implements Observateur {

    @FXML
    private Label date1;

    @FXML
    private Label date2;

    @FXML
    private Pane largeNode;

    @FXML
    private Pane smallNode1;

    @FXML
    private Pane smallNode2;

    @FXML
    private Label titrePage;

    @FXML
    private Button tournerDroite;

    @FXML
    private Button tournerGauche;

    Pane nodeSelected;
    Cahier cahier;
    PanneauDeControle panneauDeControle;

    public ControlleurPageJour(Cahier c, PanneauDeControle panneauDeControle) {
        this.cahier = c;
        this.panneauDeControle = panneauDeControle;
        panneauDeControle.controlleurPageJour=this;
        c.ajouterObservateur(this);
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        System.out.println("Initialisation...");
        PageJourIG page = null;
        try {
            page = (PageJourIG)cahier.getPage(cahier.getCourante());
            System.out.println(page);
            date1.setText(page.getDate().toString());
            date2.setText(page.getDate().format2());
            titrePage.setText(page.getTitre());

            this.largeNode.getChildren().clear();
            this.smallNode1.getChildren().clear();
            this.smallNode2.getChildren().clear();



            page.setNodeSelectionnee(page.getLargeNode());

        } catch (CahierException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void ajouterGPS(ActionEvent event) {

    }

    @FXML
    void ajouterPhoto(ActionEvent event) {

    }

    @FXML
    void ajouterTexte(ActionEvent event) {

    }

    @FXML
    void changerStylePage(ActionEvent event) {

    }

    @FXML
    void pagePrecedente(ActionEvent event) {
        panneauDeControle.jourPrecedent();
        panneauDeControle.chargerPageActuelle();
        cahier.notifierObservateurs();
    }

    @FXML
    void pageSuivante(ActionEvent event) {
        panneauDeControle.jourSuivant();
        panneauDeControle.chargerPageActuelle();

        cahier.notifierObservateurs();

    }

    @FXML
    void selectionnerNode(MouseEvent event)
    {
        System.out.println(event.getSource().toString());

        if(event.getSource() == nodeSelected)
        {
            nodeSelected = null;
        }else
            nodeSelected = (Pane)event.getSource();

        System.out.println("La node séléctionnée : " + nodeSelected);
        cahier.notifierObservateurs();
    }

    @Override
    public void reagir() {
        PageJourIG page = null;

            page = cahier.getPageCourante();
            System.out.println(page.toString());
            date1.setText(page.getDate().toString());
            date2.setText(page.getDate().format2());
            titrePage.setText(page.getTitre());

            largeNode.setStyle("-fx-background-color: #C7DCD5");
            smallNode1.setStyle("-fx-background-color: #C7DCD5");
            smallNode2.setStyle("-fx-background-color: #C7DCD5");

            if(nodeSelected != null)
            {
               nodeSelected.setStyle("-fx-background-color: #a5c589");
            }

    }
}