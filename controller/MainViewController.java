/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 03/07/2026
* Ultima alteracao.: 08/07/2026
* Nome.............: MainViewController.java
* Funcao...........: Definir o comportamento da tela principal.
*************************************************************** */

package controller;

import java.io.IOException;
import java.util.Set;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

public class MainViewController {
  @FXML
  private Parent root;

  @FXML
  private void initialize() {
    Pane map = (Pane) root.lookup("#map");
    Set<Node> polygons = root.lookupAll(".route-polygon");
    Set<Node> buttons = root.lookupAll(".control-button");
    Set<Node> sliders = root.lookupAll(".slider");
  }

  @FXML
  private void handleResetButton(ActionEvent event) throws IOException {
    Parent homeView = FXMLLoader.load(getClass().getResource("../view/HomeView.fxml"));
    Group group = new Group(homeView);
    StackPane root = new StackPane(group);

    root.getStyleClass().add("container");

    Scene scene = ((Button) event.getSource()).getScene();

    scene.setRoot(root);

    Scale scale = new Scale();

    scale.xProperty().bind(Bindings.min(
        scene.widthProperty().divide(1920),
        scene.heightProperty().divide(1080)));
    scale.yProperty().bind(scale.xProperty());

    homeView.getTransforms().add(scale);
  }
}
