package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    /**
     * This will be used as a splash screen for the application It will display some important data to the user and then disappear
     */
    SplashScreenController spashScreen = new SplashScreenController();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainActivity.fxml"));
        Scene scene = new Scene(root,1200,700);
        scene.getStylesheets().add(getClass().getResource("MainActivity.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        //Scene

        //Show the SplashScreen
        spashScreen.showWindow();

        //I am using the code below so the Primary Stage of the application
        //doesn't appear for 2 seconds , so the splash screen is displayed
        PauseTransition splashScreenDelay = new PauseTransition(Duration.seconds(5));
        splashScreenDelay.setOnFinished(f -> {
            primaryStage.show();
            spashScreen.hideWindow();
        });
        splashScreenDelay.playFromStart();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
