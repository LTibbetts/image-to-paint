package image2paint.alterations;

import java.util.ArrayList;

public class Stroke {
  private int x;
  private int y;
  private StrokeProperties strokeProperties;

  /** Constructor for a point */
  public Stroke(int x, int y) {
    this(x, y, new StrokeProperties(10, 10, 90));
  }

  /** Constructor for a different stroke */
  public Stroke(int x, int y, StrokeProperties strokeProperties) {
    this.x = x;
    this.y = y;
    this.strokeProperties = strokeProperties;
  }

  ///////////////////
  // Public Methods
  ///////////////////
  public void getPointsForStroke(ArrayList<Integer> xPoints, ArrayList<Integer> yPoints) {
    int d = 0;

    int x1 = x + (int) ((strokeProperties.getStrokeLength() / 2)
        * Math.cos(Math.toRadians(-strokeProperties.getStrokeAngle())));
    int x2 = x - (int) ((strokeProperties.getStrokeLength() / 2)
        * Math.cos(Math.toRadians(-strokeProperties.getStrokeAngle())));
    int y1 = y + (int) ((strokeProperties.getStrokeLength() / 2)
        * Math.sin(Math.toRadians(-strokeProperties.getStrokeAngle())));
    int y2 = y - (int) ((strokeProperties.getStrokeLength() / 2)
        * Math.sin(Math.toRadians(-strokeProperties.getStrokeAngle())));

    int dx = Math.abs(x1 - x2);
    int dy = Math.abs(y1 - y2);

    int dx2 = 2 * dx;
    int dy2 = 2 * dy;

    int ix = x1 < x2 ? 1 : -1;
    int iy = y1 < y2 ? 1 : -1;

    int xPoint = x1;
    int yPoint = y1;

    if (dx >= dy) {
      while (true) {
        getCirclePoints(xPoint, yPoint, xPoints, yPoints);
        if (xPoint == x2) {
          break;
        }
        xPoint += ix;
        d += dy2;
        if (d > dx) {
          yPoint += iy;
          d -= dx2;
        }
      }
    } else {
      while (true) {
        getCirclePoints(xPoint, yPoint, xPoints, yPoints);
        if (yPoint == y2) {
          break;
        }
        yPoint += iy;
        d += dx2;
        if (d > dy) {
          x += ix;
          d -= dy2;
        }
      }
    }
  }

  public void getCirclePoints(int xPoint, int yPoint, ArrayList<Integer> xPoints, ArrayList<Integer> yPoints){
    int strokeWidth = strokeProperties.getStrokeWidth() / 2;
    for(int deg = 0; deg < 360; ++deg){
      xPoints.add(xPoint + (int)(strokeWidth * Math.cos(Math.toRadians(deg))));
      yPoints.add(yPoint + (int)(strokeWidth * Math.sin(Math.toRadians(deg))));
    }
  } 

  /////////////////////
  // Gettters Setters
  /////////////////////

  public int getX() {
    return this.x;
  }

  public void setX(int value) {
    this.x = value;
  }

  public int getY() {
    return this.y;
  }

  public void setY(int value) {
    this.y = value;
  }

  public StrokeProperties getStrokeProperties() {
    return this.strokeProperties;
  }
}