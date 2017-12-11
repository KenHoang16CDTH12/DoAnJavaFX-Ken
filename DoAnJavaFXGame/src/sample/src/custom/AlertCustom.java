package sample.src.custom;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class AlertCustom {
    public static Alert showInformation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
        return alert;
    }
    public static Alert showInformationScore(String title, String content, Button btnResult) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
        btnResult.setDisable(true);
        return alert;
    }
    public static Alert showError(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(content);
        alert.show();
        return alert;
    }
}
