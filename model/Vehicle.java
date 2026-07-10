/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 02/07/2026
* Ultima alteracao.: 10/07/2026
* Nome.............: Vehicle.java
* Funcao...........: Controlar os veiculos da simulacao.
*************************************************************** */

package model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Vehicle extends Thread {
  private ArrayList<Point> route;
  private double speed;

  private double x;
  private double y;

  private double rotate;

  public Vehicle(ArrayList<Point> route, double speed) {
    this.route = route;
    this.speed = speed;
  }

  @Override
  public void run() {
    Point previousPoint = route.get(0);
    int pointIndex = 1;

    while (!Thread.currentThread().isInterrupted()) {
      Point nextPoint = route.get(pointIndex);
      pointIndex = (pointIndex + 1) % route.size();

      double[] vector = new double[] { nextPoint.getCoordinates()[0] - previousPoint.getCoordinates()[0],
          nextPoint.getCoordinates()[1] - previousPoint.getCoordinates()[1] };

      rotate = Math.toDegrees(Math.atan2(-vector[0], vector[1]));

      double magnitude = Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2));

      double step = speed / magnitude;

      for (double i = 0; i < 1; i += step) {
        x = previousPoint.getCoordinates()[0] + vector[0] * i;
        y = previousPoint.getCoordinates()[1] + vector[1] * i;

        try {
          Thread.sleep(10);
        } catch (InterruptedException ie) {
          return;
        }
      }

      try {
        for (Semaphore s : nextPoint.getIn()) {
          s.acquire();
        }
      } catch (InterruptedException ie) {
        return;
      }

      for (Semaphore s : nextPoint.getOut()) {
        s.release();
      }

      previousPoint = nextPoint;
    }
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getRotate() {
    return rotate;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }
}
