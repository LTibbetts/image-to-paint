package image2paint.alterations;

public class StrokeProperties {
  private int strokeLength;
  private int strokeWidth;
  private int strokeAngle;

  /** Empty Constructor: Creates a stoke property equivalent to a point */
  public StrokeProperties() {
    this(0, 0, 0);
  }

  /** Constructor for defined properties */
  public StrokeProperties(int strokeLength, int strokeWidth, int strokeAngle) {
    this.strokeLength = strokeLength;
    this.strokeWidth = strokeWidth;
    this.strokeAngle = strokeAngle;
  }

  ////////////////////
  // Getters Setters
  ////////////////////

  public int getStrokeLength() {
    return this.strokeLength;
  }

  public int getStrokeWidth() {
    return this.strokeWidth;
  }

  public int getStrokeAngle() {
    return this.strokeAngle;
  }

  public void setStrokeLength(int value) {
    this.strokeLength = value;
  }

  public void setStrokeWidth(int value) {
    this.strokeWidth = value;
  }

  public void setStrokeAngle(int value) {
    this.strokeAngle = value;
  }

}