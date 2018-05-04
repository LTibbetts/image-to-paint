/**
 * Class for reading in an image and storing the image.
 * It has methods to add convienience functions to the default
 * Image implementation.
 * 
 * This was adapted from https://www.dyclassroom.com/image-processing-project/how-to-read-and-write-image-file-in-java
 */

package image2paint.image;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.imageio.ImageIO;

public class MyImage {
  private int width;
  private int height;
  BufferedImage image;
  File file;

  /** Main constructor */
  public MyImage(String filePath) throws IOException {
    // read image
    try {
      file = new File(filePath); // image file path
      // image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      image = ImageIO.read(file);
      System.out.println("Reading complete.");
    } catch (IOException e) {
      System.out.println("Failed to load the image at: " + filePath);
      System.out.println("Error: " + e);
      return;
    }

    width = image.getWidth();
    height = image.getHeight();
  }

  /** Copy Constructor */
  public MyImage(MyImage myImage) {
    this.width = myImage.width;
    this.height = myImage.height;
    this.image = bufferedImageDeepCopy(myImage.image);
    this.file = myImage.file.getAbsoluteFile();
  }


  ///////////////////////////
  // private static methods
  ///////////////////////////

  /** Creates a deepCopy of a Buffered Image */
  private static BufferedImage bufferedImageDeepCopy(BufferedImage bi) {
    ColorModel cm = bi.getColorModel();
    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
    WritableRaster raster = bi.copyData(null);
    return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
  }
}

// // write image
// try {
// file = new File("D:\\Image\\Output.jpg"); // output file path
// ImageIO.write(image, "jpg", file);
// System.out.println("Writing complete.");
// } catch (IOException e) {
// System.out.println("Error: " + e);
// }