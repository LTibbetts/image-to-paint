package image2paint.controller;

import java.util.ArrayList;

import image2paint.alterations.Stroke;
import image2paint.image.MyImage;

public class ImageController {
  /** Source MyImage */
  private MyImage sourceImage;

  /** Destination MyImage */
  private MyImage destinationImage;

  /** ArrayList for stored changes */
  private ArrayList<Stroke> strokeList;

  public ImageController(MyImage sourceImage, MyImage destinationImage) {
    this.sourceImage = sourceImage;
    this.destinationImage = destinationImage;
    this.strokeList = new ArrayList<Stroke>();
  }

  ///////////////////
  // Public Methods
  ///////////////////

  public void addStroke(int x, int y) {
    strokeList.add(new Stroke(x, y));
    updateDestination();
  }

  ////////////////////
  // Private Methods
  ////////////////////

  private void updateDestination() {
    if (!strokeList.isEmpty()) {
      Stroke lastStroke = strokeList.get(strokeList.size() - 1);
      int x = lastStroke.getX();
      int y = lastStroke.getY();
      Integer rgb = sourceImage.getRGB(x, y);
      if (rgb != null) {
        destinationImage.setRGB(x, y, Integer.valueOf(rgb));
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