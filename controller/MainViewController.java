/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 03/07/2026
* Ultima alteracao.: 03/07/2026
* Nome.............: MainViewController.java
* Funcao...........: Definir o comportamento da tela principal.
*************************************************************** */

package controller;

import java.io.IOException;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

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

    StackPane container = new StackPane(group);
    container.getStyleClass().add("container");

    Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    double scale = Math.min(screen.getWidth() / 1920, screen.getHeight() / 1080);

    group.setScaleX(scale);
    group.setScaleY(scale);

    Scene scene = ((Button) event.getSource()).getScene();

    scene.setRoot(container);
  }
}
