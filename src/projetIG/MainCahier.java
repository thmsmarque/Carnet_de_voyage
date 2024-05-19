import cahierIG.Cahier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vues.PanneauDeControle;
import vues.controlleurs.ControlleurPageDeGarde;

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
        PanneauDeControle panneauDeControle = new PanneauDeControle(cahier);
            // Localisation du fichier FXML.
            final URL url = getClass().getResource("/fxml/PDG_Carnet.fxml");
            // Création du loader.
            final FXMLLoader fxmlLoader = new FXMLLoader(url);


        ControlleurPageDeGarde controlleurPageDeGarde = new ControlleurPageDeGarde(cahier, panneauDeControle);



        fxmlLoader.setControllerFactory(ic-> {
            if(ic.equals(vues.controlleurs.ControlleurPageDeGarde.class)) return controlleurPageDeGarde;
            else return null;
        });


        final BorderPane root = (BorderPane) fxmlLoader.load();
        // Création de la scène.
        final Scene scene = new Scene(root, 600, 800);
        stage.setScene(scene);

        stage.setTitle("Test FXML");
        stage.show();

        stage.show();
    }
}
