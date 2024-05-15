package exceptions;

import javafx.scene.control.Alert;

public class CahierException extends Exception{
    public CahierException(String msg){
        super(msg);
        // Créer une boîte de dialogue d'information
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur!");
        alert.setHeaderText(null); // Pas de texte d'en-tête
        alert.setContentText(msg);
        // Afficher la boîte de dialogue
        alert.showAndWait();
    }

}
