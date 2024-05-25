package vues.controlleurs;

import cahierIG.Cahier;
import cahierIG.DateCahier;
import cahierIG.NodeImageIG;
import exceptions.CahierException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vues.controlleurs.ControlleurPageDeGarde;
import vues.controlleurs.ControlleurPageJour;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class ControlleurSmallNodeImage {

    @FXML
    ImageView imageView;

    NodeImageIG node;

    public ControlleurSmallNodeImage(NodeImageIG node)
     {
        this.node = node;
     }

    @FXML
    void initialize()
    {
        this.imageView.setImage(node.getImage());
    }

    @FXML
    void changerImage()
    {
        /*TextInputDialog text = new TextInputDialog();
        text.setTitle("Changer texte");
        text.setHeaderText("Que veux-tu écrire à cet emplacement? :)");
        text.setContentText(texte);
        Optional<String> result = text.showAndWait();
        result.ifPresent(e -> {
            this.text.setText(e.toString());
            this.node.setTexte(e.toString());
        });*/
    }
    

}