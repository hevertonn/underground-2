/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 10/07/2026
* Ultima alteracao.: 10/07/2026
* Nome.............: Animation.java
* Funcao...........: Controlar as animacoes da tela principal.
*************************************************************** */

package controller;

import java.util.ArrayList;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import model.Vehicle;

public class Animation extends AnimationTimer {
  private Set<Node> vehiclesNodes;
  private ArrayList<Vehicle> vehicles;

  public Animation(Set<Node> vehiclesNodes, ArrayList<Vehicle> vehicles) {
    this.vehiclesNodes = vehiclesNodes;
    this.vehicles = vehicles;
  }

  @Override
  public void handle(long now) {
    for (Node vehicleNode : vehiclesNodes) {
      ImageView vehicleView = (ImageView) vehicleNode;
      Vehicle vehicle = vehicles.get(Integer.parseInt(vehicleView.getUserData().toString()));

      vehicleView.setLayoutX(vehicle.getX());
      vehicleView.setLayoutY(vehicle.getY());
      vehicleView.setRotate(vehicle.getRotate());
    }
  }
}
