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

public class ControlleurSmallNodeText {

    @FXML
    Text text;

    NodeTexteIG node;

    public ControlleurSmallNodeText(NodeTexteIG node)
     {
        this.node = node;
     }

    @FXML
    void initialize()
    {
        this.text.setText(node.getTexte());
    }

    @FXML
    void changerTexte()
    {
        TextInputDialog text = new TextInputDialog();
        text.setTitle("Changer texte");
        text.setHeaderText("Que veux-tu écrire à cet emplacement? :)");
        text.setContentText(texte);
        Optional<String> result = text.showAndWait();
        result.ifPresent(e -> {
            this.text.setText(e.toString());
            this.node.setTexte(e.toString());
        });
    }
    

}