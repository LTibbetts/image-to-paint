package image2paint;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import image2paint.controller.ImageController;
import image2paint.image.MyImage;
import image2paint.view.ImageLabel;

public class Application {

  /**
   * Create the GUI and show it. For thread safety, this method should be invoked
   * from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    // Create and set up the window.
    JFrame frame = new JFrame("HelloWorldSwing");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Setup layout manager
    BorderLayout borderLayout = new BorderLayout();
    frame.setLayout(borderLayout);

    // Two Image Views
    MyImage imageTest = new MyImage("test.png");
    MyImage imageTest2 = new MyImage("test2.png");
    ImageController imageController = new ImageController(imageTest, imageTest2);
    ImageLabel imageLabel = new ImageLabel(imageTest, imageController);
    ImageLabel imageLabel2 = new ImageLabel(imageTest2, imageController);

    JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        imageController.getDestinationImage().saveImage("testSave.png");
      }
    });

    controlPanel.add(saveButton);

    frame.add(imageLabel, BorderLayout.WEST);
    frame.add(imageLabel2, BorderLayout.CENTER);
    frame.add(controlPanel, BorderLayout.EAST);

    // frame.getContentPane().add(label);

    // Display the window.
    frame.pack();
    frame.setVisible(true);

  }

  public static void main(String[] args) {
    createAndShowGUI();
  }
}