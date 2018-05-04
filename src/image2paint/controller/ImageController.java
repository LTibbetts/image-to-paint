package image2paint.controller;

import java.util.ArrayList;

import image2paint.alterations.Stroke;
import image2paint.image.MyImage;

/**
 * Used to link multiple aspects of the UI controls together.
 */
public class ImageController {
  /** Source MyImage */
  private MyImage sourceImage;

  /** Destination MyImage */
  private MyImage destinationImage;

  /** Current Stroke for the painting */
  private Stroke currentStroke;

  /** ArrayList for stored changes */
  private ArrayList<Stroke> strokeList;

  /**
   * Constructor. Links the left and right images and the stroke controls to a
   * central object
   */
  public ImageController(MyImage sourceImage, MyImage destinationImage, Stroke currentStroke) {
    this.sourceImage = sourceImage;
    this.destinationImage = destinationImage;
    this.currentStroke = currentStroke;
    this.strokeList = new ArrayList<Stroke>();
  }

  ///////////////////
  // Public Methods
  ///////////////////

  /**
   * Adds a stroke of type currentStroke to the given x and y coordinates.
   */
  public void addStroke(int x, int y) {
    currentStroke.setX(x);
    currentStroke.setY(y);
    strokeList.add(currentStroke);
    updateDestination();
  }

  ////////////////////
  // Private Methods
  ////////////////////

  /**
   * Reflects the requested changes to the destination picture.
   */
  private void updateDestination() {
    if (!strokeList.isEmpty()) {
      int x = currentStroke.getX();
      int y = currentStroke.getY();

      // Stores the points to draw on
      ArrayList<Integer> xPoints = new ArrayList<Integer>();
      ArrayList<Integer> yPoints = new ArrayList<Integer>();

      // Populates the passed arraylist with point values.
      currentStroke.getPointsForStroke(xPoints, yPoints);

      // Get the color of the currently selected pixel.
      Integer rgb = sourceImage.getRGB(x, y);

      // If it is valid
      if (rgb != null) {

        // Draw the points that were calculated.
        for (int i = 0; i < xPoints.size() && i < yPoints.size(); ++i) {
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