import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainCahier extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Cahier de voyages");
        BorderPane root = FXMLLoader.load(getClass().getResource("PDG_Carnet.fxml"));
        stage.setScene(new Scene(root, 600,800 ));

        stage.show();
    }
}
