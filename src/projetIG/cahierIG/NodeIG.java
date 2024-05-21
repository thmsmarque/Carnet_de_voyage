package cahierIG;

import javafx.beans.binding.BooleanExpression;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class NodeIG extends Pane {
    String identifiant;

    public NodeIG() {

    }

    abstract Boolean estTexte();
    abstract Boolean estImage();
    abstract Boolean estGPS();

}
