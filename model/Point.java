/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 02/07/2026
* Ultima alteracao.: 10/07/2026
* Nome.............: Point.java
* Funcao...........: Agrupar coordenadas e seus respectivos semaforos.
*************************************************************** */

package model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Point {
  private double[] coordinates = new double[2];
  private ArrayList<Semaphore> in = new ArrayList<>();
  private ArrayList<Semaphore> out = new ArrayList<>();

  public Point(double[] coordinates) {
    this.coordinates = coordinates;
  }

  public double[] getCoordinates() {
    return coordinates;
  }

  public ArrayList<Semaphore> getIn() {
    return in;
  }

  public ArrayList<Semaphore> getOut() {
    return out;
  }
}
