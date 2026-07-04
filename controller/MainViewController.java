/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 03/07/2026
* Ultima alteracao.: 03/07/2026
* Nome.............: MainViewController.java
* Funcao...........: Definir o comportamento da tela principal.
*************************************************************** */

package controller;

import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;

public class MainViewController {
  @FXML
  private Parent root;

  @FXML
  private void initialize() {
    Set<Node> buttons = root.lookupAll(".control-button");
    Set<Node> sliders = root.lookupAll(".slider");
  }
}
