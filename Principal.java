/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 02/07/2026
* Ultima alteracao.: 08/07/2026
* Nome.............: Principal.java
* Funcao...........: Iniciar a aplicacao e configurar a interface JavaFX.
*************************************************************** */

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import controller.HomeViewController;
import controller.MainViewController;

@SuppressWarnings("unused")
public class Principal extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent homeView = FXMLLoader.load(getClass().getResource("view/HomeView.fxml"));
    Group group = new Group(homeView);
    StackPane root = new StackPane(group);

    root.getStyleClass().add("container");

    Scene scene = new Scene(root);

    scene.getStylesheets().add("css/style.css");

    Scale scale = new Scale();

    scale.xProperty().bind(Bindings.min(
        scene.widthProperty().divide(1920),
        scene.heightProperty().divide(1080)));
    scale.yProperty().bind(scale.xProperty());

    homeView.getTransforms().add(scale);

    primaryStage.setTitle("Underground 2");
    primaryStage.setScene(scene);
    primaryStage.setMaximized(true);
    primaryStage.show();
  }
}
