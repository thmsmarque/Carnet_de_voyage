package vues;

import cahierIG.Cahier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class EcouteurAjoutNodeImage extends PanneauDeControle implements EventHandler<ActionEvent> {

    Cahier cahier;

    public EcouteurAjoutNodeImage(Cahier c) {
        super(c);
        cahier = c;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
