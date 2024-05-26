import cahierIG.Cahier;
import cahierIG.DateCahier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vues.PanneauDeControle;
import vues.controlleurs.ControlleurPageDeGarde;
import vues.controlleurs.ControlleurPageJour;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MainCahier extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Cahier cahier = new Cahier();
        PanneauDeControle panneauDeControle = new PanneauDeControle(cahier,stage);
            // Localisation du fichier FXML.
            final URL url = getClass().getResource("/fxml/PDG_Carnet.fxml");
            // Création du loader.
            final FXMLLoader fxmlLoader = new FXMLLoader(url);


        ControlleurPageDeGarde controlleurPageDeGarde = new ControlleurPageDeGarde();
        ControlleurPageJour controlleurPageJour = new ControlleurPageJour(cahier, panneauDeControle,stage);

        controlleurPageDeGarde.initData(cahier, panneauDeControle);

        fxmlLoader.setControllerFactory(ic-> {
            if(ic.equals(vues.controlleurs.ControlleurPageDeGarde.class)) return controlleurPageDeGarde;
            else if(ic.equals(vues.controlleurs.ControlleurPageJour.class)) return controlleurPageJour;
            else return null;
        });


        final BorderPane root = (BorderPane) fxmlLoader.load();
        // Création de la scène.
        final Scene scene = new Scene(root, 600, 700);
        stage.setScene(scene);

        stage.setTitle("Test FXML");
        stage.show();

        stage.show();
    }
}
