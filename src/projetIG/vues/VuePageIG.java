package vues;

import cahierIG.Cahier;
import cahierIG.PageIG;
import javafx.scene.layout.VBox;

public abstract class VuePageIG extends VBox implements Observateur{

    Cahier cahier;
    PageIG page;

    public VuePageIG(Cahier c, PageIG page) {
        cahier = c;
        this.page = page;
    }
}
