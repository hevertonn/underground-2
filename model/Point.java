/* ***************************************************************
* Autor............: Heverton dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 02/07/2026
* Ultima alteracao.: 10/07/2026
* Nome.............: Point.java
* Funcao...........: Agrupar coordenadas e seus respectivos semaforos.
*************************************************************** */

package model;

import java.util.concurrent.Semaphore;

public class Point {
  private double[] coordinates = new double[2];
  private Semaphore[] in = new Semaphore[0];
  private Semaphore[] out = new Semaphore[0];

  public Point(double[] coordinates) {
    this.coordinates = coordinates;
  }

  public double[] getCoordinates() {
    return coordinates;
  }

  public Semaphore[] getIn() {
    return in;
  }

  public Semaphore[] getOut() {
    return out;
  }

  public void setIn(Semaphore... in) {
    this.in = in;
  }

  public void setOut(Semaphore... out) {
    this.out = out;
  }
}
