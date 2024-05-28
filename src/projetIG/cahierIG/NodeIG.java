package cahierIG;

import javafx.beans.binding.BooleanExpression;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class NodeIG{
    String identifiant;

    public NodeIG() {

    }

    public abstract Boolean estTexte();
    public abstract Boolean estImage();
    public abstract Boolean estGPS();

}
