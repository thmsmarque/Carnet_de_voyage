package vues;

import cahierIG.Cahier;
import javafx.scene.layout.BorderPane;

public class VueCahierIG extends BorderPane implements Observateur {

    Cahier cahier;
    public VueCahierIG(Cahier cahier)
    {
        this.cahier = cahier;
    }
    @Override
    public void reagir() {

    }
}
