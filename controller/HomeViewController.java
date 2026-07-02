/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 02/07/2026
* Ultima alteracao.: 02/07/2026
* Nome.............: HomeViewController.java
* Funcao...........: Definir o comportamento da tela inicial.
*************************************************************** */

package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class HomeViewController {
  @FXML
  private void handleStartButton(ActionEvent event) throws IOException {
    Parent mainView = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));

    Scene scene = ((Button) event.getSource()).getScene();

    scene.setRoot(mainView);
  }
}
