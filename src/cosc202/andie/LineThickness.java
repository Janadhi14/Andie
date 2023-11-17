package cosc202.andie;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

// ChatGPT was used for JavaDoc

/**
 * <p>
 * Class to change the thickness of the brush.
 * </p>
 * 
 * <p>
 * LineThickness is a class that creates a dialog box to select the thickness of the brush.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Dyrel
 * @version 1.0
 */
public class LineThickness {
    /**
     * Width of the brush.
     */
    public static int thickness;

    /**
     * Creates a LineThickness object, which displays a dialog box to select the brush thickness.
     */
    public LineThickness() {
        // Create a slider for selecting the thickness
        JSlider thicknessSlider = new JSlider(JSlider.HORIZONTAL, 1, 21, 2);
        thicknessSlider.setMajorTickSpacing(4);
        thicknessSlider.setMinorTickSpacing(1);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);

        // Create a preview panel to display the brush thickness
        JPanel previewPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(DrawActions.colour);
                int x = getWidth() / 2;
                int y = getHeight() / 2;
                int halfThickness = thickness / 2;
                g.fillRect(x - halfThickness, y - halfThickness, thickness, thickness);
            }
        };
        previewPanel.setPreferredSize(new Dimension(100, 100));

        // Create a change listener for the thickness slider
        ChangeListener sliderChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                thickness = thicknessSlider.getValue();
                previewPanel.repaint();
            }
        };

        // Add the change listener to the thickness slider
        thicknessSlider.addChangeListener(sliderChangeListener);

        // Create the dialog panel and add components
        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.add(previewPanel, BorderLayout.NORTH);
        dialogPanel.add(thicknessSlider, BorderLayout.CENTER);

        // Show the dialog box and capture the user's selection
        int option = JOptionPane.showOptionDialog(null, dialogPanel, "Select brush thickness",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        // Check the return value from the dialog box.
        if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
            // Close the JSlider if the user cancels or closes the dialog box
            thicknessSlider.getParent().setVisible(false);
            return;
        } else if (option == JOptionPane.OK_OPTION) {
            thickness = thicknessSlider.getValue();
        }
    }

    /**
     * Accessor for the brush thickness.
     *
     * @return the brush thickness (width of the brush)
     */
    public static int getThickness() {
        return thickness;
    }
}
