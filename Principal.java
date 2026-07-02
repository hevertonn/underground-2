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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import controller.HomeViewController;

@SuppressWarnings("unused")
public class Principal extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent homeView = FXMLLoader.load(getClass().getResource("view/HomeView.fxml"));

    Scene scene = new Scene(homeView);
    scene.getStylesheets().add("css/style.css");

    primaryStage.setTitle("Underground 2");

    primaryStage.setMaximized(true);
    primaryStage.setResizable(false);
    primaryStage.setMaxWidth(1920);
    primaryStage.setMaxHeight(1080);

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
