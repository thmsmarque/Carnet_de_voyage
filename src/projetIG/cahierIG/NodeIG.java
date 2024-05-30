package cahierIG;

import javafx.beans.binding.BooleanExpression;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import outils.FabriqueIdentifiant;

public abstract class NodeIG{

    String identifiant;

    public NodeIG() {
        this.identifiant = FabriqueIdentifiant.getInstance().getIdentifiantNode();
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public abstract Boolean estTexte();
    public abstract Boolean estImage();
    public abstract Boolean estGPS();

}
