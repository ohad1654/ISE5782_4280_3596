package unittests.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.Camera;
import renderer.ImageWriter;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void writeToImage() {
        ImageWriter writer=new ImageWriter("Test",800,500);
        for (int x = 0; x < writer.getNx(); x++) {
            for (int y = 0; y < writer.getNy(); y++) {
                if (x%(800/16)==0||y%(500/10)==0)
                    writer.writePixel(x,y,new Color(0,0,255));
                else
                    writer.writePixel(x,y,new Color(255,0,0));
            }
        }
        writer.writeToImage();
    }
}