import javafx.application.Application;
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
        BorderPane root = new BorderPane();
        stage.setScene(new Scene(root, 540,700 ));

        stage.show();
    }
}
