package test.cosc202.andie;
import cosc202.andie.*;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.util.Locale;


/**
 * @author Janadhi Dissanayake
 * // GPT4 was used to generate 5 test cases 
 */

public class LanguageActionsTest {
    private LanguageActions languageActions;
    private JFrame testFrame;

    @BeforeEach
    void setUp() {
        testFrame = new JFrame();
        languageActions = new LanguageActions(testFrame);
    }
    // A test to see if the menu is created 
    // @Test
    // void testCreateMenu() {
    // JMenu languageMenu = languageActions.createMenu();
    // assertNotNull(languageMenu, "Language menu should not be null");
    // assertEquals(5, languageMenu.getItemCount(), "Language menu should have 5 items");
    // }

    // /* Tests if the languages are changed  */
    // @Test
    // void testChangeLanguage() {
    //     JLabel testLabel = new JLabel("Language");
    //     testLabel.putClientProperty("originalKey", "Language");
    //     testFrame.getContentPane().add(testLabel);

    //     languageActions.changeLanguage(Locale.FRENCH);
    //     assertEquals("Langue", testLabel.getText(), "Label text should be translated to French");

    //     languageActions.changeLanguage(Locale.ENGLISH);
    //     assertEquals("Language", testLabel.getText(), "Label text should be translated back to English");
    // }

    /* Tests that the text on the UI are updated to the new translation  */
    // @Test
    // void testUpdateComponentTexts() {
    //     JLabel testLabel = new JLabel("Language");
    //     testLabel.putClientProperty("originalKey", "Language");
    //     testFrame.getContentPane().add(testLabel);

    //     languageActions.updateComponentTexts(testFrame.getRootPane(), Locale.FRENCH);
    //     assertEquals("Langue", testLabel.getText(), "Label text should be translated to French");

    //     languageActions.updateComponentTexts(testFrame.getRootPane(), Locale.ENGLISH);
    //     assertEquals("Language", testLabel.getText(), "Label text should be translated back to English");
    // }
    // @Test
    // /* Tests if there are missing resources for the translated texts  */
    // void testGetLocalizedText() {
    //     String englishText = languageActions.getLocalizedText("Language", Locale.ENGLISH);
    //     assertEquals("Language", englishText, "English text should be 'Language'");

    //     String defaultText = languageActions.getLocalizedText("NonExistentKey", Locale.ENGLISH);
    //     assertEquals("Missing resource", defaultText, "Default text should be 'Missing resource'");
    // }



}
