package image2paint.controller;

import java.util.ArrayList;

import image2paint.alterations.Stroke;
import image2paint.alterations.StrokeProperties;
import image2paint.image.MyImage;

public class ImageController {
  /** Source MyImage */
  private MyImage sourceImage;

  /** Destination MyImage */
  private MyImage destinationImage;

  /** Current Stroke for the painting */
  private Stroke currentStroke;

  /** ArrayList for stored changes */
  private ArrayList<Stroke> strokeList;

  public ImageController(MyImage sourceImage, MyImage destinationImage, Stroke currentStroke) {
    this.sourceImage = sourceImage;
    this.destinationImage = destinationImage;
    this.currentStroke = currentStroke;
    this.strokeList = new ArrayList<Stroke>();
  }

  ///////////////////
  // Public Methods
  ///////////////////

  public void addStroke(int x, int y) {
    currentStroke.setX(x);
    currentStroke.setY(y);
    strokeList.add(currentStroke);
    updateDestination();
  }

  ////////////////////
  // Private Methods
  ////////////////////

  private void updateDestination() {
    if (!strokeList.isEmpty()) {
      int x = currentStroke.getX();
      int y = currentStroke.getY();
      // StrokeProperties lastStrokeProperties = lastStroke.getStrokeProperties();
      
      // Stores the points to draw on
      ArrayList<Integer> xPoints = new ArrayList<Integer>();
      ArrayList<Integer> yPoints = new ArrayList<Integer>();

      currentStroke.getPointsForStroke(xPoints, yPoints);

      // Properties
      // int length = 10; //lastStrokeProperties.getStrokeLength();
      // int width = 5; //lastStrokeProperties.getStrokeWidth();
      // int angle = 45; lastStrokeProperties.getStrokeAngle();

      // Calculate the points
      // xPoints.add(x + (int)(length / 2 * Math.cos(Math.toRadians(angle))));
      // yPoints.add(y + (int)(length / 2 * Math.sin(Math.toRadians(angle))));
      // xPoints.add(x - (int)(length / 2 * Math.cos(Math.toRadians(angle))));
      // yPoints.add(y - (int)(length / 2 * Math.sin(Math.toRadians(angle))));

      //System.out.println();

      Integer rgb = sourceImage.getRGB(x, y);
      if (rgb != null) {
        for(int i = 0; i < xPoints.size() && i < yPoints.size(); ++i){
          destinationImage.setRGB(xPoints.get(i), yPoints.get(i), Integer.valueOf(rgb));
        }
      }
    }
  }

  ////////////////////
  // Getters Setters
  ////////////////////

  public MyImage getSourceImage() {
    return this.sourceImage;
  }

  public MyImage getDestinationImage() {
    return this.destinationImage;
  }
}