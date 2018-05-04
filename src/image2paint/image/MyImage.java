/**
 * Class for reading in an image and storing the image.
 * It has methods to add convienience functions to the default
 * Image implementation.
 * 
 * This was adapted from https://www.dyclassroom.com/image-processing-project/how-to-read-and-write-image-file-in-java
 */

package image2paint.image;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MyImage {
  private int width;
  private int height;
  BufferedImage image;

  /** Main constructor */
  public MyImage(String filePath) {

    // read image
    try {
      InputStream imageIn = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
      // file = new File(fileURL.toURI()); // image file path
      // image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      image = ImageIO.read(imageIn);
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
  }

  /** Copy Constructor */
  public MyImage(MyImage myImage, boolean reset) {
    this.width = myImage.width;
    this.height = myImage.height;
    this.image = bufferedImageDeepCopy(myImage.image);
    if(reset){
      for(int i = 0 ; i < width; i++){
        for(int j = 0; j < height; j++){
          setRGB(i, j, 0);
        }
      }
    }
  }

  ///////////////////
  // Public Methods
  ///////////////////

  public void saveImage(String filename) {

    // write image
    try {
      File file = new File(filename); // output file path
      System.out.println("Saved file to: " + file.getAbsolutePath());
      ImageIO.write(image, "png", file);
      System.out.println("Writing complete.");
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
  }

  ////////////////////
  // Getters Setters
  ////////////////////
  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public Integer getRGB(int x, int y) {
    if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
      return image.getRGB(x, y);
    }

    return null;
  }

  public void setRGB(int x, int y, int rgb) {
    if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
      image.setRGB(x, y, rgb);
    }
  }

  public ImageIcon getImageIcon() {
    return new ImageIcon(image);
  }

  ///////////////////////////
  // Private Static Methods
  ///////////////////////////

  /** Creates a deepCopy of a Buffered Image */
  private static BufferedImage bufferedImageDeepCopy(BufferedImage bi) {
    ColorModel cm = bi.getColorModel();
    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
    WritableRaster raster = bi.copyData(null);
    return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
  }
}
