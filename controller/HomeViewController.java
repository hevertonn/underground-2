/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 02/07/2026
* Ultima alteracao.: 08/07/2026
* Nome.............: HomeViewController.java
* Funcao...........: Definir o comportamento da tela inicial.
*************************************************************** */

package controller;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

public class HomeViewController {
  @FXML
  private void handleStartButton(ActionEvent event) throws IOException {
    Parent mainView = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));
    Group group = new Group(mainView);
    StackPane root = new StackPane(group);

    root.getStyleClass().add("container");

    Scene scene = ((Button) event.getSource()).getScene();

    scene.setRoot(root);

    Scale scale = new Scale();

    scale.xProperty().bind(Bindings.min(
        scene.widthProperty().divide(1920),
        scene.heightProperty().divide(1080)));
    scale.yProperty().bind(scale.xProperty());

    mainView.getTransforms().add(scale);
  }
}
