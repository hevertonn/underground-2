/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 02/07/2026
* Ultima alteracao.: 02/07/2026
* Nome.............: Principal.java
* Funcao...........: Iniciar a aplicacao e configurar a interface JavaFX.
*************************************************************** */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import controller.HomeViewController;
import controller.MainViewController;

@SuppressWarnings("unused")
public class Principal extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent homeView = FXMLLoader.load(getClass().getResource("view/HomeView.fxml"));
    Group group = new Group(homeView);

    StackPane container = new StackPane(group);
    container.getStyleClass().add("container");

    Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    double scale = Math.min(screen.getWidth() / 1920, screen.getHeight() / 1080);

    group.setScaleX(scale);
    group.setScaleY(scale);

    Scene scene = new Scene(container, screen.getWidth(), screen.getHeight());
    scene.getStylesheets().add("css/style.css");

    primaryStage.setTitle("Underground 2");

    primaryStage.setFullScreen(true);
    primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
