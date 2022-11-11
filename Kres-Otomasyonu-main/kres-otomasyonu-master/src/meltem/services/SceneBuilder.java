package meltem.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import meltem.enums.LogType;
import meltem.models.RouteData;
import meltem.services.logging.Logger;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class SceneBuilder<T> {
    public T param;
    public static RouteData routeData = new RouteData();
    public static SceneBuilder Instance;
    public static String modalWarning = "";
    static Stage PrimaryStage;
    private String getSceneName(String sceneName) {
        // return sceneName.getClass().getSimpleName().toLowerCase();
        return sceneName;
    }

    public SceneBuilder(Stage primaryStage) {
        if(SceneBuilder.Instance == null) {
            Instance = this;
        }
        if(SceneBuilder.PrimaryStage == null) {
            SceneBuilder.PrimaryStage = primaryStage;
        }
    }

    public void BuildModal(String sceneName, ActionEvent event) {
        try {
            routeData = null;
            Logger.Log(LogType.Debug, String.format("/meltem/scenes/%s.fxml", getSceneName(sceneName)));
            Stage modalStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(String.format("meltem/scenes/%s.fxml", getSceneName(sceneName)))));
            modalStage.setScene(new Scene(root));
            modalStage.setTitle("Kreş Otomasyonu");
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(
                    ((Node)event.getSource()).getScene().getWindow()
            );
            // modalStage.getIcons().add(new Image("@../icons/lock.png"));
            modalStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void BuildWarningModal(String warning, ActionEvent event) {
        try {
            routeData = null;
            modalWarning = warning;
            Logger.Log(LogType.Debug,"/meltem/scenes/modal_invalid_input.fxml");
            Stage modalStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("meltem/scenes/modal_invalid_input.fxml")));
            modalStage.setScene(new Scene(root));
            modalStage.setTitle("Uyarı");
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(
                    ((Node)event.getSource()).getScene().getWindow()
            );
            // modalStage.getIcons().add(new Image("@../icons/lock.png"));
            modalStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void BuildScene(String sceneName)  {
        try {
            routeData = null;
            Logger.Log(LogType.Debug, String.format("/meltem/scenes/%s.fxml", getSceneName(sceneName)));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(String.format("meltem/scenes/%s.fxml", getSceneName(sceneName)))));
            PrimaryStage.setTitle("Kres Otomasyonu");
            Scene scene = new Scene(root, 1200, 840);
            scene.getStylesheets().add("meltem/styles/main.css");
            scene.getStylesheets().add(
                    String.format("meltem/styles/%s.css", sceneName)
            );
            for(String s :scene.getStylesheets()) {
                Logger.Log(LogType.Debug, String.format("Stylesheet %s sayfa ile birlikte yuklendi.", s));
            }
            PrimaryStage.setScene(scene);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void BuildScene(String sceneName, RouteData data) throws IOException {
        routeData = data;
        Logger.Log(LogType.Debug, String.format("./scenes/%s.fxml", getSceneName(sceneName)));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(String.format("meltem/scenes/%s.fxml", getSceneName(sceneName)))));
        PrimaryStage.setTitle("Kres Otomasyonu");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("meltem/styles/main.css");
        scene.getStylesheets().add(
                String.format("meltem/styles/%s.css", sceneName)
        );
        for(String s :scene.getStylesheets()) {
            Logger.Log(LogType.Debug, String.format("Stylesheet %s sayfa ile birlikte yuklendi.", s));
        }
        PrimaryStage.setScene(scene);
    }
    public void BuildSceneWithParams(String sceneName, T data) {
        try {
            Logger.Log(LogType.Debug, String.format("/meltem/scenes/%s.fxml", getSceneName(sceneName)));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(String.format("meltem/scenes/%s.fxml", getSceneName(sceneName)))));
            PrimaryStage.setTitle("Kres Otomasyonu");
            Scene scene = new Scene(root, 1200, 840);
            scene.getStylesheets().add("meltem/styles/main.css");
            scene.getStylesheets().add(
                    String.format("meltem/styles/%s.css", sceneName)
            );
            for(String s :scene.getStylesheets()) {
                Logger.Log(LogType.Debug, String.format("Stylesheet %s sayfa ile birlikte yuklendi.", s));
            }
            this.param = data;
            PrimaryStage.setScene(scene);
        }
        catch(Exception ex) {
            Logger.LogError(ex.getMessage());
        }
    }
}
