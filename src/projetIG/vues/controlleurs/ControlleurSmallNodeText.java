package vues.controlleurs;

import cahierIG.Cahier;
import cahierIG.DateCahier;
import cahierIG.NodeTexteIG;
import exceptions.CahierException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vues.controlleurs.ControlleurPageDeGarde;
import vues.controlleurs.ControlleurPageJour;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;

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
        if(node != null)
            if(node.getTexte() != null)
                this.text.setText(node.getTexte());
            else
                //System.out.println("Le texte est nulle");
        else
            //System.out.println("Node est nulle");
    }

    @FXML
    void changerTexte()
    {
        //System.out.println("Pane pressed");
;        TextInputDialog text = new TextInputDialog();
        text.setTitle("Changer texte");
        text.setHeaderText("Que veux-tu écrire à cet emplacement? :)");
        text.setContentText(node.getTexte());
        Optional<String> result = text.showAndWait();
        result.ifPresent(e -> {
            this.text.setText(e.toString());
            this.node.setTexte(e.toString());
        });
    }
    

}