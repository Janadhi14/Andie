package cosc202.andie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * <p>
 * GitHub CoPilot was used to generate some of this  code.
 * NetBeans was used to generate setter and getter methods
 * 
 * This class creates a custom colour chooser panel that allows the user to choose a colour
 * from a colour chooser panel and preview it on a preview panel.
 * </p>
 * 
 * @author Dyrel Lumiwes
 * version 1.0
 */
   
public class ColourOptionBox implements ChangeListener {
    public JDialog dialog;
    public JColorChooser chooser;
    public JPanel previewPanel;
    public JButton okBtn;
    public JButton cancelBtn;

    public JLabel previewText;
    public JPanel panel;
    public JPanel buttonPanel;

    public JPanel textPanel;
    public JPanel blockingPanel;
    public JPanel previewPanels;

    public ArrayList<Color> colourArray;

    /**
     * Constructor for CustomColourPanel that also instantiates a colour chooser panel, 
     * depending on colorType and whether it creates an add button or not.

     * @param colorType - choose what type of colour chooser panel to create
     * @param multiple - choose whether to create an add button or not
     * @param modal  - choose whether the dialog is modal or not
     * 
     */
    public ColourOptionBox(String colorType, boolean multiple, boolean modal) {

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        colourArray = new ArrayList<Color>();
        chooser = new JColorChooser();
        Listener l = new Listener(buttons, chooser);
        chooser.getSelectionModel().addChangeListener(this);


        dialog = new JDialog(Andie.frame, "Choose a colour", modal);
        dialog.setSize(new Dimension(650, 400));
        dialog.setLayout(new FlowLayout());
        dialog.setResizable(false);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //Removes all chooser panels apart from HSV

      
        previewPanel = new JPanel();
        previewPanel.setBackground(DrawActions.colour);
        previewPanel.setPreferredSize(new Dimension(100, 100));

        textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension(100, 20));
        blockingPanel = new JPanel();
        blockingPanel.setPreferredSize(new Dimension(100, 20));


        previewPanels = new JPanel();
        previewPanels.add(previewPanel);
        previewPanels.add(blockingPanel);
        previewText = new JLabel("Colour preview");
        textPanel.add(previewText);
        textPanel.add(blockingPanel);


        okBtn = new JButton("Ok");
        okBtn.setPreferredSize(new Dimension(80, 20));
        cancelBtn = new JButton("Cancel");
        cancelBtn.setPreferredSize(new Dimension(80, 20));
     
     
     
        buttons.add(okBtn);
        buttons.add(cancelBtn);

        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(450,40));
        buttonPanel.add(okBtn);
        buttonPanel.add(cancelBtn);
      
        okBtn.addActionListener(l);
        cancelBtn.addActionListener(l);
      //  addBtn.addActionListener(l);

        //Overwrite chooser panel
        chooser.setPreviewPanel(previewPanel);
        panel.add(chooser);
        panel.add(textPanel);
        panel.add(previewPanels);
        panel.add(blockingPanel);
        panel.add(buttonPanel);

        dialog.add(panel);
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
        
        AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if (!accp.getDisplayName().equals(colorType)) {
               chooser.removeChooserPanel(accp);
            }
        }
    }
    /**
     * This method changes the background of the preview panel depending on the colour chosen
     * or the colour chooser component
     */
    public void stateChanged(ChangeEvent e) {
        try {
            previewPanel.setBackground(chooser.getColor());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    /**
     * Action listener class to handle actions on the buttons on the custom colour chooser
     */
    public class Listener implements ActionListener {
        JColorChooser chooser;
        public ArrayList<JButton> buttons = new ArrayList<JButton>();

        public Listener(ArrayList<JButton> buttons, JColorChooser chooser) {
            this.buttons = buttons;
            this.chooser = chooser;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //ok
            if (e.getSource() == this.buttons.get(0)) {
                DrawActions.colour = chooser.getColor();
                colourArray.add(chooser.getColor());
                dialog.dispose();
            }//cancel
            if (e.getSource() == this.buttons.get(1)) {
                dialog.dispose();
            }
       
        }

      
    }
}