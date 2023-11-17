package test.cosc202.andie;
import cosc202.andie.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
/**
 * Tests for the Color actions menu
 */
public class ColourActionsTest{
    /* Initial test */
    @Test
    void initialDummyTest(){

    }

    /* Check that color menu is not null */
    @Test
    void createMenuTest(){
        ColourActions caTest = new ColourActions();

        Assertions.assertNotNull(caTest.createMenu());
    }


}