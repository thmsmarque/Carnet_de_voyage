package vues;

import cahierIG.Cahier;
import cahierIG.PageIG;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class VuePageDeGardeIG{

    @FXML
    private Button pdjButton;
    @FXML
    private Button pageAleatoireButton;

    private Cahier cahier;

    VuePageDeGardeIG(Cahier c){
        this.cahier = c;
    }

    @FXML
    private void allerAPageDuJour()
    {
        PanneauDeControle pdc = new PanneauDeControle(cahier);
    }


}
