/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 02/07/2026
* Ultima alteracao.: 10/07/2026
* Nome.............: TrafficControl.java
* Funcao...........: Compartilhar os semaforos com os veiculos da simulacao.
*************************************************************** */

package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Semaphore;

import javafx.scene.Node;
import javafx.scene.shape.Polygon;

public class TrafficControl {
  private ArrayList<ArrayList<Point>> routes = new ArrayList<>();

  public TrafficControl(Set<Node> polygons) {
    for (Node polygonNode : polygons) {
      Polygon polygon = (Polygon) polygonNode;
      int polygonId = Integer.parseInt(polygon.getUserData().toString());

      routes.add(polygonId, new ArrayList<>());

      for (int i = 0; i < polygon.getPoints().size(); i += 2) {
        routes.get(polygonId)
            .add(new Point(new double[] { polygon.getPoints().get(i),
                polygon.getPoints().get(i + 1) }));
      }
    }

    loadSemaphores();
  }

  private void loadSemaphores() {
    Properties properties = new Properties();
    HashMap<String, Semaphore> semaphores = new HashMap<>();

    try (FileInputStream fis = new FileInputStream("semaphores.properties")) {
      properties.load(fis);
    } catch (IOException ioe) {
      System.out.println("Semaphores file not found.");
      return;
    }

    for (String key : properties.stringPropertyNames()) {
      Semaphore semaphore = semaphores.getOrDefault(properties.getProperty(key), new Semaphore(1));
      semaphores.putIfAbsent(properties.getProperty(key), semaphore);

      if (key.startsWith("default")) {
        semaphore.tryAcquire();
        continue;
      }

      String[] keyStrings = key.split("\\.");

      if (keyStrings[2].equals("in")) {
        routes.get(Integer.parseInt(keyStrings[0])).get(Integer.parseInt(keyStrings[1])).getIn().add(semaphore);
      } else {
        routes.get(Integer.parseInt(keyStrings[0])).get(Integer.parseInt(keyStrings[1])).getOut().add(semaphore);
      }
    }
  }

  public ArrayList<Point> getRoute(int idx) {
    return routes.get(idx);
  }
}
