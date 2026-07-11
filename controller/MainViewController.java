/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 03/07/2026
* Ultima alteracao.: 09/07/2026
* Nome.............: MainViewController.java
* Funcao...........: Definir o comportamento da tela principal.
*************************************************************** */

package controller;

import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import model.TrafficControl;
import model.Vehicle;

public class MainViewController {
  @FXML
  private Parent root;
  private ArrayList<Vehicle> vehicles = new ArrayList<>();
  private Animation animation;

  @FXML
  private void initialize() {
    Set<Node> vehiclesNodes = root.lookupAll(".vehicle");
    Set<Node> polygonsNodes = root.lookupAll(".route-polygon");
    Set<Node> buttonsNodes = root.lookupAll(".control-button");
    Set<Node> slidersNodes = root.lookupAll(".slider");

    for (Node vehicleNode : vehiclesNodes) {
      ImageView vehicleView = (ImageView) vehicleNode;

      vehicleView.setTranslateX(-vehicleView.getBoundsInLocal().getWidth() / 2);
      vehicleView.setTranslateY(-vehicleView.getBoundsInLocal().getHeight() / 2);
    }

    TrafficControl trafficControl = new TrafficControl(polygonsNodes);

    for (int i = 0; i < vehiclesNodes.size(); i++) {
      vehicles.add(i, new Vehicle(trafficControl.getRoute(i), 5));
      vehicles.get(i).setDaemon(true);
      vehicles.get(i).start();
    }

    setShowRouteButtonsHandlers(buttonsNodes, polygonsNodes);
    setPlayPauseButtonsHandlers(buttonsNodes, vehicles);
    setSpeedSlidersHandlers(slidersNodes, vehicles);

    animation = new Animation(vehiclesNodes, vehicles);
    animation.start();
  }

  private void setShowRouteButtonsHandlers(Set<Node> buttonsNodes, Set<Node> polygonsNodes) {
    for (Node buttonNode : buttonsNodes) {
      if (buttonNode.getUserData().toString().startsWith("sr")) {
        Button button = (Button) buttonNode;

        for (Node polygonNode : polygonsNodes) {
          if (polygonNode.getUserData().toString()
              .endsWith(button.getUserData().toString()
                  .substring(2))) {
            button.setOnMousePressed(event -> {
              polygonNode.setVisible(true);
            });

            button.setOnMouseReleased(event -> {
              polygonNode.setVisible(false);
            });
          }
        }
      }
    }
  }

  private void setPlayPauseButtonsHandlers(Set<Node> buttonsNodes, ArrayList<Vehicle> vehicles) {
    for (Node buttonNode : buttonsNodes) {
      if (buttonNode.getUserData().toString().startsWith("pp")) {
        Button button = (Button) buttonNode;

        button.setOnAction(event -> {
          if (button.getId().equals("pause-button")) {
            button.setId("play-button");
          } else {
            button.setId("pause-button");
          }

          vehicles.get(Integer.parseInt(button.getUserData().toString().substring(2))).toggleRunning();
        });
      }
    }
  }

  private void setSpeedSlidersHandlers(Set<Node> slidersNodes, ArrayList<Vehicle> vehicles) {
    for (Node sliderNode : slidersNodes) {
      Slider slider = (Slider) sliderNode;

      slider.valueProperty().addListener((observable, oldValue, newValue) -> {
        vehicles.get(Integer.parseInt(slider.getUserData().toString())).setSpeed(newValue.doubleValue());
      });
    }
  }

  @FXML
  private void handleResetButton(ActionEvent event) throws IOException {
    Parent homeView = FXMLLoader.load(getClass().getResource("../view/HomeView.fxml"));
    Group group = new Group(homeView);
    StackPane root = new StackPane(group);

    root.getStyleClass().add("container");

    Scene scene = ((Button) event.getSource()).getScene();

    animation.stop();

    for (Vehicle vehicle : vehicles) {
      vehicle.interrupt();
    }

    scene.setRoot(root);

    Scale scale = new Scale();

    scale.xProperty().bind(Bindings.min(
        scene.widthProperty().divide(1920),
        scene.heightProperty().divide(1080)));
    scale.yProperty().bind(scale.xProperty());

    homeView.getTransforms().add(scale);
  }
}
