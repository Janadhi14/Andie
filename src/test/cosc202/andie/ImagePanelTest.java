package test.cosc202.andie;
import cosc202.andie.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImagePanelTest {
    
    @Test
    void initialDummyTest(){
    }

    @Test 
    //should be true because default zoom is 100
    void getZoomInitialValue(){
        ImagePanel test = new ImagePanel();
        Assertions.assertEquals(100.0,test.getZoom());
    }
}
