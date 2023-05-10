// Use Java to do a, screenshot function, use the shortcut key ctrl alt a, select the screen, and then screenshot to generate pictures, save to D disk.
 Use a text editor to analyze the data.
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ScreenShots {
    public static void main(String[] args) throws IOException {
        BufferedImage image = new Robot().createScreenCapture(new int[]{
            0, 0, 800, 600
        });
        ImageIO.write(image, "PNG", new File("screen.png"));
    }
}