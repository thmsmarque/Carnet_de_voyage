package vues;

import cahierIG.Cahier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurModeEditionLecture extends PanneauDeControle implements EventHandler<ActionEvent> {

    Cahier c;

    public EcouteurModeEditionLecture(Cahier c) {
        super(c);
        this.c = c;
    }


    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
