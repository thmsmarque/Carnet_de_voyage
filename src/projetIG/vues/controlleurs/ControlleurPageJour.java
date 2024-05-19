package vues.controlleurs;

import cahierIG.Cahier;
import cahierIG.PageJourIG;
import exceptions.CahierException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import vues.PanneauDeControle;

public class ControlleurPageJour {

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

    Cahier cahier;
    PanneauDeControle panneauDeControle;

    public ControlleurPageJour(Cahier c, PanneauDeControle panneauDeControle) {
        this.cahier = c;
        this.panneauDeControle = panneauDeControle;
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        PageJourIG page = null;
        try {
            page =(PageJourIG)cahier.getPage(cahier.getCourante());
            titrePage.setText(page.getTitre());
            description.setText(page.getDescription());
            photo.setImage(new Image(page.getPhoto()));
            numero.setText(page.getNumeroPage() + "/" + carnet.getNbPagesJour());
            setMap(page.getX(), page.getY());
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

    }

    @FXML
    void pageSuivante(ActionEvent event) {

    }

}