package image2paint;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import image2paint.alterations.Stroke;
import image2paint.alterations.StrokeProperties;
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

    Stroke currentStroke = new Stroke(0, 0);

    // Two Image Views
    MyImage imageTest = new MyImage("test.png");
    MyImage imageTest2 = new MyImage("test2.png");
    ImageController imageController = new ImageController(imageTest, imageTest2, currentStroke);
    ImageLabel imageLabel = new ImageLabel(imageTest, imageController);
    ImageLabel imageLabel2 = new ImageLabel(imageTest2, imageController);


    /** Begin JPanel for Controls */
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new GridLayout(4, 1));

    // Stroke Length
    JPanel strokeLengthPanel = new JPanel();
    strokeLengthPanel.setLayout(new BoxLayout(strokeLengthPanel, BoxLayout.Y_AXIS));

    JLabel strokeLengthSliderLabel = new JLabel("Stroke Length (px)");
    strokeLengthSliderLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    JSlider strokeLengthSlider = new JSlider(0, (int)(Math.sqrt(Math.pow(imageTest.getWidth(), 2) + Math.pow(imageTest.getHeight(), 2))));
    strokeLengthSlider.addChangeListener(new ChangeListener(){
    
      @Override
      public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()){
          int value = (int)source.getValue();
          StrokeProperties strokeProperties = currentStroke.getStrokeProperties();
          strokeProperties.setStrokeLength(value);
        }
      }
    });
    strokeLengthSlider.setValue(10);
    strokeLengthSlider.setMajorTickSpacing((int)(Math.sqrt(Math.pow(imageTest.getWidth(), 2) + Math.pow(imageTest.getHeight(), 2))) / 2);
    strokeLengthSlider.setMinorTickSpacing((int)(Math.sqrt(Math.pow(imageTest.getWidth(), 2) + Math.pow(imageTest.getHeight(), 2))) / 4);
    strokeLengthSlider.setPaintTicks(true);
    strokeLengthSlider.setPaintLabels(true);

    strokeLengthPanel.add(strokeLengthSliderLabel);
    strokeLengthPanel.add(strokeLengthSlider);

    // Stroke Width
    JPanel strokeWidthPanel = new JPanel();
    strokeWidthPanel.setLayout(new BoxLayout(strokeWidthPanel, BoxLayout.Y_AXIS));

    JLabel strokeWidthSliderLabel = new JLabel("Stroke Width (px)");
    strokeWidthSliderLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    JSlider strokeWidthSlider = new JSlider(0, (int)(Math.sqrt(Math.pow(imageTest.getWidth(), 2) + Math.pow(imageTest.getHeight(), 2))));
    strokeWidthSlider.addChangeListener(new ChangeListener(){
    
      @Override
      public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()){
          int value = (int)source.getValue();
          StrokeProperties strokeProperties = currentStroke.getStrokeProperties();
          strokeProperties.setStrokeWidth(value);
        }
      }
    });
    strokeWidthSlider.setValue(10);
    strokeWidthSlider.setMajorTickSpacing((int)(Math.sqrt(Math.pow(imageTest.getWidth(), 2) + Math.pow(imageTest.getHeight(), 2))) / 2);
    strokeWidthSlider.setMinorTickSpacing((int)(Math.sqrt(Math.pow(imageTest.getWidth(), 2) + Math.pow(imageTest.getHeight(), 2))) / 4);
    strokeWidthSlider.setPaintTicks(true);
    strokeWidthSlider.setPaintLabels(true);
    
    strokeWidthPanel.add(strokeWidthSliderLabel);
    strokeWidthPanel.add(strokeWidthSlider);
    
    // Stroke Angle
    JPanel strokeAnglePanel = new JPanel();
    strokeAnglePanel.setLayout(new BoxLayout(strokeAnglePanel, BoxLayout.Y_AXIS));

    JLabel strokeAngleSliderLabel = new JLabel("Stroke Angle (deg)");
    strokeAngleSliderLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    JSlider strokeAngleSlider = new JSlider(0, 180);
    strokeAngleSlider.addChangeListener(new ChangeListener(){
    
      @Override
      public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()){
          int value = (int)source.getValue();
          StrokeProperties strokeProperties = currentStroke.getStrokeProperties();
          strokeProperties.setStrokeAngle(value);
        }
      }
    });
    strokeAngleSlider.setValue(15);
    strokeAngleSlider.setMajorTickSpacing(180 / 2);
    strokeAngleSlider.setMinorTickSpacing(180 / 4);
    strokeAngleSlider.setPaintTicks(true);
    strokeAngleSlider.setPaintLabels(true);

    strokeAnglePanel.add(strokeAngleSliderLabel);
    strokeAnglePanel.add(strokeAngleSlider);


    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        imageController.getDestinationImage().saveImage("testSave.png");
      }
    });

    controlPanel.add(strokeLengthPanel);
    controlPanel.add(strokeWidthPanel);
    controlPanel.add(strokeAnglePanel);
    controlPanel.add(saveButton);
    /** End JPanel for Controls */

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