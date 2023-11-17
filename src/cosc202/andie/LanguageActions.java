package cosc202.andie;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;





/**
 * A class that manages the language of the UI compoenents in a JFrame. 
 * The LanguageActions class enables multilanguage capabilities to the Andie User-Interface.
 * 
 * @author Raaid Taha
 * @author Janadhi Dissanayake
 * @author ChatGPT to generate some method implementations and error handling
 * @version 1.0
 */

public class LanguageActions {

    private final Map<String, Locale> languages = new LinkedHashMap<>();
    private final JFrame frame;
    /**
     * Constructs a LanguageActions object with reference to a JFrame.
     * Initializes the supported languages.
     *
     * @param frame The JFrame containing the UI components.
     */

    public LanguageActions(JFrame frame) {
        this.frame = frame; 
       
        languages.put("English", Locale.ENGLISH);
        languages.put("French", Locale.FRENCH);
        languages.put("Chinese", Locale.CHINESE);
        languages.put("Italian", Locale.ITALIAN);
        languages.put("German", Locale.GERMAN);
    }
    /**
     * Creates a JMenu for language selection.
     *
     * @return JMenu containing the supported language options.
     */

    public JMenu createMenu() {
        JMenu languageMenu = new JMenu(getLocalizedText("Language"));
        int menuItemIndex = 1; // Starting index for the accelerator

        for (Map.Entry<String, Locale> entry : languages.entrySet()) {
            JMenuItem languageMenuItem = new JMenuItem(entry.getKey());
            languageMenuItem.addActionListener(new LanguageActionListener(entry.getValue()));
           //languageMenu.add(languageMenuItem);

            languageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0 + menuItemIndex, InputEvent.CTRL_DOWN_MASK));

            languageMenu.add(languageMenuItem);
            menuItemIndex++; // Increment index for the next item            

        }

        return languageMenu;
    }

    /**
     * A nested class that implements ActionListner interface.
     * Used to handle language change events.
     */
    private class LanguageActionListener implements ActionListener {
        private final Locale locale;
        /**
         * Constructs a LanguageActionistner with a specified Loacle.
         * 
         * @parm locale the Locale to be set as the language change event is activated.
         */
        public LanguageActionListener(Locale locale) {
            this.locale = locale;
        }
        /**
         * triggered when an item in the language menu is clicked 
         * Changes the language in the application based on the associated locale in
         * the LanguageActions constructor.
         * 
         * @param e Is the ActionEvent object containing details about the event. 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            changeLanguage(locale);
        }
    }
    /**
     * Changes the application's language based on teh specified Locale.
     * Updates all UI components' text and repaints the Jframe.
     * 
     * @param locale the Locale object representing the new language.
     */
    public void changeLanguage(Locale locale) {
        Locale.setDefault(locale);
        updateComponentTexts(frame.getRootPane(), locale);
        frame.repaint();
    }

    /**
     * Recursively updates the text of UI components based on the specified Locale.
     * Handles various UI component types, JMenu, JLabel, JMenuItem and JButton.
     *
     * @param component The root component to start updating from.
     * @param locale The Locale object representing the new language.
     */

    public void updateComponentTexts(Component component, Locale locale) {
        if (component instanceof JMenu) {
            JMenu menu = (JMenu) component;
            if (menu.getClientProperty("originalKey") == null) {
                menu.putClientProperty("originalKey", menu.getText());
            }
            menu.setText(getLocalizedText((String) menu.getClientProperty("originalKey"), locale));
    
            for (Component child : menu.getMenuComponents()) {
                updateComponentTexts(child, locale);
            }
        } else if (component instanceof JMenuItem) {
            JMenuItem menuItem = (JMenuItem) component;
            if (menuItem.getClientProperty("originalKey") == null) {
                menuItem.putClientProperty("originalKey", menuItem.getText());
            }
            menuItem.setText(getLocalizedText((String) menuItem.getClientProperty("originalKey"), locale));
        } else if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (label.getClientProperty("originalKey") == null) {
                label.putClientProperty("originalKey", label.getText());
            }
            label.setText(getLocalizedText((String) label.getClientProperty("originalKey"), locale));
        } else if (component instanceof JButton) {
            JButton button = (JButton) component;
            if (button.getClientProperty("originalKey") == null) {
                button.putClientProperty("originalKey", button.getText());
            }
            button.setText(getLocalizedText((String) button.getClientProperty("originalKey"), locale));
        } else if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                updateComponentTexts(child, locale);
            }
        }
        else if (component instanceof JOptionPane) {
            JOptionPane optionPane = (JOptionPane) component;
            if (optionPane.getClientProperty("originalKey") == null) {
                optionPane.putClientProperty("originalKey", optionPane.getMessage());
            }
            optionPane.setMessage(getLocalizedText((String) optionPane.getClientProperty("originalKey"), locale));
        
            for (Component child : ((Container) optionPane).getComponents()) {
                updateComponentTexts(child, locale);
            }
        
    }
    }
    
    /**
     * Retrieves the localized text for the paticular key based on specified Locale.
     * The method uses a ResourceBundle to look up the appropriate translation for the text.
     * If the key is not found, it returns a default text and logs an error message.
     *
     * @param key The key is associated with the required text within the ResourceBundle.
     * @param locale The Locale object which represents the required language.
     * @return The localized text for the specific key and Locale or default text if the key is not found.
     */
    public String getLocalizedText(String key, Locale locale) {

        if (key == null) {
            //System.err.println("Null key provided for localization.");
            return "";
        }
        ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.Language", locale);

        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            System.err.println("Missing resource for key: " + key);
            return "Missing resource";
        }
    }

    /**
     * Retrieves the localized text for the paticular key based on specified Locale.
     * The method uses a ResourceBundle to look up the appropriate translation for the text .
     * If the key is not found, it returns a default text and logs an error message.
     *
     * @param key The key is associated with the required text within the ResourceBundle.
     * @return The localized text for the specific key and Locale or default text if the key is not found.
     */
    private String getLocalizedText(String key) {
        return getLocalizedText(key, Locale.getDefault());
    }

}