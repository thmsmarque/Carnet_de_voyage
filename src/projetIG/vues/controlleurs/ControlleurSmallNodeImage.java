package vues.controlleurs;

import cahierIG.Cahier;
import cahierIG.DateCahier;
import cahierIG.NodeImageIG;
import exceptions.CahierException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vues.controlleurs.ControlleurPageDeGarde;
import vues.controlleurs.ControlleurPageJour;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class ControlleurSmallNodeImage {

    @FXML
    ImageView imageView;

    Stage stage;
    NodeImageIG node;
    Cahier cahier;

    public ControlleurSmallNodeImage(NodeImageIG node, Stage stage)
     {
        this.node = node;
        this.stage = stage;
     }

    @FXML
    void initialize()
    {
        this.imageView.setImage(node.getImage());
    }

    @FXML
    void modifierImage()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            node.setImage(image);
        }
    }
    

}