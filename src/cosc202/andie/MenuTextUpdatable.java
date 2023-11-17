package cosc202.andie;

import java.util.ResourceBundle;

/*
 * Interface for classes that need to update their menu text when the language changes.
 */
public interface MenuTextUpdatable {
    void updateMenuText(ResourceBundle bundle); // Update the menu text
}
