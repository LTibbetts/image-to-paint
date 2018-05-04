package image2paint.alterations;

public class Stroke {
  private int x;
  private int y;

  /** Constructor for a point */
  public Stroke(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /////////////////////
  // Gettters Setters
  /////////////////////

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}