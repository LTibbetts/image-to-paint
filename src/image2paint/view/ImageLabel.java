package image2paint.view;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import image2paint.controller.ImageController;
import image2paint.image.MyImage;

public class ImageLabel extends JLabel implements MouseListener, MouseMotionListener {

  /** Serializable ID */
  private static final long serialVersionUID = -2032419359768730226L;

  /** MyImage */
  private MyImage image;

  /** ImageController */
  private ImageController imageController;

  /** Flag to id image */
  private boolean isSource = false;

  /** Constructor */
  public ImageLabel(MyImage image, ImageController imageController) {
    super();

    // Set fields
    this.image = image;
    this.imageController = imageController;

    if (imageController.getSourceImage().equals(image)) {
      this.isSource = true;
    }

    // Add mouse listener
    addMouseListener(this);
    addMouseMotionListener(this);

    // Set the image icon
    setIcon(image.getImageIcon());

    // Set the max size
    setSize(new Dimension(image.getWidth(), image.getHeight()));

    // Handles resizing
    addComponentListener(new ComponentListener() {

      @Override
      public void componentShown(ComponentEvent e) {

      }

      @Override
      public void componentResized(ComponentEvent e) {
        e.getComponent().setSize(new Dimension(image.getWidth(), image.getHeight()));
        repaint();
        // e.getComponent().validate();
      }

      @Override
      public void componentMoved(ComponentEvent e) {

      }

      @Override
      public void componentHidden(ComponentEvent e) {

      }
    });
  }

  ///////////////////
  // Public Methods
  ///////////////////

  public void changeImage(MyImage image) {
    this.image = image;
    super.setIcon(image.getImageIcon());
    // super.
  }

  private void updateImage() {
    if (!isSource) {
      image = new MyImage(imageController.getDestinationImage());
      setIcon(image.getImageIcon());
    }

    this.getParent().repaint();
  }

  /////////////////////////////
  // Mouse Listener Overrides
  /////////////////////////////

  @Override
  public void mouseClicked(MouseEvent e) {
    if (imageController.getSourceImage().equals(image)) {
      imageController.addStroke(e.getX(), e.getY());
      // System.out.println("X: " + e.getX() + ", Y: " + e.getY());
    }
    updateImage();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (imageController.getSourceImage().equals(image)) {
      imageController.addStroke(e.getX(), e.getY());
      // System.out.println("X: " + e.getX() + ", Y: " + e.getY());
    }
    updateImage();
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseMoved(MouseEvent e) {

  }

}